package repleyva.dev.composenote.feature_note.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import repleyva.dev.composenote.feature_note.ui.generics.ComposeNoteApp
import repleyva.dev.composenote.feature_note.ui.routes.Navigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeNoteApp { Navigation() } }
    }
}