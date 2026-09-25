package com.secreto.tvshortcuts.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object ShortcutIcons {

    val PlayStore = icon("PlayStore") {
        moveTo(7f, 4f)
        lineTo(20f, 12f)
        lineTo(7f, 20f)
        close()
    }

    val Settings = icon("Settings") {
        // Simple gear
        moveTo(10f, 3f)
        lineTo(14f, 3f)
        lineTo(14.5f, 6f)
        lineTo(17f, 7f)
        lineTo(19f, 5f)
        lineTo(21f, 7f)
        lineTo(19f, 9f)
        lineTo(20f, 12f)
        lineTo(23f, 12f)
        lineTo(23f, 15f)
        lineTo(20f, 15f)
        lineTo(19f, 18f)
        lineTo(21f, 20f)
        lineTo(19f, 22f)
        lineTo(17f, 20f)
        lineTo(14.5f, 21f)
        lineTo(14f, 24f)
        lineTo(10f, 24f)
        lineTo(9.5f, 21f)
        lineTo(7f, 20f)
        lineTo(5f, 22f)
        lineTo(3f, 20f)
        lineTo(5f, 18f)
        lineTo(4f, 15f)
        lineTo(1f, 15f)
        lineTo(1f, 12f)
        lineTo(4f, 12f)
        lineTo(5f, 9f)
        lineTo(3f, 7f)
        lineTo(5f, 5f)
        lineTo(7f, 7f)
        lineTo(9.5f, 6f)
        close()

        moveTo(10f, 10f)
        lineTo(14f, 10f)
        lineTo(16f, 12f)
        lineTo(16f, 16f)
        lineTo(14f, 18f)
        lineTo(10f, 18f)
        lineTo(8f, 16f)
        lineTo(8f, 12f)
        close()
    }

    val Wifi = icon("Wifi") {
        moveTo(2f, 9f)
        lineTo(4f, 7f)
        lineTo(7f, 5f)
        lineTo(12f, 4f)
        lineTo(17f, 5f)
        lineTo(20f, 7f)
        lineTo(22f, 9f)
        lineTo(20f, 11f)
        lineTo(17f, 9f)
        lineTo(12f, 8f)
        lineTo(7f, 9f)
        lineTo(4f, 11f)
        close()

        moveTo(7f, 14f)
        lineTo(9f, 12f)
        lineTo(12f, 11f)
        lineTo(15f, 12f)
        lineTo(17f, 14f)
        lineTo(15f, 16f)
        lineTo(12f, 15f)
        lineTo(9f, 16f)
        close()

        moveTo(10f, 19f)
        lineTo(12f, 17f)
        lineTo(14f, 19f)
        lineTo(12f, 21f)
        close()
    }

    val Bluetooth = icon("Bluetooth") {
        moveTo(11f, 2f)
        lineTo(18f, 8f)
        lineTo(14f, 12f)
        lineTo(18f, 16f)
        lineTo(11f, 22f)
        lineTo(11f, 14.5f)
        lineTo(6f, 19f)
        lineTo(4.5f, 17f)
        lineTo(10f, 12f)
        lineTo(4.5f, 7f)
        lineTo(6f, 5f)
        lineTo(11f, 9.5f)
        close()

        moveTo(13f, 6f)
        lineTo(13f, 10f)
        lineTo(15.5f, 8f)
        close()

        moveTo(13f, 14f)
        lineTo(13f, 18f)
        lineTo(15.5f, 16f)
        close()
    }

    val Apps = icon("Apps") {
        square(3f, 3f, 9f, 9f)
        square(15f, 3f, 21f, 9f)
        square(3f, 15f, 9f, 21f)
        square(15f, 15f, 21f, 21f)
    }

    val Storage = icon("Storage") {
        square(3f, 4f, 21f, 20f)

        moveTo(6f, 7f)
        lineTo(18f, 7f)
        lineTo(18f, 9f)
        lineTo(6f, 9f)
        close()

        moveTo(6f, 11f)
        lineTo(18f, 11f)
        lineTo(18f, 13f)
        lineTo(6f, 13f)
        close()

        moveTo(6f, 15f)
        lineTo(15f, 15f)
        lineTo(15f, 17f)
        lineTo(6f, 17f)
        close()
    }

    val Display = icon("Display") {
        moveTo(2f, 4f)
        lineTo(22f, 4f)
        lineTo(22f, 17f)
        lineTo(14f, 17f)
        lineTo(14f, 20f)
        lineTo(18f, 20f)
        lineTo(18f, 22f)
        lineTo(6f, 22f)
        lineTo(6f, 20f)
        lineTo(10f, 20f)
        lineTo(10f, 17f)
        lineTo(2f, 17f)
        close()

        moveTo(5f, 7f)
        lineTo(19f, 7f)
        lineTo(19f, 14f)
        lineTo(5f, 14f)
        close()
    }

    val Sound = icon("Sound") {
        moveTo(3f, 9f)
        lineTo(7f, 9f)
        lineTo(12f, 4f)
        lineTo(12f, 20f)
        lineTo(7f, 15f)
        lineTo(3f, 15f)
        close()

        moveTo(15f, 8f)
        lineTo(17f, 9f)
        lineTo(18f, 12f)
        lineTo(17f, 15f)
        lineTo(15f, 16f)
        lineTo(14f, 14f)
        lineTo(15.5f, 13f)
        lineTo(15.5f, 11f)
        lineTo(14f, 10f)
        close()
    }

    val Accessibility = icon("Accessibility") {
        // Head
        square(10f, 2f, 14f, 6f)

        // Body / arms
        moveTo(4f, 8f)
        lineTo(20f, 8f)
        lineTo(20f, 11f)
        lineTo(14f, 11f)
        lineTo(14f, 14f)
        lineTo(18f, 21f)
        lineTo(15f, 22f)
        lineTo(12f, 17f)
        lineTo(9f, 22f)
        lineTo(6f, 21f)
        lineTo(10f, 14f)
        lineTo(10f, 11f)
        lineTo(4f, 11f)
        close()
    }

    val Developer = icon("Developer") {
        moveTo(8f, 5f)
        lineTo(2f, 12f)
        lineTo(8f, 19f)
        lineTo(10f, 17f)
        lineTo(6f, 12f)
        lineTo(10f, 7f)
        close()

        moveTo(16f, 5f)
        lineTo(14f, 7f)
        lineTo(18f, 12f)
        lineTo(14f, 17f)
        lineTo(16f, 19f)
        lineTo(22f, 12f)
        close()

        moveTo(13f, 3f)
        lineTo(10f, 21f)
        lineTo(12f, 21f)
        lineTo(15f, 3f)
        close()
    }

    val Keyboard = icon("Keyboard") {
        square(2f, 5f, 22f, 19f)

        square(4f, 7f, 6f, 9f)
        square(8f, 7f, 10f, 9f)
        square(12f, 7f, 14f, 9f)
        square(16f, 7f, 20f, 9f)

        square(4f, 11f, 7f, 13f)
        square(9f, 11f, 12f, 13f)
        square(14f, 11f, 17f, 13f)

        moveTo(5f, 15f)
        lineTo(19f, 15f)
        lineTo(19f, 17f)
        lineTo(5f, 17f)
        close()
    }

    val Language = icon("Language") {
        // Stylized A
        moveTo(11f, 3f)
        lineTo(14f, 3f)
        lineTo(21f, 21f)
        lineTo(17.5f, 21f)
        lineTo(15.5f, 16f)
        lineTo(9.5f, 16f)
        lineTo(7.5f, 21f)
        lineTo(4f, 21f)
        close()

        moveTo(10.5f, 13f)
        lineTo(14.5f, 13f)
        lineTo(12.5f, 7f)
        close()
    }

    val DateTime = icon("DateTime") {
        // Simple clock/octagonal outline
        moveTo(8f, 3f)
        lineTo(16f, 3f)
        lineTo(21f, 8f)
        lineTo(21f, 16f)
        lineTo(16f, 21f)
        lineTo(8f, 21f)
        lineTo(3f, 16f)
        lineTo(3f, 8f)
        close()

        moveTo(11f, 6f)
        lineTo(13f, 6f)
        lineTo(13f, 11f)
        lineTo(17f, 13f)
        lineTo(16f, 15f)
        lineTo(11f, 12.5f)
        close()
    }

    val About = icon("About") {
        // Information symbol
        moveTo(9f, 2f)
        lineTo(15f, 2f)
        lineTo(20f, 7f)
        lineTo(20f, 17f)
        lineTo(15f, 22f)
        lineTo(9f, 22f)
        lineTo(4f, 17f)
        lineTo(4f, 7f)
        close()

        moveTo(11f, 10f)
        lineTo(13f, 10f)
        lineTo(13f, 18f)
        lineTo(11f, 18f)
        close()

        moveTo(11f, 6f)
        lineTo(13f, 6f)
        lineTo(13f, 8f)
        lineTo(11f, 8f)
        close()
    }

    private fun icon(
        name: String,
        block: androidx.compose.ui.graphics.vector.PathBuilder.() -> Unit
    ): ImageVector {
        return ImageVector.Builder(
            name = name,
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.White),
                pathBuilder = block
            )
        }.build()
    }

    private fun androidx.compose.ui.graphics.vector.PathBuilder.square(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float
    ) {
        moveTo(left, top)
        lineTo(right, top)
        lineTo(right, bottom)
        lineTo(left, bottom)
        close()
    }
}