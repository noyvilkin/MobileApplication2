package com.colman.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.colman.assignment2.model.Student

class StudentRecyclerAdapter(private val students: MutableList<Student>) :
    RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {

    // This allows StudentsListActivity to listen for row clicks [cite: 17]
    var onItemClick: ((Student) -> Unit)? = null

    // This class holds the views for a single row
    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTv: TextView = view.findViewById(R.id.student_row_name)
        val idTv: TextView = view.findViewById(R.id.student_row_id)
        val checkBox: CheckBox = view.findViewById(R.id.student_row_check)
    }

    // Creates the visual row from student_list_row.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view)
    }

    // Connects the data to the visual views
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameTv.text = student.name
        holder.idTv.text = student.id

        // Handle the checkbox status [cite: 16]
        holder.checkBox.apply {
            isChecked = student.isChecked
            // We use setOnClickListener instead of onCheckedChangeListener
            // to avoid triggering updates during recycling
            setOnClickListener {
                student.isChecked = isChecked
            }
        }

        // Handle clicking the whole row to open details [cite: 17]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(student)
        }
    }

    override fun getItemCount(): Int = students.size
}