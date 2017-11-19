package land.eies.graphql.annotation

import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class GraphQLDataFetcher(
        val bindings: Array<GraphQLFieldBinding> = emptyArray()
)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GraphQLFieldBinding(
        val fieldName: String,
        val parentType: String
)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Repository
annotation class GraphQLRepository

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class GraphQLTypeResolver(
        val typeName: String
)
