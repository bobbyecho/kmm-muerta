package com.bobbyecho.muerta.android.features.reminder.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bobbyecho.muerta.feature.reminder.domain.model.Reminder

@Composable
fun ReminderItem(
	reminder: Reminder,
	toggle: () -> Unit,
	onRowClicked: () -> Unit,
) {
	val context = LocalContext.current

	val textDecoration = remember(reminder.isDone) {
		if(reminder.isDone) {
			TextDecoration.LineThrough
		} else {
			TextDecoration.None
		}
	}

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 5.dp)
			.background(Color.Transparent)
			.clickable {
				Toast
					.makeText(
						context,
						"id: ${reminder.id}",
						Toast.LENGTH_LONG
					)
					.show()
			},
	) {
		Row(modifier = Modifier.padding(start = 15.dp)) {
			RadioButton(
				modifier = Modifier
					.size(20.dp)
					.align(Alignment.CenterVertically),
				selected = reminder.isDone,
				onClick = toggle,
				colors = RadioButtonDefaults.colors(
					unselectedColor = Color.Gray,
					selectedColor = Color.Red
				)
			)
			Column(
				modifier = Modifier
					.weight(1F)
					.padding(
						start = 10.dp
					)
			) {
				Text(
					modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
					color = Color.Black,
					text = reminder.title,
					textDecoration = textDecoration,
					fontSize = 17.sp
				)
				Divider(thickness = 0.6.dp)
			}
		}
	}
}


@Preview()
@Composable
fun ReminderItemPreview() {
	val reminder = Reminder(
		id = 1L,
		title = "meeting at 8 o'clock",
		desc = "",
	)

	ReminderItem(
		reminder = reminder,
		toggle = {},
		onRowClicked = {}
	)
}