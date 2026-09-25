package com.secreto.tvshortcuts.ui

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.secreto.tvshortcuts.R
import com.secreto.tvshortcuts.ShortcutType


// ============================================================
// DATA MODEL
// ============================================================

private data class ShortcutUiItem(
    @param:StringRes val titleRes: Int,
    @param:StringRes val subtitleRes: Int,
    val symbol: String,
    val type: ShortcutType,
    val featured: Boolean = false,
    val iconRes: Int? = null
)


// ============================================================
// SHORTCUTS
// ============================================================

private val shortcutItems = listOf(

    // Play Store
    ShortcutUiItem(
        titleRes = R.string.shortcut_play_store,
        subtitleRes = R.string.shortcut_play_store_subtitle,
        symbol = "",
        type = ShortcutType.PLAY_STORE,
        featured = true,
        iconRes = R.drawable.ic_play_store
    ),

    // Settings
    ShortcutUiItem(
        titleRes = R.string.shortcut_settings,
        subtitleRes = R.string.shortcut_settings_subtitle,
        symbol = "",
        type = ShortcutType.SETTINGS,
        iconRes = R.drawable.ic_settings
    ),

    // Wi-Fi
    ShortcutUiItem(
        titleRes = R.string.shortcut_wifi,
        subtitleRes = R.string.shortcut_wifi_subtitle,
        symbol = "",
        type = ShortcutType.WIFI,
        iconRes = R.drawable.ic_wifi
    ),

    // Bluetooth
    ShortcutUiItem(
        titleRes = R.string.shortcut_bluetooth,
        subtitleRes = R.string.shortcut_bluetooth_subtitle,
        symbol = "",
        type = ShortcutType.BLUETOOTH,
        iconRes = R.drawable.ic_bluetooth
    ),

    // Apps
    ShortcutUiItem(
        titleRes = R.string.shortcut_apps,
        subtitleRes = R.string.shortcut_apps_subtitle,
        symbol = "",
        type = ShortcutType.APPS,
        iconRes = R.drawable.ic_apps
    ),

    // Storage
    ShortcutUiItem(
        titleRes = R.string.shortcut_storage,
        subtitleRes = R.string.shortcut_storage_subtitle,
        symbol = "",
        type = ShortcutType.STORAGE,
        iconRes = R.drawable.ic_storage
    ),

    // Display
    ShortcutUiItem(
        titleRes = R.string.shortcut_display,
        subtitleRes = R.string.shortcut_display_subtitle,
        symbol = "",
        type = ShortcutType.DISPLAY,
        iconRes = R.drawable.ic_display
    ),

    // Sound
    ShortcutUiItem(
        titleRes = R.string.shortcut_sound,
        subtitleRes = R.string.shortcut_sound_subtitle,
        symbol = "",
        type = ShortcutType.SOUND,
        iconRes = R.drawable.ic_sound
    ),

    // Accessibility
    ShortcutUiItem(
        titleRes = R.string.shortcut_accessibility,
        subtitleRes = R.string.shortcut_accessibility_subtitle,
        symbol = "",
        type = ShortcutType.ACCESSIBILITY,
        iconRes = R.drawable.ic_accessibility
    ),

    // Developer Options
    ShortcutUiItem(
        titleRes = R.string.shortcut_developer,
        subtitleRes = R.string.shortcut_developer_subtitle,
        symbol = "",
        type = ShortcutType.DEVELOPER,
        iconRes = R.drawable.ic_developer
    ),

    // Keyboard
    ShortcutUiItem(
        titleRes = R.string.shortcut_keyboard,
        subtitleRes = R.string.shortcut_keyboard_subtitle,
        symbol = "",
        type = ShortcutType.KEYBOARD,
        iconRes = R.drawable.ic_keyboard
    ),

    // Language
    ShortcutUiItem(
        titleRes = R.string.shortcut_language,
        subtitleRes = R.string.shortcut_language_subtitle,
        symbol = "",
        type = ShortcutType.LANGUAGE,
        iconRes = R.drawable.ic_language
    ),

    // Date & Time
    ShortcutUiItem(
        titleRes = R.string.shortcut_date_time,
        subtitleRes = R.string.shortcut_date_time_subtitle,
        symbol = "",
        type = ShortcutType.DATE_TIME,
        iconRes = R.drawable.ic_date_time
    ),

    // About Device
    ShortcutUiItem(
        titleRes = R.string.shortcut_about,
        subtitleRes = R.string.shortcut_about_subtitle,
        symbol = "",
        type = ShortcutType.ABOUT,
        iconRes = R.drawable.ic_about
    )
)


