package repleyva.dev.composenote.feature_note.domain.use_case

import repleyva.dev.composenote.feature_note.domain.model.InvalidNoteException
import repleyva.dev.composenote.feature_note.domain.model.Note
import repleyva.dev.composenote.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class CreateNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) throw InvalidNoteException("The title of the note can't be empty.")
        if (note.content.isBlank()) throw InvalidNoteException("The content of the note can't be empty.")
        repository.insertNote(note)
    }
}