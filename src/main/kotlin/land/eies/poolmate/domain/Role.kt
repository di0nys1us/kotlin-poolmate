package land.eies.poolmate.domain

import java.io.Serializable
import javax.persistence.Entity

@Entity
data class Role(
        var id: Long?,
        var changeInformation: ChangeInformation,
        var name: String
) : Serializable
