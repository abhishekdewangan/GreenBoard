package com.structure.greenboard.BaseLib;

/**
 * Created by abhishekdewa on 5/11/2016.
 */
public class OperationResult {
    public int resultStatus;
    public Object result;
    public String resultMsg;

    public OperationResult() {

    }

    @Override
    public String toString() {
        return "OperationResult{" +
                "resultStatus=" + resultStatus +
                ", result=" + result +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
