package com.maksimisu.noteminder.domain.usecases

import com.maksimisu.noteminder.domain.model.Note
import com.maksimisu.noteminder.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) = noteRepository.insertNote(note)
}