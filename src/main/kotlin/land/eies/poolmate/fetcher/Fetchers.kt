package land.eies.poolmate.fetcher

import graphql.GraphQLException
import graphql.schema.DataFetchingEnvironment

fun DataFetchingEnvironment.getLong(name: String): Long = this.getArgument<Long>(name)

fun DataFetchingEnvironment.getString(name: String): String = this.getArgument<String>(name)

fun DataFetchingEnvironment.containsId(): Boolean = this.containsArgument("id")

fun DataFetchingEnvironment.getId(): Long = this.getLong("id")

fun DataFetchingEnvironment.containsEmail(): Boolean = this.containsArgument("email")

fun DataFetchingEnvironment.getEmail(): String = this.getString("email")

fun missingEnvironment(): Nothing = throw GraphQLException("environment was null")
