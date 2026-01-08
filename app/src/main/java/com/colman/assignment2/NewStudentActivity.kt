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
        val phoneEt = findViewById<EditText>(R.id.new_student_phone_et)
        val addressEt = findViewById<EditText>(R.id.new_student_address_et)
        val saveBtn = findViewById<Button>(R.id.new_student_save_btn)
        val cancelBtn = findViewById<Button>(R.id.new_student_cancel_btn)

        saveBtn.setOnClickListener {
            val name = nameEt.text.toString()
            val phone = phoneEt.text.toString()
            val address = addressEt.text.toString()

            // Validations (per your previous request)
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!phone.all { it.isDigit() }) {
                phoneEt.error = "Numbers only, please"
                return@setOnClickListener
            }

            // Use the new helper function to get a unique, structured ID
            val autoId = Model.shared.getNextId()

            val newStudent = Student(
                name = name,
                id = autoId, // This will now be 200000021, then 200000022, etc.
                phone = phone,
                address = address,
                isChecked = false
            )

            Model.shared.data.add(newStudent)
            finish()
        }

        cancelBtn.setOnClickListener { finish() }
    }
}