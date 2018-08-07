package nnk.com.passecure;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class email_detail extends AppCompatActivity {
     EditText et,et6;
     private DatabaseReference mdatabase;
     private FirebaseAuth mAuth;
     private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_detail);
        et=findViewById(R.id.editText);
        et6=findViewById(R.id.editText6);
        mAuth=FirebaseAuth.getInstance();

    }
    public void onStart()
    {

        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }

    public void login(View view) {
        final String email=et.getText().toString().trim();
        final String pass=(et6.getText().toString().trim());
        if(email.isEmpty())
        {
            et.setError("Empty");
            et.requestFocus();
        }
        else
        {
            if(pass.isEmpty())
            {
                et6.setError("Empty");
                et6.requestFocus();
            }
            else
            {
                if(pass.length()<6)
                {
                    et6.setError("Length should be 6");
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        MainActivity.flag=true;
                                        Intent intent = new Intent(email_detail.this,MainActivity.class);
                                        MainActivity.logFlag=1;
                                        intent.putExtra("k1",pass);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(email_detail.this, "Wrong Username or Password",
                                                Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });



                }
            }
        }
    }

    public void register(View view) {
        //MainActivity.flag=true;
        startActivity(new Intent(this,user_detail.class));


    }
    public void onDestroy()
    {
        super.onDestroy();
    }
}
