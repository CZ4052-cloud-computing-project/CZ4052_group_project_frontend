package com.example.digital_detox_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.digital_detox_app.data.ProfileDataSource
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel(
    private val profileDataSource: ProfileDataSource
): ViewModel() {
//    private val _userProfile = MutableLiveData<String?>()
//    val userProfile: LiveData<String?> get() = _userProfile

    var userProfile = MutableStateFlow("")
    init {
        userProfile.value = profileDataSource.getUserProfile()
    }

    fun updateUserProfile(updatedProfile: String) {
        profileDataSource.updateUserProfile(updatedProfile)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DigitalDetoxApplication)
                val userProfileDataSource = application.container.userProfileDataSource
                ProfileViewModel(profileDataSource = userProfileDataSource)
            }
        }
    }
}