package land.eies.poolmate.domain

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity

@Entity
data class Session(
        var id: Long?,
        var date: LocalDate,
        var poolLength: Int,
        var calories: Int,
        var deleted: Boolean = false,
        var version: Long?,
        var user: User,
        var changeInformation: ChangeInformation
) : Serializable
