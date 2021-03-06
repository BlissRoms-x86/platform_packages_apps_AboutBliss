/*
* Copyright (C) 2015 BlissRoms Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.blissroms.about;

import android.app.Activity;
import android.os.Bundle;
import android.net.Uri;
import android.os.Build;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.provider.Settings;
import android.content.Intent;
import com.android.settings.Utils;
import android.os.SystemProperties;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.dashboard.SummaryLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

import com.android.internal.logging.MetricsProto.MetricsEvent;

public class AboutBliss extends SettingsPreferenceFragment {

	private static final String KEY_MOD_BUILD_DATE = "build_date";
	private static final String KEY_BLISS_VERSION = "bliss_version";
	private static final String KEY_BLISS_SHARE = "share";

	Preference mSourceUrl;
	Preference mGoogleUrl;
	Preference mTelegramUrl;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.about_bliss);

	setValueSummary(KEY_MOD_BUILD_DATE, "ro.build.date");
	setValueSummary(KEY_BLISS_VERSION, "ro.bliss.version");
	findPreference(KEY_BLISS_VERSION).setEnabled(true);
        mSourceUrl = findPreference("bliss_source");
	mTelegramUrl = findPreference("bliss_telegram");
        mGoogleUrl = findPreference("bliss_google_plus");
    }

   private void setValueSummary(String preference, String property) {
        try {
            findPreference(preference).setSummary(
                    SystemProperties.get(property,
                            getResources().getString(R.string.device_info_default)));
        } catch (RuntimeException e) {
            // No recovery
        }
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == mSourceUrl) {
            launchUrl("https://github.com/BlissRoms");
        } else if (preference == mTelegramUrl) {
            launchUrl("");
        } else if (preference == mGoogleUrl) {
            launchUrl("https://plus.google.com/communities/118265887490106132524");
        } else if (preference.getKey().equals(KEY_BLISS_SHARE)) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, String.format(
                getActivity().getString(R.string.share_message), Build.MODEL));
        startActivity(Intent.createChooser(intent, getActivity().getString(R.string.share_chooser_title)));
        }
        return super.onPreferenceTreeClick(preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(intent);
}

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.ABOUT_BLISS;
    }
}
