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
                                AsyncImage(model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFwmlxg8z3_-Hpf0bCHnVxRwjF2Cq6JOqCA&s",
                                    contentDescription = null,
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(100.dp)
                                )

                                Text(text = "   Charities")
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
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(100.dp)
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
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(100.dp)
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
                                AsyncImage(model = "https://media.istockphoto.com/id/1168401897/vector/fashion-models-sketch-hand-drawn-silhouette-pop-art.jpg?s=612x612&w=0&k=20&c=T7arFXpqicjeqfajoaSQQJqoi5MWjMTHg-uijZm3VnQ=",
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(Color.White)
                                )

                                Text(text = "   Fashion")
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
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(100.dp)
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
                                AsyncImage(model = "https://static.vecteezy.com/system/resources/previews/004/311/270/non_2x/paint-class-tools-canvas-easel-seat-and-wood-frames-free-vector.jpg",
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
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(100.dp)
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
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(100.dp)
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
