package com.example.sarika.budgetbuddy.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public String Uid;
    private FirebaseAuth mAuth;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mAuth=FirebaseAuth.getInstance();
        Uid=mAuth.getUid();
    }

    public LiveData<String> getText() {
        return mText;
    }
}