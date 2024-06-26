package android.caged.jetmapsampleapp

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import android.caged.jetmapsampleapp.featur_typicode_users.domain.model.UserInfo
import android.caged.jetmapsampleapp.featur_typicode_users.presentation.UserInfoViewModel
import android.caged.jetmapsampleapp.feature_google_places.presentation.GooglePlacesInfoViewModel
import android.caged.jetmapsampleapp.ui.theme.JetmapSampleAppTheme
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.*
import com.google.maps.android.compose.Polyline
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

const val TAG = "MainActivityMap"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    getCurrentLocation(true)
                }
                permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    getCurrentLocation(false)
                }
                else -> {
                    Toast.makeText(applicationContext, "Location permission denied", Toast.LENGTH_SHORT).show()
                }
            }

        }
        setContent {
            var isMapLoaded by remember { mutableStateOf(false) }
            JetmapSampleAppTheme {
                locationPermissionRequest.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
                // A surface container using the 'background' color from the theme
                val usersInfoViewModel: UserInfoViewModel = hiltViewModel()
                val usersInfoState = usersInfoViewModel.usersInfoState.value
                val scaffoldState = rememberBottomSheetScaffoldState()

                val glaces: GooglePlacesInfoViewModel = hiltViewModel()
                val gPlaceInfoState = glaces.googlePlacesInfoState.value
                val searchText = remember { mutableStateOf("") }


                LaunchedEffect(key1 = true){
                    glaces.evenFlow.collectLatest { event ->
                        when(event){
                            is GooglePlacesInfoViewModel.UIEvent.ShowSnackBar ->{
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                    usersInfoViewModel.evenFlow.collectLatest { event ->
                        when(event){
                            is UserInfoViewModel.UIEvent.ShowSnackBar ->{
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }
                Scaffold() { innerPadding ->
                    println(innerPadding)
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                GoogleMapView(
                                    modifier = Modifier.weight(1f),
                                    onMapLoaded = {
                                        isMapLoaded = true
                                    },
                                    users = usersInfoState.usersInfo,
                                    googlePlacesInfoViewModel = glaces
                                )
                                OutlinedTextField(
                                    value = searchText.value,
                                    onValueChange = { searchText.value = it },
                                    label = { Text("Destination") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    singleLine = true,
                                    maxLines = 1,
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.LocationOn,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                            if (!isMapLoaded) {
                                AnimatedVisibility(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(MaterialTheme.colorScheme.background)
                                        .wrapContentSize(),
                                    visible = !isMapLoaded,
                                    enter = EnterTransition.None,
                                    exit = fadeOut()
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
    fun getCurrentLocation(isFineLocation: Boolean) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }



//        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//            if (location != null) {
////                Log.d(TAG, "Location: ${location.latitude}, ${location.longitude}")
//                val intent = Intent(this, MainActivity::class.java)
//                intent.putExtra("latitude", location.latitude)
//                intent.putExtra("longitude", location.longitude)
//                startActivity(intent)
//            } else {
//                Log.e(TAG, "Location is null")
//                Toast.makeText(applicationContext, "Failed to get location", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }.addOnFailureListener {
//            Log.e(TAG, "Failed to get location", it)
//            Toast.makeText(applicationContext, "Failed to get location", Toast.LENGTH_SHORT).show()
//        }

        if(isFineLocation){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        Log.d(TAG, "Location: ${location.latitude}, ${location.longitude}")
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("latitude", location.latitude)
                        intent.putExtra("longitude", location.longitude)
                        startActivity(intent)
                    } else {
                        Log.e(TAG, "Location is null")
                        Toast.makeText(applicationContext, "Failed to get location", Toast.LENGTH_SHORT)
                            .show()
                    }
                }.addOnFailureListener {
                    Log.e(TAG, "Failed to get location", it)
                    Toast.makeText(applicationContext, "Failed to get location", Toast.LENGTH_SHORT).show()
                }
            }else{
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            }
        }else{
            if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        Log.d(TAG, "Location: ${location.latitude}, ${location.longitude}")
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("latitude", location.latitude)
                        intent.putExtra("longitude", location.longitude)
                        startActivity(intent)
                    } else {
                        Log.e(TAG, "Location is null")
                        Toast.makeText(applicationContext, "Failed to get location", Toast.LENGTH_SHORT)
                            .show()
                    }
                }.addOnFailureListener {
                    Log.e(TAG, "Failed to get location", it)
                    Toast.makeText(applicationContext, "Failed to get location", Toast.LENGTH_SHORT).show()
                }
            }else{
                requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            }
        }
    }
}



@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GoogleMapView(modifier: Modifier, onMapLoaded: () -> Unit, users:  List<UserInfo>, googlePlacesInfoViewModel: GooglePlacesInfoViewModel) {
    val singapore = LatLng(1.35, 103.87)
    val singapore2 = LatLng(1.40, 103.77)
    // get latitude and longitude from intent
//    val intent =
//    val singapore = LatLng(intent.getDoubleExtra("latitude", 1.35), intent.getDoubleExtra("longitude", 103.87))
    var pos by remember {
        mutableStateOf(LatLng(singapore.latitude, singapore.longitude))
    }


    var poi by remember {
        mutableStateOf("")
    }
    val _makerList: MutableList<LatLng> =   mutableListOf<LatLng>()

    _makerList.add(singapore)
    _makerList.add(singapore2)

    var pos2 by remember {
        mutableStateOf(_makerList)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }

    var mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(compassEnabled = false)
        )
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = uiSettings,
        onMapLoaded = onMapLoaded,
        googleMapOptionsFactory = {
            GoogleMapOptions().camera(
                CameraPosition.fromLatLngZoom(
                    singapore,
                    11f
                )
            )
        },
        onMapClick = {
            Log.d(TAG, "Coordinate clicked: $it")
            pos2.add(it)
            pos = it
        },
        onPOIClick = {
            Log.d(TAG, "POI clicked: ${it.name}")
            poi = it.name
            Log.d(TAG, "POI clicked: ${it.latLng}")

            googlePlacesInfoViewModel.getDirection(
                origin = "${singapore.latitude}, ${singapore.longitude}",
                destination = "${it.latLng.latitude}, ${it.latLng.longitude}",
                key = MapKey.KEY
            )

//            val gPlaceInfoState = googlePlacesInfoViewModel.googlePlacesInfoState.value



        }
    ){
        // Drawing on the map is accomplished with a child-based API
        val markerClick: (Marker) -> Boolean = {
            Log.d(TAG, "${it.title} was clicked")
            false
        }
        pos2.forEach { posistion ->

            Marker(
                state = MarkerState(position = posistion),
                title = "Singapore ",
                snippet = "Marker in Singapore ${posistion.latitude}, ${posistion.longitude}",
                onClick = markerClick,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            )
        }

        Polyline(points = googlePlacesInfoViewModel.polyLinesPoints.value, onClick = {
            Log.d(TAG, "${it.points} was clicked")
        })
//        Marker(
//            position = singapore2,
//            title = "Singapore",
//            snippet = "Marker in Singapore"
//        )
//        Circle(
//            center = singapore,
//            fillColor = MaterialTheme.colors.secondary,
//            strokeColor = MaterialTheme.colors.secondaryVariant,
//            radius = 1000.0,
//        )

    }
//    Column(modifier = Modifier.padding(10.dp))
//    {
//        CustomNaveBar(poi=poi)
//        Spacer(modifier = Modifier.height(LocalConfiguration.current.screenWidthDp.dp))
//        LazyRow {
//            items(count = users.size) { user ->
//                UserInfoRow(user = users[user], onItemClicked = {} )
//            }
//        }
//    }



}
/**
 * Stateless composable that displays a full-width [UserInfo].
 *
 * @param UserInfo item to show
 * @param modifier modifier for this element
 */
@Composable
fun UserInfoRow(
    user: UserInfo,
    onItemClicked: (UserInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable { onItemClicked(user) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(500.dp)
            .width(LocalConfiguration.current.screenWidthDp.dp - 90.dp),
    ) {
        Column(modifier.padding(10.dp))  {
            Text(user.name)
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(user.email)
                Text(user.website)
            }
        }
    }
}
@Composable
fun CustomNaveBar(poi: String?){
    Row(modifier = Modifier.height(10.dp)) {
        Spacer(modifier = Modifier.height(10.dp))
    }
    Card(
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.padding(start = 4.dp)
            ){
                Icon(Icons.Filled.Menu,contentDescription = "menu",modifier = Modifier.fillMaxHeight())
            }
            Text(text = poi ?: "Search...", modifier = Modifier.padding(top = 15.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
            IconButton(
                onClick = {}
            ) {
                Image(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = " ",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Cyan, CircleShape)
                        .padding(4.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val users: List<UserInfo> = listOf<UserInfo>(
        UserInfo(name = "John Doe", email = "uer@g.net", phone = "123124", username = "User Name", website = "", id = 1),
        UserInfo(name = "John Doe", email = "uer@g.net", phone = "123124", username = "User Name", website = "", id = 1),
        UserInfo(name = "John Doe", email = "uer@g.net", phone = "123124", username = "User Name", website = "", id = 1)
    )
    JetmapSampleAppTheme {
        users.map { user ->
            UserInfoRow(user = user, onItemClicked = {} )
        }

    }
}