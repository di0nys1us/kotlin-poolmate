package land.eies.poolmate.configuration

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.graphql.GraphQLSpringWiringFactory
import land.eies.graphql.annotation.GraphQLDataFetcher
import land.eies.graphql.annotation.GraphQLFieldBinding
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
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
    lateinit var graphQLSpringWiringFactory: GraphQLSpringWiringFactory

    @Test
    fun resolveDataFetcher() {
        graphQLSpringWiringFactory.resolveDataFetcher("fieldNameA", "parentTypeA").let { dataFetcher ->
            assertThat(dataFetcher).isSameAs(TestDataFetcherA)
        }

        graphQLSpringWiringFactory.resolveDataFetcher("fieldNameB", "parentTypeB").let { dataFetcher ->
            assertThat(dataFetcher).isSameAs(TestDataFetcherB)
        }

        graphQLSpringWiringFactory.resolveDataFetcher("fieldNameC", "parentTypeC").let { dataFetcher ->
            assertThat(dataFetcher).isSameAs(TestDataFetcherB)
        }
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
