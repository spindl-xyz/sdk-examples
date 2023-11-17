package xyz.spindl.spindlsdkdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.lang3.RandomStringUtils
import xyz.spindl.sdk.Spindl
import xyz.spindl.spindlsdkdemo.ui.theme.SpindlSDKDemoTheme

// private val spindlSharedPreferencesName = "xyz.spindl.sdk.preferences"
//    private val spindlPreferenceApiKey = "ApiKey"
//    private val spindlPreferencePersistentId = "PersistentId"

private const val MAIN_ACTIVITY_TAG = "MainActivity"

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private lateinit var spindl: Spindl
    private lateinit var apiKey: String
    private val backgroundScope = CoroutineScope(Dispatchers.Default)
    private val hasIdentity: State<Boolean>
        get() = mutableStateOf(spindl.hasIdentity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiKey = getString(R.string.spindlApiKey)
        lifecycleScope.launch {
            spindl = Spindl(context = applicationContext, lifecycleOwner = this@MainActivity)
        }

        setContent {
            var eventName by remember { mutableStateOf("") }

            var walletAddress by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            //var hasIdentity by remember { mutableStateOf(false) }

            SpindlSDKDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(contentAlignment = Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Box {
                                Button(onClick = {
                                    Log.d(
                                        MAIN_ACTIVITY_TAG,
                                        "Clear Data onClick from ${Thread.currentThread().name}"
                                    )
                                    backgroundScope.launch {
                                        clearData()

                                    }
                                }) {
                                    Text("Reset Event Data")
                                }
                            }

                            Spacer(modifier = Modifier.size(16.dp))

                            Box {
                                Column(horizontalAlignment = Alignment.End) {
                                    OutlinedTextField(
                                        value = walletAddress,
                                        onValueChange = { walletAddress = it },
                                        label = { Text("Wallet Address") }
                                    )

                                    OutlinedTextField(
                                        value = email,
                                        onValueChange = { email = it },
                                        label = { Text("Customer User ID") }
                                    )

                                    Button(onClick = {
                                        Log.d(
                                            MAIN_ACTIVITY_TAG,
                                            "Identity onClick from ${Thread.currentThread().name}"
                                        )
                                        backgroundScope.launch {
//                                                Log.d(TAG, "Hello from coroutine on ${Thread.currentThread().name}")
                                            identify(apiKey = apiKey, walletAddress, email)
                                            walletAddress = ""
                                            email = ""
                                        }
                                    }
                                    ) {
                                        Text("Identify")
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.size(16.dp))

                            Box {

                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {


                                    OutlinedTextField(
                                        value = eventName,
                                        onValueChange = { eventName = it },
                                        label = { Text("Event Name") }
                                    )

                                    Button(
                                        onClick = {
//                                            Log.d(TAG, "Track onClick from ${Thread.currentThread().name}")
                                            backgroundScope.launch {
//                                                Log.d(TAG, "Hello from coroutine on ${Thread.currentThread().name}")
                                                trackNamedEvent(eventName)
                                                eventName = ""
                                            }

                                        }
                                    ) {
                                        Text("Track")
                                    }
                                }
                            }


                            Spacer(modifier = Modifier.size(16.dp))

                            Box {
                                Button(
                                    onClick = { backgroundScope.launch { trackRandomEvent() } }
                                ) {
                                    Text("Track Random Event")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun clearData() {
        //
        spindl.clearEvents() // May be removed
    }

    private suspend fun identify(apiKey: String, wallet: String, userId: String) {
        spindl.identify(apiKey = apiKey, walletAddress = wallet, customerUserId = userId)
    }

    private suspend fun trackNamedEvent(eventName: String) {
        val prop1Key = RandomStringUtils.randomAlphabetic(6)
        val prop1Value = RandomStringUtils.randomAlphanumeric(3, 9)
        val properties = JsonObject()
        properties.addProperty(prop1Key, prop1Value)

        spindl.track(eventName, properties)
    }

    private suspend fun trackRandomEvent() {
        val eventTitle = RandomStringUtils.randomAlphabetic(1, 12)
        val prop1Key = RandomStringUtils.randomAlphabetic(6)
        val prop1Value = RandomStringUtils.randomAlphanumeric(3, 9)
        val properties = JsonObject()
        properties.addProperty(prop1Key, prop1Value)

        spindl.track(eventTitle, properties)
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpindlSDKDemoTheme {
        Greeting("Android")
    }
}