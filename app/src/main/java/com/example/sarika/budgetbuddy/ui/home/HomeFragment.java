package com.example.sarika.budgetbuddy.ui.home;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sarika.budgetbuddy.R;
import com.example.sarika.budgetbuddy.UserDocInfo;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    //private List<UserDocInfo> userDocInfoList;
    private RecyclerView mRecyclerView;
    private HomeRecyclerAdapter adapter;
    private ProgressBar progressCircle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        mRecyclerView = root.findViewById(R.id.recyclerView);
        /*adapter = new HomeRecyclerAdapter(homeViewModel.getList().getValue(),getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         */
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<UserDocInfo>>() {
            @Override
            public void onChanged(List<UserDocInfo> userDocInfos) {
                //Toast.makeText(getActivity(),"got livedata",Toast.LENGTH_LONG).show();
                adapter = new HomeRecyclerAdapter(userDocInfos,getContext());
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                progressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        //userDocInfoList=new ArrayList<UserDocInfo>();
        mRecyclerView=view.findViewById(R.id.recyclerView);
        progressCircle=view.findViewById(R.id.progress_circle);
    }

    public void onStart(){
        super.onStart();
        homeViewModel.getData();
    }
}



class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>{
    private final String TAG="Recycle";
    List<UserDocInfo> mList;
    Context mContext;
    public HomeRecyclerAdapter(List<UserDocInfo> list, Context context){
        mList=list;
        mContext=context;
    }

    @NonNull
    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(mList.get(position).getCategoryName());
        holder.budget.setText("Budget: "+mList.get(position).getBudget());
        holder.expense.setText("Expense: "+mList.get(position).getExpense()+"");
        holder.progressBar.setMax(mList.get(position).getBudget());
        holder.progressBar.setProgress(mList.get(position).getExpense());
        holder.progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        holder.percent.setText((mList.get(position).getExpense()*100.00/mList.get(position).getBudget())+"%");
        if(mList.get(position).getExpense() > mList.get(position).getBudget())
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "list size="+mList.size());
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName, budget, expense,percent;
        RelativeLayout parentLayout;
        ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.category_name);
            budget=itemView.findViewById(R.id.budget);
            expense=itemView.findViewById(R.id.expense);
            parentLayout=itemView.findViewById(R.id.parent_layout);
            progressBar=itemView.findViewById(R.id.progress_bar);
            percent=itemView.findViewById(R.id.percent);
        }
    }
}