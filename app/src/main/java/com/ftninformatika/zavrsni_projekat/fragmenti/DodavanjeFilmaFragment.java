package com.ftninformatika.zavrsni_projekat.fragmenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ftninformatika.zavrsni_projekat.R;
import com.ftninformatika.zavrsni_projekat.net.MyHelper;
import com.ftninformatika.zavrsni_projekat.net.Response;
import com.ftninformatika.zavrsni_projekat.net.Search;
import com.ftninformatika.zavrsni_projekat.adapteri.MyRecAdapter;
import com.ftninformatika.zavrsni_projekat.net.Info;
import com.ftninformatika.zavrsni_projekat.net.MyNetInterface;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DodavanjeFilmaFragment extends Fragment implements MyRecAdapter.MyOnClickListener {
    private EditText etSearch;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private MyHelper myhelper=null;

    public DodavanjeFilmaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dodavanje_filma, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSearch=view.findViewById(R.id.etSearch);
        btnSearch=view.findViewById(R.id.btnSearch);
        recyclerView=view.findViewById(R.id.rvDodavanjeFilma);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search=etSearch.getText().toString();
                if (search.equals("")){
                    Toast.makeText(getActivity(), "Unesite naziv filma", Toast.LENGTH_SHORT).show();
                }else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Info.Base_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    MyNetInterface myNetInterface = retrofit.create(MyNetInterface.class);
                    Call<Response>responseCall=myNetInterface.getFilms(Info.API_Key,search);
                    responseCall.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            if (!response.isSuccessful()){
                                Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                            }
                            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            MyRecAdapter adapter=new MyRecAdapter(DodavanjeFilmaFragment.this,response.body().getSearch());
                            recyclerView.setAdapter(adapter);

                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


    @Override
    public void MyOnClick(Search id) {
        try {
            getMyhelper().getSearchDao().create(id);
            Toast.makeText(getActivity(), id.getTitle()+" uspesno dodat", Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,new PregledFilmovaFragment()).commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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