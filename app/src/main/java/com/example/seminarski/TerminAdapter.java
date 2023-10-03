package com.example.seminarski;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TerminAdapter extends RecyclerView.Adapter<TerminAdapter.TerminViewHolder> {
    private List<Termin> listaTermina;

    public TerminAdapter(List<Termin> listaTermina) {
        this.listaTermina = listaTermina;
    }

    @NonNull
    @Override
    public TerminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new TerminViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TerminViewHolder holder, int position) {
        Termin termin = listaTermina.get(holder.getAdapterPosition());
        holder.nazivUslugeTextView.setText(termin.getNazivUsluge());
        holder.cenaUslugeTextView.setText(String.valueOf(termin.getCenaUsluge()));
        holder.datumTextView.setText(termin.getDatum()); // Bind the date to the TextView

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int terminId = listaTermina.get(holder.getAdapterPosition()).getId();
                DatabaseHelper db= new DatabaseHelper(v.getContext());
                db.obrisiTermin(terminId);

                listaTermina.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaTermina.size();
    }

    public static class TerminViewHolder extends RecyclerView.ViewHolder {
        public TextView nazivUslugeTextView;
        public TextView cenaUslugeTextView;
        public TextView datumTextView;
        public Button deleteButton;

        public TerminViewHolder(View view) {
            super(view);
            nazivUslugeTextView = view.findViewById(R.id.nazivUslugeTextView);
            cenaUslugeTextView = view.findViewById(R.id.cenaUslugeTextView);
            datumTextView = view.findViewById(R.id.datumTextView);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }


}
