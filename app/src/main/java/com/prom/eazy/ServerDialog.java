package com.prom.eazy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.prom.eazy.R;
import com.prom.eazy.ui.login.LoginActivity;

public class ServerDialog extends AppCompatDialogFragment {
    private EditText editTextServer;
    private ExampleDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_server, null);
        builder.setView(view)
                .setTitle("Server adress")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String server = editTextServer.getText().toString();
                        listener.applyTexts(server);
                    }
                });
        editTextServer = view.findViewById(R.id.server);
        if(SharedPref.getInstance(LoginActivity.getAppContext()).isServerOn()) {
            editTextServer.setText(SharedPref.getInstance(LoginActivity.getAppContext()).serverOn());
            SharedPref.getInstance(LoginActivity.getAppContext()).clearSrv();
        }
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    public interface ExampleDialogListener {
        void applyTexts(String server);
    }
}
