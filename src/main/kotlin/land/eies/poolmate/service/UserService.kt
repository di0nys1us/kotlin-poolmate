package land.eies.poolmate.service

import land.eies.poolmate.repository.UserRepository
import land.eies.poolmate.repository.UserRoleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
        val userRepository: UserRepository,
        val userRoleRepository: UserRoleRepository
) {

    fun findUser(userId: Long) = userRepository.findById(userId)

    fun findUserRolesByUserId(userId: Long) = userRoleRepository.findByPkUserId(userId)

    fun findUserRolesByRoleName(roleName: String) = userRoleRepository.findByPkRoleName(roleName)
}
