package land.eies.graphql

import graphql.ExecutionInput
import java.util.function.Supplier

data class ExecutionInputSupplier(
        private val query: String?,
        private val operationName: String?,
        private val context: Any?,
        private val root: Any?,
        private val variables: MutableMap<String, Any>?
) : Supplier<ExecutionInput> {

    override fun get(): ExecutionInput {
        return ExecutionInput(query, operationName, context, root, variables)
    }
}
