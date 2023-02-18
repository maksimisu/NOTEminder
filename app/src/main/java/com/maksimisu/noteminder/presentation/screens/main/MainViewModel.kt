package com.maksimisu.noteminder.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimisu.noteminder.domain.model.Note
import com.maksimisu.noteminder.domain.usecases.GetAllNotesUseCase
import com.maksimisu.noteminder.domain.usecases.GetNoteByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val getNoteByTitleUseCase: GetNoteByTitleUseCase
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    init {
        getAllNotes()
    }

    private val _notesByTitle = MutableLiveData<List<Note>>()
    val notesByTitle: LiveData<List<Note>>
        get() = _notesByTitle

    fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUseCase.invoke().let {
                _notes.postValue(it)
            }
        }
    }

    fun getNotesByTitle(title: String) {
        viewModelScope.launch {
            getNoteByTitleUseCase.invoke(title).let {
                _notesByTitle.postValue(it)
            }
        }
    }
}