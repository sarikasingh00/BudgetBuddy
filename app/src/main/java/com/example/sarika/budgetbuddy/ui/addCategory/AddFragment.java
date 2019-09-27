package com.example.sarika.budgetbuddy.ui.addCategory;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarika.budgetbuddy.R;
import com.google.firebase.auth.FirebaseAuth;

public class AddFragment extends Fragment {

    private AddViewModel mViewModel;
    private TextView nameCategory;
    private TextView budgetAmount;
    private Button addButton;
    private TextView AddText;

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onViewCreated(View view,Bundle savedInstanceState){
        nameCategory=view.findViewById(R.id.category_name);
        budgetAmount=view.findViewById(R.id.budget_amount);
        addButton=view.findViewById(R.id.add_button);
        AddText=view.findViewById(R.id.AddCategory);
    }

    @Override
    public void onStart() {
        super.onStart();
        //AddText.setText("In on start");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat_name= nameCategory.getText().toString();
                int budget= Integer.parseInt(budgetAmount.getText().toString());
                mViewModel.addData(cat_name,budget);
               // if(mViewModel.addSuccess) {
                nameCategory.setText("");
                budgetAmount.setText("");
                Toast.makeText(getActivity(), "Category Added!",Toast.LENGTH_LONG).show();
                //}
            }
        });
    }
}