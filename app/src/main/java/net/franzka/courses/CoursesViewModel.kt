package net.franzka.courses

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.franzka.courses.repository.Repository
import net.franzka.courses.utils.APIConstants

class CoursesViewModel(private val repository: Repository, application: Application
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "CoursesViewModel"
    }

    private val _authenticated = MutableLiveData<Boolean>()
    val authenticated: LiveData<Boolean> = _authenticated

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _connectionCount = MutableLiveData<Int>()
    val connectionCount: LiveData<Int> = _connectionCount

    private val _waitingForResponse = MutableLiveData<Boolean>()
    val waitingForResponse: LiveData<Boolean> = _waitingForResponse

    private val sharedPref = getApplication<Application>().getSharedPreferences("prefs", Context.MODE_PRIVATE)


    fun loadShardPrefs() {

        _username.value = sharedPref.getString(APIConstants.KEY_USERNAME, "")
        _token.value = sharedPref.getString(APIConstants.KEY_TOKEN, "")
        _token.value?.let {
            if (it.isNotEmpty()) connectionCount(it)
        }

    }

    private fun setShardPrefs() {

        with(sharedPref.edit()) {
            putString(APIConstants.KEY_USERNAME, _username.value)
            putString(APIConstants.KEY_TOKEN, _token.value)
            apply()
        }
    }

    fun signUp(username: String, email: String, password: String) {

        _waitingForResponse.value = true

        viewModelScope.launch {
            val response = repository.signUp(username, email, password)
            _status.value = (response.body()?.status == APIConstants.TRUE)
            _message.value = response.body()?.message ?: ""
            _waitingForResponse.value = false
        }
    }

    fun signIn(login: String, password: String) {

        _message.value = ""
        _waitingForResponse.value = true

        viewModelScope.launch {

            val response = repository.signIn(login, password)
            _status.value = (response.body()?.status == APIConstants.TRUE)
            _message.value = response.body()?.message ?: ""

            if (_status.value == true) {
                _username.value = response.body()?.result?.username
                _token.value = response.body()?.result?.token
                setShardPrefs()
                _authenticated.value = true
            }

            _waitingForResponse.value = false
        }
    }

    fun forgottenPassword(login: String) {

        _waitingForResponse.value = true

        viewModelScope.launch {
            val response = repository.forgottenPassword(login)
            _status.value = (response.body()?.status == APIConstants.TRUE)
            _message.value = response.body()?.message ?: ""
            _waitingForResponse.value = false
        }

    }


    fun changePassword(newPassword: String) {

        _waitingForResponse.value = true

        viewModelScope.launch {
            val response = repository.changePassword(_token.value ?: "", newPassword)
            _status.value = (response.body()?.status == APIConstants.TRUE)
            _message.value = response.body()?.message ?: ""
            _waitingForResponse.value = false
        }

        // TODO si y a une erreur : vider les sharedpref, remettre sur l'écran (et ça à chaque fois qu'il y a un token)
        // TODO voir pour différencier les erreurs de connexion au réseau e les erreurs rendues par le serveur

    }


    fun signOut() {

        viewModelScope.launch {
            val response = repository.signOut(_token.value!!)
            resetData()
            setShardPrefs()
            _authenticated.value = false
        }

    }

    private fun connectionCount(token: String) {

        _waitingForResponse.value = true

        viewModelScope.launch {
            val response = repository.connectionCount(token)
            _status.value = (response.body()?.status == APIConstants.TRUE)
            if (_status.value == true) {
                _connectionCount.value = response.body()?.result?.count
            } else {
                _authenticated.value = false
            }
            _waitingForResponse.value = false
        }
    }

    private fun resetData() {
        _username.value = ""
        _token.value = ""
        _message.value = ""
        _status.value = false
        _connectionCount.value = 0
    }

}

class CoursesViewModelFactory(private val repository: Repository/*, private val activity: Activity*/, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoursesViewModel(repository, application) as T
    }
}
