package com.example.directoryofskilledpeople;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkilledPeopleAdapter extends RecyclerView.Adapter<SkilledPeopleAdapter.ViewHolder>{
    List<Skilled_People> skilledPeople;
    Context context;
    DatabaseHelper databaseHelper;

    public SkilledPeopleAdapter(List<Skilled_People> skilledPeople, Context context) {
        this.skilledPeople = skilledPeople;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.skilled_people_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Skilled_People skilled_people = skilledPeople.get(position);

        holder.textViewId.setText(skilled_people.getId());
        holder.editTextName.setText(skilled_people.getName());
        holder.editTextAddress.setText(skilled_people.getAddress());
        holder.editTextTitle.setText(skilled_people.getTitle());
        holder.editTextPhone.setText(skilled_people.getPhone());
        holder.editTextEmail.setText(skilled_people.getEmail());
        holder.editTextNationality.setText(skilled_people.getNationality());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewId;
        EditText editTextName;
        EditText editTextAddress;
        EditText editTextTitle;
        EditText editTextPhone;
        EditText editTextEmail;
        EditText editTextNationality;
        Button btn_edit;
        Button btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.ET_id);
            editTextName = itemView.findViewById(R.id.ET_name);
            editTextAddress = itemView.findViewById(R.id.ET_address);
            editTextTitle = itemView.findViewById(R.id.ET_title);
            editTextPhone = itemView.findViewById(R.id.ET_phone);
            editTextEmail = itemView.findViewById(R.id.ET_email);
            editTextNationality = itemView.findViewById(R.id.ET_nationality);

            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
