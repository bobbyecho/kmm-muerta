package com.bobbyecho.muerta.android.features.reminder.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bobbyecho.muerta.android.MyApplicationTheme
import com.bobbyecho.muerta.android.R
import com.bobbyecho.muerta.android.core.utils.FakeNavigator
import com.bobbyecho.muerta.android.features.reminder.presentation.destinations.AddReminderScreenDestination
import com.bobbyecho.muerta.feature.reminder.domain.model.Reminder
import com.bobbyecho.muerta.feature.reminder.viewmodel.ReminderViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

typealias ToggleReminderType = (id: Long, isDone: Boolean) -> Unit

@RootNavGraph(start = true)
@Destination
@Composable
fun ReminderScreen(navigator: DestinationsNavigator, reminderViewModel: ReminderViewModel = koinViewModel()) {
	val reminderState = reminderViewModel.reminderState.collectAsState()

	LaunchedEffect(true) {
		reminderViewModel.loadReminders()
	}

	val toggleReminder: ToggleReminderType = { id, isDone ->
		reminderViewModel.toggleReminder(id = id, isDone = isDone)
	}

	ReminderContainer(
		reminderList = reminderState.value.reminders,
		navigator = navigator,
		toggleReminder = toggleReminder
	)
}

@Composable
fun ReminderContainer(
	navigator: DestinationsNavigator,
	reminderList: List<Reminder>,
	toggleReminder: ToggleReminderType
) {
	Scaffold(
		modifier = Modifier
			.background(Color.White)
			.fillMaxSize()
			.padding(vertical = 15.dp),
		bottomBar = {
			Row(
				Modifier
					.padding(horizontal = 15.dp)
					.clickable {
						navigator.navigate(AddReminderScreenDestination)
					}) {
				Image(
					modifier = Modifier
						.background(
							Color.Red,
							CircleShape
						)
						.clip(CircleShape),
					imageVector = Icons.Rounded.Add,
					contentDescription = null,
					colorFilter = ColorFilter.tint(
						Color.White
					)
				)
				Text(
					modifier = Modifier.padding(horizontal = 10.dp),
					text = "New Reminder",
					color = Color.Red,
					fontSize = 17.sp,
					fontWeight = FontWeight.Bold
				)
			}
		}
	) {
		Column(modifier = Modifier.padding(it)) {
			Text(
				modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
				text = stringResource(R.string.reminder_title_header),
				color = Color.Blue,
				fontSize = 31.sp,
				fontWeight = FontWeight.ExtraBold,
			)
			ReminderList(reminderList = reminderList, toggleReminder = toggleReminder)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun ReminderScreenPreview() {
	MyApplicationTheme(darkTheme = false) {
		ReminderContainer(
			reminderList = sampleReminderList,
			navigator = FakeNavigator,
			toggleReminder = {_,_ -> }
		)
	}
}