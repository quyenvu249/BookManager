package com.example.bookmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanager.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LogInActivity extends AppCompatActivity {
    EditText edName, edPass;
    Button btnLogin, btnCancel, btnPHP;
    CheckBox chkpass;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        edName = findViewById(R.id.edName);
        edPass = findViewById(R.id.edPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkpass = findViewById(R.id.chkPass);
        //...
        tvResult = findViewById(R.id.tvResult);
        btnPHP = findViewById(R.id.btnPHP);
        //...
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edName.setText("");
                edPass.setText("");
            }
        });
    }

    public int checkUP(String userName, String passWord) {
        if (userName.equals("admin") && passWord.equals("admin")) {
            return 1;
        } else {
            return -1;
        }

    }

    String strU, strP;

    public void checkLogin(View view) {
        strU = edName.getText().toString();
        strP = edPass.getText().toString();
        if (strU.equals("") || strP.equals("")) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if (checkUP(strU, strP) > 0) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                //finish();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LogInActivity.this, MainActivity.class));
                        finish();
                    }
                }, 1000);
            } else {
                Toast.makeText(getApplicationContext(), "Username hoặc Password sai", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void checkPassword(String username, String pass, boolean sts) {
        //tạo file để lưu trữ
        SharedPreferences sharedPreferences = getSharedPreferences("User_file", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sts == false) {
            editor.clear();
        } else {
            editor.putString("Username", username);
            editor.putString("Password", pass);
            editor.putBoolean("Remember", sts);
        }
        editor.commit();
    }

    public void checkPass(View view) {
        String u = edName.getText().toString();
        String p = edPass.getText().toString();
        Boolean sts = chkpass.isChecked();
        checkPassword(u, p, sts);
    }

    public void readData(View view) {
        HttpReadFromPHP httpReadFromPHP = new HttpReadFromPHP();
        httpReadFromPHP.execute();
    }

    public class HttpReadFromPHP extends AsyncTask<Void, Void, String> {
        //khai báo chuỗi kết nối api
        public static final String SERVER = "http://192.168.1.5//serverphp/readJSONapi.php";//cmd : ipconfig để lấy địa chỉ ip
        //kết quả đọc dữ liệu
        String result;
        String rLine;

        //thực hiện lấy dữ liệu từ api
        @Override
        protected String doInBackground(Void... voids) {
            URL url = null;
            try {
                url = new URL(SERVER);//lẫy chuỗi kết nối từ server
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//mở kết nối
                httpURLConnection.connect();//thực hiện kết nối
                //đọc dữ liệu
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while ((rLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(rLine);
                }
                result = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        //hàm trả về kết quả
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //đưa dữ liệu về client
            tvResult.setText(result);
        }
    }
}
