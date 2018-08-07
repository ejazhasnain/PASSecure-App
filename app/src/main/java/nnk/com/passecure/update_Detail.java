package nnk.com.passecure;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class update_Detail extends AppCompatActivity {
    EditText et9,et10;
    private String title,web,user,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__detail);
        et9=findViewById(R.id.editText10);
        et10=findViewById(R.id.editText11);
        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        title=b.getString("k1");
        web=b.getString("k2");
        user=b.getString("k3");
        password=b.getString("k4");
        et9.setText(user);
        et10.setText(password);
    }

    public void save_Edit(View view) {
          user=et9.getText().toString().trim();
          password=et10.getText().toString().trim();

              if(user.isEmpty())
              {
                  et9.setError("Empty");
                  et9.requestFocus();
              }
              else
              {
                  if(password.isEmpty())
                  {
                      et10.setError("Empty");
                      et10.requestFocus();
                  }
                  else
                  {
                      String pass=new MainActivity().sendPass();
                      password=new Encrypt().one(pass,password);
                      FirebaseAuth mAuth=FirebaseAuth.getInstance();
                      FirebaseUser mUser=mAuth.getCurrentUser();
                      String uid=mUser.getUid();
                      DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Websites").child(retrieve.title).child(web);
                      mDatabase.child("Password").setValue(password);
                      mDatabase.child("Username").setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                              if(task.isSuccessful())
                              {
                                  Toast.makeText(update_Detail.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
//                                  MainActivity.logFlag=0;
//                                  MainActivity.flag=true;
//                                  startActivity(new Intent(update_Detail.this,MainActivity.class));
//                                  finish();
                                  et9.setText("");
                                  et10.setText("");
                                  et9.requestFocus();
                              }
                              else
                              {
                                  Toast.makeText(update_Detail.this, "Updation Failed", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
                  }
              }
          }


 }
