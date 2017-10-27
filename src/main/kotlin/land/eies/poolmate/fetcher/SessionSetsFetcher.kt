package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.Session
import land.eies.poolmate.domain.SessionSet
import land.eies.poolmate.graphql.GraphQLDataFetcherWiring
import land.eies.poolmate.repository.SessionSetRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcherWiring(fieldName = "sessionSets", parentType = "Session")
@Transactional
class SessionSetsFetcher(val sessionSetRepository: SessionSetRepository) : DataFetcher<List<SessionSet>> {

    override fun get(environment: DataFetchingEnvironment?): List<SessionSet> {
        if (environment == null) {
            return emptyList()
        }

        if (environment.getSource<Any?>() is Session) {
            return sessionSetRepository.findSessionSetsBySessionId(environment.getSource<Session>().id!!)
        }

        return emptyList()
    }
}
