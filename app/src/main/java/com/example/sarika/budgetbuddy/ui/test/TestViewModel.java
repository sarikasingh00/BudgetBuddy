package com.example.sarika.budgetbuddy.ui.test;

import android.widget.TextView;

import androidx.lifecycle.ViewModel;

public class TestViewModel extends ViewModel {

    public int sum=0;

    public int findSum(int a, int b){
        sum=a+b;
        return sum;
    }
}
