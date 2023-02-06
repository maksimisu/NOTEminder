package com.maksimisu.noteminder.presentation.screens.detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimisu.noteminder.domain.model.Note
import com.maksimisu.noteminder.domain.usecases.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    fun getNoteById(id: Long) {
        viewModelScope.launch {
            getNoteByIdUseCase.invoke(id).let {
                _note.postValue(it)
            }
        }
    }

}