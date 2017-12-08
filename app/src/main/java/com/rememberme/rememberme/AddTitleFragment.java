package com.rememberme.rememberme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by samsung on 2017-12-06.
 */

public class AddTitleFragment extends Fragment {

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup addTitle = (ViewGroup)   inflater.inflate(R.layout.fragment_add_title,container ,false);
        return addTitle;
    }
}
