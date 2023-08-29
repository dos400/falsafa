package uz.hamroev.faylasuf.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {

    private val _sharedData = MutableLiveData<String>()
    val sharedData: LiveData<String> get() = _sharedData

    fun updateData(newData: String) {
        _sharedData.value = newData
    }
}