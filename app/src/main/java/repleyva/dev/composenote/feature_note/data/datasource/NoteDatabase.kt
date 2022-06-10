package repleyva.dev.composenote.feature_note.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import repleyva.dev.composenote.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao
}