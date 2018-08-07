package nnk.com.passecure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class show_details extends AppCompatActivity {
     TextView tv10,tv11,tv12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        tv10=findViewById(R.id.textView10);
        tv11=findViewById(R.id.textView11);
        tv12=findViewById(R.id.textView12);
        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        String web=b.getString("k1");
        String username=b.getString("k2");
        String pass=b.getString("k3");
        tv10.setText(web);
        tv11.setText("Username :"+username);
        tv12.setText("Decrypted Password :"+pass);
    }
}
