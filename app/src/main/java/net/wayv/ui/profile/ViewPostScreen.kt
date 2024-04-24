package net.wayv.ui.profile


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import androidx.lifecycle.ViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import net.wayv.navigation.ROUTE_HOME
import net.wayv.navigation.ROUTE_VIEW_POST


data class Item(

    val imageUrl: String? = "",
    val eventName: String? = "",
    val eventLocation: String? = "",
    val eventDate: String? = "",
    val eventTime: String? = "",
    val eventType: String? = "",
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
                val item = document.toObject(Item::class.java)?.copy(eventType = document.id)
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
            .background(Color.White)
    ) {

        Text(text = "Posts")


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
        ) {

            items.forEach { item ->
                item {
                    Column(
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
                                .size(150.dp)
                        )

                        item.eventName?.let { Text(text = it) }
                        item.eventLocation?.let { Text(text = it) }
                        item.eventDate?.let { Text(text = it) }
                        item.eventTime?.let { Text(text = it) }
                        item.eventType?.let { Text(text = it) }
                        item.eventCategory?.let { Text(text = it) }
                        item.eventDescription?.let { Text(text = it) }

                    }

                }
            }


        }
    }
}


@Composable
fun ViewPostScreen(navController: NavHostController, viewModel: FirestoreViewModel) {
    val items by viewModel.items.observeAsState(initial = emptyList())

    // Fetch items when the composable is first created
    LaunchedEffect(viewModel, key2 = true) {
        viewModel.fetchItems()
    }


    Column(
        modifier = Modifier
            .background(Color.White)
    ) {

        Text(
            modifier = Modifier
                .clickable {
                    navController.navigate(ROUTE_HOME) {
                        popUpTo(ROUTE_VIEW_POST) { inclusive = true }
                    }
                },
            text = "go home",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )



        ItemList(items)

    }


}