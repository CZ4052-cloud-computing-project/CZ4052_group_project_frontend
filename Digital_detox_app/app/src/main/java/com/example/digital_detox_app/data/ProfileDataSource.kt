package com.example.digital_detox_app.data

class ProfileDataSource {
    private var username: String = "" // Assuming UserProfile is a data class representing user data

    fun getUserProfile(): String {
        return username
    }

    fun updateUserProfile(updatedProfile: String) {
        username = updatedProfile
    }
}