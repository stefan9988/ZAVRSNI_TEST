package com.ftninformatika.zavrsni_projekat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ftninformatika.zavrsni_projekat.fragmenti.DodavanjeFilmaFragment;
import com.ftninformatika.zavrsni_projekat.fragmenti.PregledFilmovaFragment;
import com.ftninformatika.zavrsni_projekat.net.Film;
import com.ftninformatika.zavrsni_projekat.net.Info;
import com.ftninformatika.zavrsni_projekat.net.MyNetInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new PregledFilmovaFragment()).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.add){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,new DodavanjeFilmaFragment())
                    .addToBackStack(null).commit();
        }
        else if (item.getItemId()==R.id.settings){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,new SettingsFr())
                    .addToBackStack(null).commit();
        }
        return true;
    }
}