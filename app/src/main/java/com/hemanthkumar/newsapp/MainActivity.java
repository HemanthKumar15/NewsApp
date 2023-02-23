package com.hemanthkumar.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hemanthkumar.newsapp.Models.Articles;
import com.hemanthkumar.newsapp.Models.NewsApiResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView newsView;
    Adapter adapter;
    Toolbar toolbar;
    ProgressDialog dialog;
    RequestManager manager;
    TextView warnTxt;
    ImageView warnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        newsView = findViewById(R.id.newsView);
        warnImg = findViewById(R.id.warn_img);
        warnTxt = findViewById(R.id.warn_txt);
        newsView.setHasFixedSize(true);
        newsView.setLayoutManager(new GridLayoutManager(this,1));
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching Articles");
        dialog.show();
        setSupportActionBar(toolbar);
// setting icon
        getSupportActionBar().setIcon(R.drawable.ticon);
        manager = new RequestManager(this);
        manager.getArticles(listener, null);


    }
    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<Articles> list, String message) {
            dialog.dismiss();
            showNews(list);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            newsView.setVisibility(View.GONE);
            warnImg.setVisibility(View.VISIBLE);
            warnTxt.setVisibility(View.VISIBLE);
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolmenu,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                manager.getArticles(listener, query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                manager.getArticles(listener, newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private void showNews(List<Articles> list) {
        adapter = new Adapter(this, list);
        newsView.setAdapter(adapter);
    }
}