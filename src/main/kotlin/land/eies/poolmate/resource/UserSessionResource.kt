package land.eies.poolmate.resource

import land.eies.poolmate.service.SessionService
import land.eies.poolmate.service.dto.SessionDTO
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/users")
class UserSessionResource(
        val sessionService: SessionService
) {

    @GetMapping("/{userId}/sessions")
    fun getUserSessions(
            @PathVariable userId: Long,
            @RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
            @RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate
    ): ResponseEntity<List<SessionDTO>> {
        return ResponseEntity.ok(sessionService.findSessions(userId, startDate, endDate))
    }
}
