package com.prom.eazy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetDialogPt extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.bottom_sheet_dialog_pt, container, false);
        Button button2 = v.findViewById(R.id.button2);
        radioGroup = (RadioGroup) v.findViewById(R.id.radioGroup);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View bu) {
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) v.findViewById(selectedId);

                dismiss();
                mListener.onButtonClicked(radioButton.getText().toString());
            }
        });

        return v;
    }
    public interface BottomSheetListener {
        void onButtonClicked(String valider);
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

