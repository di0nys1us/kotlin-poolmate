package land.eies.poolmate.domain

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class UserRolePK(
        var user: User,
        var role: Role
) : Serializable
