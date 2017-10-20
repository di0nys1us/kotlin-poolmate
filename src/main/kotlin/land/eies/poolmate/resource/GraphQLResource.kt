package land.eies.poolmate.resource

import graphql.GraphQL
import land.eies.poolmate.graphql.ExecutionInputSupplier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("graphql")
class GraphQLResource(val graphQL: GraphQL) {

    @GetMapping
    fun get(executionInputSupplier: ExecutionInputSupplier): ResponseEntity<Any> {
        val executionResult = graphQL.execute(executionInputSupplier.get())

        return ResponseEntity.ok(executionResult.toSpecification())
    }

    @PostMapping
    fun post(@RequestBody executionInputSupplier: ExecutionInputSupplier): ResponseEntity<Any> {
        val executionResult = graphQL.execute(executionInputSupplier.get())

        return ResponseEntity.ok(executionResult.toSpecification())
    }
}
