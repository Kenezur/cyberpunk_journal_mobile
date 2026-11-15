package com.example.cyberpunkCalendar

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.example.cyberpunkCalendar.ui.theme.BackgroundGradient
import com.example.cyberpunkCalendar.ui.theme.BorderColor
import com.example.cyberpunkCalendar.ui.theme.CyberpunkJournalCalendarTheme
import com.example.cyberpunkCalendar.ui.theme.FillColor
import com.example.cyberpunkCalendar.ui.theme.Typography
import com.example.cyberpunkCalendar.ui.theme.YellowNormal
import org.intellij.lang.annotations.JdkConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BackgroundGradient
            CyberpunkJournalCalendarTheme {
                Box(modifier = Modifier.fillMaxSize().background(BackgroundGradient).padding(vertical = 20.dp).verticalScroll(rememberScrollState()))
                {
                    Column () {
                        for (i in 1..20) MissionAdd(modifier = Modifier.fillMaxWidth().background(Color.Transparent).padding(15.dp, 10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MissionAdd(name: String = "NAME", description: String = "This is a description", @SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RectangleShape, border = BorderStroke(2.dp, BorderColor), colors = CardColors(FillColor, FillColor, FillColor, FillColor)) {
        Text(
            text = name,
            modifier = modifier,
            style = Typography.bodyLarge,
            fontSize = 30.sp
        )
        Text(
            text = description,
            modifier = modifier,
            style = Typography.labelSmall,
            fontSize = 24.sp
        )
    }
}
