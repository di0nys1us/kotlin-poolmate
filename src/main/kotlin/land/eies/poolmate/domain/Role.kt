package land.eies.poolmate.domain

import java.io.Serializable
import javax.persistence.Entity

@Entity
data class Role(
        var id: Long?,
        var name: String,
        var deleted: Boolean = false,
        var version: Long?,
        var changeInformation: ChangeInformation
) : Serializable
