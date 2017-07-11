package land.eies.poolmate.repository

import land.eies.poolmate.domain.Role
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface RoleRepository : PagingAndSortingRepository<Role, Long> {

    fun findRoleByName(name: String): Optional<Role>
}
