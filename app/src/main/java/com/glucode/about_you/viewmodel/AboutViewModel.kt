package com.glucode.about_you.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData

class AboutViewModel : ViewModel() {

    private var _currentEngineer = MutableLiveData<Engineer>()
    val currentEngineer = _currentEngineer
    fun setCurrentEngineer(engineer: Engineer) {
        _currentEngineer.value = engineer
    }

    private var _engineerList = MutableLiveData(MockData.engineers)
    val engineerList = _engineerList


    private val _currentProfileImageUri = MutableLiveData<Uri>()
    val currentProfileImageUri: LiveData<Uri> = _currentProfileImageUri
    fun setCurrentProfileImage(uri: Uri?) {
        if (uri != null) {
            _currentProfileImageUri.value = uri
        }
    }

}