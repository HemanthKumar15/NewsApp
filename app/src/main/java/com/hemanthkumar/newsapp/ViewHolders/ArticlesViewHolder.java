package com.hemanthkumar.newsapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hemanthkumar.newsapp.R;

import java.text.CollationElementIterator;

public class ArticlesViewHolder extends RecyclerView.ViewHolder {

    public TextView title_txt,sourcentime_txt;
    public ImageView image;
    public CardView cardView;

    public ArticlesViewHolder(@NonNull View itemView) {
        super(itemView);
        title_txt = itemView.findViewById(R.id.title_txt);
        sourcentime_txt = itemView.findViewById(R.id.sourcentime);
        image = itemView.findViewById(R.id.image);
        cardView = itemView.findViewById(R.id.cardView);
    }
}
