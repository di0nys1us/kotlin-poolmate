package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.Session
import land.eies.poolmate.domain.SessionSet
import land.eies.poolmate.repository.SessionSetRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
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
