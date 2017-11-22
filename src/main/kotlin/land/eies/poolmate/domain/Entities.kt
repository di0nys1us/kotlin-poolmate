package land.eies.poolmate.domain

import java.io.Serializable
import java.time.Duration
import java.time.LocalDate
import javax.persistence.Entity
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

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
        @get:NotBlank
        var hashedPassword: String,
        var administrator: Boolean = false
) : Serializable

@Entity
data class Session(
        var id: Long? = null,
        var version: Long? = null,
        var deleted: Boolean = false,
        @get:NotNull
        @get:PastOrPresent
        var date: LocalDate,
        @get:NotNull
        var poolLength: Int,
        @get:NotNull
        var calories: Int,
        @get:NotNull
        var user: User
) : Serializable

@Entity
data class SessionSet(
        var id: Long? = null,
        var version: Long? = null,
        var deleted: Boolean = false,
        @get:NotNull
        var number: Int,
        @get:NotNull
        var swimmingTime: Duration,
        @get:NotNull
        var restTime: Duration,
        @get:NotNull
        var laps: Int,
        @get:NotNull
        var averageStrokes: Int,
        @get:NotNull
        var speed: Int,
        @get:NotNull
        var efficiencyIndex: Int,
        @get:NotNull
        var session: Session
) : Serializable
