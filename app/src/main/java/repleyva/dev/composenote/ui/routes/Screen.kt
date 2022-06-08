package repleyva.dev.composenote.ui.routes

sealed class Screen(val route: String) {
    object ToNoteListScreen: Screen("NoteListScreen")
    object ToNoteCreateScreen: Screen("NoteCreateScreen")
}
