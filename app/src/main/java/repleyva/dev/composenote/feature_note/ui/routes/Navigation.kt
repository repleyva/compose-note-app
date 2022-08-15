package repleyva.dev.composenote.feature_note.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import repleyva.dev.composenote.feature_note.ui.flow.note_create.CreateNoteScreen
import repleyva.dev.composenote.feature_note.ui.flow.notes_list.NoteListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ToNoteListScreen.route
    ) {
        composable(
            route = Screen.ToNoteListScreen.route
        ) {
            NoteListScreen() {
                navController.navigate(it)
            }
        }
        composable(
            route = Screen.ToNoteCreateScreen.route + "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId",
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor",
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            CreateNoteScreen(noteColor = color) { navController.navigateUp() }
        }
    }
}