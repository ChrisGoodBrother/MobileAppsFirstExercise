package com.example.firstexercise

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.main_activity), RecipesInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.recipes_fragment_container_view, RecipesFragment())
                .commit()
        }
    }

    override fun onRecipeClick(ID: Int) {
        Toast.makeText(this, "The item that was clicked has an ID($ID)",Toast.LENGTH_SHORT).show()
    }

    override fun onShareClick(ID: Int) {
        Toast.makeText(this, "User shared item with ID($ID)", Toast.LENGTH_SHORT).show()
    }

    override fun onLikeClick(ID: Int) {
        Toast.makeText(this, "User liked item with ID($ID)", Toast.LENGTH_SHORT).show()
    }
}