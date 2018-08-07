package nnk.com.passecure;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import de.hdodenhof.circleimageview.CircleImageView;

public class add_detail extends AppCompatActivity {
        TextView tv;
        private CircleImageView image;
        private FirebaseUser mUser;
        private FirebaseAuth mAuth;
        private DatabaseReference mDatabase;
        EditText et1,et2,et3;
        private String text;
        private int c=0;
        private ProgressDialog mprogress;
        private static String pass;
        private static String website,password,username;
        private static String uid;
        private HashMap<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail);
        tv=findViewById(R.id.tv);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        pass=b.getString("k3");
        mprogress=new ProgressDialog(this);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.editText2);
      //  et2.setSelection(et2.getText().length());
        et3=findViewById(R.id.editText3);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        uid=mUser.getUid();
        text=b.getString("k1");
        tv.setText(text);
        int img=b.getInt("k2");
        image=findViewById(R.id.image);
        image.setImageResource(img);
        map=new HashMap<String, String>();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");

    }


    public void reset(View view) {

        et1.setText("");
        et2.setText("");
        et3.setText("");
        et1.requestFocus();
    }

    public void save_data(View view) {
        website=et1.getText().toString().trim();
        username=et2.getText().toString().trim();
        password=et3.getText().toString().trim();
        //Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        if(website.isEmpty())
        {
            et1.setError("Empty");
            et1.requestFocus();
        }
        else
        {
            if(username.isEmpty())
            {
                et2.setError("Empty");
                et2.requestFocus();
            }
            else
            {
                if(password.isEmpty())
                {
                    et3.setError("Empty");
                    et3.requestFocus();
                }
                else
                {
                    mprogress.setMessage("Saving Data");
                    mprogress.setCanceledOnTouchOutside(false);
                    mprogress.show();
                    password=new Encrypt().one(pass,password);
                    upload(website,password,username);


                }
            }
        }
    }

    public void upload(final String website, final String password, final String username) {
        map.put("Username", username);
        map.put("Password", password);
        map.put("Website_Name",website);
        mDatabase.child(uid).child("Websites").child(text).child(website).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(add_detail.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    mprogress.dismiss();
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et1.requestFocus();
//                    MainActivity.logFlag=0;
//                    MainActivity.flag=true;
//                    startActivity(new Intent(add_detail.this,MainActivity.class));
//                    finish();
//                    Intent i=new Intent(add_detail.this,show_details.class);
//                    i.putExtra("k1",website);
//                    i.putExtra("k2",username);
//                    i.putExtra("k3",password);
//                    startActivity(i);
//                    finish();

                }
            }
        });
    }



}

