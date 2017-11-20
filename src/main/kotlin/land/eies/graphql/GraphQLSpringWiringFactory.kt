package land.eies.graphql

import graphql.schema.DataFetcher
import graphql.schema.TypeResolver
import graphql.schema.idl.FieldWiringEnvironment
import graphql.schema.idl.InterfaceWiringEnvironment
import graphql.schema.idl.UnionWiringEnvironment
import graphql.schema.idl.WiringFactory
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLTypeResolver
import org.springframework.beans.factory.ListableBeanFactory

class GraphQLSpringWiringFactory(private val listableBeanFactory: ListableBeanFactory) : WiringFactory {

    override fun providesDataFetcher(environment: FieldWiringEnvironment?): Boolean =
            resolveDataFetcher(environment!!.fieldDefinition.name, environment.parentType.name) != null

    override fun getDataFetcher(environment: FieldWiringEnvironment?): DataFetcher<*> =
            resolveDataFetcher(environment!!.fieldDefinition.name, environment.parentType.name)!!

    override fun providesTypeResolver(environment: InterfaceWiringEnvironment?): Boolean =
            resolveTypeResolver(environment!!.interfaceTypeDefinition.name) != null

    override fun getTypeResolver(environment: InterfaceWiringEnvironment?): TypeResolver =
            resolveTypeResolver(environment!!.interfaceTypeDefinition.name)!!

    override fun providesTypeResolver(environment: UnionWiringEnvironment?): Boolean =
            resolveTypeResolver(environment!!.unionTypeDefinition.name) != null

    override fun getTypeResolver(environment: UnionWiringEnvironment?): TypeResolver =
            resolveTypeResolver(environment!!.unionTypeDefinition.name)!!

    private fun resolveDataFetcher(fieldName: String, parentType: String): DataFetcher<*>? {
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

    private fun resolveTypeResolver(typeName: String): TypeResolver? {
        val candidates = listableBeanFactory.getBeansOfType(TypeResolver::class.java)
                .filter { beanEntry ->
                    listableBeanFactory.findAnnotationOnBean(beanEntry.key, GraphQLTypeResolver::class.java).let { annotation ->
                        annotation != null && annotation.typeName == typeName
                    }
                }

        if (candidates.size > 1) {
            throw IllegalStateException("${candidates.size} type resolvers defined for typeName = \"$typeName\", expected only one")
        }

        return candidates.values.firstOrNull()
    }
}
