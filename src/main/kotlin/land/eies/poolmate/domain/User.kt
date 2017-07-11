package land.eies.poolmate.domain

import java.io.Serializable
import javax.persistence.Entity

@Entity
data class User(
        var id: Long?,
        var firstName: String,
        var lastName: String,
        var email: String,
        var hashedPassword: String,
        var accountExpired: Boolean = false,
        var accountLocked: Boolean = false,
        var credentialsExpired: Boolean = false,
        var enabled: Boolean = true,
        var deleted: Boolean = false,
        var version: Long?,
        var changeInformation: ChangeInformation
) : Serializable
