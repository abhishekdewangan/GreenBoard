package com.structure.greenboard.Activities.Splash;

import com.structure.greenboard.BaseLib.BaseHandler;
import com.structure.greenboard.BaseLib.HandlerInf;
import com.structure.greenboard.Models.SplashPojo;

/**
 * Created by abhishekdewa on 5/23/2016.
 */
public class Splash_Handler extends BaseHandler {

    public Splash_Handler(HandlerInf handlerInf) {
        super(handlerInf);
    }

    @Override
    public void parseRespose(String response) {
        parseResponse(response, SplashPojo.class);
    }
}
