package com.glucode.about_you.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glucode.about_you.engineers.models.Engineer

class AboutViewModel : ViewModel() {

    private var _currentEngineer = MutableLiveData<Engineer>()
    val currentEngineer = _currentEngineer
    fun setCurrentEngineer(engineer: Engineer) {
        _currentEngineer.value = engineer
    }

}