package net.wayv.ui.profile


import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import net.wayv.navigation.ROUTE_ADD_POST
import net.wayv.navigation.ROUTE_PROFILE
import java.util.UUID


@Composable
fun AddPostScreen(navController: NavHostController) {

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

                Text(text = "New Post")

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

                var eventType by rememberSaveable {
                    mutableStateOf("")
                }


                var eventCategory by rememberSaveable {
                    mutableStateOf("")
                }

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
                    value = eventType,
                    onValueChange = { eventType = it },
                    label = { Text(text = "Type") },
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
                )

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
                    }
                ) {
                    Text( "Image")
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
                    photoUri?.let { uploadImageToFirebaseStorage(it, eventName, eventLocation, eventDate, eventTime, eventType, eventCategory, eventDescription) }

                }) {

                    Text(text = "Post")


                }


                OutlinedButton(onClick = {

                    navController.navigate(ROUTE_PROFILE) {
                        popUpTo(ROUTE_ADD_POST) { inclusive = true }
                    }

                }) {

                    Text(text = "View Post")

                }











            }



        }
    }
}





fun uploadImageToFirebaseStorage(imageUri: Uri, eventName: String, eventLocation: String, eventDate: String, eventTime: String, eventType: String, eventCategory: String, eventDescription: String) {
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
            saveToFirestore(downloadUri.toString(), eventName, eventLocation, eventDate, eventTime, eventType, eventCategory, eventDescription)
        } else {


        }
    }
}

fun saveToFirestore(imageUrl: String, eventName: String, eventLocation: String, eventDate: String, eventTime: String, eventType: String, eventCategory: String, eventDescription: String) {
    val db = Firebase.firestore
    val imageInfo = hashMapOf(
        "imageUrl" to imageUrl,
        "eventName" to eventName,
        "eventLocation" to eventLocation,
        "eventDate" to eventDate,
        "eventTime" to eventTime,
        "eventType" to eventType,
        "eventCategory" to eventCategory,
        "eventDescription" to eventDescription


    )




    db.collection("Events")
        .add(imageInfo)
        .addOnSuccessListener {



        }
        .addOnFailureListener {
            // Handle error
        }
}







@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    AddPostScreen(rememberNavController())
}




