package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.Session
import land.eies.poolmate.domain.SessionSet
import land.eies.poolmate.graphql.GraphQLDataFetcher
import land.eies.poolmate.graphql.GraphQLFieldBinding
import land.eies.poolmate.repository.SessionSetRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "sessionSets", parentType = "Query"),
        GraphQLFieldBinding(fieldName = "sessionSets", parentType = "Session")
))
@Transactional
class SessionSetsFetcher(val sessionSetRepository: SessionSetRepository) : DataFetcher<List<SessionSet>> {

    override fun get(environment: DataFetchingEnvironment?): List<SessionSet> {
        if (environment == null) {
            return emptyList()
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
