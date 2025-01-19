package com.example.firstexercise

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RecipesFragment(private val credentialsManager: CredentialsManager): Fragment(R.layout.fragment_recipes) {

    private val viewModel: RecipesViewModel by viewModels()
    private var listener: RecipesInterface? = null

    private val recipesRecyclerView
        get() = requireView().findViewById<RecyclerView>(R.id.recipeList)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        require(context is RecipesInterface) {
            "Activity $context must implement fragment's EventListener"
        }

        listener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            credentialsManager.logout()
        }

        view.findViewById<SearchView>(R.id.searchView)
            .setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("QUERY", "New Query: $newText")
                    val text = newText ?: ""

                    if(text.count() >= 3)
                        viewModel.setQuery(text)
                    else
                        viewModel.setQuery("")
                    return true
                }
            })

        viewLifecycleOwner
            .lifecycleScope
            .launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel
                        .recipesFlow
                        .collect { recipes ->
                            recipesRecyclerView.adapter = RecipesAdapter(recipes)
                            delay(2000L)
                        }
                }
            }
    }
}

class RecipesViewModel: ViewModel() {
    private val queryFlow = MutableStateFlow("")
    val recipesFlow = queryFlow.asStateFlow().map {
        query -> listOfRecipes.filter { recipe -> recipe.title.contains(query, ignoreCase = true) || recipe.description.contains(query, ignoreCase = true) }
    }

    fun setQuery(query: String) {
        queryFlow.value = query
    }
}