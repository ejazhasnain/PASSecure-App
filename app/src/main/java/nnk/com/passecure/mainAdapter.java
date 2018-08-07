package nnk.com.passecure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.ViewHolder>
{
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private ArrayList<cardView> list;
    Context context;
    private static ArrayList<String> web=new ArrayList<String>();
    private static ArrayList<String> user = new ArrayList<String>();
    private static ArrayList<String> password=new ArrayList<String>();


    public mainAdapter(Context c,ArrayList <cardView> map)
    {
        context=c;
        list=map;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final cardView cv=list.get(position);
            ImageView img;
            TextView tv;
            Button b1,b2;

            img=holder.imageView;
            tv=holder.textView;
            String pass1=new MainActivity().sendPass();


            img.setImageResource(cv.getImg());
            tv.setText(cv.getTitle());
            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String val1=new MainActivity().sendPass();
                    Intent i=new Intent(context,add_detail.class);
                    i.putExtra("k1",cv.getTitle());
                    i.putExtra("k2",cv.getImg());
                    i.putExtra("k3",val1);
                    String s=cv.getTitle();
                    MainActivity.flag=true;
                    context.startActivity(i);

                }
            });
            holder.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   String val1=new MainActivity().sendPass();
                    Intent i=new Intent(context,retrieve.class);
                    i.putExtra("k1",val1);
                    i.putExtra("k2",cv.getTitle());
                    context.startActivity(i);
                }
            });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        Button button1,button2;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.tv);
            button1=itemView.findViewById(R.id.b1);
            button2 =itemView.findViewById(R.id.b2);
        }


    }
}