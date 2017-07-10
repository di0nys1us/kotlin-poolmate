package land.eies.poolmate.domain

import java.io.Serializable
import javax.persistence.Entity

@Entity
data class UserRole(
        var pk: UserRolePK,
        var changeInformation: ChangeInformation
) : Serializable