// ============================================================
// MAIN SCREEN
// ============================================================

@Composable
fun ShortcutsScreen(
    onShortcutClick: (ShortcutType) -> Unit
) {

    val background = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF121522),
            Color(0xFF0B0D16),
            Color(0xFF07080E)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {

        // Soft blue glow - upper right
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF1769FF).copy(alpha = 0.13f),
                            Color(0xFF0B3A86).copy(alpha = 0.055f),
                            Color.Transparent
                        ),
                        center = Offset(
                            x = 1700f,
                            y = 80f
                        ),
                        radius = 1100f
                    )
                )
        )

        // Subtle violet / blue glow - lower left
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF5036C8).copy(alpha = 0.07f),
                            Color(0xFF17265E).copy(alpha = 0.035f),
                            Color.Transparent
                        ),
                        center = Offset(
                            x = 100f,
                            y = 950f
                        ),
                        radius = 950f
                    )
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 54.dp,
                    end = 54.dp,
                    top = 30.dp,
                    bottom = 34.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ====================================================
            // HEADER
            // ====================================================

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    // APP BRAND
                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Secreto ",
                                color = Color.White,
                                fontSize = 34.sp,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = "TV",
                                color = Color(0xFF16C9F4),
                                fontSize = 34.sp,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = " Shortcuts",
                                color = Color.White,
                                fontSize = 34.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(5.dp)
                        )

                        Text(
                            text = stringResource(R.string.app_tagline),
                            color = Color.White.copy(alpha = 0.58f),
                            fontSize = 16.sp
                        )
                    }

                    // DEVELOPER BRAND
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {

                        Text(
                            text = "A SecretoTools Project",
                            color = Color.White.copy(alpha = 0.62f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = "MOHAMED LALAH",
                            color = Color(0xFF32A9FF),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            // ====================================================
            // GRID - 4 CARDS PER ROW
            // ====================================================

            val rows = shortcutItems.chunked(4)

            items(rows.size) { rowIndex ->

                val row = rows[rowIndex]

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    row.forEach { item ->

                        ShortcutCard(
                            item = item,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onShortcutClick(item.type)
                            }
                        )
                    }

                    repeat(4 - row.size) {

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            item {
                Spacer(
                    modifier = Modifier.height(32.dp)
                )
            }
        }
    }
}


// ============================================================
// SHORTCUT CARD
// ============================================================

@Composable
private fun ShortcutCard(
    item: ShortcutUiItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    var focused by remember {
        mutableStateOf(false)
    }

    val title = stringResource(item.titleRes)
    val subtitle = stringResource(item.subtitleRes)

    val scale by animateFloatAsState(
        targetValue = if (focused) 1.035f else 1.0f,
        animationSpec = tween(durationMillis = 110),
        label = "cardScale"
    )

    val shape = RoundedCornerShape(18.dp)

    // ========================================================
    // CARD COLORS
    // ========================================================

    val normalCardBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF252735),
            Color(0xFF1C1E29)
        )
    )

    val focusedCardBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF454957),
            Color(0xFF323541)
        )
    )

    val playStoreBrush = Brush.linearGradient(
        colors = if (focused) {

            listOf(
                Color(0xFF32D27C),
                Color(0xFF15995A)
            )

        } else {

            listOf(
                Color(0xFF25BC6D),
                Color(0xFF12844E)
            )
        }
    )

    val cardBrush = when {

        item.featured ->
            playStoreBrush

        focused ->
            focusedCardBrush

        else ->
            normalCardBrush
    }

    // ========================================================
    // CARD
    // ========================================================

    Box(
        modifier = modifier
            .height(128.dp)
            .scale(scale)
            .clip(shape)
            .background(cardBrush)
            .then(
                if (focused) {

                    Modifier.border(
                        width = 2.5.dp,
                        color = Color.White.copy(alpha = 0.96f),
                        shape = shape
                    )

                } else {

                    Modifier
                }
            )
            .onFocusChanged {
                focused = it.isFocused
            }
            .clickable {
                onClick()
            }
            .focusable()
            .padding(
                horizontal = 15.dp,
                vertical = 14.dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ICON
            ShortcutIcon(
                symbol = item.symbol,
                iconRes = item.iconRes,
                featured = item.featured,
                focused = focused
            )

            Spacer(
                modifier = Modifier.width(13.dp)
            )

            // TEXT
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = titleFontSize(title),
                    fontWeight = FontWeight.Bold,
                    lineHeight = titleLineHeight(title),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = subtitle,
                    color = Color.White.copy(
                        alpha = if (focused) 0.80f else 0.62f
                    ),
                    fontSize = subtitleFontSize(subtitle),
                    lineHeight = 17.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


// ============================================================
// ICON
// ============================================================

@Composable
private fun ShortcutIcon(
    symbol: String,
    iconRes: Int?,
    featured: Boolean,
    focused: Boolean
) {

    val iconBackground = when {

        featured && focused ->
            Color.White.copy(alpha = 0.22f)

        featured ->
            Color.White.copy(alpha = 0.15f)

        focused ->
            Color.White.copy(alpha = 0.17f)

        else ->
            Color.White.copy(alpha = 0.09f)
    }

    Box(
        modifier = Modifier
            .size(58.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(iconBackground),
        contentAlignment = Alignment.Center
    ) {

        if (iconRes != null) {

            // Play Store:
            // Keep the original Vector Drawable colors
            // so the white bag + white handle + green play symbol
            // are displayed correctly.
            if (featured) {

                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

            } else {

                // All other shortcut icons are unified in white.
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }

        } else {

            Text(
                text = symbol,
                color = Color.White,
                fontSize = iconFontSize(symbol),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}


// ============================================================
// RESPONSIVE TITLE SIZE
// ============================================================

private fun titleFontSize(
    title: String
): TextUnit {

    return when {

        title.length >= 24 ->
            14.sp

        title.length >= 18 ->
            15.sp

        title.length >= 14 ->
            16.sp

        title.length >= 11 ->
            17.sp

        else ->
            19.sp
    }
}


// ============================================================
// RESPONSIVE TITLE LINE HEIGHT
// ============================================================

private fun titleLineHeight(
    title: String
): TextUnit {

    return when {

        title.length >= 18 ->
            17.sp

        title.length >= 14 ->
            18.sp

        else ->
            22.sp
    }
}


// ============================================================
// RESPONSIVE SUBTITLE SIZE
// ============================================================

private fun subtitleFontSize(
    subtitle: String
): TextUnit {

    return when {

        subtitle.length >= 25 ->
            12.sp

        subtitle.length >= 21 ->
            12.5.sp

        subtitle.length >= 17 ->
            13.sp

        else ->
            14.sp
    }
}


// ============================================================
// FALLBACK TEXT ICON SIZE
// ============================================================

private fun iconFontSize(
    symbol: String
): TextUnit {

    return when (symbol) {

        "WiFi" ->
            12.sp

        "BT" ->
            16.sp

        "</>" ->
            16.sp

        "A" ->
            25.sp

        "i" ->
            27.sp

        else ->
            25.sp
    }
}