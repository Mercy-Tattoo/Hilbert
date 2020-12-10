package com.example.a008broadcastbestpractice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        RecyclerView newsTitleRecycleView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecycleView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecycleView.setAdapter(adapter);
        return view;
    }
    private List<FragmentNews> getNews(){
        List<FragmentNews> newsList = new ArrayList<>();
        for(int i = 1; i <= 50; i++){
            FragmentNews news = new FragmentNews();
            news.setTitle("标题" + i);
            news.setAuthor(getAuthor());
            news.setContent(getRandomLengthContent("这是一条新的内容" + i + "。"));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++){
            builder.append(content);
        }
        return builder.toString();
    }
    private String getAuthor(){
        Random random = new Random();
        String author = "作者" + random;
        return author;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<FragmentNews> mNewList;

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View view){
                super(view);
                newsTitleText = (TextView) view.findViewById(R.id.news_title);
            }
        }
        public NewsAdapter(List<FragmentNews> newsList){
            mNewList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentNews news = mNewList.get(holder.getAdapterPosition());
                    //如果是单页模式，则直接启动NewsContentActivity
                    NewsContentActivity.actionStart(getActivity(),
                            news.getTitle(), news.getCollect(), news.getAuthor(), news.getContent());
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            FragmentNews news = mNewList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewList.size();
        }
    }
}