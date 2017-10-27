package land.eies.poolmate.configuration

import graphql.schema.DataFetcher
import graphql.schema.idl.FieldWiringEnvironment
import graphql.schema.idl.WiringFactory
import land.eies.poolmate.graphql.GraphQLDataFetcher
import land.eies.poolmate.graphql.GraphQLDataFetcherWiring
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.stereotype.Component

@Component
class GraphQLSpringWiringFactory(val listableBeanFactory: ListableBeanFactory) : WiringFactory {

    fun resolveDataFetcher(fieldName: String, parentType: String): DataFetcher<*>? {
        val candidates = listableBeanFactory.getBeansOfType(DataFetcher::class.java)
                .filter { it.key != null && it.value != null }
                .filter {
                    listableBeanFactory.findAnnotationOnBean(it.key, GraphQLDataFetcherWiring::class.java).let {
                        it != null && it.fieldName == fieldName && it.parentType == parentType
                    } || listableBeanFactory.findAnnotationOnBean(it.key, GraphQLDataFetcher::class.java).let {
                        it != null && it.value.any {
                            it.fieldName == fieldName && it.parentType == parentType
                        }
                    }
                }
                .map { it.value }

        if (candidates.size > 1) {
            throw IllegalStateException("${candidates.size} data fetchers defined for fieldName = \"$fieldName\" and parentType = \"$parentType\", expected only one")
        }

        return candidates.firstOrNull()
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
