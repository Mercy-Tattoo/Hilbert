package com.example.a008broadcastbestpractice.ui.login;

import android.app.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a008broadcastbestpractice.DBOpenHelper;
import com.example.a008broadcastbestpractice.MainActivity;
import com.example.a008broadcastbestpractice.R;
import com.example.a008broadcastbestpractice.RegisterActivity;
import com.example.a008broadcastbestpractice.User;
import com.example.a008broadcastbestpractice.ui.login.LoginViewModel;
import com.example.a008broadcastbestpractice.ui.login.LoginViewModelFactory;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private Button register;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDBOpenHelper = new DBOpenHelper(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember){
            //将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {//TextUtils.isEmpty（）输入框是空值或者你就敲了几下空格键该方法都会返回true
                    ArrayList<User> data = mDBOpenHelper.getAllData();//data为获取的user表内的user信息
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {//遍历比较
                        User user = data.get(i);//获取data里的第i个user信息
                        if (account.equals(user.getName()) && password.equals(user.getPassword())) {//将信息与输入的信息进行对比
                            editor = pref.edit();
                            if (rememberPass.isChecked()) { // 检查复选框是否被选中
                                editor.putBoolean("remember_password", true);
                                editor.putString("account", account);
                                editor.putString("password", password);
                            } else {
                                editor.clear();
                            }
                            editor.apply();
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    // 跳转到注册界面
                    case R.id.register:
                        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}