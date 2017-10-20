package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.Session
import land.eies.poolmate.domain.User
import land.eies.poolmate.repository.SessionRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SessionsFetcher(val sessionRepository: SessionRepository) : DataFetcher<List<Session>> {

    override fun get(environment: DataFetchingEnvironment?): List<Session> {
        if (environment == null) {
            return emptyList()
        }

        val user = environment.getSource<User>()

        return sessionRepository.findByUserId(user.id!!)
    }
}
