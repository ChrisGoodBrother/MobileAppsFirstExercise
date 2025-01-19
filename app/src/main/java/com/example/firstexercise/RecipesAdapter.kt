package com.example.firstexercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator

class RecipesAdapter(private val recipes: List<Recipe>): RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    var recipeHandler: RecipesInterface? = null

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

    class RecipeViewHolder(itemView: View, private val recipeHandler: RecipesInterface?) : RecyclerView.ViewHolder(itemView) {

        private val recipeImage = itemView.findViewById<ImageView>(R.id.recipeImage)
        private val recipeTitle = itemView.findViewById<TextView>(R.id.title)
        private val recipeDescription = itemView.findViewById<TextView>(R.id.smallDescription)
        private val likeIcon = itemView.findViewById<ImageButton>(R.id.likeIcon)
        private val shareIcon = itemView.findViewById<ImageButton>(R.id.shareIcon)
        private var isSelected = false
        private val progressIndicator = itemView.findViewById<CircularProgressIndicator>(R.id.progressIndicator)

        fun bind(recipe: Recipe) {
            recipeImage.setImageResource(recipe.image)
            recipeTitle.text = recipe.title
            recipeDescription.text = recipe.description

            itemView.setOnClickListener {
                recipeHandler?.onRecipeClick(recipe.ID)
            }

            likeIcon.setOnClickListener {
                isSelected = !isSelected
                likeIcon.setImageResource(
                    if (isSelected) R.drawable.likeiconred else R.drawable.likeicon
                )
                recipeHandler?.onLikeClick(recipe.ID)
            }

            shareIcon.setOnClickListener {
                recipeHandler?.onShareClick(recipe.ID)
            }
        }
    }
}