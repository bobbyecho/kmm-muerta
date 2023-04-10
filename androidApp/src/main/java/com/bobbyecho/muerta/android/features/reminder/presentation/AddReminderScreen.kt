package com.bobbyecho.muerta.android.features.reminder.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bobbyecho.muerta.android.MyApplicationTheme
import com.bobbyecho.muerta.android.core.utils.FakeNavigator
import com.bobbyecho.muerta.feature.reminder.viewmodel.ReminderViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun AddReminderScreen(
	navigator: DestinationsNavigator,
	reminderViewModel: ReminderViewModel = koinViewModel(),
) {
	val addReminderState = reminderViewModel.addReminderState.collectAsState()
	val canSubmit = remember(addReminderState.value.title) {
		addReminderState.value.title.isNotEmpty()
	}

	Scaffold(
		backgroundColor = Color(0xFFEEEEEE),
		modifier = Modifier
			.background(Color(0xFFEEEEEE))
			.padding(horizontal = 15.dp)
			.fillMaxSize()
			.border(
				width = 1.dp,
				color = Color.Transparent,
				shape = MaterialTheme.shapes.small.copy(
					topStart = CornerSize(10.dp),
					topEnd = CornerSize(10.dp)
				)
			),
		topBar = {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						top = 15.dp,
						bottom = 25.dp
					),
				horizontalArrangement = Arrangement.SpaceBetween,
			) {
				Text(
					text = "Cancel",
					fontSize = 17.sp,
					color = Color.Blue,
					modifier = Modifier.clickable { navigator.navigateUp() }
				)
				Text(
					text = "New Reminder",
					fontSize = 17.sp,
					color = Color.Black,
					fontWeight = FontWeight.Bold
				)
				Text(
					modifier = Modifier.clickable {
						if(canSubmit) {
							reminderViewModel.insertReminder()
							navigator.popBackStack()
						}
					},
					text = "Add",
					fontSize = 17.sp,
					color = if(canSubmit) Color.Blue else Color.Gray,
					fontWeight = FontWeight.Bold
				)
			}
		}) {
			Column(
				modifier = Modifier
					.padding(it)
			) {
				TextField(
					modifier = Modifier.fillMaxWidth(),
					value = addReminderState.value.title,
					onValueChange = { title ->
						reminderViewModel.onTitleChanged(title)
					},
					shape = MaterialTheme.shapes.small.copy(
						bottomStart = ZeroCornerSize,
						bottomEnd = ZeroCornerSize,
						topStart = CornerSize(10.dp),
						topEnd = CornerSize(10.dp)
					),
					textStyle = TextStyle(
						color = Color.Black,
						fontSize = 17.sp
					),
					placeholder = {
						Text(
							text = "Title",
							fontSize = 17.sp,
							color = Color.LightGray
						)
					},
					colors = TextFieldDefaults.textFieldColors(
						backgroundColor = Color.White,
						focusedIndicatorColor = Color.Transparent,
						unfocusedIndicatorColor = Color.Transparent,
					)
				)
				Column(
					modifier = Modifier
						.background(Color.White)
						.height(1.dp)
						.fillMaxWidth(),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Spacer(modifier = Modifier
						.height(0.7.dp)
						.fillMaxWidth(0.92F)
						.background(Color.LightGray))
				}
				TextField(
					modifier = Modifier.fillMaxWidth(),
					shape = MaterialTheme.shapes.small.copy(
						topStart = ZeroCornerSize,
						topEnd = ZeroCornerSize,
						bottomStart = CornerSize(10.dp),
						bottomEnd = CornerSize(10.dp)
					),
					textStyle = TextStyle(
						color = Color.Black,
						fontSize = 17.sp
					),
					placeholder = {
						Text(
							text = "Notes",
							fontSize = 17.sp,
							color = Color.LightGray
						)
					},
					colors = TextFieldDefaults.textFieldColors(
						backgroundColor = Color.White,
						focusedIndicatorColor = Color.Transparent,
						unfocusedIndicatorColor = Color.Transparent,
					),
					value = addReminderState.value.description,
					onValueChange = { notes ->
						reminderViewModel.onNotesChanged(notes)
					},
				)
			}
	}
}

@Preview
@Composable
fun AddReminderScreenPreview() {
	MyApplicationTheme(darkTheme = false) {
		AddReminderScreen(
			navigator = FakeNavigator,
		)
	}
}