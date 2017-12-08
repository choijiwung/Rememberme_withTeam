package com.rememberme.rememberme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.rememberme.rememberme.R.id.addTitle_button;


/**
 * Created by samsung on 2017-11-17.
 */
// 여행목록 탭
public class ListFragment extends Fragment {

    MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //레이아웃을 인플레이터 할수 있게하는 메소드
        final ViewGroup rootView   = (ViewGroup) inflater.inflate (R.layout.fragment_list, container, false); // false는 바로 붙이지 않고, 동작할때만 붙일 수있게

        Button BtnAddTitle = (Button) rootView.findViewById(addTitle_button);
        BtnAddTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup)(rootView.getParent())).removeView(rootView);
                Intent goToAddTitleActivity = new Intent(getActivity(), AddTitleActivity.class);
                getActivity().startActivity(goToAddTitleActivity);
            }
        });
        return rootView;


    }


}