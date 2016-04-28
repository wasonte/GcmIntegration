/**
 * Copyright 2015 Google Inc. All Rights Reserved.
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

package gcm.play.android.samples.com.gcmquickstart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView mInformationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                   if(intent.getBooleanExtra("register", true)){
                       // Register button was pushed
                       if (MyGcmManager.getInstance().getRegisteredInGCM()){
                           mInformationTextView.setText(getString(R.string.gcm_send_message));
                       } else {
                           mInformationTextView.setText(getString(R.string.token_error_message));
                       }
                   } else {
                       // unRegister button was pushed
                       if (!MyGcmManager.getInstance().getRegisteredInGCM()){
                           mInformationTextView.setText("Token deleted from server");
                       } else {
                           mInformationTextView.setText("Error deleting token from");
                       }
                   }
            }
        };
        mInformationTextView = (TextView) findViewById(R.id.informationTextView);

    }

    public void clickRegister(View v){
        MyGcmManager.getInstance().registerGCM(this, true);
    }

    public void clickUnregister(View v){
        MyGcmManager.getInstance().registerGCM(this, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(MyGcmManager.GCM_REGISTRATION));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}
