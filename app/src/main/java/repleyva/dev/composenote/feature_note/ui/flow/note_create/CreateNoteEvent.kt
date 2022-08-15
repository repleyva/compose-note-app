package repleyva.dev.composenote.feature_note.ui.flow.note_create

import androidx.compose.ui.focus.FocusState

sealed interface CreateNoteEvent{

    data class EnteredTitle(val value: String): CreateNoteEvent

    data class ChangeTitleFocus(val focusState: FocusState): CreateNoteEvent

    data class EnteredContent(val value: String): CreateNoteEvent

    data class ChangeContentFocus(val focusState: FocusState): CreateNoteEvent

    data class ChangeColor(val color: Int): CreateNoteEvent

    object SaveNote: CreateNoteEvent

}