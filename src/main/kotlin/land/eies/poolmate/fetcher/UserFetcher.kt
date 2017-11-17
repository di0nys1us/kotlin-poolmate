package land.eies.poolmate.fetcher

import graphql.GraphQLException
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

    override fun get(environment: DataFetchingEnvironment?): User {
        if (environment == null) {
            throw GraphQLException("environment was null")
        }

        return userRepository.getOne(environment.getId())
    }
}
