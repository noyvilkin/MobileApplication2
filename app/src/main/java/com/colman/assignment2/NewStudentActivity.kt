package com.colman.assignment2

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.colman.assignment2.model.Model
import com.colman.assignment2.model.Student

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)
        title = "New Student"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nameEt = findViewById<EditText>(R.id.new_student_name_et)
        val idEt = findViewById<EditText>(R.id.new_student_id_et)
        val phoneEt = findViewById<EditText>(R.id.new_student_phone_et)
        val addressEt = findViewById<EditText>(R.id.new_student_address_et)
        val saveBtn = findViewById<Button>(R.id.new_student_save_btn)
        val cancelBtn = findViewById<Button>(R.id.new_student_cancel_btn)

        saveBtn.setOnClickListener {
            val name = nameEt.text.toString().trim()
            val id = idEt.text.toString().trim()
            val phone = phoneEt.text.toString().trim()
            val address = addressEt.text.toString().trim()

            if (name.isEmpty()) {
                nameEt.error = "Name cannot be empty"
                return@setOnClickListener
            }

            if (id.isEmpty()) {
                idEt.error = "ID cannot be empty"
                return@setOnClickListener
            }

            if (!id.matches(Regex("\\d+"))) {
                idEt.error = "ID must contain only numbers"
                return@setOnClickListener
            }

            if (Model.instance.getStudentById(id) != null) {
                idEt.error = "A student with this ID already exists"
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                phoneEt.error = "Phone cannot be empty"
                return@setOnClickListener
            }

            if (!phone.matches(Regex("^05[0-9]{8}$"))) {
                phoneEt.error = "Invalid phone number"
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                addressEt.error = "Address cannot be empty"
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}