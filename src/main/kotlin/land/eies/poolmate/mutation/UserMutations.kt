package land.eies.poolmate.mutation

import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLFieldBinding
import land.eies.graphql.validation.ValidationException
import land.eies.poolmate.domain.User
import land.eies.poolmate.repository.UserRepository
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable
import javax.validation.Validator
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateUserInput(
        @get:NotBlank
        val firstName: String,
        @get:NotBlank
        val lastName: String,
        @get:NotBlank @get:Email
        val email: String,
        @get:NotBlank
        val password: String,
        @get:NotNull
        val administrator: Boolean
) : Serializable

data class CreateUserOutput(
        val user: User?
) : Serializable

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "createUser", parentType = "Mutation")
))
@Transactional
class CreateUserMutation(
        private val objectMapper: ObjectMapper,
        private val validator: Validator,
        private val userRepository: UserRepository
) : DataFetcher<CreateUserOutput> {

    override fun get(environment: DataFetchingEnvironment?): CreateUserOutput {
        if (environment == null) {
            return CreateUserOutput(null)
        }

        if (environment.containsArgument("input")) {
            val input = objectMapper.convertValue<CreateUserInput>(
                    environment.getArgument("input"),
                    CreateUserInput::class.java
            )

            val violations = validator.validate<Any?>(input)

            if (violations.isNotEmpty()) {
                throw ValidationException(violations)
            }

            val user = User(
                    firstName = input.firstName,
                    lastName = input.lastName,
                    email = input.email,
                    hashedPassword = input.password, // TODO Password hashing
                    administrator = input.administrator
            )

            return CreateUserOutput(userRepository.save(user))
        }

        return CreateUserOutput(null)
    }
}
