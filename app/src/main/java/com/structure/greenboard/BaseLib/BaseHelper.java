package com.structure.greenboard.BaseLib;

/**
 * Created by abhishekdewa on 5/11/2016.
 */
public abstract class BaseHelper implements HandlerInf {
    private HelperInf mHelperInf;

    public BaseHelper(HelperInf helperInf) {
        mHelperInf = helperInf;
    }

    @Override
    public void onResponseHandle(OperationResult operationResult) {
        if (operationResult.getResultStatus() == 1) {
            mHelperInf.onSuccess(operationResult);
        } else if (operationResult.getResultStatus() == 0) {
            mHelperInf.onError(operationResult);
        }
    }
}
