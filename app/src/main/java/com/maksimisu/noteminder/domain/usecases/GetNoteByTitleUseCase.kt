package com.maksimisu.noteminder.domain.usecases

import com.maksimisu.noteminder.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByTitleUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(title: String) = noteRepository.getNoteByTitle(title)
}