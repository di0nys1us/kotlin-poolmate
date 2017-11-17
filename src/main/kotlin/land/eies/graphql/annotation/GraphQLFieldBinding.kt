package land.eies.graphql.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GraphQLFieldBinding(
        val fieldName: String,
        val parentType: String
)
