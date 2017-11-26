package com.daria.weather.simpleweatherapplication.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.daria.weather.simpleweatherapplication.di.activity.BaseActivityModule;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Daria Popova on 19.11.17.
 */

public abstract class BaseFragment extends Fragment {

    @Inject
    @Named(BaseActivityModule.ACTIVITY_CONTEXT)
    protected Context context;
//    @Inject
//    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
//
//
//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return childFragmentInjector;
//    }

}
