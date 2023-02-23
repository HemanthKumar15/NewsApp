package com.hemanthkumar.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hemanthkumar.newsapp.Models.Articles;
import com.hemanthkumar.newsapp.ViewHolders.ArticlesViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ArticlesViewHolder>{
    private Context context;
    private List<Articles> articles;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticlesViewHolder(LayoutInflater.from(context).inflate(R.layout.articles_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title_txt.setText(articles.get(position).getTitle());
        holder.sourcentime_txt.setText(articles.get(position).getSource().getName());
        if(articles.get(position).getUrlToImage() != null){
            Picasso.get()
                    .load(articles.get(position).getUrlToImage())
                    .error(R.drawable.ic_error_24)
                    .into(holder.image);
        }
        else{
            holder.image.setImageResource(R.drawable.ic_no_image_24);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckArticle(articles.get(position))) {
                    context.startActivity(new Intent(context, DetailedActivity.class)
                            .putExtra("data", articles.get(position)));
                }
                else {
                    Toast.makeText(context.getApplicationContext(), "This article is currently not available, please try again after sometimes", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private boolean CheckArticle(Articles article) {
        if(article.getDescription()==null || article.getTitle() == null || article.getSource() == null || article.getContent() == null){
            return false;
        }

        return true;
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
