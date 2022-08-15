package repleyva.dev.composenote.feature_note.ui.flow.note_create

sealed interface UiEvent {

    data class ShowSnackbar(val message: String): UiEvent

    object SaveNote: UiEvent
}