package repleyva.dev.composenote.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import repleyva.dev.composenote.ui.generics.ComposeNoteApp
import repleyva.dev.composenote.ui.routes.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeNoteApp { Navigation() } }
    }
}