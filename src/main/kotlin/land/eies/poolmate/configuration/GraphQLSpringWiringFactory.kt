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

    private fun findB(candidate: Map.Entry<String, DataFetcher<*>>, fieldName: String, parentType: String): Boolean {
        return listableBeanFactory.findAnnotationOnBean(candidate.key, GraphQLDataFetcher::class.java).let { annotation ->
            annotation != null && annotation.value.any { wiring ->
                wiring.fieldName == fieldName && wiring.parentType == parentType
            }
        }
    }

    private fun findA(candidate: Map.Entry<String, DataFetcher<*>>, fieldName: String, parentType: String): Boolean {
        return listableBeanFactory.findAnnotationOnBean(candidate.key, GraphQLDataFetcherWiring::class.java).let { annotation ->
            annotation != null && annotation.fieldName == fieldName && annotation.parentType == parentType
        }
    }

    fun resolveDataFetcher(fieldName: String, parentType: String): DataFetcher<*>? {
        val candidates = listableBeanFactory.getBeansOfType(DataFetcher::class.java)
                .filter { !(it.key == null || it.value == null) }
                .filter { findA(it, fieldName, parentType) || findB(it, fieldName, parentType) }

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
