package com.ftninformatika.zavrsni_projekat.adapteri;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ftninformatika.zavrsni_projekat.R;
import com.ftninformatika.zavrsni_projekat.net.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.MyViewHolder>{
    private List<Search> data;
    private MyOnClickListener listener;


    public MyRecAdapter(MyOnClickListener listener, List<Search> data) {
        this.data = data;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(listener,data.get(position));
        holder.tvIme.setText(data.get(position).getTitle());
        holder.tvGod.setText(data.get(position).getYear());
        Picasso.get().load(data.get(position).getPoster()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIme;
        private TextView tvGod;
        private ImageView imageView;
        private  View wholeView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIme=itemView.findViewById(R.id.tvCardIme);
            tvGod =itemView.findViewById(R.id.tvCardGod);
            imageView=itemView.findViewById(R.id.ivCard);
            wholeView=itemView;
        }
        public void bind(final MyOnClickListener listener, final Search search){
            wholeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.MyOnClick(search);
                }
            });
        }
    }
    public interface MyOnClickListener {
        void MyOnClick(Search id);
    }

}

