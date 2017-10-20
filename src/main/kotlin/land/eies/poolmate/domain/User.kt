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
        var administrator: Boolean = false,
        var deleted: Boolean = false,
        var version: Long?
) : Serializable
