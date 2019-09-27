package com.example.sarika.budgetbuddy.ui.addCategory;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import com.example.sarika.budgetbuddy.UserDocInfo;

public class AddViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //private MutableLiveData<String> mText;
    public String Uid;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private static final String TAG = "DocSnippets";
    //public boolean addSuccess;

    public AddViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
        mAuth=FirebaseAuth.getInstance();
        Uid=mAuth.getUid();
        db=FirebaseFirestore.getInstance();
        //addSuccess=false;
    }
    public void addData(String name, int budget){
        Map<String,Object> category = new HashMap<>();
        category.put("Category name",name);
        category.put("Budget",budget);
        category.put("Expense",0);

        UserDocInfo user= new UserDocInfo(name,budget,0);
        Map<String,UserDocInfo> userDoc=new HashMap<>();
        userDoc.put(name,user);
        db.collection("Users").document(Uid).collection("Categories").document(name).set(category,SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        //Toast.makeText(getActivity(), "no issur in button",Toast.LENGTH_LONG).show();
                        //addSuccess=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                        //addSuccess=false;
                    }
                });
        db.collection("Users").document(Uid).set(userDoc,SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }
}