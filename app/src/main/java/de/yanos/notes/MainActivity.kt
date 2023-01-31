package de.yanos.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.yanos.notes.ui.theme.AppTheme
import de.yanos.notes.util.MainRoute
import de.yanos.notes.util.NavigationChange
import de.yanos.notes.util.navigateSingleTopTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                NoteNavController(extras = intent.extras)
            }
        }
    }
}

@Composable
fun NoteNavController(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    extras: Bundle?
) {

    val changeCallback = { change: NavigationChange ->
        scope.launch {
            navController.navigateSingleTopTo(change.route.path)
        }
    }
    NoteNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainRoute.pathWithPlaceholder,
        callback = changeCallback
    )
}

@Composable
fun NoteNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    callback: (NavigationChange) -> Job
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = MainRoute.pathWithPlaceholder,
            arguments = MainRoute.args,
            content = {
                MainView(vm = hiltViewModel<>)
            }
        )
    }
}

@Composable
fun MainView() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
    }
}