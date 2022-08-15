package repleyva.dev.composenote.feature_note.ui.flow.notes_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import repleyva.dev.composenote.feature_note.domain.model.Note
import repleyva.dev.composenote.feature_note.domain.use_case.NoteUseCases
import repleyva.dev.composenote.feature_note.domain.util.NoteOrder
import repleyva.dev.composenote.feature_note.domain.util.OrderType
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    /**
     * private variables
     */

    private var recentlyDeleteNote: Note? = null

    private var getNotesJob: Job? = null

    /**
     * State
     */

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    /**
     * Init
     */

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    /**
     * Handle Events
     */

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> handleNotes(event.noteOrder)
            is NotesEvent.DeleteNote -> deleteNote(event.note)
            is NotesEvent.RestoreNote -> restoreNote()
            is NotesEvent.ToggleOrderSection -> handleToggleOrderSection()
        }
    }

    /**
     * Events
     */

    private fun handleToggleOrderSection() {
        _state.value =
            state.value.copy(
                isOrderSelectionVisible = !state.value.isOrderSelectionVisible
            )
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.deleteNote(note)
            recentlyDeleteNote = note
        }
    }

    private fun restoreNote() {
        viewModelScope.launch {
            noteUseCases.createNote(recentlyDeleteNote ?: return@launch)
            recentlyDeleteNote = null
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

    /**
     * Validations
     */

    private fun handleNotes(noteOrder: NoteOrder) {
        val stateOrder = state.value.noteOrder::class
        val orderType = state.value.noteOrder.orderType
        if (stateOrder == noteOrder && orderType == noteOrder.orderType) return
        getNotes(noteOrder)
    }
}