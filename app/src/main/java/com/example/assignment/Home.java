package com.example.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private ListView taskListView;
    private Button addTaskButton;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ArrayAdapter<String> taskAdapter;
    private ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize views
        taskListView = findViewById(R.id.taskListView);
        addTaskButton = findViewById(R.id.addTaskButton);

        // Initialize task list and adapter
        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        taskListView.setAdapter(taskAdapter);

        // Load tasks (optional)

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open dialog or new activity to add task
                showAddTaskDialog();
            }
        });
    }

    private void showAddTaskDialog() {
        // Create a dialog to input task details
        // (You can create a custom dialog here)
        // For simplicity, you can show a Toast
        Toast.makeText(this, "Open Add Task Dialog", Toast.LENGTH_SHORT).show();
    }

    private void addTask(String taskName) {
        taskList.add(taskName);
        taskAdapter.notifyDataSetChanged();
        // Optionally, save to Firestore
        // saveTaskToFirestore(taskName);
    }

    private void markTaskAsComplete(int position) {
        String completedTask = taskList.get(position);
        taskList.remove(position);
        taskAdapter.notifyDataSetChanged();
        Toast.makeText(this, completedTask + " marked as complete", Toast.LENGTH_SHORT).show();
        // Optionally, update Firestore status
    }

    private void saveTaskToFirestore(String taskName) {
        // Implement Firestore saving logic here
        // For example:
        // db.collection("tasks").add(taskMap)
    }
}
