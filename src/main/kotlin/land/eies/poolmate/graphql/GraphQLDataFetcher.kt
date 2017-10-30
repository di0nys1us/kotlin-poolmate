package land.eies.poolmate.graphql

import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class GraphQLDataFetcher(
        val bindings: Array<GraphQLFieldBinding> = emptyArray()
)
