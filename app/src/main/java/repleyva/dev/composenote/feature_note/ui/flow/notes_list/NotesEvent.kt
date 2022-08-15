package repleyva.dev.composenote.feature_note.ui.flow.notes_list

import repleyva.dev.composenote.feature_note.domain.model.Note
import repleyva.dev.composenote.feature_note.domain.util.NoteOrder

sealed interface NotesEvent {

    data class Order(val noteOrder: NoteOrder): NotesEvent

    data class DeleteNote(val note: Note): NotesEvent

    object RestoreNote: NotesEvent

    object ToggleOrderSection: NotesEvent
}
