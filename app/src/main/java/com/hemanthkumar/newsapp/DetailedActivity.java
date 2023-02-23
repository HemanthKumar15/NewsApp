package com.hemanthkumar.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemanthkumar.newsapp.Models.Articles;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {
    private TextView title,source,content,desc;
    private ImageView image;
    private Articles articles;
    private Button fullArticleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        title = findViewById(R.id.title_txt);
        source = findViewById(R.id.source_txt);
        content = findViewById(R.id.content_txt);
        desc = findViewById(R.id.desc_txt);
        image = findViewById(R.id.image);
        fullArticleBtn = findViewById(R.id.full_article_btn);
        articles = (Articles) getIntent().getSerializableExtra("data");
        title.setText(articles.getTitle());
        source.setText("Source: "+articles.getSource().getName());
        desc.setText("Description: "+articles.getDescription());
        content.setText(articles.getContent());
        if(articles.getUrlToImage() != null){
            Picasso.get()
                    .load(articles.getUrlToImage())
                    .error(R.drawable.ic_error_24)
                    .into(image);
        }
        else{
            image.setImageResource(R.drawable.ic_no_image_24);
        }
        fullArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("url",articles.getUrl());
                startActivity(intent);

            }
        });
    }
}