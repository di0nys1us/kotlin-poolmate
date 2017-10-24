package land.eies.poolmate.configuration

import graphql.schema.DataFetcher
import graphql.schema.DataFetcherFactory
import graphql.schema.TypeResolver
import graphql.schema.idl.FieldWiringEnvironment
import graphql.schema.idl.InterfaceWiringEnvironment
import graphql.schema.idl.UnionWiringEnvironment
import graphql.schema.idl.WiringFactory

class GraphQLSpringWiringFactory : WiringFactory {

    override fun getDefaultDataFetcher(environment: FieldWiringEnvironment?): DataFetcher<*> {
        return super.getDefaultDataFetcher(environment)
    }

    override fun getDataFetcher(environment: FieldWiringEnvironment?): DataFetcher<*> {
        return super.getDataFetcher(environment)
    }

    override fun <T : Any?> getDataFetcherFactory(environment: FieldWiringEnvironment?): DataFetcherFactory<T> {
        return super.getDataFetcherFactory(environment)
    }

    override fun providesDataFetcherFactory(environment: FieldWiringEnvironment?): Boolean {
        return super.providesDataFetcherFactory(environment)
    }

    override fun getTypeResolver(environment: InterfaceWiringEnvironment?): TypeResolver {
        return super.getTypeResolver(environment)
    }

    override fun getTypeResolver(environment: UnionWiringEnvironment?): TypeResolver {
        return super.getTypeResolver(environment)
    }

    override fun providesTypeResolver(environment: InterfaceWiringEnvironment?): Boolean {
        return super.providesTypeResolver(environment)
    }

    override fun providesTypeResolver(environment: UnionWiringEnvironment?): Boolean {
        return super.providesTypeResolver(environment)
    }

    override fun providesDataFetcher(environment: FieldWiringEnvironment?): Boolean {
        return super.providesDataFetcher(environment)
    }
}
