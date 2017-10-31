package land.eies.poolmate.mutation

import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.domain.User
import land.eies.poolmate.graphql.GraphQLDataFetcher
import land.eies.poolmate.graphql.GraphQLFieldBinding
import land.eies.poolmate.repository.UserRepository
import org.springframework.transaction.annotation.Transactional

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "createUser", parentType = "Mutation")
))
@Transactional
class CreateUserMutation(
        val objectMapper: ObjectMapper,
        val userRepository: UserRepository
) : DataFetcher<User?> {

    override fun get(environment: DataFetchingEnvironment?): User? {
        if (environment == null) {
            return null
        }

        if (environment.containsArgument("input")) {
            val inputMap = environment.getArgument<Map<String, String>>("input").toMutableMap()
            inputMap["hashedPassword"] = inputMap["password"]!!
            val user = objectMapper.convertValue(inputMap, User::class.java)
            return userRepository.save(user)
        }

        return null
    }
}
