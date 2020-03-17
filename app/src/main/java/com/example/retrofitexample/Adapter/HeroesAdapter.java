package com.example.retrofitexample.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample.Api;
import com.example.retrofitexample.Model.Hero;
import com.example.retrofitexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolder> {
    private List<Hero> heroes;
    private Context context;

    public HeroesAdapter(List<Hero> heroes, Context context) {
        this.heroes = heroes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hero hero = heroes.get(position);

        String image_url =hero.getImageurl();


        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .into(holder.heroImage);

        holder.heroName.setText(hero.getName());
        holder.realName.setText(hero.getRealname());
        holder.team.setText(hero.getTeam());
        holder.firstappearance.setText(hero.getFirstappearance());
        holder.createdby.setText(hero.getCreatedby());
        holder.publisher.setText(hero.getPublisher());
        holder.heroDesc.setText(hero.getBio());

    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView heroName;
        public TextView realName;
        public TextView team;
        public TextView firstappearance;
        public TextView createdby;
        public TextView publisher;
        public ImageView heroImage;
        public TextView heroDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heroName = itemView.findViewById(R.id.heroName);
            realName = itemView.findViewById(R.id.realName);
            team = itemView.findViewById(R.id.team);
            firstappearance = itemView.findViewById(R.id.firstappearance);
            createdby = itemView.findViewById(R.id.createdby);
            publisher = itemView.findViewById(R.id.publisher);

            heroImage = itemView.findViewById(R.id.heroImage);

            heroDesc = itemView.findViewById(R.id.heroDesc);

        }
    }
}
