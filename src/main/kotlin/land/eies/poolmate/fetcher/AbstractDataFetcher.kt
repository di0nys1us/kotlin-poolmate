package land.eies.poolmate.fetcher

import graphql.GraphQLException
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment

abstract class AbstractDataFetcher<T> : DataFetcher<T> {

    override fun get(environment: DataFetchingEnvironment?): T {
        if (environment == null) {
            throw GraphQLException("environment was null")
        }

        return internalGet(environment)
    }

    abstract fun internalGet(environment: DataFetchingEnvironment): T
}
