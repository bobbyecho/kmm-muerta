package com.bobbyecho.muerta.android.features.reminder.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bobbyecho.muerta.android.MyApplicationTheme
import com.bobbyecho.muerta.feature.reminder.domain.model.Reminder

val sampleReminderList = listOf(
	Reminder(1L, "hy my name is bob", ""),
	Reminder(2L, "hy my name is sar", ""),
	Reminder(3L, "hy my name is camcung", "")
)

@Composable
fun ReminderList(
	modifier: Modifier = Modifier,
	reminderList: List<Reminder>,
	toggleReminder: ToggleReminderType
) {

	LazyColumn(
		modifier = modifier.fillMaxSize(1F),
	) {
		items(reminderList) {
			ReminderItem(
				reminder = it,
				onRowClicked = { },
				toggle = {
					toggleReminder(it.id, !it.isDone)
				},
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun ReminderListPreview() {
	MyApplicationTheme(darkTheme = false) {
		ReminderList(
			modifier = Modifier.background(Color.White),
			reminderList = sampleReminderList,
			toggleReminder = { _, _ -> }
		)
	}
}