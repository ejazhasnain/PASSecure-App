package nnk.com.passecure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

public class retrieve extends AppCompatActivity {
    private String pass,uid,u,w,val;
    public static String title;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private int c=0;
    private retrieve_Adapter ra;
    private  ArrayList<String> web=new ArrayList<String>();
    private  ArrayList<String> user = new ArrayList<String>();
    private  ArrayList<String> password=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        pass=b.getString("k1");
        title=b.getString("k2");
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        uid=mUser.getUid();


        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Websites").child(title);
        mDatabase.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String key=dataSnapshot.getKey();
                mDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Websites").child(title).child(key);
                //Exper Start

                w=dataSnapshot.child("Website_Name").getValue().toString();
                u=dataSnapshot.child("Username").getValue().toString();
                val=dataSnapshot.child("Password").getValue().toString();
                web.add(w);
                user.add(u);
                password.add(val);
                ra.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        RecyclerView rV = findViewById(R.id.rv_new);
        ra = new retrieve_Adapter(this, web, user, password);
        rV.setAdapter(ra);
        rV.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onStart()
    {
        super.onStart();
    }




}
