package repleyva.dev.composenote.ui.generics

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import repleyva.dev.composenote.ui.theme.ComposeNoteTheme

@Composable
fun ComposeNoteApp(content: @Composable () -> Unit) =
    ComposeNoteTheme { Surface(color = MaterialTheme.colors.background) { content() } }