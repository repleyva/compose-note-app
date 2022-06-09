package repleyva.dev.composenote.feature_note.ui.routes

sealed class Screen(val route: String) {
    object ToNoteListScreen: Screen("NoteListScreen")
    object ToNoteCreateScreen: Screen("NoteCreateScreen")
}
