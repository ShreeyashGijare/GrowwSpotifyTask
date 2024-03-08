package com.example.growtask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.growtask.data.data_models.artistAlbums.ArtistsAlbum
import com.example.growtask.data.data_models.artistModel.ArtistModel
import com.example.growtask.data.data_models.artistRelated.ArtistRelated
import com.example.growtask.data.data_models.artistTopTracks.ArtistTopTracks
import com.example.growtask.data.data_models.searchModel.Albums
import com.example.growtask.data.data_models.searchModel.SearchModel
import com.example.growtask.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _searchData: MutableLiveData<SearchModel> = MutableLiveData(null)
    val searchData: LiveData<SearchModel>
        get() = _searchData

    private val _artistData: MutableLiveData<ArtistModel> = MutableLiveData()
    val artistData: LiveData<ArtistModel>
        get() = _artistData

    private val _artistTopTracks: MutableLiveData<ArtistTopTracks> = MutableLiveData()
    val artistTopTracks: LiveData<ArtistTopTracks>
        get() = _artistTopTracks

    private val _artistAlbums: MutableLiveData<ArtistsAlbum> = MutableLiveData()
    val artistAlbums: LiveData<ArtistsAlbum>
        get() = _artistAlbums

    private val _artistRelated: MutableLiveData<ArtistRelated> = MutableLiveData()
    val artistRelated: LiveData<ArtistRelated>
        get() = _artistRelated

    var searchDataJob: Job? = null

    fun getSearchData(searchQuery: String, offset: Int, limit: Int) {
        searchDataJob?.cancel()
        searchDataJob = viewModelScope.async(Dispatchers.IO) {
            val getSearchDataJob = repository.searchSongs(searchQuery, offset, limit)
            withContext(Dispatchers.Main) {
                _searchData.value = getSearchDataJob
            }
        }
    }

    fun getArtistData(id: String) {
        viewModelScope.async {
            val getArtistData = repository.getArtist(id)

            withContext(Dispatchers.Main) {
                _artistData.value = getArtistData
            }
        }
    }

    fun getArtistTopTracks(id: String) {
        viewModelScope.async {
            val getArtistData = repository.getArtistTopTracks(id)
            withContext(Dispatchers.Main) {
                _artistTopTracks.value = getArtistData
            }
        }
    }

    fun getArtistAlbums(id: String) {
        viewModelScope.async {
            val getArtistData = repository.getArtistAlbum(id)
            withContext(Dispatchers.Main) {
                _artistAlbums.value = getArtistData
            }
        }
    }

    fun getArtistRelated(id: String) {
        viewModelScope.async {
            val getArtistData = repository.getArtistRelated(id)
            withContext(Dispatchers.Main) {
                _artistRelated.value = getArtistData
            }
        }
    }
}