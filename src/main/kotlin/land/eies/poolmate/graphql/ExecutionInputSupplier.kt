package land.eies.poolmate.graphql

import graphql.ExecutionInput
import java.util.function.Supplier

data class ExecutionInputSupplier(
        val query: String?,
        val operationName: String?,
        val context: Any?,
        val root: Any?,
        val variables: MutableMap<String, Any>?
) : Supplier<ExecutionInput> {

    override fun get(): ExecutionInput {
        return ExecutionInput(query, operationName, context, root, variables)
    }
}
