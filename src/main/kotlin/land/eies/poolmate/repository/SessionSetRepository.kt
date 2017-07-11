package land.eies.poolmate.repository

import land.eies.poolmate.domain.SessionSet
import org.springframework.data.repository.PagingAndSortingRepository

interface SessionSetRepository : PagingAndSortingRepository<SessionSet, Long> {

    fun findBySessionId(sessionId: Long): List<SessionSet>
}
