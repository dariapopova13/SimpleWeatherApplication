package com.daria.weather.simpleweatherapplication.ui.base;

import com.daria.weather.simpleweatherapplication.viewmodel.ViewModelChangeListener;

/**
 * Created by Daria Popova on 24.11.17.
 */

public abstract class BaseViewModelActivity<T> extends BaseActivity
        implements ViewModelChangeListener<T> {

}
