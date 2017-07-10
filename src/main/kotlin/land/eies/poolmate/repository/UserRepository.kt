package land.eies.poolmate.repository

import land.eies.poolmate.domain.User
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface UserRepository : PagingAndSortingRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>
}
