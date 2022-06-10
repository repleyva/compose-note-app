package repleyva.dev.composenote.feature_note.domain.use_case

data class NoteUseCase(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase
)
