package repleyva.dev.composenote.feature_note.ui.generics

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import repleyva.dev.composenote.feature_note.ui.theme.ComposeNoteTheme

@Composable
fun ComposeNoteApp(content: @Composable () -> Unit) =
    ComposeNoteTheme { Surface(color = MaterialTheme.colors.background) { content() } }