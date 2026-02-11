package com.solarrage.androidtest.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solarrage.androidtest.domain.model.Channel
import com.solarrage.androidtest.presentation.viewmodel.ChannelsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChannelsScreen() {
    val viewModel: ChannelsViewModel = koinViewModel()
    val channels by viewModel.channels.collectAsState()

    ChannelsContent(
        channels = channels,
        onQueryChanged = viewModel::onQueryChanged
    )
}
@Composable
private fun ChannelsContent(
    channels: List<Channel>,
    onQueryChanged: (String) -> Unit
) {

    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = text,
            onValueChange = {
                text = it
                onQueryChanged(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Search by title or category")
            }
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn {
            items(
                channels,
                key = { it.id }
            ) { channel ->
                ChannelItem(channel)
            }
        }
    }
}

@Composable
private fun ChannelItem(channel: Channel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = channel.title,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = channel.categories.joinToString(" / "),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChannelsContentPreview() {

    val listOfChannels = listOf(
        Channel(
            id = "1",
            title = "Hype Channel 1",
            isLive = false,
            categories = listOf("Sports", "Regional")
        ),
        Channel(
            id = "2",
            title = "Global Channel 2",
            isLive = false,
            categories = listOf("News")
        ),
        Channel(
            id = "3",
            title = "Local Channel 3",
            isLive = false,
            categories = listOf("News", "Regional", "NBA")
        )
    )

    ChannelsContent(
        channels = listOfChannels,
        onQueryChanged = {}
    )
}
