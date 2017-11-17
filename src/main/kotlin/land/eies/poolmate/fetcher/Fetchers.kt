package land.eies.poolmate.fetcher

import graphql.schema.DataFetchingEnvironment

fun DataFetchingEnvironment.getLong(name: String): Long = this.getArgument(name)

fun DataFetchingEnvironment.getId(): Long = this.getLong("id")
