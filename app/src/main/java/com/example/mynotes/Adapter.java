package com.example.mynotes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Myviewholder> {

    Context context;
    Activity activity;
    List<Model> noteslist;

    public Adapter(Context context, Activity activity, List<Model> noteslist) {
        this.context = context;
        this.activity = activity;
        this.noteslist = noteslist;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.title.setText(noteslist.get(position).getTitle());
        holder.description.setText(noteslist.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return noteslist.size();

    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        TextView title,description;
        RelativeLayout layout;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            layout=itemView.findViewById(R.id.note_layout);
        }
    }
}
