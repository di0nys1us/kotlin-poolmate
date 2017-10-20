package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.Session
import land.eies.poolmate.repository.SessionRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SessionFetcher(val sessionRepository: SessionRepository) : DataFetcher<Session?> {

    override fun get(environment: DataFetchingEnvironment?): Session? {
        if (environment == null) {
            return null
        }

        if (environment.containsArgument("id")) {
            val id = environment.getArgument<String>("id")
            return sessionRepository.getOne(id.toLong())
        }

        return null
    }
}