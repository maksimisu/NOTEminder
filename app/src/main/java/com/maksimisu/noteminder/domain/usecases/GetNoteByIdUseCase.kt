package com.maksimisu.noteminder.domain.usecases

import com.maksimisu.noteminder.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Long) = noteRepository.getNoteById(id)
}