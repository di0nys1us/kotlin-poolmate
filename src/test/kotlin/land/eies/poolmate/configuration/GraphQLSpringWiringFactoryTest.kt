package land.eies.poolmate.configuration

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import land.eies.poolmate.graphql.GraphQLDataFetcher
import land.eies.poolmate.graphql.GraphQLDataFetcherWiring
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
        graphQLSpringWiringFactory.resolveDataFetcher("fieldNameA", "parentTypeA").let {
            assertThat(it).isSameAs(testDataFetcherA)
        }

        graphQLSpringWiringFactory.resolveDataFetcher("fieldNameB", "parentTypeB").let {
            assertThat(it).isSameAs(testDataFetcherB)
        }

        graphQLSpringWiringFactory.resolveDataFetcher("fieldNameC", "parentTypeC").let {
            assertThat(it).isSameAs(testDataFetcherB)
        }
    }

    @Configuration
    class TestConfiguration {

        @Bean
        fun dataFetcherA(): DataFetcher<String> {
            return testDataFetcherA
        }

        @Bean
        fun dataFetcherB(): DataFetcher<String> {
            return testDataFetcherB
        }

        @Bean
        fun graphQLSpringWiringFactory(listableBeanFactory: ListableBeanFactory): GraphQLSpringWiringFactory {
            return GraphQLSpringWiringFactory(listableBeanFactory)
        }
    }

    @GraphQLDataFetcherWiring(fieldName = "fieldNameA", parentType = "parentTypeA")
    object testDataFetcherA : DataFetcher<String> {

        override fun get(environment: DataFetchingEnvironment?): String {
            return "data"
        }
    }

    @GraphQLDataFetcher(arrayOf(
            GraphQLDataFetcherWiring(fieldName = "fieldNameB", parentType = "parentTypeB"),
            GraphQLDataFetcherWiring(fieldName = "fieldNameC", parentType = "parentTypeC")
    ))
    object testDataFetcherB : DataFetcher<String> {

        override fun get(environment: DataFetchingEnvironment?): String {
            return "data"
        }
    }
}
