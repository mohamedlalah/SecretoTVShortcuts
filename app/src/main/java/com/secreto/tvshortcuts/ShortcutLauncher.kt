package com.secreto.tvshortcuts

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings

enum class ShortcutType {
    PLAY_STORE,
    SETTINGS,
    WIFI,
    BLUETOOTH,
    APPS,
    STORAGE,
    DISPLAY,
    SOUND,
    ACCESSIBILITY,
    DEVELOPER,
    KEYBOARD,
    LANGUAGE,
    DATE_TIME,
    ABOUT
}

class ShortcutLauncher(
    private val context: Context
) {

    fun launch(type: ShortcutType) {

        when (type) {

            ShortcutType.PLAY_STORE ->
                openPlayStore()

            ShortcutType.SETTINGS ->
                launchSafely(Intent(Settings.ACTION_SETTINGS))

            ShortcutType.WIFI ->
                launchSafely(Intent(Settings.ACTION_WIFI_SETTINGS))

            ShortcutType.BLUETOOTH ->
                openBluetoothAfterPermission()

            ShortcutType.APPS ->
                launchSafely(Intent(Settings.ACTION_APPLICATION_SETTINGS))

            ShortcutType.STORAGE ->
                launchSafely(Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS))

            ShortcutType.DISPLAY ->
                launchSafely(Intent(Settings.ACTION_DISPLAY_SETTINGS))

            ShortcutType.SOUND ->
                launchSafely(Intent(Settings.ACTION_SOUND_SETTINGS))

            ShortcutType.ACCESSIBILITY ->
                openAccessibility()

            ShortcutType.DEVELOPER ->
                launchSafely(
                    Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
                )

            ShortcutType.KEYBOARD ->
                launchSafely(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))

            ShortcutType.LANGUAGE ->
                launchSafely(Intent(Settings.ACTION_LOCALE_SETTINGS))

            ShortcutType.DATE_TIME ->
                openDateTime()

            ShortcutType.ABOUT ->
                launchSafely(Intent(Settings.ACTION_DEVICE_INFO_SETTINGS))
        }
    }

    /*
     * Google Play Store
     */

    private fun openPlayStore() {

        try {

            val intent =
                context.packageManager
                    .getLaunchIntentForPackage(
                        "com.android.vending"
                    )

            if (intent != null) {

                intent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                )

                context.startActivity(intent)

                return
            }

        } catch (_: Exception) {
        }

        launchSafely(
            Intent(Settings.ACTION_SETTINGS)
        )
    }

    /*
     * Bluetooth
     *
     * Called after Bluetooth permission has already
     * been handled by MainActivity.
     */

    fun openBluetoothAfterPermission() {

        /*
         * 1. Google TV / Android TV native
         *    Add Accessory screen.
         *
         * Confirmed:
         *
         * Sabrina:
         * com.android.tv.settings
         * .accessories.AddAccessoryActivity
         *
         * YOU-BOX:
         * CONNECT_INPUT is also supported.
         */

        try {

            val connectInputIntent =
                Intent(
                    "com.google.android.intent.action.CONNECT_INPUT"
                ).apply {

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            val activityInfo =
                context.packageManager.resolveActivity(
                    connectInputIntent,
                    0
                )

            if (activityInfo != null) {

                context.startActivity(
                    connectInputIntent
                )

                return
            }

        } catch (_: Exception) {
        }

        /*
         * 2. Classic Android TV
         *
         * Confirmed working on YOU-BOX.
         */

        try {

            val accessoriesIntent =
                Intent().apply {

                    component = ComponentName(
                        "com.android.tv.settings",
                        "com.android.tv.settings.accessories.AccessoriesActivity"
                    )

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            if (
                accessoriesIntent.resolveActivity(
                    context.packageManager
                ) != null
            ) {

                context.startActivity(
                    accessoriesIntent
                )

                return
            }

        } catch (_: Exception) {
        }

        /*
         * 3. Standard Bluetooth pairing
         */

        try {

            val pairingIntent =
                Intent(
                    "android.settings.BLUETOOTH_PAIRING_SETTINGS"
                ).apply {

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            if (
                pairingIntent.resolveActivity(
                    context.packageManager
                ) != null
            ) {

                context.startActivity(
                    pairingIntent
                )

                return
            }

        } catch (_: Exception) {
        }

        /*
         * 4. Standard Bluetooth settings
         */

        try {

            val bluetoothIntent =
                Intent(
                    Settings.ACTION_BLUETOOTH_SETTINGS
                ).apply {

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            if (
                bluetoothIntent.resolveActivity(
                    context.packageManager
                ) != null
            ) {

                context.startActivity(
                    bluetoothIntent
                )

                return
            }

        } catch (_: Exception) {
        }

        openBluetoothFallback()
    }

    /*
     * Bluetooth fallback
     */

    fun openBluetoothFallback() {

        launchSafely(
            Intent(Settings.ACTION_SETTINGS)
        )
    }

    /*
     * Date & Time
     *
     * Some Xiaomi Google TV devices register two activities
     * for ACTION_DATE_SETTINGS:
     *
     * 1. Power Off Timer
     * 2. Date & Time
     *
     * This causes Android to display a chooser and may send
     * the user to the Power Off Timer.
     *
     * The real Android TV Date & Time activity is:
     *
     * com.android.tv.settings.system.DateTimeActivity
     *
     * We try it directly first. If the device does not
     * provide it, we fall back to ACTION_DATE_SETTINGS.
     */

    private fun openDateTime() {

        /*
         * 1. Direct Android TV / Google TV Date & Time.
         *
         * Confirmed working on Xiaomi Google TV.
         */

        try {

            val directDateTimeIntent =
                Intent().apply {

                    component = ComponentName(
                        "com.android.tv.settings",
                        "com.android.tv.settings.system.DateTimeActivity"
                    )

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            if (
                directDateTimeIntent.resolveActivity(
                    context.packageManager
                ) != null
            ) {

                context.startActivity(
                    directDateTimeIntent
                )

                return
            }

        } catch (_: Exception) {
        }

        /*
         * 2. Standard Android Date & Time.
         *
         * Used as fallback on devices that do not expose
         * DateTimeActivity directly.
         */

        launchSafely(
            Intent(Settings.ACTION_DATE_SETTINGS)
        )
    }

    /*
     * Accessibility
     */

    private fun openAccessibility() {

        /*
         * 1. Google TV direct Accessibility page.
         *
         * Confirmed working on Sabrina.
         */

        try {

            val googleTvAccessibility =
                Intent().apply {

                    component = ComponentName(
                        "com.android.tv.settings",
                        "com.android.tv.settings.oemlink.AccessibilitySettingsActivity"
                    )

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            if (
                googleTvAccessibility.resolveActivity(
                    context.packageManager
                ) != null
            ) {

                context.startActivity(
                    googleTvAccessibility
                )

                return
            }

        } catch (_: Exception) {
        }

        /*
         * 2. Classic Android TV.
         *
         * YOU-BOX stores Accessibility as an internal
         * Fragment, so MainSettings is used instead.
         *
         * This prevents:
         * "You don't have an app that can do this"
         */

        try {

            val tvSettings =
                Intent().apply {

                    component = ComponentName(
                        "com.android.tv.settings",
                        "com.android.tv.settings.MainSettings"
                    )

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            if (
                tvSettings.resolveActivity(
                    context.packageManager
                ) != null
            ) {

                context.startActivity(tvSettings)

                return
            }

        } catch (_: Exception) {
        }

        /*
         * 3. Standard Android Accessibility.
         */

        try {

            val accessibilityIntent =
                Intent(
                    Settings.ACTION_ACCESSIBILITY_SETTINGS
                ).apply {

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            if (
                accessibilityIntent.resolveActivity(
                    context.packageManager
                ) != null
            ) {

                context.startActivity(
                    accessibilityIntent
                )

                return
            }

        } catch (_: Exception) {
        }

        /*
         * 4. Universal fallback
         */

        launchSafely(
            Intent(Settings.ACTION_SETTINGS)
        )
    }

    /*
     * Universal safe launcher
     */

    private fun launchSafely(
        intent: Intent
    ) {

        try {

            intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )

            if (
                intent.resolveActivity(
                    context.packageManager
                ) != null
            ) {

                context.startActivity(intent)

                return
            }

        } catch (_: Exception) {
        }

        try {

            val fallback =
                Intent(
                    Settings.ACTION_SETTINGS
                ).apply {

                    addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                }

            context.startActivity(fallback)

        } catch (_: Exception) {

            // Never crash because of unsupported
            // Settings activities.
        }
    }
}