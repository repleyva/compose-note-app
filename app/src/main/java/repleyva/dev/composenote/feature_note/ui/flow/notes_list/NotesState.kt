package repleyva.dev.composenote.feature_note.ui.flow.notes_list

import repleyva.dev.composenote.feature_note.domain.model.Note
import repleyva.dev.composenote.feature_note.domain.util.NoteOrder
import repleyva.dev.composenote.feature_note.domain.util.OrderType

data class NotesState(

    val notes: List<Note> = emptyList(),

    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),

    val isOrderSelectionVisible: Boolean = false
)
