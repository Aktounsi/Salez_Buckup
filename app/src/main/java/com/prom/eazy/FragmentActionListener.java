package com.prom.eazy;

import android.os.Bundle;

/**
 * Created by anildeshpande on 12/26/17.
 */

public interface FragmentActionListener {
    String ACTION_KEY = "action_key";
    int ACTION_VALUE_COUNTRY_SELECTED = 1;

    String KEY_SELECTED_COUNTRY="KEY_SELECTED_COUNTRY";

    void onActionPerformed(Bundle bundle);
    void openDrawer();
}
