package com.example.project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController

class ResultsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_results, container, false)
//
//        val correctScoreTxt = view.findViewById<TextView>(R.id.correctScoreTxt)
//        val outOfScoreTxt = view.findViewById<TextView>(R.id.outOfScoreTxt)
//        val againButton = view.findViewById<Button>(R.id.againButton)
//
//        val correctScore =  ResultsFragmentArgs.fromBundle(requireArguments()).correctScore
//        val outOfScore =  ResultsFragmentArgs.fromBundle(requireArguments()).outOfScore
//
//        correctScoreTxt.text = correctScore
//        outOfScoreTxt.text = outOfScore
//
//        againButton.setOnClickListener {
//            val action = ResultsFragmentDirections
//                .actionResultsFragmentToWelcomeFragment()
//            view.findNavController().navigate(action)
//        }
//
        return view
    }
}