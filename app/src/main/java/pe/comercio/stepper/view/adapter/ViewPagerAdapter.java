package pe.comercio.stepper.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Bravo on 15/08/16.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> mFragmentList, List<String> mFragmentTitleList) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mFragmentTitleList = mFragmentTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        Log.e("tag ","tag "+createdFragment.getTag());
        return createdFragment;
    }
}
