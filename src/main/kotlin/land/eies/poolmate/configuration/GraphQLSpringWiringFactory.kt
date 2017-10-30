package land.eies.poolmate.configuration

import graphql.schema.DataFetcher
import graphql.schema.idl.FieldWiringEnvironment
import graphql.schema.idl.WiringFactory
import land.eies.poolmate.graphql.GraphQLDataFetcher
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.stereotype.Component

@Component
class GraphQLSpringWiringFactory(val listableBeanFactory: ListableBeanFactory) : WiringFactory {

    fun resolveDataFetcher(fieldName: String, parentType: String): DataFetcher<*>? {
        val candidates = listableBeanFactory.getBeansOfType(DataFetcher::class.java)
                .filter { beanEntry ->
                    listableBeanFactory.findAnnotationOnBean(beanEntry.key, GraphQLDataFetcher::class.java).let { annotation ->
                        annotation != null && annotation.bindings.any { binding ->
                            binding.fieldName == fieldName && binding.parentType == parentType
                        }
                    }
                }

        if (candidates.size > 1) {
            throw IllegalStateException("${candidates.size} data fetchers defined for fieldName = \"$fieldName\" and parentType = \"$parentType\", expected only one")
        }

        return candidates.values.firstOrNull()
    }

    override fun providesDataFetcher(environment: FieldWiringEnvironment?): Boolean {
        if (environment == null) {
            return super.providesDataFetcher(environment)
        }

        return resolveDataFetcher(environment.fieldDefinition.name, environment.parentType.name) != null
    }

    override fun getDataFetcher(environment: FieldWiringEnvironment?): DataFetcher<*> {
        if (environment == null) {
            return super.getDataFetcher(environment)
        }

        return resolveDataFetcher(environment.fieldDefinition.name, environment.parentType.name) ?: return super.getDataFetcher(environment)
    }
}
