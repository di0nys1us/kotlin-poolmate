package land.eies.poolmate.domain

import java.io.Serializable
import java.time.Duration
import java.time.LocalDate
import javax.persistence.Entity
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class User(
        var id: Long? = null,
        var version: Long? = null,
        var deleted: Boolean = false,
        @get:NotBlank
        var firstName: String,
        @get:NotBlank
        var lastName: String,
        @get:NotBlank
        @get:Email
        var email: String,
        var hashedPassword: String,
        var administrator: Boolean = false
) : Serializable

@Entity
data class Session(
        var id: Long? = null,
        var version: Long? = null,
        var deleted: Boolean = false,
        var date: LocalDate,
        var poolLength: Int,
        var calories: Int,
        var user: User
) : Serializable

@Entity
data class SessionSet(
        var id: Long? = null,
        var version: Long? = null,
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
