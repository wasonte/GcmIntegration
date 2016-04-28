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

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

public class GcmRegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";

    public GcmRegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        registerGCM(intent.getBooleanExtra("register", true));
    }


    private void registerGCM(boolean register){
        try {

            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            if (register){
                Log.d("GcmRegistrationService", "GCM Registration Token: " + token);
                if(registerInServer(true, token)){
                    MyGcmManager.getInstance().setRegisteredInGCM(true);
                }
            } else {
                Log.d("GcmRegistrationService", "GCM Token for deletion: "  + token);
                if(registerInServer(false, token)){
                    MyGcmManager.getInstance().setRegisteredInGCM(false);
                }
            }

        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
        Intent registrationComplete = new Intent(MyGcmManager.GCM_REGISTRATION);
        registrationComplete.putExtra("register", register);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    /**
     * Persist registration to third-party servers.
     *
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private boolean registerInServer(boolean register, String token) {
        // Add custom implementation, as needed.
        // TODO
        return true;
    }

}
