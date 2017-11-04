package land.eies.poolmate.fetcher

import graphql.GraphQLException
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.Session
import land.eies.poolmate.graphql.GraphQLDataFetcher
import land.eies.poolmate.graphql.GraphQLFieldBinding
import land.eies.poolmate.repository.SessionRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "session", parentType = "Query")
))
@Transactional
class SessionFetcher(val sessionRepository: SessionRepository) : DataFetcher<Session> {

    override fun get(environment: DataFetchingEnvironment?): Session {
        if (environment == null) {
            throw GraphQLException("environment was null")
        }

        environment.getId().let {
            return sessionRepository.getOne(it)
        }
    }
}
