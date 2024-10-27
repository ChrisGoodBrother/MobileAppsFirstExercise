package com.example.firstexercise

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstexercise.ui.theme.FirstExerciseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_create_account_1)

        findViewById<CheckBox>(R.id.checkBox)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("CHECKBOXES", "Remember me is checked: $isChecked")
            }
    }
}