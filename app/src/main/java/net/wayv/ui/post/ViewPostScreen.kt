package net.wayv.ui.post


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import net.wayv.navigation.ROUTE_ADD_POST
import net.wayv.navigation.ROUTE_EXPLORE
import net.wayv.navigation.ROUTE_HOME
import net.wayv.navigation.ROUTE_VIEW_POST
import wayv.R


data class Item(

    val imageUrl: String? = "",
    val eventName: String? = "",
    val eventLocation: String? = "",
    val eventDate: String? = "",
    val eventTime: String? = "",
    val eventCategory: String? = "",
    val eventDescription: String? = ""

)


class FirestoreViewModel : ViewModel() {

    private val firestore = Firebase.firestore
    private val itemsCollection = firestore.collection("Events")

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        fetchItems()
    }

    fun fetchItems() {
        itemsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("FirestoreViewModel", "Error fetching items", error)
                return@addSnapshotListener
            }

            val itemList = mutableListOf<Item>()
            snapshot?.documents?.forEach { document ->
                val item = document.toObject(Item::class.java)?.copy(eventName = document.id)
                item?.let {
                    itemList.add(it)
                }
            }
            _items.value = itemList
        }
    }
}


@Composable
fun ItemList(items: List<Item>) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(text = "ALL EVENTS", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)


        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {

            items.forEach { item ->
                item {
                    Card(
                        modifier = Modifier
                            .background(Color.White)
                    ) {

                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.imageUrl)
                                .crossfade(true)
                                .build(),
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentDescription = item.eventName,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10))
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        Column {
                            item.eventName?.let { Text(text = it) }
                            item.eventLocation?.let { Text(text = it) }
                            item.eventDate?.let { Text(text = it) }
                            item.eventTime?.let { Text(text = it) }
                            item.eventCategory?.let { Text(text = it) }
                            item.eventDescription?.let { Text(text = it) }
                        }


                    }

                }
            }


        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewPostScreen(navController: NavHostController, viewModel: FirestoreViewModel) {
    val items by viewModel.items.observeAsState(initial = emptyList())

    // Fetch items when the composable is first created
    LaunchedEffect(viewModel, key2 = true) {
        viewModel.fetchItems()
    }

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
                    Text(text = "Posts")
                },
                navigationIcon = {
                    Icon(painter = painterResource(id = R.drawable.logo), contentDescription = "")

                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_ADD_POST){
                            popUpTo(ROUTE_VIEW_POST){ inclusive = true }
                        }
                    }) {
                        Icon(Icons.Filled.AddCircle, "")
                    }
                },
                scrollBehavior = scrollBehavior,
            )



        },
        content = {

            ItemList(items)

        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Spacer(modifier = Modifier.width(55.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_VIEW_POST) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(45.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_EXPLORE) {
                            popUpTo(ROUTE_VIEW_POST) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(45.dp))

                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEW_POST) {
                            popUpTo(ROUTE_VIEW_POST) { inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                    }

                    Spacer(modifier = Modifier.width(45.dp))

//                    IconButton(onClick = {
//                        navController.navigate(ROUTE_VIEW_POST) {
//                            popUpTo(ROUTE_HOME) { inclusive = true }
//                        }
//                    }) {
//                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
//                    }
//
//                    Spacer(modifier = Modifier.width(30.dp))
                }
            )


        }
    )


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        Text(
//            modifier = Modifier
//                .clickable {
//                    navController.navigate(ROUTE_HOME) {
//                        popUpTo(ROUTE_VIEW_POST) { inclusive = true }
//                    }
//                },
//            text = "go home",
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//
//
//
//        ItemList(items)
//
//
//
//    }


}