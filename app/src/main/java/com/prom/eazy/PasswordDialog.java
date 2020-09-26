package com.prom.eazy;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.prom.eazy.ui.login.LoginActivity;

public class PasswordDialog extends AppCompatDialogFragment {
        private EditText editTextPw;
        private com.prom.eazy.PasswordDialog.ExampleDialogListener listener;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_server, null);
            builder.setView(view)
                    .setTitle("You need to confirm password")
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String pw = editTextPw.getText().toString();
                            listener.applyTexts(pw);
                        }
                    });
            editTextPw = view.findViewById(R.id.server);
            editTextPw.setHint("Password");
            return builder.create();
        }
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            try {
                listener = (com.prom.eazy.PasswordDialog.ExampleDialogListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() +
                        "must implement ExampleDialogListener");
            }
        }
        public interface ExampleDialogListener {
            void applyTexts(String password);
        }
    }