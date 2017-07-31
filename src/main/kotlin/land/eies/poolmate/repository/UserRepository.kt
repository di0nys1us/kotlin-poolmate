package land.eies.poolmate.repository

import land.eies.poolmate.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
