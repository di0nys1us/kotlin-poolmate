package land.eies.poolmate.fetcher

import graphql.GraphQLException
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.SessionSet
import land.eies.poolmate.graphql.GraphQLDataFetcher
import land.eies.poolmate.graphql.GraphQLFieldBinding
import land.eies.poolmate.repository.SessionSetRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "sessionSet", parentType = "Query")
))
@Transactional
class SessionSetFetcher(private val sessionSetRepository: SessionSetRepository) : DataFetcher<SessionSet> {

    override fun get(environment: DataFetchingEnvironment?): SessionSet {
        if (environment == null) {
            throw GraphQLException("environment was null")
        }

        return sessionSetRepository.getOne(environment.getId())
    }
}
