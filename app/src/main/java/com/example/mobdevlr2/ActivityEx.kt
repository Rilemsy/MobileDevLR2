package com.example.mobdevlr2

//import android.R
import android.app.ActivityManager
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class ActivityEx : AppCompatActivity() {
    private var num1: Int = 0
    private var num2: Int = 0
    private var questionNumber: Int = 1
    private var numCorrectAnswers: Int = 0
    private var numIncorrectAnswers: Int = 0
    private val questionsAmount: Int = 3
    private var isSelectively: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ex)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        isSelectively = intent.getBooleanExtra("isSelectively", false)

        if (isSelectively)
        {
            num1 = intent.getIntExtra("selectedDigit", 0)
        }
        else
        {
            num1 = Random.nextInt(2, 10)
        }
        num2 = Random.nextInt(2, 10)

        val questionTextView = findViewById<TextView>(R.id.questionTextViewAll)
        val questionNumberView = findViewById<TextView>(R.id.questionNumberTextViewAll)
        questionNumberView.text = "Вопрос №$questionNumber"
        questionTextView.text = "$num1 x $num2 = ?"
    }

    override fun onStop() {
        super.onStop()
        finish()
        val mngr = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val taskList = mngr.getRunningTasks(10)
        Log.d("STOP", taskList.get(0).numActivities.toString());
    }

    fun checkAnswerAll(view: View?) {
        val answerEditText = findViewById<EditText>(R.id.answerEditTextAll)
        val answer = answerEditText.text.toString().toIntOrNull()
        if (answer == null)
        {
            val toast = Toast.makeText(this, "Ошибка. Некорректное значение", Toast.LENGTH_SHORT)
            toast.setGravity(CENTER_VERTICAL,0,0)
            toast.show()
            return
        }

        if (answerEditText.text.toString().toInt() == num1 * num2) {
            numCorrectAnswers++
            val toast = Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT)
            toast.show()
        }
        else {
            numIncorrectAnswers++
            val toast = Toast.makeText(this, "Неправильный ответ ", Toast.LENGTH_SHORT)
            toast.show()
        }

        questionNumber++

        val questionNumberView = findViewById<TextView>(R.id.questionNumberTextViewAll)
        val questionTextView = findViewById<TextView>(R.id.questionTextViewAll)

        if (questionNumber <= questionsAmount) {

            if (!isSelectively)
                num1 = Random.nextInt(2, 10)
            num2 = Random.nextInt(2, 10)
            questionNumberView.text = "Вопрос №$questionNumber"
            questionTextView.text = "$num1 x $num2 = ?"
        }
        else {
            questionNumberView.text = "Тест завершен"
            questionTextView.text = "Правильных ответов: ${((numCorrectAnswers.toDouble() / questionsAmount) * 100).toInt()}%"
            answerEditText.setInputType(InputType.TYPE_NULL);
            val answerButton = findViewById<Button>(R.id.answerButtonAll)
            answerButton.setClickable(false);
        }
        answerEditText.text.clear()
    }
}