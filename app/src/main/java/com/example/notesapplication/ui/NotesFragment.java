package com.example.notesapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapplication.R;

public class NotesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
        String[] data = getResources().getStringArray(R.array.titles);
        initRecyclerView(recyclerView, data);
        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, String[] data) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NotesAdapter adapter = new NotesAdapter(data);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(),String.format("%s: %d - %s","Выбрана позиция", position, ((TextView)view).getText()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
