package campus.tech.kakao.map.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import campus.tech.kakao.map.DataRepository.SearchDataRepository
import campus.tech.kakao.map.retrofit.CategoryData
import campus.tech.kakao.map.retrofit.Document

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SearchDataRepository = SearchDataRepository(application)

    private val _searchDataList = MutableLiveData<List<Document>>()
    val result: LiveData<List<Document>>
        get() = _searchDataList

    fun getTailCategory(category: String): String {
        val parts = category.split(">")
        return if (parts.size > 1) parts[1].trim() else parts.last().trim()
    }

    fun loadResultMapData(data: String) {
        repository.getResultFromAPI(data) { response ->
            if (response.isSuccessful) {
                val body = response.body()
                body?.documents?.let { documents ->
                    val updatedDocuments = documents.map { document ->
                        val descriptionFromCode = CategoryData.descriptions[document.categoryCode]
                        val descriptionFromCategory = getTailCategory(document.category)
                        val updateDocument = document.copy(
                            categoryDescription = descriptionFromCode,
                            categoryTail = descriptionFromCategory
                        )
                        updateDocument
                    }
                    _searchDataList.postValue(updatedDocuments)
                }
            }
        }
    }
}