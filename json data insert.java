package hiren.multicode.language;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import hiren.multicode.R;

public class Newuser extends AppCompatActivity implements View.OnClickListener
{
    Button btn1;
    EditText editText1,editText2,editText3,editText4;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);

        btn1=findViewById(R.id.f);
        editText1=findViewById(R.id.a);
        editText2=findViewById(R.id.b);
        editText3=findViewById(R.id.c);
        editText4=findViewById(R.id.d);
        pd = new ProgressDialog(this);
        pd.setMessage("please wait... ");

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == btn1)
        {
            final String username = editText1.getText().toString().trim();
            final String email = editText2.getText().toString().trim();
            final String phone = editText3.getText().toString().trim();
            final String password = editText4.getText().toString().trim();

            if (username.length()==0 && email.length()==0 && phone.length()==0 && password.length()==0)
            {
                editText1.setError("plz enter username");
                editText2.setError("plz enter email");
                editText3.setError("plz enter phone no");
                editText4.setError("plz enter password");
            }
            else if (username.length()==0)
            {
                editText1.setError("plz enter username");
            }
            else if (email.length()==0)
            {
                editText2.setError("plz enter email");
            }
            else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            {
                editText2.setError("plz enter valid email");
            }
            else if (phone.length()==0)
            {
                editText3.setError("plz enter phone no.");
            }
            else
            if (phone.length()<9)
            {
                editText3.setError("plz enter valid number");
            }
            else
            if (phone.length()>11)
            {
                editText3.setError("plz enter valid number");
            }
            else if (password.length()==0)
            {
                editText4.setError("plz enter password");
            }
            else {
                pd.show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        "https://hirendudhat.000webhostapp.com/insert.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                pd.hide();
                                Toast.makeText(Newuser.this, "registration done", Toast.LENGTH_LONG).show();

                                Intent i = new Intent(Newuser.this, Loginscreen.class);
                                startActivity(i);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                pd.hide();
                                Toast.makeText(Newuser.this, "registration fail", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("USERNAME", username);
                        params.put("EMAIL", email);
                        params.put("PHONE", phone);
                        params.put("PASSWORD", password);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }
        }
    }
}
