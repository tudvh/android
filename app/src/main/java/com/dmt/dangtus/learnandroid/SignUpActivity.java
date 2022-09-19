package com.dmt.dangtus.learnandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    TextView txtLogin;
    EditText edtName, edtUserName, edtPassword, edtEdtPasswordConfirm;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anhXa();

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkEmpty(edtName);
                checkUserName();
                checkPassword();
                checkPasswordConfirm();

                if(edtName.getError() == null && edtUserName.getError() == null && edtPassword.getError() == null && edtEdtPasswordConfirm.getError() == null) {
                    showSuccessDialog();
                } else {
                    showErrorDialog();
                }
            }
        });
    }

    private boolean checkEmpty(EditText edtDuLieu) {
        if(edtDuLieu.getText().toString().trim().isEmpty()) {
            edtDuLieu.setError("Không được để trống ô này");
            edtDuLieu.setBackgroundResource(R.drawable.background_input_text_error);
            return true;
        } else {
            edtDuLieu.setError(null);
            edtDuLieu.setBackgroundResource(R.drawable.background_input_text);
            return false;
        }
    }

    private void checkUserName() {
        if(!checkEmpty(edtUserName)) {
            if(edtUserName.getText().toString().trim().length() < 8) {
                edtUserName.setError("Vui lòng nhập đủ 8 kí tự");
                edtUserName.setBackgroundResource(R.drawable.background_input_text_error);
            }
        }
    }

    private void checkPassword() {
        if(!checkEmpty(edtPassword)) {
            if(edtPassword.getText().toString().trim().length() < 8) {
                edtPassword.setError("Vui lòng nhập đủ 8 kí tự");
                edtPassword.setBackgroundResource(R.drawable.background_input_text_error);
            }
        }
    }

    private void checkPasswordConfirm() {
        if(!checkEmpty(edtEdtPasswordConfirm)) {
            String password = edtPassword.getText().toString().trim();
            String passwordConfirm = edtEdtPasswordConfirm.getText().toString().trim();
            if(!password.equals(passwordConfirm)) {
                edtEdtPasswordConfirm.setError("Mật khẩu bạn vừa nhập không đúng");
                edtEdtPasswordConfirm.setBackgroundResource(R.drawable.background_input_text_error);
            }
        }
    }

    private void showSuccessDialog() {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Thông báo!");
        alertBuilder.setIcon(R.mipmap.ic_launcher);
        alertBuilder.setMessage("Đăng kí thành công, vui lòng đăng nhập lại để truy cập hệ thống");
        alertBuilder.setCancelable(false);

        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentSignUp = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intentSignUp);
            }
        });

        alertBuilder.show();
    }

    private void showErrorDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Lỗi!");
        alertBuilder.setIcon(R.mipmap.ic_launcher);
        alertBuilder.setMessage("Đăng kí không thành công, vui lòng kiểm tra các lỗi và thử lại");
        alertBuilder.setCancelable(false);

        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertBuilder.show();
    }

    private void anhXa() {
        txtLogin = (TextView) findViewById(R.id.loginLayout);
        btnSignUp = (Button) findViewById(R.id.signUpButton);

        edtName = (EditText) findViewById(R.id.fullNameEditText);
        edtUserName = (EditText) findViewById(R.id.userNameEditText);
        edtPassword = (EditText) findViewById(R.id.passwordEditText);
        edtEdtPasswordConfirm = (EditText) findViewById(R.id.confirmPasswordEditText);
    }
}