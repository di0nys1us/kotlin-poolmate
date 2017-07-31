package land.eies.poolmate.repository

import land.eies.poolmate.domain.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>
