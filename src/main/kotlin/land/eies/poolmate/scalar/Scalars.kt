package land.eies.poolmate.scalar

import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import graphql.schema.GraphQLScalarType
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeParseException

class Scalars {

    companion object {

        val GraphQLDuration = GraphQLScalarType("Duration", "java.time.Duration", object : Coercing<Duration?, Duration?> {
            override fun serialize(input: Any?): Duration? =
                    convert(input) ?: throw CoercingSerializeException("Invalid input '$input' for Duration")

            override fun parseValue(input: Any?): Duration? =
                    convert(input) ?: throw CoercingParseValueException("Invalid input '$input' for Duration")

            override fun parseLiteral(input: Any?): Duration? = convert(input)

            private fun convert(input: Any?): Duration? {
                when (input) {
                    is Duration -> return input
                    is String -> return parse(input)
                    is StringValue -> return parse(input.value)
                }

                return null
            }

            private fun parse(input: String): Duration? {
                return try {
                    Duration.parse(input)
                } catch (e: DateTimeParseException) {
                    null
                }
            }
        })

        val GraphQLLocalDate = GraphQLScalarType("LocalDate", "java.time.LocalDate", object : Coercing<LocalDate?, LocalDate?> {
            override fun serialize(input: Any?): LocalDate? =
                    convert(input) ?: throw CoercingSerializeException("Invalid input '$input' for LocalDate")

            override fun parseValue(input: Any?): LocalDate? =
                    convert(input) ?: throw CoercingParseValueException("Invalid input '$input' for LocalDate")

            override fun parseLiteral(input: Any?): LocalDate? = convert(input)

            private fun convert(input: Any?): LocalDate? {
                when (input) {
                    is LocalDate -> return input
                    is String -> return parse(input)
                    is StringValue -> return parse(input.value)
                }

                return null
            }

            private fun parse(input: String): LocalDate? {
                return try {
                    LocalDate.parse(input)
                } catch (e: DateTimeParseException) {
                    null
                }
            }
        })

        val GraphQLEmail = GraphQLScalarType("Email", "java.lang.String", object : Coercing<String?, String?> {
            override fun serialize(input: Any?): String? =
                    convert(input) ?: throw CoercingSerializeException("Invalid input '$input' for Email")

            override fun parseValue(input: Any?): String? =
                    convert(input) ?: throw CoercingParseValueException("Invalid input '$input' for Email")

            override fun parseLiteral(input: Any?): String? = convert(input)

            private fun convert(input: Any?): String? {
                when (input) {
                    is String -> return parse(input)
                    is StringValue -> return parse(input.value)
                }

                return null
            }

            private fun parse(input: String): String? {
                return ""
            }
        })
    }
}
