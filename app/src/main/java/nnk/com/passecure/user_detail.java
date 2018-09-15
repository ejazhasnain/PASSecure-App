package nnk.com.passecure;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;



public class user_detail extends AppCompatActivity {
    private DatabaseReference mdatabase;
    private ProgressDialog mprogress;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private  String pic=null;
    private HashMap<String,String> map;

    EditText et2,et3,et4,et5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        et2=findViewById(R.id.editText5);
        et3=findViewById(R.id.editText7);
        et4=findViewById(R.id.editText13);
        et5=findViewById(R.id.editText14);
        mprogress=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        map = new HashMap<String, String>();
    }
    public void onStart()
    {
        super.onStart();
        mdatabase= FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public void signUp(View view) {
        final String name=et2.getText().toString().trim();
        final String email=et3.getText().toString().trim();
        final String pass=et4.getText().toString().trim();
        final String mobile=et5.getText().toString().trim();

        if(name.isEmpty())
        {
            et2.setError("Empty");
            et2.requestFocus();
        }
        else
        {
            if(email.isEmpty()) {
                et3.setError("Empty");
                et3.requestFocus();
            }
            else
            {
                if(pass.isEmpty())
                {
                    et4.setError("Empty");
                    et4.requestFocus();
                }
                else
                {
                    if(mobile.isEmpty())
                    {
                        et5.setError("Empty");
                        et5.requestFocus();
                    }
                    else
                    {
                        if(pass.length()<5)
                        {
                            et4.setError("Password is weak");
                        }
                        else
                        {
                            if(mobile.length()>10)
                            {
                                et5.setError("Length should be 10");
                            }
                            else
                            {
                                mprogress.setTitle("Uploading...");
                                mprogress.setMessage("Registration Under Process");
                                mprogress.setCanceledOnTouchOutside(false);
                                mprogress.show();
                                map.put("Name",name);
                                map.put("Email",email);
                                map.put("Mobile_No",mobile);

                                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if(task.isSuccessful()){

                                            mUser=mAuth.getCurrentUser();
                                            final String uid = mUser.getUid();

                                            mdatabase.child(uid).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if(task .isSuccessful()) {
                                                         Toast.makeText(user_detail.this, "Registration success ", Toast.LENGTH_SHORT).show();
                                                         MainActivity.flag=true;
                                                         mprogress.dismiss();
                                                         Intent intent=new Intent(user_detail.this,MainActivity.class);
                                                         intent.putExtra("k1",pass);
                                                         MainActivity.logFlag=1;
                                                         startActivity(intent);
                                                         ActivityCompat.finishAffinity(user_detail.this);
                                                    }
                                                    else {
                                                        String error = task.getException().getMessage();
                                                        Toast.makeText(user_detail.this, "Unable to update database "+ error, Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                        else
                                        {
                                            Toast.makeText(user_detail.this, "Registration failed.. Error :  "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            mprogress.dismiss();
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }


}
