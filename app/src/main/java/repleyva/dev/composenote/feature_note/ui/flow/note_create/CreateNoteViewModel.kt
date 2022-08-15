package repleyva.dev.composenote.feature_note.ui.flow.note_create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import repleyva.dev.composenote.feature_note.domain.model.InvalidNoteException
import repleyva.dev.composenote.feature_note.domain.model.Note
import repleyva.dev.composenote.feature_note.domain.use_case.NoteUseCases
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * private variables
     */

    private var currentNoteId: Int? = null

    /**
     * State
     */

    private val _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Enter Title"))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(hint = "Enter some content"))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    /**
     * Init
     */

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) viewModelScope.launch {
                noteUseCases.getNote(noteId)?.also { note -> setNoteSelected(note) }
            }
        }
    }

    /**
     * Handle Events
     */

    fun onEvent(event: CreateNoteEvent) {
        when (event) {
            is CreateNoteEvent.ChangeColor -> onChangeColor(event.color)
            is CreateNoteEvent.ChangeContentFocus -> onChangeContentFocus(event.focusState)
            is CreateNoteEvent.ChangeTitleFocus -> onChangeTitleFocus(event.focusState)
            is CreateNoteEvent.EnteredContent -> onChangeContent(event.value)
            is CreateNoteEvent.EnteredTitle -> onChangeTitle(event.value)
            is CreateNoteEvent.SaveNote -> createNote()
        }
    }

    /**
     * Events
     */

    private fun onChangeColor(color: Int) {
        _noteColor.value = color
    }

    private fun onChangeTitle(title: String) {
        _noteTitle.value = noteTitle.value.copy(text = title)
    }

    private fun onChangeTitleFocus(focusState: FocusState) {
        _noteTitle.value = noteTitle.value.copy(
            isHintVisible = !focusState.isFocused
                    && noteTitle.value.text.isBlank()
        )
    }

    private fun onChangeContent(content: String) {
        _noteContent.value = noteContent.value.copy(text = content)
    }

    private fun onChangeContentFocus(focusState: FocusState) {
        _noteContent.value = noteContent.value.copy(
            isHintVisible = !focusState.isFocused
                    && noteContent.value.text.isBlank()
        )
    }

    /**
     * Validations
     */

    private fun createNote() {
        viewModelScope.launch {
            try {
                noteUseCases.createNote(
                    Note(
                        title = noteTitle.value.text,
                        content = noteContent.value.text,
                        timestamp = System.currentTimeMillis(),
                        color = noteColor.value,
                        id = currentNoteId
                    )
                )
                _eventFlow.emit(UiEvent.SaveNote)
            } catch (e: InvalidNoteException) {
                _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Couldn't save note"))
            }
        }
    }

    private fun setNoteSelected(note: Note) {
        currentNoteId = note.id
        _noteTitle.value = noteTitle.value.copy(text = note.title, isHintVisible = false)
        _noteContent.value = noteContent.value.copy(text = note.content, isHintVisible = false)
        _noteColor.value = note.color
    }

}