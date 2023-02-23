package com.hemanthkumar.newsapp;

import com.hemanthkumar.newsapp.Models.Articles;
import com.hemanthkumar.newsapp.Models.NewsApiResponse;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<Articles> list, String message);
    void onError(String message);
}
