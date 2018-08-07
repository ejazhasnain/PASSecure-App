package nnk.com.passecure;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class passcode extends AppCompatActivity {
    EditText et1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        et1=findViewById(R.id.editText8);
        mAuth=FirebaseAuth.getInstance();
    }

    public void verify(View view) {
        final String password=et1.getText().toString().trim();
        String email=mAuth.getCurrentUser().getEmail().toString();
        if(password.isEmpty())
        {
            et1.setError("Empty");
            et1.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                MainActivity.flag = true;
                                MainActivity.logFlag=1;
                                Intent intent = new Intent(passcode.this, MainActivity.class);
                                intent.putExtra("k1",password);
                                startActivity(intent);
                                finish();
                            } else {

                                Toast.makeText(passcode.this, "Wrong Passcode",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    public void onDestroy()
    {
        super.onDestroy();
        finish();
    }
}
