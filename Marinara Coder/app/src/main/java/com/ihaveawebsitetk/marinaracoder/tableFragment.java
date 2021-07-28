package com.ihaveawebsitetk.marinaracoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import marinara.Coder;

public class tableFragment extends Fragment {
    public tableFragment() {
        super(R.layout.codes_table);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // char[][] codes = Coder.codes();
        // int[] rowIds = new int[]{R.id.row0, R.id.row1, R.id.row2, R.id.row3, R.id.row4, R.id.row5, R.id.row6};

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
