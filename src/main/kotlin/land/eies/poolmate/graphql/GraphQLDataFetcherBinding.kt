package land.eies.poolmate.graphql

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GraphQLDataFetcherBinding(
        val fieldName: String,
        val parentType: String
)
