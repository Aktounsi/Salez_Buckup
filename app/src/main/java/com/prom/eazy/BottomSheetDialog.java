package com.prom.eazy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
        Button button2 = v.findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("Button 2 clicked");
                dismiss();
            }
        });

        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("kk");
        spinnerArray.add("kok");
        spinnerArray.add("kkoo");
        Spinner spinner = new Spinner(getActivity().getApplicationContext());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getActivity().getApplicationContext(), android.R.layout.simple_spinner_item,
                        spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);


        return v;
    }
    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
