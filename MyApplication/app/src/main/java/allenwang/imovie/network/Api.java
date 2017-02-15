package allenwang.imovie.network;

import allenwang.imovie.model.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by allenwang on 2017/2/16.
 */

public interface Api {

    @GET("3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<MovieList> getMovies();
}
