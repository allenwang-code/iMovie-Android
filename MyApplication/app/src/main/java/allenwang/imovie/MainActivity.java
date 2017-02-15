package allenwang.imovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.TimeUnit;

import allenwang.imovie.model.MovieList;
import allenwang.imovie.recycle_view.Adapter;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = (RecyclerView) findViewById(R.id.main_list);
        Adapter adapter = new Adapter(new MovieList());
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS);//设置写入超时时间
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(getApplication().getCacheDir(), cacheSize);
        builder.cache(cache);
        OkHttpClient mOkHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
    }
}
