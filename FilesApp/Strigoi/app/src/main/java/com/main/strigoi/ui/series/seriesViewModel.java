package com.main.strigoi.ui.series;

import androidx.lifecycle.ViewModel;

public class seriesViewModel extends ViewModel {
    public seriesViewModel() {
        System.out.println("seriesViewModel constructed. Random Number: " + Math.floor(Math.random() * 5000));
    }
}
