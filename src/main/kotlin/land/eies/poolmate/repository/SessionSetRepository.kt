package land.eies.poolmate.repository

import land.eies.poolmate.domain.SessionSet
import org.springframework.data.jpa.repository.JpaRepository

interface SessionSetRepository : JpaRepository<SessionSet, Long>
