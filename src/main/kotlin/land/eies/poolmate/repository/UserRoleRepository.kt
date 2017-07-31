package land.eies.poolmate.repository

import land.eies.poolmate.domain.UserRole
import land.eies.poolmate.domain.UserRolePK
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository : JpaRepository<UserRole, UserRolePK> {

    fun findByPkUserId(userId: Long): List<UserRole>
}
