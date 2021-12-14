package com.example.notesapplication.ui;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapplication.R;
import com.example.notesapplication.data.NoteData;
import com.example.notesapplication.data.NotesDataImpl;
import com.example.notesapplication.data.NotesSource;

public class NotesFragment extends Fragment {

    private NotesSource data;
    private NotesAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container,false);
        initViews(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                data.addNoteData(new NoteData("Заголовок " + data.size(),
                        "Описание " + data.size(),
                        R.drawable.picture1,
                        false));
                adapter.notifyItemInserted(data.size() - 1);
                recyclerView.scrollToPosition(data.size()-1);
                return true;
            case R.id.action_clear:
                data.clearNoteData();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.note_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        switch (item.getItemId()){
            case R.id.action_update:
                data.updateNoteData(position,
                        new NoteData("Заметка " + position,
                                data.getNoteData(position).getDescription(),
                                data.getNoteData(position).getPicture(),
                                false));
                adapter.notifyItemChanged(position);
                return true;
            case R.id.action_delete:
                data.deleteNoteData(position);
                adapter.notifyItemRemoved(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_lines);
        data = new NotesDataImpl(getResources()).init();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        adapter = new NotesAdapter(data, this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), String.format("Выбрана позиция - %d", position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
