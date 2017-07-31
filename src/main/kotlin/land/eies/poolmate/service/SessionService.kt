package land.eies.poolmate.service

import land.eies.poolmate.service.dto.SessionDTO
import java.time.LocalDate

interface SessionService {

    fun findSessions(userId: Long, startDate: LocalDate, endDate: LocalDate): List<SessionDTO>
}
