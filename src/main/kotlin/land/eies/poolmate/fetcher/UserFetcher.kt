package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.User
import land.eies.poolmate.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class UserFetcher(val userRepository: UserRepository) : DataFetcher<User?> {

    override fun get(environment: DataFetchingEnvironment?): User? {
        if (environment == null) {
            return null
        }

        if (environment.containsArgument("id")) {
            val id = environment.getArgument<String>("id")
            return userRepository.getOne(id.toLong())
        }

        return null
    }
}
