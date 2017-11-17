package land.eies.poolmate.domain

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity

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
