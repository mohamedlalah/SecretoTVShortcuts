package com.secreto.tvshortcuts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import com.secreto.tvshortcuts.ui.ShortcutsScreen
import com.secreto.tvshortcuts.ui.PrivacySupportScreen
import com.secreto.tvshortcuts.ui.theme.SecretoTVShortcutsTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private lateinit var shortcutLauncher: ShortcutLauncher

    private val bluetoothPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                shortcutLauncher.openBluetoothAfterPermission()
            } else {
                shortcutLauncher.openBluetoothFallback()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shortcutLauncher = ShortcutLauncher(this)

        setContent {

            SecretoTVShortcutsTheme {

                var showSplash by remember {
                    mutableStateOf(true)
                }

                var showPrivacySupport by rememberSaveable {
                    mutableStateOf(false)
                }

                if (showSplash) {

                    LaunchedEffect(Unit) {
                        delay(1500)
                        showSplash = false
                    }

                    Image(
                        painter = painterResource(
                            id = R.drawable.splash_screen
                        ),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                } else {

                    BackHandler(enabled = showPrivacySupport) {
                        showPrivacySupport = false
                    }

                    if (showPrivacySupport) {

                        PrivacySupportScreen(
                            onBack = {
                                showPrivacySupport = false
                            },
                            onPrivacyClick = {
                                openPrivacyPolicy()
                            }
                        )

                    } else {

                        ShortcutsScreen(
                            onShortcutClick = { type ->
                                if (type == ShortcutType.BLUETOOTH) {
                                    handleBluetoothShortcut()
                                } else {
                                    shortcutLauncher.launch(type)
                                }
                            },
                            onPrivacySupportClick = {
                                showPrivacySupport = true
                            }
                        )
                    }
                }
            }
        }
    }

    private fun handleBluetoothShortcut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val permission = Manifest.permission.BLUETOOTH_CONNECT

            if (
                ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                shortcutLauncher.openBluetoothAfterPermission()
            } else {
                bluetoothPermissionLauncher.launch(permission)
            }
        } else {
            shortcutLauncher.openBluetoothAfterPermission()
        }
    }

    private fun openPrivacyPolicy() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://secretotools.com/products/secreto-tv-shortcuts/privacy/")
        )
        runCatching {
            startActivity(intent)
        }
    }

}
