package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLFieldBinding
import land.eies.poolmate.domain.Session
import land.eies.poolmate.domain.SessionSet
import land.eies.poolmate.repository.SessionSetRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "sessionSet", parentType = "Query")
))
@Transactional
class SessionSetFetcher(private val sessionSetRepository: SessionSetRepository) : DataFetcher<SessionSet> {

    override fun get(environment: DataFetchingEnvironment?): SessionSet {
        if (environment == null) {
            missingEnvironment()
        }

        return sessionSetRepository.getOne(environment.getId())
    }
}

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "sessionSets", parentType = "Query"),
        GraphQLFieldBinding(fieldName = "sessionSets", parentType = "Session")
))
@Transactional
class SessionSetsFetcher(private val sessionSetRepository: SessionSetRepository) : DataFetcher<List<SessionSet>> {

    override fun get(environment: DataFetchingEnvironment?): List<SessionSet> {
        if (environment == null) {
            missingEnvironment()
        }

        if (environment.parentType.name == "Query") {
            return sessionSetRepository.findAll()
        }

        if (environment.getSource<Any?>() is Session) {
            val session = environment.getSource<Session>()
            return sessionSetRepository.findSessionSetsBySessionId(session.id!!)
        }

        return emptyList()
    }
}
