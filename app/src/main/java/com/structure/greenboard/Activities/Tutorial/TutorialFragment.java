package com.structure.greenboard.Activities.Tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.structure.greenboard.R;

/**
 * Created by abhishekdewa on 5/25/2016.
 */
public class TutorialFragment extends Fragment {
    private TextView title, subtitle;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_fragment, null);
        title = (TextView) view.findViewById(R.id.tvTitle);
        subtitle = (TextView) view.findViewById(R.id.tvSubTitle);
        imageView = (ImageView) view.findViewById(R.id.tutorialImg);
        Bundle bundle = getArguments();
        String strTitle, strSubtitle;
        int imgRes;
        strSubtitle = bundle.getString("sub");
        strTitle = bundle.getString("title");
        imgRes = bundle.getInt("img");
        title.setText(strTitle);
        subtitle.setText(strSubtitle);
        imageView.setImageResource(imgRes);
        return view;
    }
}
