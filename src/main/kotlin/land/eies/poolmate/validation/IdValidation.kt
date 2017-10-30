package land.eies.poolmate.validation

import graphql.GraphQLError
import graphql.execution.instrumentation.fieldvalidation.FieldValidation
import graphql.execution.instrumentation.fieldvalidation.FieldValidationEnvironment

class IdValidation : FieldValidation {

    override fun validateFields(validationEnvironment: FieldValidationEnvironment?): MutableList<GraphQLError> {
        return mutableListOf()
    }
}
