package com.example.a008broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context, String newsTitle, Boolean newsCollect,
                                   String newsAuthor, String newsContent){
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_collect", newsCollect);
        intent.putExtra("news_author", newsAuthor);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle = getIntent().getStringExtra("news_title");//获取传入的新闻标题
        Boolean newsCollect = getIntent().getBooleanExtra("news_collect", false);//获取传入的新闻收藏与否
        String newsAuthor = getIntent().getStringExtra("news_author");//获取传入的新闻作者
        String newsContent = getIntent().getStringExtra("news_content");//获取传入的新闻内容
        NewsContentFragment newsContentFragment = (NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle, newsCollect, newsAuthor, newsContent);//刷新NewsContentFragment界面
    }
}