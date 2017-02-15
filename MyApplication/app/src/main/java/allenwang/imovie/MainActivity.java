package allenwang.imovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import allenwang.imovie.model.MovieList;
import allenwang.imovie.recycle_view.Adapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = (RecyclerView) findViewById(R.id.main_list);
        Adapter adapter = new Adapter(new MovieList());
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }
}
