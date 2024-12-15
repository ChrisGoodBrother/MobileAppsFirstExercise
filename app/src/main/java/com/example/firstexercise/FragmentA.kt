package com.example.firstexercise

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentA : Fragment(R.layout.fragment_a) {
    interface EventListener {
        fun onGoToBPressed()
    }

    private var listener: EventListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("PREVIEW", "Context is: $context")

        require(context is EventListener) {
            "Activity $context must implement fragment's EventListener"
        }

        listener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.navigateButton).setOnClickListener {
            listener?.onGoToBPressed()
        }
    }

}