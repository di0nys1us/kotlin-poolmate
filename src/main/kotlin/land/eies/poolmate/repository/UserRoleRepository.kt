package land.eies.poolmate.repository

import land.eies.poolmate.domain.UserRole
import land.eies.poolmate.domain.UserRolePK
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRoleRepository : PagingAndSortingRepository<UserRole, UserRolePK> {

    fun findByPkUserId(userId: Long): List<UserRole>

    fun findByPkUserEmail(userEmail: String): List<UserRole>

    fun findByPkRoleId(roleId: Long): List<UserRole>

    fun findByPkRoleName(roleName: String): List<UserRole>
}
