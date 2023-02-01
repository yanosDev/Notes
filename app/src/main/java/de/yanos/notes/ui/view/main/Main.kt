package de.yanos.notes.ui.view.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import de.yanos.notes.util.NavigationChange

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    vm: MainViewModel = hiltViewModel(),
    callback: (NavigationChange) -> Unit = {}
) {

}