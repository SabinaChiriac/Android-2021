package com.example.childhoodfriends.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.childhoodfriends.R;
import com.example.childhoodfriends.models.dbEntities.PersonItemElement;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>{

    ArrayList<PersonItemElement> personList;

    public PersonAdapter(ArrayList<PersonItemElement> personList) { this.personList = personList; }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_person, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(view);

        Log.e("PersonAdapter", "onCreateViewHolder");

        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        PersonItemElement personItemElement = personList.get(position);
        holder.bind(personItemElement);

        Log.e("PersonAdapter", "onBindViewHolder: " + position);
    }

    @Override
    public int getItemCount() {
        return this.personList.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView city;
        private TextView neighborhood;
        private TextView email;
        private TextView school;
        private TextView description;
        private TextView frequented_places;
        private TextView birthday;

        PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            city = itemView.findViewById(R.id.city);
            neighborhood = itemView.findViewById(R.id.neighborhood);
            email = itemView.findViewById(R.id.email);
            school = itemView.findViewById(R.id.school);
            description = itemView.findViewById(R.id.description);
            frequented_places = itemView.findViewById(R.id.frequented_places);
            birthday = itemView.findViewById(R.id.birthday);
        }

        void bind(PersonItemElement personItemElement) {
            name.setText(personItemElement.getName());
            city.setText(personItemElement.getCity());
            neighborhood.setText(personItemElement.getNeighborhood());
            email.setText(personItemElement.getEmail());
            school.setText(personItemElement.getSchool());
            description.setText(personItemElement.getDescription());
            frequented_places.setText(personItemElement.getFreq_places());
            birthday.setText(personItemElement.getBirthday());
        }
    }
}
