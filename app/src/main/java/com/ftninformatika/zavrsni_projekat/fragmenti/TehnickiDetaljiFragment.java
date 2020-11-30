package com.ftninformatika.zavrsni_projekat.fragmenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ftninformatika.zavrsni_projekat.R;
import com.ftninformatika.zavrsni_projekat.net.Film;
import com.ftninformatika.zavrsni_projekat.net.Info;
import com.ftninformatika.zavrsni_projekat.net.MyHelper;
import com.ftninformatika.zavrsni_projekat.net.MyNetInterface;
import com.ftninformatika.zavrsni_projekat.net.Search;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TehnickiDetaljiFragment extends Fragment {
    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvPlot;
    private ImageView imageView;
    private String id;
    private Film film;
    private RatingBar ratingBar;
    private Button button;
    private MyHelper myhelper;

    public TehnickiDetaljiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tehnicki_detalji, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle=view.findViewById(R.id.tvTitle);
        tvYear=view.findViewById(R.id.tvYear);
        tvPlot=view.findViewById(R.id.tvPlot);
        imageView=view.findViewById(R.id.ivDetalji);
        id=getArguments().getString(Info.BUNDLE_ID);
        ratingBar=view.findViewById(R.id.ratingbar);
        ratingBar.setRating(5);
        button=view.findViewById(R.id.btnobrisi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    Search search= getMyhelper().getSearchDao().queryBuilder()
//                            .where().eq(Search.FieldId,id).queryForFirst();
//                    getMyhelper().getSearchDao().delete(search);
//                    Toast.makeText(getActivity(), "Obrisano", Toast.LENGTH_SHORT).show();
//                } catch (SQLException e) {
//                    Toast.makeText(getActivity(), e.getErrorCode(), Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }

            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Info.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyNetInterface myNetInterface = retrofit.create(MyNetInterface.class);
        Call<Film>filmCall=myNetInterface.getFilmById(Info.API_Key,id);
        filmCall.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                }
                film=response.body();
                tvTitle.setText(film.getTitle());
                tvYear.setText(film.getYear());
                tvPlot.setText(film.getPlot());
                Picasso.get().load(film.getPoster()).into(imageView);
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public MyHelper getMyhelper(){
        if (myhelper==null){
            myhelper= OpenHelperManager.getHelper(getActivity(),MyHelper.class);
        }
        return  myhelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myhelper!= null){
            OpenHelperManager.releaseHelper();
            myhelper=null;
        }
    }
}