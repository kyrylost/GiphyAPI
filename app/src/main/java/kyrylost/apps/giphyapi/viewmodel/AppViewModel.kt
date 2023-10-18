package kyrylost.apps.giphyapi.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kyrylost.apps.giphyapi.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    appNavigator: AppNavigator
): ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

}