package net.wayv.ui.add


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import net.wayv.navigation.ROUTE_ADD_POST
import net.wayv.navigation.ROUTE_ADD_SPORTS
import net.wayv.navigation.ROUTE_SPORTS
import net.wayv.navigation.ROUTE_VIEW_POST
import java.util.UUID


@Composable
fun AddSportsScreen(navController: NavHostController) {

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        item {

            Column(

                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),

                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Text(text = "New Post", fontWeight = FontWeight.Bold)

                var photoUri: Uri? by remember { mutableStateOf(null) }
                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    photoUri = uri
                }

                var eventName by rememberSaveable {
                    mutableStateOf("")
                }

                var eventLocation by rememberSaveable {
                    mutableStateOf("")
                }

                var eventDate by rememberSaveable {
                    mutableStateOf("")
                }

                var eventTime by rememberSaveable {
                    mutableStateOf("")
                }


                var eventCategory by rememberSaveable {
                    mutableStateOf("")
                }

                var expanded by remember  { mutableStateOf(false) }

                val suggestions = listOf("Performing Arts", "Visual Arts", "Food & Drink", "Festivals & Fairs",
                    "Fashion", "Sports & Active Life", "Nightlife", "Lectures & Books", "Kids & Family", "Charities"
                )

                var textfieldSize by remember { mutableStateOf(Size.Zero) }

                val icon = if (expanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown


                var eventDescription by rememberSaveable {
                    mutableStateOf("")
                }



                OutlinedTextField(
                    value = eventName,
                    onValueChange = { eventName = it },
                    label = { Text(text = "Event Name") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = eventLocation,
                    onValueChange = { eventLocation = it },
                    label = { Text(text = "Location") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = eventDate,
                    onValueChange = { eventDate = it },
                    label = { Text(text = "Date") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = eventTime,
                    onValueChange = { eventTime = it },
                    label = { Text(text = "Time") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )


                OutlinedTextField(
                    value = eventCategory,
                    onValueChange = { eventCategory = it },
                    label = { Text(text = "Category") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        },
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { expanded = !expanded }
                        )
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            eventCategory = label
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }




                OutlinedTextField(
                    value = eventDescription,
                    onValueChange = { eventDescription = it },
                    label = { Text(text = "Description") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )






                OutlinedButton(
                    onClick = {
                        launcher.launch(
                            PickVisualMediaRequest(
                                //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                                //Or use .VideoOnly if you only want videos.
                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text( " Add Image")
                }


                if (photoUri != null) {
                    //Use Coil to display the selected image
                    val painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(data = photoUri)
                            .build()
                    )

                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(150.dp)
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray),
                        contentScale = ContentScale.Crop,

                        )
                }


                OutlinedButton(onClick = {
                    photoUri?.let { uploadImageToFirebaseStorage9(it, eventName, eventLocation, eventDate, eventTime, eventCategory, eventDescription) }

                },
                    colors = ButtonDefaults.outlinedButtonColors(Color(0xFFbb8fce))
                ) {

                    Text(text = "Post")


                }


                OutlinedButton(onClick = {

                    navController.navigate(ROUTE_SPORTS) {
                        popUpTo(ROUTE_ADD_SPORTS) { inclusive = true }
                    }

                },
                    colors = ButtonDefaults.outlinedButtonColors(Color(0xFFbb8fce))
                ) {

                    Text(text = "View Post ->")

                }




            }



        }
    }
}





fun uploadImageToFirebaseStorage9(imageUri: Uri, eventName: String, eventLocation: String, eventDate: String, eventTime: String, eventCategory: String, eventDescription: String) {
    val storageRef = FirebaseStorage.getInstance().reference
    val imageRef = storageRef.child("images/${UUID.randomUUID()}")

    val uploadTask = imageRef.putFile(imageUri)
    uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        imageRef.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
            saveToFirestore9(downloadUri.toString(), eventName, eventLocation, eventDate, eventTime, eventCategory, eventDescription)
        } else {


        }
    }
}

fun saveToFirestore9(imageUrl: String, eventName: String, eventLocation: String, eventDate: String, eventTime: String, eventCategory: String, eventDescription: String) {
    val db = Firebase.firestore
    val imageInfo = hashMapOf(
        "imageUrl" to imageUrl,
        "eventName" to eventName,
        "eventLocation" to eventLocation,
        "eventDate" to eventDate,
        "eventTime" to eventTime,
        "eventCategory" to eventCategory,
        "eventDescription" to eventDescription


    )




    db.collection("Sports")
        .add(imageInfo)
        .addOnSuccessListener {



        }
        .addOnFailureListener {
            // Handle error
        }
}







@Preview(showBackground = true)
@Composable
fun PreviewLight9() {
    AddSportsScreen(rememberNavController())
}




