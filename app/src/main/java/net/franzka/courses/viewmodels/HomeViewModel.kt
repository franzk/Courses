package net.franzka.courses.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.franzka.courses.models.Home
import net.franzka.courses.repository.HomeRepository
import net.franzka.courses.utils.APIConstants
import net.franzka.courses.utils.Utils

class HomeViewModel(private val homeRepository: HomeRepository, application: Application
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _homeId: Long? = null
    val homeId: Long? get() = _homeId
    fun setHomeId(homeId: Long?) { this._homeId = homeId }

    val name = MutableLiveData<String>()

    val myHomes = MutableLiveData<List<Home>>()

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _waitingForResponse = MutableLiveData<Boolean>()
    val waitingForResponse: LiveData<Boolean> = _waitingForResponse



    fun create(name: String) = viewModelScope.launch {
        _waitingForResponse.value = true
        val token = Utils.getAppToken()
        viewModelScope.launch {
            Log.d(TAG, "create: $token")
            val response = homeRepository.create(name, token)
            _status.value = (response.body()?.status == APIConstants.TRUE)
            _message.value = response.body()?.message ?: ""
            _waitingForResponse.value = false
        }

    }

    fun getMyHomes(token: String) {

        _waitingForResponse.value = true

        viewModelScope.launch {
            val response = homeRepository.myHomes(token)
            _status.value = (response.body()?.status == APIConstants.TRUE)
            _message.value = response.body()?.message
            if (_status.value == true) {
                myHomes.value = response.body()?.result?.homes
            } else {
                myHomes.value = listOf()
            }
            _waitingForResponse.value = false
        }
    }

}

class HomeViewModelFactory(private val homeRepository: HomeRepository, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeRepository, application) as T
    }
}
