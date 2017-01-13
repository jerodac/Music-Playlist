package br.com.jerodac.business;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import br.com.jerodac.Fragments.BaseFragment;
import br.com.jerodac.R;

/**
 * @author Jean Rodrigo Dalbon Cunha on 13/01/17.
 */
public class FlowManager {

    private AppCompatActivity mActivity;
    private int mContainer = R.id.container;
    private FragmentManager mFragmentManager;
    private AlertDialog dialog;

    public FlowManager(AppCompatActivity appCompatActivity) {
        mActivity = appCompatActivity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    public void replace(BaseFragment newFragment, boolean backStack) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(mContainer, newFragment, newFragment.getTagName());
        if (backStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    public void replaceNoneTransition(BaseFragment newFragment, boolean backStack) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.replace(mContainer, newFragment, newFragment.getTagName());
        if (backStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    public void add(BaseFragment addFragment) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.add(mContainer, addFragment, addFragment.getTagName());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void closeCurrent() {
        mFragmentManager.popBackStack();
    }

}
