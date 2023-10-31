package com.example.project3

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

private var firstRun = true

class WelcomeFragment : Fragment() {

    private var param1: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getBoolean("firstRun")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        val startButton = view.findViewById<Button>(R.id.startButton)
        val difficultySelect = view.findViewById<RadioGroup>(R.id.difficultySelect)
        val operationSelect = view.findViewById<RadioGroup>(R.id.operationSelect)
        val plusButton = view.findViewById<ImageButton>(R.id.plusQuestions)
        val minusButton = view.findViewById<ImageButton>(R.id.minusQuestions)
        val numOfQuestions = view.findViewById<TextView>(R.id.numOfQuestions)
        val resultsText = view.findViewById<TextView>(R.id.resultsText)



        if(firstRun == false){
            val correctAnswers = WelcomeFragmentArgs.fromBundle(requireArguments()).correctAnswers
            val outOf = WelcomeFragmentArgs.fromBundle(requireArguments()).numOfQuestions
            val operation = WelcomeFragmentArgs.fromBundle(requireArguments()).operation.lowercase()

            var displayResultsStr = "You got $correctAnswers out of $outOf in $operation. "

            if(correctAnswers.toDouble() / outOf.toDouble() >= 0.8){
                displayResultsStr = displayResultsStr + "Good work!"
                resultsText.setTextColor(Color.DKGRAY)
            }
            else{
                displayResultsStr = displayResultsStr + "You need to practice more!"
                resultsText.setTextColor(Color.RED)
            }

            resultsText.text = displayResultsStr
        }

        plusButton.setOnClickListener {
            var num =  numOfQuestions.text.toString().toInt()
            num++
            numOfQuestions.text = num.toString()
        }

        minusButton.setOnClickListener {
            var num =  numOfQuestions.text.toString().toInt()
            num--
            numOfQuestions.text = num.toString()
        }


        startButton.setOnClickListener {
            firstRun = false

            val diffId = difficultySelect.checkedRadioButtonId
            val diffButton = view.findViewById<RadioButton>(diffId)
            val difficulty = diffButton.text.toString()

            val operId = operationSelect.checkedRadioButtonId
            val operButton = view.findViewById<RadioButton>(operId)
            val operation = operButton.text.toString()


            val questions = numOfQuestions.text.toString()
            val action = WelcomeFragmentDirections
                .actionWelcomeFragmentToQuestionsFragment(questions, difficulty, operation)
            view.findNavController().navigate(action)
        }

        return view
    }
}