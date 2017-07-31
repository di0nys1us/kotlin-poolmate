package land.eies.poolmate.resource

import land.eies.poolmate.service.SessionService
import land.eies.poolmate.service.dto.SessionDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SessionResource(
        val sessionService: SessionService
) {

    @GetMapping("/sessions")
    fun getSessions(): ResponseEntity<List<SessionDTO>> {
        return ResponseEntity.ok(emptyList())
    }
}
