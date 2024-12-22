package com.example.firstexercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity(), RecipeEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)

        val listOfRecipes = listOf(
            Recipe(1,R.drawable.image1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka.."),
            Recipe(2,R.drawable.image2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare.."),
            Recipe(3,R.drawable.image3, "Tonkotsu Ramen", "Is a Japanese noodle soup dish that originated in Fukuoka, Fu.."),
            Recipe(4,R.drawable.image4, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake made fr.."),
            Recipe(5,R.drawable.image5, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable.."),
            Recipe(6,R.drawable.image6, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it ca.."),
            Recipe(7,R.drawable.image7, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare.."),
        )

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipeList)
        recipesRecyclerView.adapter = RecipesAdapter(listOfRecipes, this)

        recipesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
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

class RecipesAdapter(val recipes: List<Recipe>, private val recipeHandler: RecipeEventHandler): RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view, recipeHandler)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    class RecipeViewHolder(itemView: View, private val recipeHandler: RecipeEventHandler): RecyclerView.ViewHolder(itemView) {

        private val recipeImage = itemView.findViewById<ImageView>(R.id.recipeImage)
        private val recipeTitle = itemView.findViewById<TextView>(R.id.title)
        private val recipeDescription = itemView.findViewById<TextView>(R.id.smallDescription)
        private val likeIcon = itemView.findViewById<ImageButton>(R.id.likeIcon)
        private val shareIcon = itemView.findViewById<ImageButton>(R.id.shareIcon)
        private var isSelected = false

        fun bind(recipe: Recipe) {
            recipeImage.setImageResource(recipe.image)
            recipeTitle.text = recipe.title
            recipeDescription.text = recipe.description

            itemView.setOnClickListener {
                recipeHandler.onRecipeClick(recipe.ID)
            }

            likeIcon.setOnClickListener {
                isSelected = !isSelected
                if(isSelected)
                    likeIcon.setImageResource(R.drawable.likeiconred)
                else
                    likeIcon.setImageResource(R.drawable.likeicon)

                recipeHandler.onLikeClick(recipe.ID)
            }

            shareIcon.setOnClickListener {
                recipeHandler.onShareClick(recipe.ID)
            }
        }
    }

}