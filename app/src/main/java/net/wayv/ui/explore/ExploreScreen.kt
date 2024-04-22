package net.wayv.ui.explore


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
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
import net.wayv.navigation.ROUTE_PROFILE
import wayv.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(navController: NavHostController) {
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
                    Text(text = "Explore")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_PROFILE){
                            popUpTo(ROUTE_EXPLORE){ inclusive = true }
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
                    .padding(10.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                item {
                    Spacer(modifier = Modifier.height(100.dp))

                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Visual Arts")
                        }

                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Performing Arts")
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Lectures & Books")
                        }

                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Fashion")
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Food & Drink")
                        }

                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Festivals & Fairs")
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Charities")
                        }

                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Sports & Active Life")
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Nightlife")
                        }

                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Kids & Family")
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
                            popUpTo(ROUTE_EXPLORE) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_EXPLORE) {
                            popUpTo(ROUTE_EXPLORE) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_BOOKMARKS) {
                            popUpTo(ROUTE_EXPLORE) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_PROFILE) {
                            popUpTo(ROUTE_EXPLORE) { inclusive = true }
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
fun PreviewLight() {
    ExploreScreen(rememberNavController())
}
