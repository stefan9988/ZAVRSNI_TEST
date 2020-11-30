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
import android.widget.Toast;

import com.ftninformatika.zavrsni_projekat.R;
import com.ftninformatika.zavrsni_projekat.adapteri.MyRecAdapter;
import com.ftninformatika.zavrsni_projekat.adapteri.MySecAdapter;
import com.ftninformatika.zavrsni_projekat.net.Info;
import com.ftninformatika.zavrsni_projekat.net.MyHelper;
import com.ftninformatika.zavrsni_projekat.net.Search;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PregledFilmovaFragment extends Fragment implements MySecAdapter.MyOnClickListener1 {
    private RecyclerView recyclerView;
    private MyHelper myhelper=null;
    private List<Search>searchList=new ArrayList<>();


    public PregledFilmovaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregled_filmova, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rvPregledFilmova);

        try {
            searchList =getMyhelper().getSearchDao().queryForAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MySecAdapter adapter=new MySecAdapter(PregledFilmovaFragment.this,searchList);
        recyclerView.setAdapter(adapter);
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


    @Override
    public void MyOnClick1(Search id) {
        TehnickiDetaljiFragment tehnickiDetaljiFragment=new TehnickiDetaljiFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Info.BUNDLE_ID,id.getImdbID());
        tehnickiDetaljiFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,tehnickiDetaljiFragment)
                .addToBackStack(null).commit();
    }
}