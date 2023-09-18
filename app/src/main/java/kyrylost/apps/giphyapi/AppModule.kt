package kyrylost.apps.giphyapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kyrylost.apps.giphyapi.api.ApiInstance
import kyrylost.apps.giphyapi.api.ApiRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGifsApiRepository() : ApiRepository {
        return ApiRepository(ApiInstance.apiService)
    }

}