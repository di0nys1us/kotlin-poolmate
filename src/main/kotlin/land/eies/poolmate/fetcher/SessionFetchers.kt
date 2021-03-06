package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLFieldBinding
import land.eies.poolmate.domain.Session
import land.eies.poolmate.domain.User
import land.eies.poolmate.repository.SessionRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "session", parentType = "Query")
))
@Transactional
class SessionFetcher(private val sessionRepository: SessionRepository) : DataFetcher<Session> {

    override fun get(environment: DataFetchingEnvironment?): Session {
        if (environment == null) {
            missingEnvironment()
        }

        return sessionRepository.getOne(environment.getId())
    }
}

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "sessions", parentType = "Query"),
        GraphQLFieldBinding(fieldName = "sessions", parentType = "User")
))
@Transactional
class SessionsFetcher(private val sessionRepository: SessionRepository) : DataFetcher<List<Session>> {

    override fun get(environment: DataFetchingEnvironment?): List<Session> {
        if (environment == null) {
            missingEnvironment()
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