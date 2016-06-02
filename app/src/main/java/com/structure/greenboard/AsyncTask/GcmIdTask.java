package com.structure.greenboard.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.structure.greenboard.R;
import com.structure.greenboard.Interfaces.NotifyGcmRegId;

/**
 * Created by abhishekdewa on 5/23/2016.
 */
public class GcmIdTask extends AsyncTask<Void, Void, Void> {
    private NotifyGcmRegId notify;
    private Context context;

    public GcmIdTask(NotifyGcmRegId notifyGcmRegId, Context context) {
        notify = notifyGcmRegId;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        String token = null;
        try {
            //Creating an instanceid
            InstanceID instanceID = InstanceID.getInstance(context.getApplicationContext());
            token = instanceID.getToken(context.getString(R.string.defaultREgId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            notify.setRegId(token, 1);
        } catch (Exception e) {
            //If any error occurred
            notify.setRegId(token, 0);
        }
        return null;
    }


}
