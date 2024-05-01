package net.wayv.ui.home


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import net.wayv.navigation.ROUTE_ADD_POST
import net.wayv.navigation.ROUTE_CHARITIES
import net.wayv.navigation.ROUTE_EXPLORE
import net.wayv.navigation.ROUTE_FASHION
import net.wayv.navigation.ROUTE_FESTIVALS
import net.wayv.navigation.ROUTE_FOOD
import net.wayv.navigation.ROUTE_HOME
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
fun HomeScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Home")
                },
                navigationIcon = {
                    Icon(painter = painterResource(id = R.drawable.logo), contentDescription = "")
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_ADD_POST){
                            popUpTo(ROUTE_HOME){ inclusive = true }
                        }
                    }) {
                        Icon(Icons.Filled.AddCircle, "")
                    }
                },
                scrollBehavior = scrollBehavior,
            )



        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                item {
                    Spacer(modifier = Modifier.height(80.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(10.dp)
                    ){
                        Image(painter = painterResource(id = R.drawable.discover), contentDescription = "",
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Event Categories"
                        )

                        Text(text = "View all", modifier = Modifier.clickable {
                            navController.navigate(ROUTE_EXPLORE) {
                                popUpTo(ROUTE_HOME) { inclusive = true }
                            }
                        },
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Light
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                    ) {
                        item {
                            Card(onClick = {
                                navController.navigate(ROUTE_FASHION) {
                                    popUpTo(ROUTE_HOME) { inclusive = true }
                                }
                            },
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {
                                AsyncImage(model = "https://media.istockphoto.com/id/1168401897/vector/fashion-models-sketch-hand-drawn-silhouette-pop-art.jpg?s=612x612&w=0&k=20&c=T7arFXpqicjeqfajoaSQQJqoi5MWjMTHg-uijZm3VnQ=",
                                    contentDescription = null,
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(100.dp)
                                )

                                Text(text = "   Fashion")
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Card(onClick = {
                                navController.navigate(ROUTE_FOOD) {
                                    popUpTo(ROUTE_HOME) { inclusive = true }
                                }
                            },
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {
                                AsyncImage(model = "https://clipart-library.com/8300/2368/burger-with-french-fries-soda-icon-illustration-fast-food-icon-concept-isolated-flat-cartoon-style_138676-1340.jpg",
                                    contentDescription = null,
                                    Modifier.size(100.dp)
                                )

                                Text(text = "   Food & Drink")
                            }

                            Spacer(modifier = Modifier.width(10.dp))


                            Card(onClick = {
                                navController.navigate(ROUTE_LECTURES) {
                                    popUpTo(ROUTE_HOME) { inclusive = true }
                                }
                            },
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {
                                AsyncImage(model = "https://media.istockphoto.com/id/1316740716/vector/read-book-education-concept-with-tiny-character-student-reading-open-textbook-for-studying.jpg?s=612x612&w=0&k=20&c=gsPCc7Yc3__AP0h-zAh5rnISDv1QI1vb02rFrD8FjOE=",
                                    contentDescription = null,
                                    Modifier.size(100.dp)
                                )

                                Text(text = "   Lectures & Books")
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Card(onClick = {
                                navController.navigate(ROUTE_CHARITIES) {
                                    popUpTo(ROUTE_HOME) { inclusive = true }
                                }
                            },
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {
                                AsyncImage(model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFwmlxg8z3_-Hpf0bCHnVxRwjF2Cq6JOqCA&s",
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(Color.White)
                                )

                                Text(text = "   Charities")
                            }
                        }

                    }



                    Spacer(modifier = Modifier.height(30.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Upcoming Events"
                        )

                        Text(text = "View all", modifier = Modifier.clickable {
                            navController.navigate(ROUTE_VIEW_POST) {
                                popUpTo(ROUTE_HOME) { inclusive = true }
                            }
                        },
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Light
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_PERFORMING_ARTS) {
                                        popUpTo(ROUTE_HOME) { inclusive = true }
                                    }
                                }
                                .fillMaxSize(),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Row{
                                AsyncImage(model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNhrJchXeWCVqv4jddKnzB2vvnsjH7OBTBaQ&s",
                                    contentDescription = null,
                                    Modifier.size(100.dp)
                                )

                                Text(text = "   Performing Arts")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_VISUAL_ARTS) {
                                        popUpTo(ROUTE_HOME) { inclusive = true }
                                    }
                                }
                                .fillMaxSize(),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Row{
                                AsyncImage(model = "data:image/jpeg;base64,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",
                                    contentDescription = null,
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(100.dp)
                                )

                                Text(text = "   Visual Arts")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_FESTIVALS) {
                                        popUpTo(ROUTE_HOME) { inclusive = true }
                                    }
                                }
                                .fillMaxSize(),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Row{
                                AsyncImage(model = "https://media.istockphoto.com/id/1300357920/vector/bazaar-trade-tents-cartoon-vector-illustrations-set-middle-east-marketplace-flat-color.jpg?s=612x612&w=0&k=20&c=qqyVse76nutaIasHY8ZvETnpsftwEJ6kSaqRA-ZYEUM=",
                                    contentDescription = null,
                                    Modifier.size(100.dp)
                                )

                                Text(text = "   Festivals & Fairs")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ROUTE_SPORTS) {
                                        popUpTo(ROUTE_HOME) { inclusive = true }
                                    }
                                }
                                .fillMaxSize(),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Row{
                                AsyncImage(model = "https://media.istockphoto.com/id/1204568602/vector/crowd-people-run-marathon-vector-illustration-in-color-abstract-effect-isolated.jpg?s=612x612&w=0&k=20&c=HqS98N8y62ow1gS85giX16qG9BuUlGVE7ABZMmoseVI=",
                                    contentDescription = null,
                                    Modifier.size(100.dp)
                                )
                                
                                Text(text = "   Sports & Active Life")
                            }
                        }
                    }




                    Spacer(modifier = Modifier.height(100.dp))
                }


            }


        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Spacer(modifier = Modifier.width(55.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_HOME) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(45.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_EXPLORE) {
                            popUpTo(ROUTE_HOME) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(45.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEW_POST) {
                            popUpTo(ROUTE_HOME) { inclusive = true }
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
    HomeScreen(rememberNavController())
}
