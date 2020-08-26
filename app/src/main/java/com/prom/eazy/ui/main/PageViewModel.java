package com.prom.eazy.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.prom.eazy.ClientItem;
import com.prom.eazy.R;

import java.util.ArrayList;

public class PageViewModel extends ViewModel {
    //ArrayList<ClientItem> clientList = new ArrayList<>();

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<ArrayList<ClientItem>> mText = Transformations.map(mIndex, new Function<Integer, ArrayList<ClientItem>>() {
        @Override
        public ArrayList<ClientItem> apply(Integer input) { ArrayList<ClientItem> clientList = new ArrayList<>();
            switch (input){
                case 1 : clientList.add(new ClientItem(1, R.drawable.store, "Line 1", "Line 2"));
                    clientList.add(new ClientItem(2,R.drawable.store, "Line 3", "Line 4"));
                    clientList.add(new ClientItem(3,R.drawable.store, "Line 5", "Line 6"));
                    clientList.add(new ClientItem(4,R.drawable.store, "Line 7", "Line 8")); break;
                case 2 : clientList.add(new ClientItem(5,R.drawable.store, "Line 9", "Line 10"));
                    clientList.add(new ClientItem(6,R.drawable.store, "Line 11", "Line 12"));
                    clientList.add(new ClientItem(7,R.drawable.store, "Line 13", "Line 14"));
                    clientList.add(new ClientItem(8,R.drawable.store, "Line 15", "Line 16"));
                    clientList.add(new ClientItem(9,R.drawable.store, "Line 25", "Line 26")); break;
                case 3 : clientList.add(new ClientItem(10,R.drawable.store, "Line 17", "Line 18"));
                    clientList.add(new ClientItem(11,R.drawable.store, "Line 19", "Line 20"));
                    clientList.add(new ClientItem(12,R.drawable.store, "Line 21", "Line 22"));
                    clientList.add(new ClientItem(13,R.drawable.store, "Line 23", "Line 24")); break;
                case 4 : clientList.add(new ClientItem(14,R.drawable.store, "Line 25", "Line 26"));
                    clientList.add(new ClientItem(15,R.drawable.store, "Line 27", "Line 28"));
                    clientList.add(new ClientItem(16,R.drawable.store, "Line 29", "Line 30")); break;
                case 5 : clientList.add(new ClientItem(17,R.drawable.store, "Line 25", "Line 26"));
                    clientList.add(new ClientItem(18,R.drawable.store, "Line 27", "Line 28"));
                    clientList.add(new ClientItem(19,R.drawable.store, "Line 29", "Line 30"));
                    clientList.add(new ClientItem(20,R.drawable.store, "Line 25", "Line 26"));
                    clientList.add(new ClientItem(21,R.drawable.store, "Line 27", "Line 28"));
                    clientList.add(new ClientItem(22,R.drawable.store, "Line 29", "Line 30")); break;
            }
            return clientList;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<ArrayList<ClientItem>> getText() {
        return mText;
    }
}
