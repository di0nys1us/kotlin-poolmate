package land.eies.poolmate.fetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.User
import land.eies.poolmate.graphql.GraphQLDataFetcher
import land.eies.poolmate.graphql.GraphQLFieldBinding
import land.eies.poolmate.repository.UserRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "users", parentType = "Query")
))
@Transactional
class UsersFetcher(val userRepository: UserRepository) : DataFetcher<List<User>> {

    override fun get(environment: DataFetchingEnvironment?): List<User> {
        if (environment == null) {
            return emptyList()
        }

        return userRepository.findAll()
    }
}
