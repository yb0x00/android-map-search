package campus.tech.kakao.map.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import campus.tech.kakao.map.DataRepository.SearchDataRepository
import campus.tech.kakao.map.Data.SearchData
import campus.tech.kakao.map.retrofit.Document

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SearchDataRepository = SearchDataRepository(application)

    private val _searchDataList = MutableLiveData<List<Document>>()
    val result: LiveData<List<Document>>
        get() = _searchDataList

    fun loadResultMapData(data: String) {
        repository.getResultFromAPI(data) { response ->
            if (response.isSuccessful) {
                val body = response.body()
                body?.documents?.let { documents ->
                    _searchDataList.postValue(documents)
                }
            }
        }
    }
}