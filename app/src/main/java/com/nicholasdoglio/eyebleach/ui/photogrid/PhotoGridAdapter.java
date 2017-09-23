package com.nicholasdoglio.eyebleach.ui.photogrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class PhotoGridAdapter extends RecyclerView.Adapter<PhotoGridAdapter.ViewHolder> {
    private Context context;
    private List<ChildData> posts;

    PhotoGridAdapter(Context context, List<ChildData> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View regularView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_grid_item, parent, false);
        return new ViewHolder(regularView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.cat_error);

        Glide.with(context).load(posts.get(position).getThumbnail())
                .apply(options)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_grid)
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            imageView.setOnClickListener(view -> Intents.startDetailActivityFromParcelable(view, getAdapterPosition(), (ArrayList<ChildData>) posts));
        }
    }
}
