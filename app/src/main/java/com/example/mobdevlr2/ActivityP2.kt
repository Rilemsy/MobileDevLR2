package com.example.mobdevlr2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityP2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun switchToExAllNumbers(view: View?) {
        val intent = Intent(this, ActivityEx::class.java)
        intent.putExtra("isSelectively", false)
        startActivity(intent)
    }

    fun switchToExSelectively(view: View?) {
        val digit = findViewById<EditText>(R.id.selectDigitEditText).text.toString().toIntOrNull()
        if (digit != null && digit >= 2 && digit <= 9)
        {
            val intent = Intent(this, ActivityEx::class.java)
            intent.putExtra("selectedDigit",digit)
            intent.putExtra("isSelectively", true)
            startActivity(intent)
        }
        else
        {
            val toast = Toast.makeText(this, "Ошибка. Некорректное число", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}