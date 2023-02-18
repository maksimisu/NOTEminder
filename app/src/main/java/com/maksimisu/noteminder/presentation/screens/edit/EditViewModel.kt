package com.maksimisu.noteminder.presentation.screens.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimisu.noteminder.domain.model.Note
import com.maksimisu.noteminder.domain.usecases.AddNoteUseCase
import com.maksimisu.noteminder.domain.usecases.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
): ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch {
            addNoteUseCase.invoke(note)
            onSuccess()
        }
    }

    fun getNoteById(id: Long) {
        viewModelScope.launch {
            getNoteByIdUseCase.invoke(id).let {
                _note.postValue(it)
            }
        }
    }

}