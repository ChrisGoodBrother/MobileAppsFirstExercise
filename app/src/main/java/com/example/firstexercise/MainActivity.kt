package com.example.firstexercise

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity(R.layout.main_activity), RecipesInterface {

    private val credentialsManager: CredentialsManager
        get() = (application as MyApp).credentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.recipes_fragment_container_view, RecipesFragment(credentialsManager))
                .commit()
        }

        lifecycleScope.launch {
            credentialsManager.isLoggedIn.collect { isLoggedIn ->
                if(!isLoggedIn) {
                    goToLogin()
                }
            }
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, AuthenticationActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            putExtra("ACTION", "LOGIN")
        }
        startActivity(intent)
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