package com.rememberme.rememberme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import static com.rememberme.rememberme.R.id.BtnEndDate;
import static com.rememberme.rememberme.R.id.Btnstartdate;
import static com.rememberme.rememberme.R.id.Btntripsend;
import static com.rememberme.rememberme.R.id.descriptionText;
import static com.rememberme.rememberme.R.id.regionText;
import static com.rememberme.rememberme.R.id.titleText;

/**
 * Created by samsung on 2017-12-06.
 */

public class AddTitleFragment extends Fragment{

    @Nullable
    @Override
// 여기서 버튼을 누르면 달력이 나오고, 달력을 설정해서 데이 숫자가 넘어와야한다.
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup addTitle = (ViewGroup) inflater.inflate(R.layout.fragment_add_title,container,false);
//Btnstartdate Btnenddate
        final EditText etDescription,etRegion,etTitle;



        Button startdate = (Button) addTitle.findViewById(Btnstartdate);
        Button enddate = (Button) addTitle.findViewById(BtnEndDate);
        Button tripsendbtn = (Button) addTitle.findViewById(Btntripsend);

        etDescription = (EditText) addTitle.findViewById(descriptionText);
        etRegion = (EditText) addTitle.findViewById(regionText);
        etTitle = (EditText) addTitle.findViewById(titleText);

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup)(addTitle.getParent())).removeView(addTitle);
                Intent goTocalendar = new Intent(getActivity(), trip_calendar_Activity.class);
                getActivity().startActivity(goTocalendar);
            }
        });
//        if(!Bundle == null)
//        String startDay = getArguments().getString("startday");
//        String endDay = getArguments().getString("endDay");
//        startdate.setText(startDay);
//        enddate.setText(endDay);

        tripsendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHttpAsyncTask httpTask = new MyHttpAsyncTask(getContext());
                HashMap<String, Object> params = new HashMap<>();
                params.put("url", "http://70.12.50.58:3000/trips");
                params.put("start","2017-09-18");
                params.put("end","2017-09-22");
                params.put("title", etTitle.getText().toString());
                params.put("description", etDescription.getText().toString());
                params.put("region", etRegion.getText().toString());

                httpTask.execute(params);

            }
        });

        return addTitle;
    }
}
