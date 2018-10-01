package com.example.maveetha.passwordapp;


import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

public class Login extends Activity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    Button login;
    Button registerr;
    EditText enterpassword;
    TextView forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.login_btn);
        registerr=(Button)findViewById(R.id.register_btn);
        enterpassword=(EditText)findViewById(R.id.password_edt);
        forgetpass=(TextView)findViewById(R.id.textView2);

        loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        loginDataBaseAdapter.open();

        registerr.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i=new Intent(Login.this,Registration.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String Password=enterpassword.getText().toString();

                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(Password);

                if(Password.equals(storedPassword))
                {
                    Toast.makeText(Login.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
                    Intent ii=new Intent(Login.this,Simple_NotepadActivity.class);
                    startActivity(ii);
                }
                else
                if(Password.equals("")){
                    Toast.makeText(Login.this, "Please Enter Your Password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Login.this, "Password Incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

        forgetpass.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                final Dialog dialog = new Dialog(Login.this);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forget_search);
                dialog.show();

                final  EditText security=(EditText)dialog.findViewById(R.id.securityhint_edt);
                final  TextView getpass=(TextView)dialog.findViewById(R.id.textView3);

                Button ok=(Button)dialog.findViewById(R.id.getpassword_btn);
                Button cancel=(Button)dialog.findViewById(R.id.cancel_btn);

                ok.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        String userName=security.getText().toString();
                        if(userName.equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Please enter your securityhint", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String storedPassword=loginDataBaseAdapter.getAllTags(userName);
                            if(storedPassword==null)
                            {
                                Toast.makeText(getApplicationContext(), "Please enter correct securityhint", Toast.LENGTH_SHORT).show();
                            }else{
                                Log.d("GET PASSWORD",storedPassword);
                                getpass.setText(storedPassword);
                            }
                        }
                    }
                });
                cancel.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
    public void google(View V){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com.kh"));
        startActivity(browserIntent);
    }

}
