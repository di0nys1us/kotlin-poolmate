package land.eies.poolmate.service

import land.eies.poolmate.service.dto.UserDTO
import java.util.*

interface UserService {

    fun findUser(userId: Long): Optional<UserDTO>
}
