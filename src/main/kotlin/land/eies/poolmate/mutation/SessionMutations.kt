package land.eies.poolmate.mutation

import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLFieldBinding
import land.eies.poolmate.domain.Session
import land.eies.poolmate.repository.SessionRepository
import land.eies.poolmate.repository.UserRepository
import java.io.Serializable
import java.time.LocalDate

data class CreateSessionInput(
        val userId: Long,
        val date: LocalDate,
        val poolLength: Int,
        val calories: Int
) : Serializable

data class CreateSessionOutput(
        val session: Session? = null
) : Serializable

@GraphQLDataFetcher(bindings = arrayOf(
        GraphQLFieldBinding(fieldName = "createSession", parentType = "Mutation")
))
class CreateSessionMutation(
        private val objectMapper: ObjectMapper,
        private val userRepository: UserRepository,
        private val sessionRepository: SessionRepository
) : DataFetcher<CreateSessionOutput> {

    override fun get(environment: DataFetchingEnvironment?): CreateSessionOutput {
        if (environment == null) {
            return CreateSessionOutput()
        }

        if (environment.containsArgument("input")) {
            val input = objectMapper.convertValue<CreateSessionInput>(
                    environment.getArgument<Any>("input"),
                    CreateSessionInput::class.java
            )

            val user = userRepository.getOne(input.userId)

            val session = sessionRepository.save(Session(
                    date = input.date,
                    poolLength = input.poolLength,
                    calories = input.calories,
                    user = user
            ))

            return CreateSessionOutput(session)
        }

        return CreateSessionOutput()
    }
}
