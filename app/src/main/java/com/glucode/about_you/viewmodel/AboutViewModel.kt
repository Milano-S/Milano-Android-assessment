package com.glucode.about_you.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData

class AboutViewModel : ViewModel() {

    private var _currentEngineer = MutableLiveData<Engineer>()
    val currentEngineer: LiveData<Engineer> = _currentEngineer
    fun setCurrentEngineer(engineer: Engineer) {
        _currentEngineer.value = engineer
    }

    private var _engineerList = MutableLiveData(MockData.engineers)
    val engineerList: LiveData<List<Engineer>> = _engineerList

    private val _currentProfileImageUri = MutableLiveData<Pair<Uri, String>>()
    val currentProfileImageUri: LiveData<Pair<Uri, String>> = _currentProfileImageUri
    fun setCurrentProfileImage(uri: Uri?, name: String?) {
        if (uri != null && name != null) {
            _currentProfileImageUri.value = Pair(uri, name)
        }
    }


    fun updateEngineerProfileImage(engineer: Engineer, uri: Uri) {
        val updatedList = _engineerList.value?.map {
            if (it.name == engineer.name) {
                it.copy(defaultImageName = uri.toString())
            } else {
                it
            }
        }
        _engineerList.value = updatedList
    }
}
