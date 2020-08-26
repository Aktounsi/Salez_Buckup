package com.prom.eazy.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prom.eazy.ClientAdapter;
import com.prom.eazy.ClientItem;
import com.prom.eazy.R;
import com.prom.eazy.VenteActivity;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String EXTRA_ID = "com.prom.eazy.EXTRA_ID";

    private RecyclerView mRecyclerView;
    private ClientAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        Log.d("success","OnCreate PlaceholderFragment ");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_client_recycler_view, container, false);
        final RecyclerView mRecyclerView = root.findViewById(R.id.recyclerView);
        pageViewModel.getText().observe(this, new Observer<ArrayList<ClientItem>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ClientItem> clientList) {
                Log.d("success","OnCreateView PlaceholderFragment Onchange");
                // textView.setText(s);
                if (getContext() != null) mLayoutManager = new LinearLayoutManager(getContext());
                mAdapter = new ClientAdapter(clientList);
                mRecyclerView.setLayoutManager(mLayoutManager);

                //mAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdapter);

                final ArrayList<ClientItem> cl = clientList;
                mAdapter.setOnItemClickListener(new ClientAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        int id = cl.get(position).getId();

                        Intent intent = new Intent(getActivity(), VenteActivity.class);
                        intent.putExtra(EXTRA_ID, id);
                        startActivity(intent);
                    }
                });


            }
        }); Log.d("success","OnCreateView PlaceholderFragment");
        return root;
    }
}

