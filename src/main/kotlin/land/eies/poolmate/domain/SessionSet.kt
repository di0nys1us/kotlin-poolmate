package land.eies.poolmate.domain

import java.io.Serializable
import java.time.Duration
import javax.persistence.Entity

@Entity
data class SessionSet(
        var id: Long?,
        var version: Long?,
        var deleted: Boolean = false,
        var number: Int,
        var swimmingTime: Duration,
        var restTime: Duration,
        var laps: Int,
        var averageStrokes: Int,
        var speed: Int,
        var efficiencyIndex: Int,
        var session: Session
) : Serializable
