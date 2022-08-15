package repleyva.dev.composenote.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repleyva.dev.composenote.feature_note.data.datasource.NoteDatabase
import repleyva.dev.composenote.feature_note.data.repository.NoteRepositoryImpl
import repleyva.dev.composenote.feature_note.domain.repository.NoteRepository
import repleyva.dev.composenote.feature_note.domain.use_case.CreateNote
import repleyva.dev.composenote.feature_note.domain.use_case.DeleteNoteUseCase
import repleyva.dev.composenote.feature_note.domain.use_case.GetNotesUseCase
import repleyva.dev.composenote.feature_note.domain.use_case.NoteUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase =
        Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun providesNoteUseCase(repository: NoteRepository): NoteUseCases =
        NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            createNote = CreateNote(repository)
        )
}