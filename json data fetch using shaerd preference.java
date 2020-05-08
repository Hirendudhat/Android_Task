package hiren.multicode.language;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import hiren.multicode.R;
import hiren.multicode.openclass.Background;

public class Loginscreen extends AppCompatActivity  {
    Button button;
    TextView textView;
    EditText editText1,editText2;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        button=findViewById(R.id.button);
        textView=findViewById(R.id.e);
        editText1=findViewById(R.id.edit1);
        editText2=findViewById(R.id.edit2);
        progressBar= new ProgressDialog(this);
        progressBar.setMessage("Loding...");
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String name=editText1.getText().toString();
               final String pass=editText2.getText().toString();

               if (name.length()==0 && pass.length()==0)
               {
                   editText1.setError("plz enter username");
                   editText2.setError("plz enter password");
               }
               else if (name.length()==0)
               {
                   editText1.setError("plz enter username");
               }
               else if (pass.length()==0)
               {
                   editText2.setError("plz enter password");
               }
               else {
                   progressBar.show();
                   StringRequest request =new StringRequest(Request.Method.POST, "https://hirendudhat.000webhostapp.com/login.php", new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           progressBar.hide();
                           if (response.trim().equals("0"))
                           {
                               Toast.makeText(Loginscreen.this, "User Not Found", Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               Background background=new Background(getApplicationContext());
                               background.setName(name);
                               Intent intent1=new Intent(Loginscreen.this,MainActivity.class);
                               startActivity(intent1);
                           }
                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           progressBar.hide();
                           Toast.makeText(Loginscreen.this, "No Internet", Toast.LENGTH_SHORT).show();
                       }
                   })
                   {
                       @Override
                       protected Map<String, String> getParams() throws AuthFailureError {
                           HashMap map=new HashMap();
                           map.put("USERNAME",name);
                           map.put("PASSWORD",pass);
                           return map;
                       }
                   };
                   RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                   requestQueue.add(request);
               }
           }
       });

       textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Loginscreen.this,Newuser.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}