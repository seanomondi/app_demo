package net.wayv.ui.categories

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import net.wayv.navigation.ROUTE_ADD_FASHION
import net.wayv.navigation.ROUTE_ADD_FESTIVALS
import net.wayv.navigation.ROUTE_ADD_PERFORMING_ARTS
import net.wayv.navigation.ROUTE_ADD_POST
import net.wayv.navigation.ROUTE_CHARITIES
import net.wayv.navigation.ROUTE_EXPLORE
import net.wayv.navigation.ROUTE_FASHION
import net.wayv.navigation.ROUTE_FESTIVALS
import net.wayv.navigation.ROUTE_PERFORMING_ARTS


data class Item7(

    val docId: String? = "",
    val imageUrl: String? = "",
    val eventName: String? = "",
    val eventLocation: String? = "",
    val eventDate: String? = "",
    val eventTime: String? = "",
    val eventCategory: String? = "",
    val eventDescription: String? = ""

)


class FirestoreViewModel7 : ViewModel() {

    private val firestore = Firebase.firestore
    private val itemsCollection = firestore.collection("Performing_Arts")

    private val _items = MutableLiveData<List<Item7>>()
    val items: LiveData<List<Item7>> = _items

    init {
        fetchItems()
    }

    fun fetchItems() {
        itemsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("FirestoreViewModel7", "Error fetching items", error)
                return@addSnapshotListener
            }

            val itemList = mutableListOf<Item7>()
            snapshot?.documents?.forEach { document ->
                val item = document.toObject(Item7::class.java)?.copy(docId = document.id)
                item?.let {
                    itemList.add(it)
                }
            }
            _items.value = itemList
        }
    }
}


@Composable
fun ItemList7(items: List<Item7>) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))


        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .background(Color.White)
        ) {

            items.forEach { item ->
                item {
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            SubcomposeAsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(item.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                loading = {
                                    CircularProgressIndicator()
                                },
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10))
                                    .fillMaxWidth()
                                    .height(250.dp)
                            )

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                item.eventName?.let { Text(text = it, fontWeight = FontWeight.Bold) }
                                item.eventLocation?.let { Text(text = it, fontWeight = FontWeight.Bold) }
                                item.eventDate?.let { Text(text = it, fontWeight = FontWeight.Bold) }
                                item.eventTime?.let { Text(text = it, fontWeight = FontWeight.Bold) }
                                item.eventCategory?.let { Text(text = it) }
                                item.eventDescription?.let { Text(text = it) }
                            }

                            Spacer(modifier = Modifier.height(80.dp))

                        }


                    }



                }
            }


        }


    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformingArtsScreen(navController: NavHostController, viewModel: FirestoreViewModel7) {
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
                    Text(text = "Performing Arts")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_EXPLORE){
                            popUpTo(ROUTE_PERFORMING_ARTS){ inclusive = true }
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_ADD_PERFORMING_ARTS){
                            popUpTo(ROUTE_PERFORMING_ARTS){ inclusive = true }
                        }
                    }) {
                        Icon(Icons.Filled.AddCircle, "")
                    }
                },
                scrollBehavior = scrollBehavior,
            )



        }, content = {
            ItemList7(items)



        }

    )

}


