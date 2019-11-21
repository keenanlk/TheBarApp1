package com.example.thebarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class recyclerView_Config {
    private Context mContext;
    private ReviewAdapter mReviewAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Review> reviews, List<String> keys){
        mContext = context;
        mReviewAdapter = new ReviewAdapter(reviews, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mReviewAdapter);
    }

    class ReviewItemView extends RecyclerView.ViewHolder {
        private TextView mComment;

        private String key;

        public ReviewItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.review_list_item, parent, false));

            mComment = (TextView) itemView.findViewById(R.id.comment_txt);
        }

        public void bind(Review review, String key){
            mComment.setText(review.getComment());
            this.key = key;
        }

    }
    class ReviewAdapter extends RecyclerView.Adapter<ReviewItemView>{
        private List<Review> mReviewList;
        private List<String> mKeys;

        public ReviewAdapter(List<Review> mReviewList, List<String> mKeys) {
            this.mReviewList = mReviewList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ReviewItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReviewItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ReviewItemView holder, int position) {
            holder.bind(mReviewList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mReviewList.size();
        }
    }
}
