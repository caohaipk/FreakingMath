package com.wordpress.grayfaces.freakingmath.gamemode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.grayfaces.freakingmath.R;
import com.wordpress.grayfaces.freakingmath.truefalse.PlayActivity;

/**
 * Project FreakingMath
 * Created by Gray on 10/24/2017.
 */

public class StartTrueFalse extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_start, container, false);
        TextView title = (TextView)view.findViewById(R.id.start_txtTitle);
        title.setText("TRUE / FALSE");
        View btnPlay = view.findViewById(R.id.start_btnPlay);
        setChange(btnPlay);
        return view;
    }
    private void setChange(View play){
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PlayActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
    }
}