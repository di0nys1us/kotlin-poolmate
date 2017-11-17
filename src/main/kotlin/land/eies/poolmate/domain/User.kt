package land.eies.poolmate.domain

import java.io.Serializable
import javax.persistence.Entity

@Entity
data class User(
        var id: Long? = null,
        var version: Long? = null,
        var deleted: Boolean = false,
        var firstName: String,
        var lastName: String,
        var email: String,
        var hashedPassword: String,
        var administrator: Boolean = false
) : Serializable
