package com.example.notesapplication.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapplication.R;
import com.example.notesapplication.data.NoteData;
import com.example.notesapplication.data.NotesSource;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private final static String TAG = "NotesAdapter";
    private NotesSource dataSource;
    private OnItemClickListener itemClickListener;

    public NotesAdapter (NotesSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        Log.d(TAG, "onCreateViewHolder");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.bind(dataSource.getNoteData(position));
        Log.d(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tittle;
        private TextView description;
        private AppCompatImageView image;
        private CheckBox like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.tittle);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
            tittle = itemView.findViewById(R.id.like);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener!= null) {
                        itemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }

        public void bind(NoteData noteData) {
            tittle.setText(noteData.getTittle());
            description.setText(noteData.getDescription());
            like.setChecked(noteData.isLike());
            image.setImageResource(noteData.getPicture());
        }
    }
}















