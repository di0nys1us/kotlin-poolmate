package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLFieldBinding
import land.eies.poolmate.domain.User
import land.eies.poolmate.repository.UserRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "user", parentType = "Query")
))
@Transactional
class UserFetcher(val userRepository: UserRepository) : DataFetcher<User> {

    override fun get(environment: DataFetchingEnvironment?): User? {
        if (environment == null) {
            missingEnvironment()
        }

        if (environment.containsId()) {
            return userRepository.findById(environment.getId()).orElse(null)
        }

        if (environment.containsEmail()) {
            return userRepository.findByEmail(environment.getEmail())
        }

        return null
    }
}

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "users", parentType = "Query")
))
@Transactional
class UsersFetcher(private val userRepository: UserRepository) : DataFetcher<List<User>> {

    override fun get(environment: DataFetchingEnvironment?): List<User> {
        if (environment == null) {
            missingEnvironment()
        }

        return userRepository.findAll()
    }
}
