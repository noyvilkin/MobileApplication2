package com.colman.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.colman.assignment2.model.Model
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        // 1. Initialize the RecyclerView
        recyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)

        // 2. Set the Layout Manager (Vertical list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. Initialize and set the Adapter using the Singleton Model
        adapter = StudentRecyclerAdapter(Model.shared.data)
        recyclerView.adapter = adapter

        // 4. Handle Row Clicks - Navigate to Details
        // The underscore '_' is used because the student parameter is not needed yet.
        // This resolves the "Parameter student is never used" warning.
        adapter.onItemClick = { _ ->
            // This is the hook for Partner B to open StudentDetailsActivity.
            // Example: val intent = Intent(this, StudentDetailsActivity::class.java)
            // startActivity(intent)
        }

        // 5. Setup the FAB to navigate to the New Student screen
        val fab: FloatingActionButton = findViewById(R.id.add_student_fab)
        fab.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    // Refresh the list when returning from the "New Student" screen
    override fun onResume() {
        super.onResume()
        val newCount = Model.shared.data.size
        if (newCount > 0) {
            adapter.notifyItemInserted(newCount - 1)
        }
    }
}