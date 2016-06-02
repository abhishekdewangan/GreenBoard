package com.structure.greenboard.Activities.Splash;

import com.structure.greenboard.BaseLib.BaseHelper;
import com.structure.greenboard.BaseLib.HelperInf;
import com.structure.greenboard.util.Constants;

import java.util.Map;

/**
 * Created by abhishekdewa on 5/23/2016.
 */
public class Splash_Helper extends BaseHelper {
    public Splash_Helper(HelperInf helperInf) {
        super(helperInf);
    }

    public void registerGcmToServer(Map<String, String> params) {
        Splash_Handler splash_handler = new Splash_Handler(this);
        String partialUrl = "Insert" + Constants.RESOURCE_FORMAT;
        splash_handler.postItem(partialUrl, params);
    }
}
