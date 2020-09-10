package com.prom.eazy.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.prom.eazy.Api;
import com.prom.eazy.ApiAgent;
import com.prom.eazy.Model;
import com.prom.eazy.R;
import com.prom.eazy.ServerDialog;
import com.prom.eazy.SharedPref;
import com.prom.eazy.data.LoginDataSource;
import com.prom.eazy.services.BackgroundService;
import com.prom.eazy.ui.login.LoginViewModel;
import com.prom.eazy.ui.login.LoginViewModelFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.valueOf;

public class LoginActivity extends AppCompatActivity implements ServerDialog.ExampleDialogListener {

    private static Context context;
     private ProgressBar loadingProgressBar;
     private EditText usernameEditText ;
     private EditText passwordEditText ;
     private Button loginButton ;
     private TextView errTextView;
     private Button supp;
     private TextView sign;


    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.context = getApplicationContext();
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

         usernameEditText = findViewById(R.id.username);
         passwordEditText = findViewById(R.id.password);
         loginButton = findViewById(R.id.login);
         loadingProgressBar = findViewById(R.id.loading);
         errTextView = findViewById(R.id.err);
         sign = findViewById(R.id.sign);
        supp = findViewById(R.id.supp);
         supp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SharedPref.getInstance(LoginActivity.getAppContext()).clearSrv();

             }
         });

         sign.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(LoginActivity.this,com.prom.eazy.SignActivity.class);
                 startActivity(intent);
             }
         });



        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        /*loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }

                if ((loginResult.getError() != null)) {

                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);
                //loadingProgressBar.setVisibility(View.GONE);

                //Complete and destroy login activity once successful
                //finish();
            }
        });*/

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
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
                                    passwordEditText.getText().toString());
                            Log.d("khraa","TRY");

                        }catch (Exception e){
                            Log.d("khraa","CATCH");

                        }
                    }

                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Log.d("khraa","onclick");
                loadingProgressBar.setVisibility(View.VISIBLE);
                //loginViewModel.login(usernameEditText.getText().toString(),
                        //passwordEditText.getText().toString());
                if (!SharedPref.getInstance(LoginActivity.getAppContext()).isServerOn()){
                    openDialog();
                }
                else{
                    try {
                        fun(usernameEditText.getText().toString(),
                                passwordEditText.getText().toString());
                        Log.d("khraa"," try onclick loginBtn");

                    }catch (Exception e){
                        Log.d("khraa","catch onclick loginBtn");

                    }
                }


            }
        });


    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

 /*   private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this,com.prom.eazy.HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
        Log.d("khraa","toast failed");


    }
*/
    public void fun(String username,String password){
       Api api = ApiAgent.getAgent().create(Api.class);
        Call<Model> login = api.login(username,password);
        login.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.body().getIsSuccess() == 1) {
                    //get username
                    //storing the user in shared preferences
                    SharedPref.getInstance(LoginActivity.getAppContext()).storeUserName(response.body().getUsername());
                    Log.d("khraa","onResponse : exist "+ SharedPref.getInstance(LoginActivity.getAppContext()).serverOn());
                    String welcome = getString(R.string.welcome) + response.body().getUsername();
                    // TODO : initiate successful logged in experience
                    Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,com.prom.eazy.HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }else {
                    Log.d("khraa", "onResponse : doesn't exist "+ SharedPref.getInstance(LoginActivity.getAppContext()).serverOn());
                    //Toast.makeText(getApplicationContext(), "Username ou mdp incorrect !", Toast.LENGTH_LONG).show();
                    loadingProgressBar.setVisibility(View.GONE);
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                    errTextView.setText("Username ou mdp incorrect !");
                    errTextView.setPadding(30,30,30,30);

                }

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("khraa","onFailure "+ SharedPref.getInstance(LoginActivity.getAppContext()).serverOn());
                errTextView.setText("Tentative de connexion au serveur échouée !");
                errTextView.setPadding(30,30,30,30);
                SharedPref.getInstance(LoginActivity.getAppContext()).clearSrv();
                loadingProgressBar.setVisibility(View.GONE);
                //Thread.sleep(500);
                openDialog();
                //Toast.makeText(getApplicationContext(), "Tentative de connexion au serveur échouée !", Toast.LENGTH_SHORT).show();
            }



        });

    }

    public static Context getAppContext() {
        return LoginActivity.context;
    }

    public void openDialog() {
        ServerDialog serverDialog = new ServerDialog();
        serverDialog.show(getSupportFragmentManager(), "Server dialog");
    }



    @Override
    public void applyTexts(String server) {

        try {
            SharedPref.getInstance(LoginActivity.getAppContext()).storeServer(server);
            fun(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
            Log.d("khraa","try valider boite de dialog");

        }catch (Exception e){
            Log.d("khraa","catch valider boite de dialog");

        }

    }
}
