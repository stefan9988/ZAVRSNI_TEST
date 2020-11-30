package com.ftninformatika.zavrsni_projekat.adapteri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ftninformatika.zavrsni_projekat.R;
import com.ftninformatika.zavrsni_projekat.net.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MySecAdapter extends RecyclerView.Adapter<MySecAdapter.MyViewHolder1> {
    private List<Search> data;
    private MySecAdapter.MyOnClickListener1 listener;


    public MySecAdapter(MySecAdapter.MyOnClickListener1 listener, List<Search> data) {
        this.data = data;
        this.listener=listener;
    }
    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card,parent,false);
        MyViewHolder1 myViewHolder1=new MyViewHolder1(view);
        return myViewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        holder.bind(listener,data.get(position));
        holder.tvIme.setText(data.get(position).getTitle());
        holder.tvGod.setText(data.get(position).getYear());
        Picasso.get().load(data.get(position).getPoster()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder {
        private TextView tvIme;
        private TextView tvGod;
        private ImageView imageView;
        private View wholeView;
        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            tvIme=itemView.findViewById(R.id.tvCardIme);
            tvGod =itemView.findViewById(R.id.tvCardGod);
            imageView=itemView.findViewById(R.id.ivCard);
            wholeView=itemView;
        }
        public void bind(final MySecAdapter.MyOnClickListener1 listener, final Search search){
            wholeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.MyOnClick1(search);
                }
            });
        }
    }
    public interface MyOnClickListener1 {
        void MyOnClick1(Search id);
    }
}
