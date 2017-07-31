package land.eies.poolmate.service.dto

import java.io.Serializable

data class UserDTO(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val email: String,
        val roles: List<RoleDTO>
) : Serializable
