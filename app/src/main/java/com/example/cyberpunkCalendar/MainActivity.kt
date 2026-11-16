package com.example.cyberpunkCalendar

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.CaseMap
import android.os.Build
import android.os.Bundle
import android.os.storage.StorageVolume
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.File
import java.io.InputStream
import java.time.LocalDateTime
import com.example.cyberpunkCalendar.ui.theme.BackgroundGradient
import com.example.cyberpunkCalendar.ui.theme.BorderColor
import com.example.cyberpunkCalendar.ui.theme.CyberpunkJournalCalendarTheme
import com.example.cyberpunkCalendar.ui.theme.FillColor
import com.example.cyberpunkCalendar.ui.theme.Typography
import com.example.cyberpunkCalendar.ui.theme.YellowNormal
import com.google.api.client.http.HttpHeaders
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.services.calendar.Calendar
import org.intellij.lang.annotations.JdkConstants
import java.lang.reflect.Array
import java.time.LocalDate
import java.time.LocalTime

object Constants {

    const val REQUEST_ACCOUNT_PICKER = 1000
    const val REQUEST_AUTHORIZATION = 1001
    const val REQUEST_GOOGLE_PLAY_SERVICES = 1002
    const val REQUEST_PERMISSION_GET_ACCOUNTS = 1003
    const val PREF_ACCOUNT_NAME = "getCalendarEvent"
}

data class GetEventModel(
    var id: Int = 0,
    var summary: String? = "",
    var startDate: String = "",
)

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if(GetSavedDate() != LocalDate.now().toString())
            {
                FileSave()
                Log.d("testprint", "Saved Date changed")
            }
            CyberpunkJournalCalendarTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundGradient)
                    .padding(vertical = 20.dp)
                    .verticalScroll(rememberScrollState()))
                {
                    Column () {
                        val fin: FileInputStream = LocalContext.current.openFileInput("test_database.txt")
                        var a: Int
                        val temp = StringBuilder()
                        while (fin.read().also { a = it } != -1) {
                            temp.append(a.toChar())
                        }
                        val asd = temp.toString().split("\n")
                        for (i in asd){
                            if ('`' in i){
                            var name = ""
                            var desc = ""
                            for (a in i){
                                if (a != '`') name += a
                                else break
                            }
                            desc = i.substringAfter("$name`")
                            MissionAdd(name = name, description = desc, modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent)
                                .padding(15.dp, 10.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FileSave(database : kotlin.Array<String> = arrayOf("test1`test1 desc", "test2`test2 description",
    LocalDate.now().toString())){
    val filename = "test_database.txt"
    val fos : FileOutputStream = LocalContext.current.openFileOutput(filename, Context.MODE_PRIVATE)
    var writedata = ""
    for (i in database){
        writedata += i
        if (i != database[database.size - 1]) writedata += "\n"
    }
    fos.write(writedata.toByteArray())
    fos.flush()
    fos.close()
}

@Composable
fun FileLoad(): String {
    val filename = "test_database.txt"
    val fin: FileInputStream = LocalContext.current.openFileInput(filename)
    var a: Int
    val temp = StringBuilder()
    while (fin.read().also { a = it } != -1) {
        temp.append(a.toChar())
    }
    return temp.toString()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GetSavedDate(): String{
    val filename = "test_database.txt"
    val fin: FileInputStream = LocalContext.current.openFileInput(filename)
    var a: Int
    val temp = StringBuilder()
    while (fin.read().also { a = it } != -1) {
        temp.append(a.toChar())
    }
    val asd = temp.toString().split("\n")
    return asd[asd.size - 1]
}

@Composable
fun MissionAdd(name: String = "NAME", description: String = "This is a description", @SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RectangleShape, border = BorderStroke(2.dp, BorderColor), colors = CardColors(FillColor, FillColor, FillColor, FillColor)) {
        Text(
            text = name.uppercase(),
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
