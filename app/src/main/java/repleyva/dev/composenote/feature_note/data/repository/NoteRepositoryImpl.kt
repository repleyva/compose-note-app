package repleyva.dev.composenote.feature_note.data.repository

import kotlinx.coroutines.flow.Flow
import repleyva.dev.composenote.feature_note.data.datasource.NoteDao
import repleyva.dev.composenote.feature_note.domain.model.Note
import repleyva.dev.composenote.feature_note.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {

    override fun getNotes(): Flow<List<Note>> = dao.getNotes()

    override suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)

    override suspend fun insertNote(note: Note) = dao.insertNote(note)

    override suspend fun deleteNote(note: Note) = dao.deleteNote(note)

}