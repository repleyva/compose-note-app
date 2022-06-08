package repleyva.dev.composenote.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import repleyva.dev.composenote.ui.flow.notes_list.NoteListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ToNoteListScreen.route
    ) {
        composable(
            route = Screen.ToNoteListScreen.route
        ) { NoteListScreen() }
        composable(
            route = Screen.ToNoteCreateScreen.route
        ) {}
    }
}