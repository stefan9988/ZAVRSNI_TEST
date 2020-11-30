package com.ftninformatika.zavrsni_projekat;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;


public class SettingsFr extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref, rootKey);
    }
}