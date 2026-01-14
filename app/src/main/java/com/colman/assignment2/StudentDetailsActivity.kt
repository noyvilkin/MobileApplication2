package com.colman.assignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.colman.assignment2.model.Model
import com.colman.assignment2.model.Student

class StudentDetailsActivity : AppCompatActivity() {
    private var student: Student? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        title = "Student Details"

        val studentId = intent.getStringExtra("student_id")
        student = Model.instance.getStudentById(studentId)

        student?.let {
            val nameTv: TextView = findViewById(R.id.name_tv)
            val idTv: TextView = findViewById(R.id.id_tv)
            val phoneTv: TextView = findViewById(R.id.phone_tv)
            val addressTv: TextView = findViewById(R.id.address_tv)
            val checkedCb: CheckBox = findViewById(R.id.checked_cb)
            val avatarIv: ImageView = findViewById(R.id.avatar_iv)

            nameTv.text = it.name
            idTv.text = it.id
            phoneTv.text = it.phone
            addressTv.text = it.address
            checkedCb.isChecked = it.isChecked
            // In a real application, you would load the avatar image using a library like Glide or Picasso
            // For now, we'll just use a placeholder
            avatarIv.setImageResource(R.drawable.student_avatar)
        }

        val editBtn: Button = findViewById(R.id.edit_btn)
        editBtn.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_id", student?.id)
            startActivity(intent)
        }
    }
}