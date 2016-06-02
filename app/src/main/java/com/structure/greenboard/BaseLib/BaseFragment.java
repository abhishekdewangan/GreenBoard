package com.structure.greenboard.BaseLib;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

/**
 * Created by abhishekdewa on 5/11/2016.
 */
public abstract class BaseFragment extends Fragment implements HelperInf {
    private Activity activity;
    private SwipeRefreshLayout pullRefresh;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    public abstract void onSuccessAction(OperationResult result);

    public abstract void onErrorAction(OperationResult result);

    @Override
    public void onSuccess(OperationResult operationResult) {
        onSuccessAction(operationResult);
    }

    @Override
    public void onError(OperationResult operationResult) {
        onErrorAction(operationResult);
    }

    public void showProgressIndicator(View view, int loadingContainer) {
        pullRefresh = (SwipeRefreshLayout) view.findViewById(loadingContainer);
        pullRefresh.setRefreshing(true);
    }

    public void stopProgressIndicator() {
        pullRefresh.setRefreshing(false);
    }
}
