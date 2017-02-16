package allenwang.imovie.recycle_view;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import allenwang.imovie.Constant;
import allenwang.imovie.R;
import allenwang.imovie.model.MovieList;
import allenwang.imovie.model.Result;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by allenwang on 2017/2/15.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<MovieList> lists = new ArrayList<>(); // not used
    private MovieList movies = new MovieList();
    private ArrayList<Result> results;

    public Adapter() {
        movies.setResults(new ArrayList<Result>());
        results = new ArrayList<>();
    }

    public Adapter(ArrayList<MovieList> movies) {
        this.lists = movies;
    }

    public Adapter(MovieList movies) {
        this.movies = movies;
        this.results = (ArrayList<Result>) movies.getResults();
    }

    public void updateData(MovieList movieList) {
        this.movies = movieList;
        this.results = (ArrayList<Result>) movieList.getResults();
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
        String posterUrl = Constant.IMAGE_URL + results.get(position).getPosterPath();
        String backdropUrl = Constant.IMAGE_URL + results.get(position).getBackdropPath();


        viewHolder.txtViewTitle.setText(movies.getResults().get(position).getOriginalTitle());
        viewHolder.txtViewContent.setText(movies.getResults().get(position).getOverview());

        Context context = viewHolder.imgViewPoster.getContext();
        Picasso.with(context).cancelRequest(viewHolder.imgViewPoster);
        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(context)
                    .load(posterUrl)
                    .placeholder(R.drawable.default_movie)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(viewHolder.imgViewPoster);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(context)
                    .load(backdropUrl)
                    .placeholder(R.drawable.default_movie)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(viewHolder.imgViewPoster);
        }
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