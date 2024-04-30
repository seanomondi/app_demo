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
                                AsyncImage(model = "https://images.pond5.com/empty-stage-fashion-show-runway-illustration-252061603_iconl_nowm.jpeg",
                                    contentDescription = null,
                                    Modifier.size(100.dp)
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
                                AsyncImage(model = "https://img.freepik.com/premium-vector/volunteering-volunteer-organization-collects-humanitarian-aid-volunteers-are-holding-letters_491047-317.jpg",
                                    contentDescription = null,
                                    Modifier.size(100.dp)
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
                                AsyncImage(model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTy6KfQNes3oveKHJ2iUGTsnL3dArHu1gpdDwoUi0wrF6JFbCrAF8HOyvxTO5spiZSaSo0&usqp=CAU",
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
                                AsyncImage(model = "https://img.freepik.com/premium-vector/artist-painting-picture-canvas-easel-painter-with-paint-palette-brush-drawing-creative-woman-apron-creates-artwork-creation-process-flat-vector-illustration-isolated-white-background_198278-13925.jpg",
                                    contentDescription = null,
                                    Modifier.size(100.dp)
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
                                AsyncImage(model = "https://t4.ftcdn.net/jpg/03/60/58/03/360_F_360580371_4FTntuz62CAG258ZYABPwlaGetlaHrFC.jpg",
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
