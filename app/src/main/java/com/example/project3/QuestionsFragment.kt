package com.example.project3

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import kotlin.random.Random

class QuestionsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_questions, container, false)
        val difficulty = QuestionsFragmentArgs.fromBundle(requireArguments()).difficulty
        val operation =  QuestionsFragmentArgs.fromBundle(requireArguments()).operation
        val numOfQuestions =  QuestionsFragmentArgs.fromBundle(requireArguments()).numOfQuestions

        val num1 = view.findViewById<TextView>(R.id.num1)
        val operand = view.findViewById<TextView>(R.id.operand)
        val num2 = view.findViewById<TextView>(R.id.num2)
        val doneButton = view.findViewById<Button>(R.id.doneButton)
        val answerEditText = view.findViewById<EditText>(R.id.answerEditText)
        var maxNum  = 10

        if(difficulty == "Medium"){
            maxNum = 25
        }
        else if(difficulty == "Hard"){
            maxNum = 50
        }
        else {
            maxNum = 10
        }

        val random1 = Random.nextInt(1, maxNum)
        val random2 = Random.nextInt(1, maxNum)

        num1.text = random1.toString()
        num2.text = random2.toString()

        if(operation == "Addition"){
            operand.text = "+"
        }
        else if(operation == "Multiplication"){
            operand.text = "x"
        }
        else if(operation == "Division"){
            operand.text = "/"
        }
        else if(operation == "Subtraction"){
            operand.text = "-"
        }
        else{
            operand.text = "Err"
        }

        var num1Txt = num1.text.toString()
        var num2Txt = num2.text.toString()
        val operandTxt = operand.text.toString()
        var answer = getAnswer(num1Txt.toInt(), num2Txt.toInt(), operandTxt)
        var questionNum = 1
        var correctAnswers = 0

        doneButton.setOnClickListener {
            if(answerEditText.text.toString() != ""){
                var userAnswer = Integer.parseInt(answerEditText.text.toString())
                if(userAnswer == answer){
                    correctAnswers++

                    var mediaPlayer = MediaPlayer.create(context, R.raw.correct)
                    mediaPlayer.start()
                    Toast.makeText(activity, "Correct. Good work!", Toast.LENGTH_LONG).show()
                }
                else{
                    var mediaPlayer = MediaPlayer.create(context, R.raw.wrong)
                    mediaPlayer.start()
                    Toast.makeText(activity, "Wrong", Toast.LENGTH_LONG).show()
                }

                questionNum++
                if(questionNum <= numOfQuestions.toInt()){

                    val random3 = Random.nextInt(1, maxNum)
                    val random4 = Random.nextInt(1, maxNum)

                    num1.text = random3.toString()
                    num2.text = random4.toString()

                    num1Txt = num1.text.toString()
                    num2Txt = num2.text.toString()

                    answer = getAnswer(num1Txt.toInt(), num2Txt.toInt(), operandTxt)
                }
                else{
                    val action = QuestionsFragmentDirections
                        .actionQuestionsFragmentToWelcomeFragment(correctAnswers.toString(), numOfQuestions, operation)
                    view.findNavController().navigate(action)
                }
            }
        }

        return view
    }

    private fun getAnswer(num1: Int, num2: Int, operand: String) : Int {
        var answer = 0

        if(operand == "+"){
            answer = num1 + num2
        }
        else if(operand == "x"){
            answer = num1 * num2
        }
        else if(operand == "/"){
            answer = num1 / num2
        }
        else if(operand == "-"){
            answer = num1 - num2
        }

        return answer
    }
}