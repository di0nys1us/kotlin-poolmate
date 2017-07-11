package land.eies.poolmate.repository

import land.eies.poolmate.domain.Session
import org.springframework.data.repository.PagingAndSortingRepository
import java.time.LocalDate
import java.util.*

interface SessionRepository : PagingAndSortingRepository<Session, Long> {

    fun findByDate(date: LocalDate): Optional<Session>
}
