package com.prom.eazy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.prom.eazy.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignActivity extends AppCompatActivity implements ServerDialog.ExampleDialogListener {
    private static Context context;
    private ProgressBar loadingProgressBar;
    private EditText usernameEditText ;
    private EditText nameEditText ;
    private EditText secondNameEditText ;
    private EditText phoneEditText ;

    private EditText passwordEditText ;
    private EditText confPasswordEditText ;

    private Button create ;
    private TextView errTextView;
    private TextView log;

    private int enableButton = 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignActivity.context = getApplicationContext();

        setContentView(R.layout.activity_sign);


        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        confPasswordEditText = findViewById(R.id.conf_password);
        phoneEditText = findViewById(R.id.phone);
        nameEditText = findViewById(R.id.name);
        secondNameEditText = findViewById(R.id.second_name);
        create = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);
        errTextView = findViewById(R.id.err);
        log = findViewById(R.id.sign);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this,com.prom.eazy.ui.login.LoginActivity.class);
                startActivity(intent);
            }
        });

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    //loginViewModel.login(usernameEditText.getText().toString(),
                    //passwordEditText.getText().toString());
                    if (!SharedPref.getInstance(LoginActivity.getAppContext()).isServerOn()){
                        openDialog();
                    }
                    else{
                        try {
                            fun(usernameEditText.getText().toString(),
                                    passwordEditText.getText().toString(),
                                    phoneEditText.getText().toString(),
                                    nameEditText.getText().toString(),
                                    secondNameEditText.getText().toString());
                            Log.d("khraa","TRY");

                        }catch (Exception e){
                            Log.d("khraa","CATCH");

                        }
                    }

                }
                return false;
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) { Log.d("khraa","onclick");
            loadingProgressBar.setVisibility(View.VISIBLE);
            //loginViewModel.login(usernameEditText.getText().toString(),
            //passwordEditText.getText().toString());
            if (!SharedPref.getInstance(SignActivity.getAppContext()).isServerOn()){
                openDialog();
            }
            else{
                try {
                    fun(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(),
                            phoneEditText.getText().toString(),
                            nameEditText.getText().toString(),
                            secondNameEditText.getText().toString());
                    Log.d("khraa"," try onclick loginBtn");

                }catch (Exception e){
                    Log.d("khraa","catch onclick loginBtn");

                }
            }


        }
    });

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

                isUserNameValid(usernameEditText.getText().toString());
                //enable the button create
                create.setEnabled(isPasswordValid(passwordEditText.getText().toString())&&
                        isConfPasswordValid(passwordEditText.getText().toString(),
                                confPasswordEditText.getText().toString()) &&
                        isNameValid(nameEditText.getText().toString()) &&
                        isNameValid(secondNameEditText.getText().toString()) &&
                        !usernameEditText.getText().toString().trim().isEmpty()
                );

            }
        });


        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

               if(!isPasswordValid(passwordEditText.getText().toString()))
                   passwordEditText.setError("Mot de passe non valid");
                //enable the button create
                create.setEnabled(isPasswordValid(passwordEditText.getText().toString())&&
                        isConfPasswordValid(passwordEditText.getText().toString(),
                                confPasswordEditText.getText().toString()) &&
                        isNameValid(nameEditText.getText().toString()) &&
                        isNameValid(secondNameEditText.getText().toString()) &&
                        !usernameEditText.getText().toString().trim().isEmpty()
                );
            }
        });

        confPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

                if(!isConfPasswordValid(passwordEditText.getText().toString(),
                        confPasswordEditText.getText().toString()))
                    confPasswordEditText.setError("Champ non valid");

                //enable the button create
                create.setEnabled(isPasswordValid(passwordEditText.getText().toString())&&
                        isConfPasswordValid(passwordEditText.getText().toString(),
                                confPasswordEditText.getText().toString()) &&
                        isNameValid(nameEditText.getText().toString()) &&
                        isNameValid(secondNameEditText.getText().toString()) &&
                        !usernameEditText.getText().toString().trim().isEmpty()
                );
            }
        });

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

                if(!isNameValid(nameEditText.getText().toString()))
                    nameEditText.setError("champ non valid");

                //enable the button create
                create.setEnabled(isPasswordValid(passwordEditText.getText().toString())&&
                        isConfPasswordValid(passwordEditText.getText().toString(),
                                confPasswordEditText.getText().toString()) &&
                        isNameValid(nameEditText.getText().toString()) &&
                        isNameValid(secondNameEditText.getText().toString()) &&
                        !usernameEditText.getText().toString().trim().isEmpty()
                );

            }
        });

        secondNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

                if(!isNameValid(secondNameEditText.getText().toString()))
                    secondNameEditText.setError("champ non valid");

                //enable the button create
                create.setEnabled(isPasswordValid(passwordEditText.getText().toString())&&
                        isConfPasswordValid(passwordEditText.getText().toString(),
                                confPasswordEditText.getText().toString()) &&
                        isNameValid(nameEditText.getText().toString()) &&
                        isNameValid(secondNameEditText.getText().toString()) &&
                        !usernameEditText.getText().toString().trim().isEmpty()
                );
            }
        });

        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

                if(!isPhoneValid(phoneEditText.getText().toString()))
                    phoneEditText.setError("champ non valid");

                //enable the button create
                create.setEnabled(isPasswordValid(passwordEditText.getText().toString())&&
                        isConfPasswordValid(passwordEditText.getText().toString(),
                                confPasswordEditText.getText().toString()) &&
                                isPhoneValid(phoneEditText.getText().toString()) &&
                        isNameValid(nameEditText.getText().toString()) &&
                        isNameValid(secondNameEditText.getText().toString()) &&
                        !usernameEditText.getText().toString().trim().isEmpty()
                );
            }
        });




}

    public void fun(String username,String password,String phone, String name,String secondName){
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelSign> sign = api.sign(username,password,phone,name,secondName);
        sign.enqueue(new Callback<ModelSign>() {
            @Override
            public void onResponse(Call<ModelSign> call, Response<ModelSign> response) {
                if (response.body().getIsSuccess() == 1) {
                    //get username
                    //storing the user in shared preferences
                    SharedPref.getInstance(SignActivity.getAppContext()).storeUserName(response.body().getUsername());
                    Log.d("khraa","onResponse : account created  "+ SharedPref.getInstance(SignActivity.getAppContext()).serverOn());
                    String welcome = getString(R.string.welcome) + response.body().getUsername();
                    // TODO : initiate successful logged in experience
                    Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignActivity.this,com.prom.eazy.HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }else {
                    Log.d("khraa", "onResponse : account not created "+ SharedPref.getInstance(SignActivity.getAppContext()).serverOn());
                    //Toast.makeText(getApplicationContext(), "Username ou mdp incorrect !", Toast.LENGTH_LONG).show();
                    loadingProgressBar.setVisibility(View.GONE);
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                    errTextView.setText("Veillez vérifier les champs !");
                    errTextView.setPadding(30,30,30,30);

                }

            }

            @Override
            public void onFailure(Call<ModelSign> call, Throwable t) {
                Log.d("khraa","onFailure "+ SharedPref.getInstance(SignActivity.getAppContext()).serverOn());
                errTextView.setText("Tentative de connexion au serveur échouée !");
                errTextView.setPadding(30,30,30,30);
                SharedPref.getInstance(SignActivity.getAppContext()).clearSrv();
                loadingProgressBar.setVisibility(View.GONE);
                //Thread.sleep(500);
                openDialog();
                //Toast.makeText(getApplicationContext(), "Tentative de connexion au serveur échouée !", Toast.LENGTH_SHORT).show();
            }



        });

    }

    public static Context getAppContext() {
        return SignActivity.context;
    }

    public void openDialog() {
        ServerDialog serverDialog = new ServerDialog();
        serverDialog.show(getSupportFragmentManager(), "Server dialog");
    }



    @Override
    public void applyTexts(String server) {

        try {
            SharedPref.getInstance(SignActivity.getAppContext()).storeServer(server);
            fun(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    phoneEditText.getText().toString(),
                    nameEditText.getText().toString(),
                    secondNameEditText.getText().toString());
            Log.d("khraa","try valider boite de dialog");

        }catch (Exception e){
            Log.d("khraa","catch valider boite de dialog");

        }

    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isConfPasswordValid(String password,String confPassword) {
        return password.equals(confPassword);
    }

    final String pattern = "[a-zA-Z]{2,20}";

    private boolean isNameValid(String name) {
        return name.matches(pattern);
    }

    final String patternPhone = "[0][567][0-9]{8}";

    private boolean isPhoneValid(String name) {
        return name.matches(patternPhone);
    }



    private void isUserNameValid(String pseudo) {
        String username = pseudo.trim();
        if (username == null) {
            usernameEditText.setError("Champs obligatoire");
        }

        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelUsername> ifExist = api.ifExist(username);
        ifExist.enqueue(new Callback<ModelUsername>() {
            @Override
            public void onResponse(Call<ModelUsername> call, Response<ModelUsername> response) {
                if (response.body().getIsSuccess() == 1) {
                    usernameEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_box, 0);
                    Log.d("khraa", "ok");
                }else {
                    usernameEditText.setError("pseudo existe");
                }
            }

            @Override
            public void onFailure(Call<ModelUsername> call, Throwable t) {

            }
        });
    }
}
