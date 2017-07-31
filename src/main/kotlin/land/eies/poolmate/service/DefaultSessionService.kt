package land.eies.poolmate.service

import land.eies.poolmate.repository.SessionRepository
import land.eies.poolmate.service.dto.SessionDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class DefaultSessionService(
        val sessionRepository: SessionRepository
) : SessionService {

    override fun findSessions(userId: Long, startDate: LocalDate, endDate: LocalDate): List<SessionDTO> {
        val sessions = sessionRepository.findByUserIdAndDateBetween(userId, startDate, endDate)

        return sessions.map {
            SessionDTO(
                    it.id!!,
                    it.date,
                    it.poolLength,
                    it.calories
            )
        }
    }
}
