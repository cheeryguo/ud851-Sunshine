package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;
import android.util.Log;

/**
 * Created by cnguogua on 2017-07-05.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {
    private static final String TAG = SettingsFragment.class.getSimpleName();
    /**
     * Called when a shared preference is changed, added, or removed. This
     * may be called even if a preference is set to its existing value.
     * <p>
     * <p>This callback will be run on your main thread.
     *
     * @param sharedPreferences The {@link SharedPreferences} that received
     *                          the change.
     * @param key               The key of the preference that was changed, added, or
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if(null != preference)
        {
            String string = sharedPreferences.getString(key, "");
            SetPreferenceSummary(preference,string);
        }
    }

    /**
     * Called during {@link #onCreate(Bundle)} to supply the preferences for this fragment.
     * Subclasses are expected to call {@link #setPreferenceScreen(PreferenceScreen)} either
     * directly or via helper methods such as {@link #addPreferencesFromResource(int)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     * @param rootKey            If non-null, this preference fragment should be rooted at the
     *                           {@link PreferenceScreen} with this key.
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.perfs);
        PreferenceManager preferenceManager = getPreferenceManager();
        int preferenceCount = getPreferenceScreen().getPreferenceCount();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        for(int i=0;i<preferenceCount;++i)
        {
            Preference preference = getPreferenceScreen().getPreference(i);
            String string = sharedPreferences.getString(preference.getKey(), "");
            SetPreferenceSummary(preference,string);
        }
    }

    void SetPreferenceSummary(Preference preference, Object object)
    {
        String value = (String) object;

        if(!(preference instanceof CheckBoxPreference))
        {

            String key = preference.getKey();
            if(key == getString(R.string.pref_location_key))
            {
                preference.setSummary(value);
            }
            else if(key.equals(getString(R.string.pref_units_key)))
            {
                ListPreference listPreference = (ListPreference) preference;
                int indexOfValue = listPreference.findIndexOfValue(value);
                String charSequence = listPreference.getEntries()[indexOfValue].toString();
                Log.d(TAG, "SetPreferenceSummary: " + charSequence);
                preference.setSummary(charSequence);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Called when a Preference has been changed by the user. This is
     * called before the state of the Preference is about to be updated and
     * before the state is persisted.
     *
     * @param preference The changed Preference.
     * @param newValue   The new value of the Preference.
     * @return True to update the state of the Preference with the new value.
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return true;
    }
}
