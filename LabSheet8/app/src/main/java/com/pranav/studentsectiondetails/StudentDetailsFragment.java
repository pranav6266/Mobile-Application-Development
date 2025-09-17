package com.pranav.studentsectiondetails;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StudentDetailsFragment extends Fragment {
    // Data arrays for the fragment
    private final String[] studentNames = {"Meena", "Ravi", "Priya", "Amit", "Sunita", "Vikram"};
    private final String[] studentSections = {"Section: 5CSE1", "Section: 5CSE2", "Section: 5CSE1", "Section: 5CSE3", "Section: 5CSE2", "Section: 5CSE3"};
    private TextView nameTextView, sectionTextView;
    // Required empty public constructor
    public StudentDetailsFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_details, container, false);
        nameTextView = view.findViewById(R.id.studentNameTextView);
        sectionTextView = view.findViewById(R.id.studentSectionTextView);
        return view;
    }
    public void displayDetails(int position) {
        if (nameTextView != null && sectionTextView != null) {
            nameTextView.setText(studentNames[position]);
            sectionTextView.setText(studentSections[position]);
        }
    }
}
