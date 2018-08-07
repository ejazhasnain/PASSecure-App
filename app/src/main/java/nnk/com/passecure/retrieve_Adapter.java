package nnk.com.passecure;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class retrieve_Adapter extends RecyclerView.Adapter<retrieve_Adapter.ViewHolder>
{
    private Context context;
    private ArrayList<String> web = new ArrayList<String>();
    private ArrayList<String> user = new ArrayList<String>();
    private ArrayList<String> password = new ArrayList<String>();



    public retrieve_Adapter(Context c,ArrayList<String> webs,ArrayList<String> user1,ArrayList<String> password1)
    {
        this.context=c;
        this.web=webs;
        this.user=user1;
        this.password=password1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.retrieve_card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
          holder.tv1.setText(web.get(position));
          holder.tv2.setText("Username :"+user.get(position));
          holder.tv3.setText("Encrypt Password :"+password.get(position));
          holder.button1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                    Intent intent=new Intent(context,update_Detail.class);
                    intent.putExtra("k1",retrieve.title);
                    intent.putExtra("k2",web.get(position));
                    intent.putExtra("k3",user.get(position));
                    String val=new Decrypt().Dec_one(new MainActivity().sendPass(),password.get(position));
                    intent.putExtra("k4",val);
                    context.startActivity(intent);
                   ((Activity)context).finish();

              }
          });
          holder.button2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
                  alertDialog.setTitle("Delete");
                  alertDialog.setMessage("Are you to Delete Data ?");
                  alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which)
                      {

                          FirebaseAuth mAuth=FirebaseAuth.getInstance();
                          FirebaseUser mUser=mAuth.getCurrentUser();
                          String uid=mUser.getUid();
//                  Toast.makeText(context, "uid = "+uid, Toast.LENGTH_SHORT).show();

                          final DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Websites").child(retrieve.title);
                          mDatabase.addValueEventListener(new ValueEventListener() {
                              @Override
                              public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild(web.get(position))){
//
                                        mDatabase.child(web.get(position)).removeValue();
//                                        deleteProgress.dismiss();
                                        Toast.makeText(context, "Deleted Successfully..", Toast.LENGTH_SHORT).show();
                                        //Testing

                                        String val1=new MainActivity().sendPass();
                                        Intent i=new Intent(context,retrieve.class);
                                        i.putExtra("k1",val1);
                                        i.putExtra("k2",retrieve.title);
                                        context.startActivity(i);
                                        ((Activity)context).finish();

                                    }
                              }

                              @Override
                              public void onCancelled(DatabaseError databaseError) {

                              }
                          });

                      }
                  });
                  alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which)
                      {

                      }
                  });
                  alertDialog.setCancelable(false);
                  alertDialog.show();
              }
          });

          holder.button3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
                  alertDialog.setTitle("Retrieve");
                  alertDialog.setMessage("Do you want to see Decrypted Password ?");
                  alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which)
                      {
                           Intent i=new Intent(context,show_details.class);
                           i.putExtra("k1",web.get(position));
                           i.putExtra("k2",user.get(position));
                           String val=new Decrypt().Dec_one(new MainActivity().sendPass(),password.get(position));
                           i.putExtra("k3",val);
                           context.startActivity(i);

                      }
                  });

                  alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which)
                      {

                      }
                  });
                  alertDialog.setCancelable(false);
                  alertDialog.show();
              }
          });
    }

    @Override
    public int getItemCount() {
        return web.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView tv1,tv2,tv3;
         Button button1,button2,button3;
        public ViewHolder(View itemView) {
            super(itemView);
           button1=itemView.findViewById(R.id.button10);
            button2=itemView.findViewById(R.id.button11);
            button3=itemView.findViewById(R.id.button8);
            tv1=itemView.findViewById(R.id.retWebsiteText);
            tv2=itemView.findViewById(R.id.retUserName);
            tv3=itemView.findViewById(R.id.retPassword);
        }
    }
}
