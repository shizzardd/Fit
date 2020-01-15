package com.example.goodproject;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodproject.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    Button btnSingIn, btnRegister;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSingIn=findViewById(R.id.btnSignIn);
        btnRegister=findViewById(R.id.btnRegister);

        auth=FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        root = findViewById(R.id.root_element);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 showRegisterWindow();
            }


        });
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingInWindow();
            }


        });
    }

    private void showSingInWindow() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите данные для Входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(sign_in_window);


        final MaterialEditText email = sign_in_window.findViewById(R.id.emailField);
        final MaterialEditText pass = sign_in_window.findViewById(R.id.passField);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root, "Введите Вашу E-mail", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (pass.getText().toString().length()<7){
                    Snackbar.make(root, "Пароль должен состоять минимум из 8 символов", Snackbar.LENGTH_LONG).show();
                    return;
                }

                //Вход
                auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(MainActivity.this, MapActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                     Snackbar.make(root,"Ошибка авторизации"+ e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });

        dialog.show();

    }

    private void showRegisterWindow() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Регистрация");
        dialog.setMessage("Введите все данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);

        final MaterialEditText firstName = register_window.findViewById(R.id.firstNameField);
        final MaterialEditText name = register_window.findViewById(R.id.nameField);
        final MaterialEditText midleName = register_window.findViewById(R.id.midleNameField);
        final MaterialEditText phone = register_window.findViewById(R.id.phoneField);
        final MaterialEditText birhtday = register_window.findViewById(R.id.birhtdayField);
        final MaterialEditText email = register_window.findViewById(R.id.emailField);
        final MaterialEditText pass = register_window.findViewById(R.id.passField);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Зарегистирироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(firstName.getText().toString())){
                    Snackbar.make(root, "Введите Вашу Фамилию", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(root, "Введите Ваш Имя", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(midleName.getText().toString())){
                    Snackbar.make(root, "Введите Ваш Отчетсво", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(root, "Введите Ваш номер телефона", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(birhtday.getText().toString())){
                    Snackbar.make(root, "Введите Вашу дату рождения", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root, "Введите Вашу E-mail", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (pass.getText().toString().length()<7){
                    Snackbar.make(root, "Пароль должен состоять минимум из 8 символов", Snackbar.LENGTH_LONG).show();
                    return;
                }

                //Регистрация

             auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setFirsName(firstName.getText().toString());
                                user.setName(name.getText().toString());
                                user.setMiddleName(midleName.getText().toString());
                                user.setPhone(phone.getText().toString());
                                user.setBirthday(birhtday.getText().toString());
                                user.setEmail(email.getText().toString());
                                user.setPass(pass.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(root, "Пользователь добавлен", Snackbar.LENGTH_SHORT).show();
                                    }

                                });


                            }
                        });
            }
        });

        dialog.show();

    }



}
