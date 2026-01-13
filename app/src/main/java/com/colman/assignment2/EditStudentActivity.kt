package com.colman.assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.colman.assignment2.model.Model
import com.colman.assignment2.model.Student

class EditStudentActivity : AppCompatActivity() {
    private var student: Student? = null
    private var originalId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        originalId = intent.getStringExtra("student_id")
        student = Model.instance.getStudentById(originalId)

        val nameEt: EditText = findViewById(R.id.name_et)
        val idEt: EditText = findViewById(R.id.id_et)
        val phoneEt: EditText = findViewById(R.id.phone_et)
        val addressEt: EditText = findViewById(R.id.address_et)
        val checkedCb: CheckBox = findViewById(R.id.checked_cb)

        student?.let {
            nameEt.setText(it.name)
            idEt.setText(it.id)
            phoneEt.setText(it.phone)
            addressEt.setText(it.address)
            checkedCb.isChecked = it.isChecked
        }

        val saveBtn: Button = findViewById(R.id.save_btn)
        saveBtn.setOnClickListener {
            val newStudent = Student(
                nameEt.text.toString(),
                idEt.text.toString(),
                phoneEt.text.toString(),
                addressEt.text.toString(),
                checkedCb.isChecked
            )
            originalId?.let { Model.instance.updateStudent(it, newStudent) }
            navigateToStudentList()
        }

        val deleteBtn: Button = findViewById(R.id.delete_btn)
        deleteBtn.setOnClickListener {
            originalId?.let { Model.instance.deleteStudent(it) }
            navigateToStudentList()
        }

        val cancelBtn: Button = findViewById(R.id.cancel_btn)
        cancelBtn.setOnClickListener {
            finish()
        }
    }

    private fun navigateToStudentList() {
        val intent = Intent(this, StudentsListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}