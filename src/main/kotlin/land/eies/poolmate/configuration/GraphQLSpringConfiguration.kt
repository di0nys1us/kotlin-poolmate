package land.eies.poolmate.configuration

import graphql.GraphQL
import graphql.execution.instrumentation.fieldvalidation.FieldValidationInstrumentation
import graphql.execution.instrumentation.fieldvalidation.SimpleFieldValidation
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import land.eies.graphql.GraphQLSpringWiringFactory
import land.eies.poolmate.scalar.Scalars
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class GraphQLSpringConfiguration {

    @Bean
    fun typeDefinitionRegistry(@Value("classpath:/graphql/*.graphql") resources: Array<Resource>): TypeDefinitionRegistry {
        val schemaParser = SchemaParser()
        val typeDefinitionRegistry = TypeDefinitionRegistry()

        resources.forEach { typeDefinitionRegistry.merge(schemaParser.parse(it.file)) }

        return typeDefinitionRegistry
    }

    @Bean
    fun runtimeWiring(listableBeanFactory: ListableBeanFactory): RuntimeWiring =
            RuntimeWiring.newRuntimeWiring()
                    .scalar(Scalars.GraphQLDuration)
                    .scalar(Scalars.GraphQLLocalDate)
                    .scalar(Scalars.GraphQLEmail)
                    .wiringFactory(GraphQLSpringWiringFactory(listableBeanFactory))
                    .build()

    @Bean
    fun graphQLSchema(typeDefinitionRegistry: TypeDefinitionRegistry, runtimeWiring: RuntimeWiring): GraphQLSchema =
            SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)

    @Bean
    fun graphQL(graphQLSchema: GraphQLSchema): GraphQL =
            GraphQL.newGraphQL(graphQLSchema)
                    .instrumentation(FieldValidationInstrumentation(SimpleFieldValidation()))
                    .build()
}
