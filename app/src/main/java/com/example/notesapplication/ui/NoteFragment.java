package com.example.notesapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesapplication.MainActivity;
import com.example.notesapplication.R;
import com.example.notesapplication.data.NoteData;
import com.example.notesapplication.observe.Publisher;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

public class NoteFragment extends Fragment {
    private static final String ARG_NOTE_DATA = "Param_NoteData";

    private NoteData noteData;
    public Publisher publisher;

    private TextInputEditText tittle;
    private TextInputEditText description;
    private DatePicker datePicker;


    public static  NoteFragment newInstance(NoteData noteData) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE_DATA, noteData);
        fragment.setArguments(args);
        return fragment;
    }

    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null) {
            noteData = getArguments().getParcelable(ARG_NOTE_DATA);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        initView(view);
        if (noteData != null) {
            populateView();
        }
        return view;
    }

    private void populateView() {
        tittle.setText(noteData.getTittle());
        description.setText(noteData.getDescription());
        initDatePicker(noteData.getDate());
    }

    private void initDatePicker(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                null);

    }

    private void initView(View view) {
        tittle = view.findViewById(R.id.inputTitle);
        description = view.findViewById(R.id.inputDescription);
        datePicker = view.findViewById(R.id.inputDate);
    }

    @Override
    public void onStop() {
        super.onStop();
        noteData = collectNoteData();
    }

    private NoteData collectNoteData() {
        String tittle = this.tittle.getText().toString();
        String description = this.description.getText().toString();
        Date date = getDateFromDatePicker();
        int picture;
        boolean like;
        if (noteData!= null) {
            picture = noteData.getPicture();
            like = noteData.isLike();
        } else {
            picture = R.drawable.picture1;
            like = false;
        }
        return new NoteData(tittle, description, picture, like, date);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        publisher.notifySingle(noteData);
    }

    private Date getDateFromDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.datePicker.getYear());
        calendar.set(Calendar.MONTH, this.datePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, this.datePicker.getDayOfMonth());
        return calendar.getTime();

    }

}
