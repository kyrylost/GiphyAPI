package kyrylost.apps.giphyapi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kyrylost.apps.giphyapi.api.ApiInstance
import kyrylost.apps.giphyapi.api.ApiRepository
import kyrylost.apps.giphyapi.navigation.AppNavigator
import kyrylost.apps.giphyapi.navigation.AppNavigatorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGifsApiRepository() : ApiRepository {
        return ApiRepository(ApiInstance.apiService)
    }

    @Singleton
    @Provides
    fun provideAppNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator {
        return appNavigatorImpl
    }

}