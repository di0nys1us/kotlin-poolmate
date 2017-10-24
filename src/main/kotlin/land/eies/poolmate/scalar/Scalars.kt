package land.eies.poolmate.scalar

import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import java.time.temporal.TemporalAccessor
import java.time.temporal.TemporalAmount

class Scalars {

    companion object {

        val GraphQLDuration = GraphQLScalarType("TemporalAmount", "java.time.temporal.TemporalAmount", object : Coercing<TemporalAmount?, String?> {
            override fun serialize(dataFetcherResult: Any?): String? {
                return dataFetcherResult.toString()
            }

            override fun parseValue(input: Any?): TemporalAmount? {
                return null
            }

            override fun parseLiteral(input: Any?): TemporalAmount? {
                return null
            }
        })

        val GraphQLLocalDate = GraphQLScalarType("TemporalAccessor", "java.time.temporal.TemporalAccessor", object : Coercing<TemporalAccessor?, String?> {
            override fun serialize(dataFetcherResult: Any?): String? {
                return dataFetcherResult.toString()
            }

            override fun parseValue(input: Any?): TemporalAccessor? {
                return null
            }

            override fun parseLiteral(input: Any?): TemporalAccessor? {
                return null
            }
        })
    }
}
