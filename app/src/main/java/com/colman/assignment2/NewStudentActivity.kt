package com.colman.assignment2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.colman.assignment2.model.Model
import com.colman.assignment2.model.Student

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val nameEt = findViewById<EditText>(R.id.new_student_name_et)
        val idEt = findViewById<EditText>(R.id.new_student_id_et)
        val phoneEt = findViewById<EditText>(R.id.new_student_phone_et)
        val addressEt = findViewById<EditText>(R.id.new_student_address_et)
        val saveBtn = findViewById<Button>(R.id.new_student_save_btn)
        val cancelBtn = findViewById<Button>(R.id.new_student_cancel_btn)

        saveBtn.setOnClickListener {
            val name = nameEt.text.toString()
            val id = idEt.text.toString()
            val phone = phoneEt.text.toString()
            val address = addressEt.text.toString()

            if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!phone.all { it.isDigit() }) {
                phoneEt.error = "Numbers only, please"
                phoneEt.requestFocus()
                return@setOnClickListener
            }

            val newStudent = Student(
                name = name,
                id = id, 
                phone = phone,
                address = address,
                isChecked = false
            )

            Model.instance.data.add(newStudent)
            finish()
        }

        cancelBtn.setOnClickListener { finish() }
    }
}