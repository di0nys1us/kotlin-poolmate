package land.eies.poolmate.domain

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity

@Entity
data class Session(
        var id: Long?,
        var changeInformation: ChangeInformation,
        var user: User,
        var date: LocalDate,
        var poolLength: Int,
        var calories: Int
) : Serializable
