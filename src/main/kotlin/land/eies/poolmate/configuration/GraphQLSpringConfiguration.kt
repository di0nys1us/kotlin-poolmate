package land.eies.poolmate.configuration

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import graphql.schema.idl.TypeRuntimeWiring
import land.eies.poolmate.fetcher.SessionFetcher
import land.eies.poolmate.fetcher.SessionSetsFetcher
import land.eies.poolmate.fetcher.SessionsFetcher
import land.eies.poolmate.fetcher.UserFetcher
import land.eies.poolmate.scalar.Scalars
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class GraphQLSpringConfiguration {

    @Autowired
    lateinit var userFetcher: UserFetcher

    @Autowired
    lateinit var sessionFetcher: SessionFetcher

    @Autowired
    lateinit var sessionsFetcher: SessionsFetcher

    @Autowired
    lateinit var sessionSetsFetcher: SessionSetsFetcher

    @Bean
    fun typeDefinitionRegistry(@Value("classpath:/graphql/*.graphql") resources: Array<Resource>): TypeDefinitionRegistry {
        val schemaParser = SchemaParser()
        val typeDefinitionRegistry = TypeDefinitionRegistry()

        resources.forEach { typeDefinitionRegistry.merge(schemaParser.parse(it.file)) }

        return typeDefinitionRegistry
    }

    @Bean
    fun runtimeWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
                .scalar(Scalars.GraphQLDuration)
                .scalar(Scalars.GraphQLLocalDate)
                .wiringFactory(GraphQLSpringWiringFactory())
                .type(TypeRuntimeWiring.newTypeWiring("Query")
                        .dataFetcher("user", userFetcher)
                        .dataFetcher("session", sessionFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("User")
                        .dataFetcher("sessions", sessionsFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Session")
                        .dataFetcher("sessionSets", sessionSetsFetcher))
                .build()
    }

    @Bean
    fun graphQLSchema(typeDefinitionRegistry: TypeDefinitionRegistry, runtimeWiring: RuntimeWiring): GraphQLSchema {
        return SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)
    }

    @Bean
    fun graphQL(graphQLSchema: GraphQLSchema): GraphQL {
        return GraphQL.newGraphQL(graphQLSchema).build()
    }
}
