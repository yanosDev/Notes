package de.yanos.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import de.yanos.corelibrary.ui.theme.AppTheme
import de.yanos.notes.ui.view.main.MainView
import de.yanos.notes.util.MainRoute
import de.yanos.notes.util.NavigationChange
import de.yanos.notes.util.navigateSingleTopTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(this) { config ->

            }
        }
    }
}

@Composable
fun NoteNavController(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), scope: CoroutineScope = rememberCoroutineScope(), extras: Bundle?
) {
    val changeCallback = { change: NavigationChange ->
        scope.launch {
            navController.navigateSingleTopTo(change.route.path)
        }
        Unit
    }
    NoteNavHost(
        modifier = modifier, navController = navController, startDestination = MainRoute.pathWithPlaceholder, callback = changeCallback
    )
}

@Composable
fun NoteNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainRoute.pathWithPlaceholder,
    callback: (NavigationChange) -> Unit = {}
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {
        composable(route = MainRoute.pathWithPlaceholder, arguments = MainRoute.args, content = {
            MainView(
                modifier = modifier, vm = hiltViewModel(), callback = callback
            )
        })
    }
}