package com.hrishi.mvvm;

import android.app.NotificationChannelGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes=new ArrayList<>();
    OnItemClickListener listner;
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
         Note note=notes.get(position);
         holder.textViewTitle.setText(note.getTitle());
         holder.textViewDescription.setText(note.getDescription());
         holder.textViewPriority.setText(String.valueOf(note.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
  public void setNotes(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
  }
  public Note getNote(int position){
        Note note=notes.get(position);
        return note;
  }
    class  NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (listner!=null && getAdapterPosition()!=RecyclerView.NO_POSITION)
                     listner.clickListener(notes.get(getAdapterPosition()));
                 }
             });

        }
    }
    public interface OnItemClickListener{
        void clickListener(Note note);
    }
    public void setOnclickListener(OnItemClickListener listener){
        this.listner=listener;
    }
}
