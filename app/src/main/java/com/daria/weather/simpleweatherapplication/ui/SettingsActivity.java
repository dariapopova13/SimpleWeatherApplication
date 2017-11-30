package com.daria.weather.simpleweatherapplication.ui;

import android.os.Bundle;

import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.ui.base.BaseDaggerActivity;
import com.daria.weather.simpleweatherapplication.ui.fragment.PreferencesFragment;

public class SettingsActivity extends BaseDaggerActivity {

    private PreferencesFragment testFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        unitUI();
    }

    private void unitUI() {
        testFragment = PreferencesFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.settings_fragment_holder, testFragment)
                .commit();
    }
}
