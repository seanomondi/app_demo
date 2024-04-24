package net.wayv.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.wayv.navigation.ROUTE_BOOKMARKS
import net.wayv.navigation.ROUTE_EXPLORE
import net.wayv.navigation.ROUTE_HOME
import net.wayv.navigation.ROUTE_PERFORMING_ARTS
import net.wayv.navigation.ROUTE_PROFILE
import wayv.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformingArtsScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Performing Arts")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_PROFILE){
                            popUpTo(ROUTE_PERFORMING_ARTS){ inclusive = true }
                        }
                    }) {
                        Icon(Icons.Filled.Person, "")
                    }
                },
                scrollBehavior = scrollBehavior,
            )



        }, content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                item {
                    Spacer(modifier = Modifier.height(100.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize(),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "",
                                Modifier.size(150.dp)
                            )
                            Text(text = "   item 1")
                        }
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }
            }


        }, bottomBar = {
            BottomAppBar(
                actions = {
                    Spacer(modifier = Modifier.width(30.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_PERFORMING_ARTS) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_EXPLORE) {
                            popUpTo(ROUTE_PERFORMING_ARTS) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_BOOKMARKS) {
                            popUpTo(ROUTE_PERFORMING_ARTS) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_PROFILE) {
                            popUpTo(ROUTE_PERFORMING_ARTS) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(30.dp))
                }
            )


        }
    )

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    PerformingArtsScreen(rememberNavController())
}
