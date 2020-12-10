package com.example.a008broadcastbestpractice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_content,container, false);
        return view;
    }

    public void refresh(String newsTitle, Boolean newsCollect, String newsAuthor, String newsContent){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        ImageView newsCollectText = (ImageView) view.findViewById(R.id.news_collect);
        TextView newsTitleText = (TextView) view.findViewById(R.id.news_title);
        TextView newsAuthorText = (TextView) view.findViewById(R.id.news_author);
        TextView newsContentText = (TextView) view.findViewById(R.id.news_content);
        newsCollectText.setClickable(newsCollect);//刷新新闻的收藏与否
        newsTitleText.setText(newsTitle);//刷新新闻的标题
        newsAuthorText.setText(newsAuthor);//刷新新闻的作者
        newsContentText.setText(newsContent);//刷新新闻的内容
    }
}