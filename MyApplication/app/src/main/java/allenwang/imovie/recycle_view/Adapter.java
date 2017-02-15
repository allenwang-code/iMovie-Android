package allenwang.imovie.recycle_view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import allenwang.imovie.R;
import allenwang.imovie.model.MovieList;
import allenwang.imovie.model.Result;

/**
 * Created by allenwang on 2017/2/15.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<MovieList> lists = new ArrayList<>();
    private MovieList movies = new MovieList();
    private ArrayList<Result> results;

    public Adapter(ArrayList<MovieList> movies) {
        this.lists = movies;
    }

    public Adapter(MovieList movies) {
        this.movies = movies;
        this.results = (ArrayList<Result>) movies.getResults();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_main, null);

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.txtViewTitle.setText(movies.getResults().get(position).getOriginalTitle());
        viewHolder.txtViewTitle.setText(movies.getResults().get(position).getOverview());
        Picasso.with(viewHolder.imgViewPoster.getContext()).cancelRequest(viewHolder.imgViewPoster);
        Picasso.with(viewHolder.imgViewPoster.getContext())
                .load(results.get(position).getPosterPath()).into(viewHolder.imgViewPoster);
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return results.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtViewTitle;
        public TextView txtViewContent;
        public ImageView imgViewPoster;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            itemLayoutView.setOnClickListener(this);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.title);
            txtViewContent = (TextView) itemLayoutView.findViewById(R.id.content);
            imgViewPoster = (ImageView) itemLayoutView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}