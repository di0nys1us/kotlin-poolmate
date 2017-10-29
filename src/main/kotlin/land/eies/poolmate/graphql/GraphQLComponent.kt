package land.eies.poolmate.graphql

import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class GraphQLComponent(
        val dataFetcherBindings: Array<GraphQLDataFetcherBinding> = emptyArray()
)
