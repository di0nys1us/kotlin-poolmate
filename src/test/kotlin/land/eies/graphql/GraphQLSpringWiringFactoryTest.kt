package land.eies.graphql

import graphql.language.FieldDefinition
import graphql.language.ObjectTypeDefinition
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.FieldWiringEnvironment
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLFieldBinding
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ContextConfiguration
class GraphQLSpringWiringFactoryTest {

    @Autowired
    lateinit private var graphQLSpringWiringFactory: GraphQLSpringWiringFactory

    @Test
    fun getDataFetcher() {
        graphQLSpringWiringFactory.getDataFetcher(fieldWiringEnvironment("fieldNameA", "parentTypeA")).let { dataFetcher ->
            assertThat(dataFetcher).isSameAs(TestDataFetcherA)
        }

        graphQLSpringWiringFactory.getDataFetcher(fieldWiringEnvironment("fieldNameB", "parentTypeB")).let { dataFetcher ->
            assertThat(dataFetcher).isSameAs(TestDataFetcherB)
        }

        graphQLSpringWiringFactory.getDataFetcher(fieldWiringEnvironment("fieldNameC", "parentTypeC")).let { dataFetcher ->
            assertThat(dataFetcher).isSameAs(TestDataFetcherB)
        }
    }

    private fun fieldWiringEnvironment(fieldName: String, parentType: String): FieldWiringEnvironment {
        val fieldWiringEnvironment = mock(FieldWiringEnvironment::class.java)

        Mockito.`when`(fieldWiringEnvironment.fieldDefinition).thenReturn(FieldDefinition(fieldName))
        Mockito.`when`(fieldWiringEnvironment.parentType).thenReturn(ObjectTypeDefinition(parentType))

        return fieldWiringEnvironment
    }

    @Configuration
    class TestConfiguration {

        @Bean
        fun dataFetcherA(): DataFetcher<String> = TestDataFetcherA

        @Bean
        fun dataFetcherB(): DataFetcher<String> = TestDataFetcherB

        @Bean
        fun dataFetcherC(): DataFetcher<String> = TestDataFetcherC

        @Bean
        fun graphQLSpringWiringFactory(listableBeanFactory: ListableBeanFactory): GraphQLSpringWiringFactory =
                GraphQLSpringWiringFactory(listableBeanFactory)
    }

    @GraphQLDataFetcher(bindings = arrayOf(
            GraphQLFieldBinding(fieldName = "fieldNameA", parentType = "parentTypeA")
    ))
    object TestDataFetcherA : DataFetcher<String> {

        override fun get(environment: DataFetchingEnvironment?): String = "data"
    }

    @GraphQLDataFetcher(bindings = arrayOf(
            GraphQLFieldBinding(fieldName = "fieldNameB", parentType = "parentTypeB"),
            GraphQLFieldBinding(fieldName = "fieldNameC", parentType = "parentTypeC")
    ))
    object TestDataFetcherB : DataFetcher<String> {

        override fun get(environment: DataFetchingEnvironment?): String = "data"
    }

    object TestDataFetcherC : DataFetcher<String> {

        override fun get(environment: DataFetchingEnvironment?): String = "data"
    }
}
