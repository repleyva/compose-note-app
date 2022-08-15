package repleyva.dev.composenote.feature_note.ui.flow.notes_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import repleyva.dev.composenote.R
import repleyva.dev.composenote.feature_note.domain.util.NoteOrder
import repleyva.dev.composenote.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit,
) {
    Column(modifier = modifier) {
        OrderTop(noteOrder, onOrderChange)
        Spacer(modifier = Modifier.height(16.dp))
        OrderBottom(noteOrder, onOrderChange)
    }
}

@Composable
private fun OrderTop(
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit,
) = Row(modifier = Modifier.fillMaxWidth()) {
    DefaultRadioButton(
        text = stringResource(R.string.copy_title_filter),
        selected = noteOrder is NoteOrder.Title,
        onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
    )
    Spacer(modifier = Modifier.width(8.dp))
    DefaultRadioButton(
        text = stringResource(R.string.copy_date_filter),
        selected = noteOrder is NoteOrder.Date,
        onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) }
    )
    Spacer(modifier = Modifier.width(8.dp))
    DefaultRadioButton(
        text = stringResource(R.string.copy_color_filter),
        selected = noteOrder is NoteOrder.Color,
        onSelect = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) }
    )
}

@Composable
private fun OrderBottom(
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit,
) = Row(modifier = Modifier.fillMaxWidth()) {
    DefaultRadioButton(
        text = stringResource(R.string.copy_ascending_filter),
        selected = noteOrder.orderType is OrderType.Ascending,
        onSelect = { onOrderChange(noteOrder.copy(OrderType.Ascending)) }
    )
    Spacer(modifier = Modifier.width(8.dp))
    DefaultRadioButton(
        text = stringResource(R.string.copy_descending_filter),
        selected = noteOrder.orderType is OrderType.Descending,
        onSelect = { onOrderChange(noteOrder.copy(OrderType.Descending)) }
    )
}