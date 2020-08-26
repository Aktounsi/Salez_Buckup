package com.prom.eazy.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.prom.eazy.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.dim, R.string.lun, R.string.mar, R.string.mer, R.string.jeu};
    private final Context mContext;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        Log.d("success","SectionsPagerAdapter SectionsPagerAdapter");

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Log.d("success","getItem SectionPagerAdapter");

        return PlaceholderFragment.newInstance(position + 1);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("success","getPageTitle SectionPagerAdapter");
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show total pages.
        return TAB_TITLES.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);


            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();

    }



/*    @Override
    public int getItemPosition(Object object){

        if(true){ //this includes deleting or adding pages
            Log.d("success","getItemPOSITION NONE SectionPagerAdapter");
            return PagerAdapter.POSITION_NONE;
    }
else
        return PagerAdapter.POSITION_UNCHANGED; //this ensures high performance in other operations such as editing list items.

}

*/
}