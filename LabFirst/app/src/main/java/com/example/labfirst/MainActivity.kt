package com.example.labfirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labfirst.ui.theme.LabFirstTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabFirstTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(mainViewModel: MainViewModel = viewModel()) {
    val initialText = stringResource(id = R.string.welcome_message)
    val newText = stringResource(id = R.string.new_message)
    if (mainViewModel.text.isEmpty()) {
        mainViewModel.onTextChanged(initialText)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = mainViewModel.text,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            mainViewModel.onTextChanged(newText)
        }, Modifier.padding(16.dp)) {
            Text("Change text")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabFirstTheme {
        Greeting()
    }
}