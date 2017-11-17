package land.eies.poolmate.mutation

import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLFieldBinding
import land.eies.poolmate.domain.User
import land.eies.poolmate.repository.UserRepository
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

data class CreateUserInput(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val administrator: Boolean
) : Serializable

data class CreateUserOutput(
        val user: User? = null
) : Serializable

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "createUser", parentType = "Mutation")
))
@Transactional
class CreateUserMutation(
        private val objectMapper: ObjectMapper,
        private val userRepository: UserRepository
) : DataFetcher<User?> {

    override fun get(environment: DataFetchingEnvironment?): User? {
        if (environment == null) {
            return null
        }

        if (environment.containsArgument("input")) {
            return userRepository.save(null)
        }

        return null
    }
}
