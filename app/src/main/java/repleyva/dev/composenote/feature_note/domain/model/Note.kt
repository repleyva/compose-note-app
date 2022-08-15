package repleyva.dev.composenote.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import repleyva.dev.composenote.feature_note.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(
            RedOrange,
            RedPink,
            BabyBlue,
            Violet,
            LightGreen,
        )
    }
}

class InvalidNoteException(message: String): Exception(message)
