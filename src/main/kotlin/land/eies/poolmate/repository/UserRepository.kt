package land.eies.poolmate.repository

import land.eies.graphql.annotation.GraphQLRepository
import land.eies.poolmate.domain.User
import org.springframework.data.jpa.repository.JpaRepository

@GraphQLRepository
interface UserRepository : JpaRepository<User, Long>
