package com.example.firstexercise

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class ActivitySample :
    AppCompatActivity(R.layout.activity_sample),
    FragmentA.EventListener,
    FragmentB.EventListener
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //If there's no Instance State then place FragmentA
        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<FragmentA>(R.id.fragment_container_view)
            }
        }


    }

    override fun onGoToBPressed() {
        Log.d("PREVIEW", "B Pressed")
        supportFragmentManager.commit {
            replace<FragmentB>(R.id.fragment_container_view)
            addToBackStack(null)
        }
    }

    override fun onGoToAPressed() {
        Log.d("PREVIEW", "A Pressed")
        supportFragmentManager.commit {
            replace<FragmentA>(R.id.fragment_container_view)
            addToBackStack(null)
        }
    }
}