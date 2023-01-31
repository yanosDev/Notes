package de.yanos.notes.util

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController

interface NavigationChange {
    val route: Route
}

sealed interface Route {
    val path: String
    val pathWithPlaceholder: String
    val deeplink: String
    val args: List<NamedNavArgument>
    fun routeWithArgs(list: List<Pair<String, String?>> = listOf()): String = path
}

class OpenRoute(route: Route)

object MainRoute : Route {
    override val path: String get() = "main"
    override val pathWithPlaceholder: String get() = "main"
    override val deeplink: String get() = "yanos://main"
    override val args: List<NamedNavArgument> get() = listOf()
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        /*popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            this@navigateSingleTopTo.graph.nodes
            saveState = true
        }*/
        launchSingleTop = true
        restoreState = true
    }