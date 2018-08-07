package nnk.com.passecure;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
   private ArrayList<cardView> list;
   private FirebaseAuth mAuth;
   private FirebaseUser mUser;
   public static boolean flag=false;
   public static String pass;
   public static int logFlag=0;


    @Override
    public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            if(logFlag!=0){
                Intent intent = getIntent();
                Bundle b=intent.getExtras();
                pass=b.getString("k1");
            }

            RecyclerView recyclerView = findViewById(R.id.rv);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            list = new ArrayList<cardView>();
            list.add(new cardView(R.drawable.book, "EDUCATION"));
            list.add(new cardView(R.drawable.social, "SOCIAL"));
            list.add(new cardView(R.drawable.entertainment, "ENTERTAINMENT"));
            list.add(new cardView(R.drawable.transportation, "TRANSPORTATION"));
            list.add(new cardView(R.drawable.per, "PERSONAL"));
            list.add(new cardView(R.drawable.others, "OTHERS"));
            mainAdapter adapt = new mainAdapter(this,list);
            recyclerView.setAdapter(adapt);
            mAuth=FirebaseAuth.getInstance();
            mUser=mAuth.getCurrentUser();
    }
    public void onStart()
    {
        super.onStart();
        if(mUser==null)
        {
            gotoStart();
        }
        else
        {
                if(flag==false) {
                    startActivity(new Intent(this, passcode.class));
                    finish();
                }
        }
    }
      public String sendPass()
      {
            return pass;
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_settings,menu);
        return true;

    }

    public void action(MenuItem item) {
        logFlag=0;
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this,email_detail.class));
        finish();

    }


    public void onDestroy()
    {
        logFlag=0;
        flag=false;
        ActivityCompat.finishAffinity(MainActivity.this);
        super.onDestroy();
    }
    public void gotoStart()
    {
        startActivity(new Intent(this,email_detail.class));
        finish();
    }
}
