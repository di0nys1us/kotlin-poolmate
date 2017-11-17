package land.eies.poolmate.resource

import graphql.GraphQL
import land.eies.graphql.ExecutionInputSupplier
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Future

@RestController
@RequestMapping("graphql")
class GraphQLResource(private val graphQL: GraphQL) {

    @GetMapping
    fun get(executionInputSupplier: ExecutionInputSupplier): Future<ResponseEntity<Any>> {
        val executionResult = graphQL.execute(executionInputSupplier.get())

        return AsyncResult<ResponseEntity<Any>>(ResponseEntity.ok(executionResult.toSpecification()))
    }

    @PostMapping
    fun post(@RequestBody executionInputSupplier: ExecutionInputSupplier): Future<ResponseEntity<Any>> {
        val executionResult = graphQL.execute(executionInputSupplier.get())

        return AsyncResult<ResponseEntity<Any>>(ResponseEntity.ok(executionResult.toSpecification()))
    }
}
