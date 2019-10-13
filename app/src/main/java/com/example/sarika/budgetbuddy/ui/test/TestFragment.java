package com.example.sarika.budgetbuddy.ui.test;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sarika.budgetbuddy.R;

public class TestFragment extends Fragment {

    private TestViewModel mViewModel;
    //public View rootView;
    public TextView obj;
    public EditText obj1;
    public EditText obj2;
    public Button button;

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //rootView=inflater.inflate(R.layout.fragment_test, container, false);
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    public void onViewCreated(View view,Bundle savedInstanceState){
        obj= (TextView) view.findViewById(R.id.textView2);
        obj1= (EditText)view.findViewById(R.id.editText);
        obj2=(EditText) view.findViewById(R.id.editText2);
        button= (Button)view.findViewById(R.id.button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
    }

    public void onStart(){
        super.onStart();

        obj.setText("abc");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum=mViewModel.findSum(Integer.parseInt(obj1.getText().toString()),Integer.parseInt(obj2.getText().toString()));
                String sum1=String.valueOf(sum);
                obj1.setText(sum1);
                obj.setText("xyza");
            }
        });


    }

}