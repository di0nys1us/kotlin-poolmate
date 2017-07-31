package land.eies.poolmate.service

import land.eies.poolmate.repository.UserRepository
import land.eies.poolmate.repository.UserRoleRepository
import land.eies.poolmate.service.dto.RoleDTO
import land.eies.poolmate.service.dto.UserDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class DefaultUserService(
        val userRepository: UserRepository,
        val userRoleRepository: UserRoleRepository
) : UserService {

    override fun findUser(userId: Long): Optional<UserDTO> {
        val roles = userRoleRepository.findByPkUserId(userId)
                .map {
                    RoleDTO(
                            it.pk.role.id!!,
                            it.pk.role.name
                    )
                }

        return userRepository.findById(userId).map {
            UserDTO(
                    it.id!!,
                    it.firstName,
                    it.lastName,
                    it.email,
                    roles
            )
        }
    }
}
