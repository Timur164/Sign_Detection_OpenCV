package com.timyr.opencv_demo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.timyr.opencv_demo.R;
import com.timyr.opencv_demo.RoadSignApp;
import com.timyr.opencv_demo.activity.CameraActivity;
import com.timyr.opencv_demo.controller.BaseFragment;

public class SettingsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.setting_fragment, container, false);
        String key = null;
        if (getArguments() != null && getArguments().getString("key") != null)
            key = getArguments().getString("key");
        final EditText widthSize = (EditText) view.findViewById(R.id.widthSize);
        final EditText heightSize = (EditText) view.findViewById(R.id.heightSize);
        final EditText minSize = (EditText) view.findViewById(R.id.minSize);
        final EditText scaleFactor = (EditText) view.findViewById(R.id.scaleFactor);
        final EditText minNeighbors = (EditText) view.findViewById(R.id.minNeighbors);
        final SwitchCompat colorFilter = (SwitchCompat) view.findViewById(R.id.colorFilter);
        final SwitchCompat showSign = (SwitchCompat) view.findViewById(R.id.showSign);
        final SwitchCompat showProSign = (SwitchCompat) view.findViewById(R.id.showProSign);
        final SwitchCompat showWarSign = (SwitchCompat) view.findViewById(R.id.showWarSign);

        widthSize.setText("" + RoadSignApp.getInstance().getWidthSize());
        heightSize.setText("" + RoadSignApp.getInstance().getHeightSize());
        minSize.setText("" + RoadSignApp.getInstance().getMinSize());
        scaleFactor.setText("" + RoadSignApp.getInstance().getScaleFactor());
        minNeighbors.setText("" + RoadSignApp.getInstance().getMinNeighbors());
        colorFilter.setChecked(RoadSignApp.getInstance().isColorFilter());
        showSign.setChecked(RoadSignApp.getInstance().isShowSign());
        showProSign.setChecked(RoadSignApp.getInstance().isShowProSign());
        showWarSign.setChecked(RoadSignApp.getInstance().isShowWarSign());

        Button buttonSave = (Button) view.findViewById(R.id.buttonSave);
        buttonSave.setBackgroundResource(android.R.drawable.btn_default);
        final String finalKey = key;
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!widthSize.getText().toString().isEmpty() && !heightSize.getText().toString().isEmpty() && !minSize.getText().toString().isEmpty()) {
                    RoadSignApp.getInstance().setWidthSize(Integer.parseInt(widthSize.getText().toString()));
                    RoadSignApp.getInstance().setHeightSize(Integer.parseInt(heightSize.getText().toString()));
                    RoadSignApp.getInstance().setMinSize(Double.valueOf(minSize.getText().toString()));
                    RoadSignApp.getInstance().setScaleFactor(Double.valueOf(scaleFactor.getText().toString()));
                    RoadSignApp.getInstance().setMinNeighbors(Integer.parseInt(minNeighbors.getText().toString()));
                    RoadSignApp.getInstance().setColorFilter(colorFilter.isChecked());
                    RoadSignApp.getInstance().setShowSign(showSign.isChecked());
                    RoadSignApp.getInstance().setShowProSign(showProSign.isChecked());
                    RoadSignApp.getInstance().setShowWarSign(showWarSign.isChecked());
                    Toast.makeText(getActivity(), "Сохранено", Toast.LENGTH_LONG).show();
                    if (finalKey != null) {
                        Intent cameraIntent = new Intent(getActivity(), CameraActivity.class);
                        startActivityForResult(cameraIntent, 200);
                    }
                } else {
                    Toast.makeText(getActivity(), "Заполните пустые поля", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
