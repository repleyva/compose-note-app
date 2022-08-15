package repleyva.dev.composenote.feature_note.domain.use_case

import repleyva.dev.composenote.feature_note.domain.model.Note
import repleyva.dev.composenote.feature_note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? = repository.getNoteById(id)
}