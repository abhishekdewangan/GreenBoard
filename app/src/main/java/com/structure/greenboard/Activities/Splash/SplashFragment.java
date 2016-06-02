package com.structure.greenboard.Activities.Splash;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.structure.greenboard.Activities.NextActivity.NextActivity;
import com.structure.greenboard.Activities.Tutorial.TutorialActivity;
import com.structure.greenboard.AsyncTask.GcmIdTask;
import com.structure.greenboard.BaseLib.BaseFragment;
import com.structure.greenboard.BaseLib.OperationResult;
import com.structure.greenboard.Interfaces.NotifyGcmRegId;
import com.structure.greenboard.MyApplication;
import com.structure.greenboard.R;
import com.structure.greenboard.util.Constants;
import com.structure.greenboard.util.MSharedPreference;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhishekdewa on 5/23/2016.
 */
public class SplashFragment extends BaseFragment implements NotifyGcmRegId {
    private View layout, tvG, tvB;
    private boolean gcmFlag = false, animationFlag = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_, null);
        initUIElement(view);
        if (MSharedPreference.getSharedPref(getContext()).getGcmId() == null) {
            GcmIdTask task = new GcmIdTask(this, getContext());
            task.execute();
        } else {
            gcmFlag = true;
        }
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(layout, "alpha", 0, 1);
        animator1.setDuration(300);
        animator1.start();
        startAnimation(tvG, false, 1200, 400);
        startAnimation(tvB, true, 1600, 400);
        return view;
    }

    private void startAnimation(View targetView, boolean flag, int startDelay, int duration) {
        int coordinates[] = new int[2];
        targetView.getLocationOnScreen(coordinates);
        int endY = coordinates[1];
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(targetView, "translationY", endY, endY - 30, endY);
        animator2.setStartDelay(startDelay);
        animator2.setDuration(duration);
        animator2.start();
        if (flag)
            animator2.addListener(listener);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initUIElement(View view) {
        layout = view.findViewById(R.id.layoutview);
        tvG = view.findViewById(R.id.tvG);
        tvB = view.findViewById(R.id.tvB);
    }

    Animator.AnimatorListener listener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            animationFlag = true;
            if (gcmFlag) {
                startNextActivity();
            } else {
                Toast.makeText(MyApplication.getAppContext(), "Animation end but gcmId Not Reg into db", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private void startNextActivity() {
        int status = MSharedPreference.getSharedPref(MyApplication.getAppContext()).getTutorialFlag();
        if (status == -1) {
            Intent intent = new Intent(getContext(), TutorialActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
        if (status == 1) {
            Intent intent = new Intent(getContext(), NextActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onSuccessAction(OperationResult result) {
        Toast.makeText(MyApplication.getAppContext(), "Gcm id reg into server successfully", Toast.LENGTH_LONG).show();
        gcmFlag = true;
        if (animationFlag) {
            startNextActivity();
        }
    }

    @Override
    public void onErrorAction(OperationResult result) {
        Log.e("splash result", result.toString());
        Toast.makeText(getContext(), "Error in Reg Gcm id into database", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setRegId(String gcmtoken, int status) {
        if (status == 1) {
            gcmFlag = true;
            MSharedPreference.getSharedPref(getContext()).addGcmId(gcmtoken);
            Splash_Helper helper = new Splash_Helper(this);
            JSONArray rows = new JSONArray();
            JSONArray values = new JSONArray();
            values.put("" + gcmtoken);
            rows.put("gcm_id");
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("table", Constants.GCM_TABLE);
            parameters.put("rows", rows.toString());
            parameters.put("values", values.toString());
            Log.e("params are", parameters.toString());
            helper.registerGcmToServer(parameters);
        }
        if (status == 0) {
            Toast.makeText(getContext(), "Gcm Registration Failed", Toast.LENGTH_LONG).show();
        }
    }
}
