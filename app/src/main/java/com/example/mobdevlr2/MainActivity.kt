package com.example.mobdevlr2

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calculate(v: View?)
    {
        val a = findViewById<EditText>(R.id.paramAEditText).text.toString().toDoubleOrNull()
        val b = findViewById<EditText>(R.id.paramBEditText).text.toString().toDoubleOrNull()
        val c = findViewById<EditText>(R.id.paramCEditText).text.toString().toDoubleOrNull()
        val answerTextView: TextView = findViewById(R.id.answerTextView)

        if (a != null && b != null && c != null) {
            val discriminant = b * b - 4 * a * c
            if (discriminant > 0) {
                val x1 = (-b + Math.sqrt(discriminant)) / (2 * a)
                val x2 = (-b - Math.sqrt(discriminant)) / (2 * a)
                answerTextView.text = "Ответ: x1 = $x1, x2 = $x2"
            } else if (discriminant == 0.0) {
                val x = -b / (2 * a)
                answerTextView.text = "Ответ: x = $x"
            } else {
                answerTextView.text = "Ответ: нет решения"
            }
        } else {
            answerTextView.text = "Ошибка! Корректно введите все коэффициенты."
        }
    }

    fun switchToP2(view: View?) {
        val intent = Intent(this, ActivityP2::class.java)
        startActivity(intent)
    }

}