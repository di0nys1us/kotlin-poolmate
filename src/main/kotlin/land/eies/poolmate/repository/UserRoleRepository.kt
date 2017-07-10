package land.eies.poolmate.repository

import land.eies.poolmate.domain.UserRole
import land.eies.poolmate.domain.UserRolePK
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRoleRepository : PagingAndSortingRepository<UserRole, UserRolePK> {

    fun findByPkUserId(userId: Long): List<UserRole>
}
