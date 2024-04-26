package net.wayv.ui.explore


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.wayv.navigation.ROUTE_ADD_POST
import net.wayv.navigation.ROUTE_CHARITIES
import net.wayv.navigation.ROUTE_EXPLORE
import net.wayv.navigation.ROUTE_FASHION
import net.wayv.navigation.ROUTE_FESTIVALS
import net.wayv.navigation.ROUTE_FOOD
import net.wayv.navigation.ROUTE_HOME
import net.wayv.navigation.ROUTE_KIDS
import net.wayv.navigation.ROUTE_LECTURES
import net.wayv.navigation.ROUTE_NIGHTLIFE
import net.wayv.navigation.ROUTE_PERFORMING_ARTS
import net.wayv.navigation.ROUTE_SPORTS
import net.wayv.navigation.ROUTE_VIEW_POST
import net.wayv.navigation.ROUTE_VISUAL_ARTS
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
                    Icon(painter = painterResource(id = R.drawable.logo), contentDescription = "")

                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_ADD_POST){
                            popUpTo(ROUTE_EXPLORE){ inclusive = true }
                        }
                    }) {
                        Icon(Icons.Filled.AddCircle, "")
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
                    Spacer(modifier = Modifier.height(80.dp))

                    var searchText by remember {
                        mutableStateOf(TextFieldValue())
                    }
                        TextField(value = searchText, onValueChange = { searchText = it },
                            placeholder = { Text(text = "Search")},
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "")
                            }
                        )

                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_PERFORMING_ARTS) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Performing Arts")
                        }

                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_VISUAL_ARTS) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Visual Arts")
                        }
                    }

                    Row(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ROUTE_FOOD) {
                                    popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                }
                            }
                            .padding(15.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Food & Drink")
                        }

                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_FESTIVALS) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
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
                                .clickable {
                                    navController.navigate(ROUTE_FASHION) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Fashion")
                        }

                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_SPORTS) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
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
                                .clickable {
                                    navController.navigate(ROUTE_NIGHTLIFE) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Nightlife")
                        }

                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_LECTURES) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Lectures & Books")
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
                                .clickable {
                                    navController.navigate(ROUTE_KIDS) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Kids & Family")
                        }

                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_CHARITIES) {
                                        popUpTo(ROUTE_EXPLORE) { inclusive = true }
                                    }
                                }
                                .width(125.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color(0xFFbb8fce))
                        ) {
                            Image(imageVector = Icons.Default.Info, contentDescription = "")
                            Text(text = "   Charities")
                        }
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }
            }


        }, bottomBar = {
            BottomAppBar(
                actions = {
                    Spacer(modifier = Modifier.width(55.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_EXPLORE) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(45.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_EXPLORE) {
                            popUpTo(ROUTE_EXPLORE) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(45.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEW_POST) {
                            popUpTo(ROUTE_EXPLORE) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(45.dp))

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
