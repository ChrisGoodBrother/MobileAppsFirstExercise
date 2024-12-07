package com.example.firstexercise

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class ActivitySample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sample)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.switch_button).setOnClickListener() {

            //Get current Fragment
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)

            supportFragmentManager.commit {
                if(currentFragment is FragmentA) {
                    //Replace FragmentA with FragmentB
                    replace<FragmentB>(R.id.fragment_container_view)
                    setReorderingAllowed(true)
                    //Add FragmentA to the back stack with name "null"
                    addToBackStack(null)
                }
                else {
                    //Get FragmentA from the back stack
                    supportFragmentManager.popBackStack()
                }
            }
        }
    }
}