package com.example.star_wars_encyclopedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.star_wars_encyclopedia.data.model.PersonageResponse
import com.example.star_wars_encyclopedia.domain.GetPersonageUseCase
import com.example.star_wars_encyclopedia.domain.model.Personage
import com.example.star_wars_encyclopedia.domain.model.toDomain
import kotlinx.coroutines.launch
import retrofit2.Response


class PersonageViewModel(application: Application) : AndroidViewModel(application){

    private val getPersonageUseCase: GetPersonageUseCase = GetPersonageUseCase(getApplication<Application>().applicationContext)
    val peoples = MutableLiveData<List<Personage>>()
    val isLoading = MutableLiveData<Boolean>()
    var currentPage = 1
    val person = MutableLiveData<Personage>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = getPersonageUseCase(currentPage)
            peoples.postValue(handlePeopleResponse(response))
            isLoading.postValue(false)
        }
    }

    private fun handlePeopleResponse(response: Response<PersonageResponse>): List<Personage>? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return resultResponse.results.toList().map { it -> it.toDomain() }
            }
        }
        return emptyList()
    }

    fun getPersonage(name: String?) {
        viewModelScope.launch {
            val personage = getPersonageUseCase.getPersonage(name)
            person.value = personage
        }
    }

}