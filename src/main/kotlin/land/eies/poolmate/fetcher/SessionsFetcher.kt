package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.Session
import land.eies.poolmate.domain.User
import land.eies.poolmate.graphql.GraphQLDataFetcher
import land.eies.poolmate.graphql.GraphQLFieldBinding
import land.eies.poolmate.repository.SessionRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "sessions", parentType = "Query"),
        GraphQLFieldBinding(fieldName = "sessions", parentType = "User")
))
@Transactional
class SessionsFetcher(val sessionRepository: SessionRepository) : DataFetcher<List<Session>> {

    override fun get(environment: DataFetchingEnvironment?): List<Session> {
        if (environment == null) {
            return emptyList()
        }

        if (environment.parentType.name == "Query") {
            return sessionRepository.findAll()
        }

        if (environment.getSource<Any?>() is User) {
            val user = environment.getSource<User>()
            return sessionRepository.findByUserId(user.id!!)
        }

        return emptyList()
    }
}
