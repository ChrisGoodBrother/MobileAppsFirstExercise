package com.example.firstexercise

interface RecipeEventHandler {
    fun onRecipeClick(ID: Int)
    fun onShareClick(ID: Int)
    fun onLikeClick(ID: Int)
}