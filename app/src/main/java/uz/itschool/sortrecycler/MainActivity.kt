package uz.itschool.sortrecycler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.itschool.sortrecycler.ui.theme.SortRecyclerTheme

@Composable
fun TaskList(tasks: List<Task>, sortOrder: MutableState<Boolean>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Task List", style = MaterialTheme.typography.headlineMedium)
            IconButton(onClick = { sortOrder.value = !sortOrder.value })
            {
                Icon(
                    imageVector = if (sortOrder.value) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward,
                    contentDescription = "Sort"
                )
            }
        }

        LazyColumn {
            items(if (sortOrder.value) tasks.sortedBy { it.priority } else tasks) { task ->
                TaskItem(task = task)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = task.title, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Priority: ${task.priority}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskList() {
    val tasks = listOf(
        Task(1, "Task 1", 2),
        Task(2, "Task 2", 1),
        Task(3, "Task 3", 3),
    )
    val sortOrder = remember { mutableStateOf(false) }

    SortRecyclerTheme {
        TaskList(tasks = tasks, sortOrder = sortOrder)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tasks = remember { mutableStateListOf<Task>() }
            tasks.addAll(
                listOf(
                    Task(1, "Task 1", 2),
                    Task(2, "Task 2", 1),
                    Task(3, "Task 3", 3),
                )
            )
            val sortOrder = remember { mutableStateOf(false) }

            SortRecyclerTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskList(tasks = tasks, sortOrder = sortOrder)
                }
            }
        }
    }
}


