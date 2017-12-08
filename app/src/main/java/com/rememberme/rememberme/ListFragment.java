package com.rememberme.rememberme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by JW on 2017-12-07.
 */

public class ListFragment extends Fragment{
    @Nullable

    Button BtnaddTitle;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //레이아웃을 인플레이터 할수 있게하는 메소드
        ViewGroup rootView   = (ViewGroup) inflater.inflate (R.layout.fragment_list, container, false); // false는 바로 붙이지 않고, 동작할때만 붙일 수있게

        BtnaddTitle = (Button)rootView.findViewById(R.id.addTrip);

        BtnaddTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addtripIntent = new Intent(getActivity(), AddTitleActivity.class);
                getActivity().startActivity(addtripIntent);
            }
        });


        return rootView;
    }

}
