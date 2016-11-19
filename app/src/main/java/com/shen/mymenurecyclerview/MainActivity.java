package com.shen.mymenurecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity implements Adapter.IonSlidingViewClickListener, Thread.UncaughtExceptionHandler{

    private RecyclerView mRecyclerView;

    private Adapter mAdapter;

    private final String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.shen.mymenurecyclerview.R.layout.activity_main);


        //在此调用下面方法，才能捕获到线程中的异常
        Thread.setDefaultUncaughtExceptionHandler(this);

        initView();
        setAdapter();

    }

    private void initView(){
        mRecyclerView = (RecyclerView) findViewById(com.shen.mymenurecyclerview.R.id.recyclerview);
    }

    private void setAdapter(){

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));      // 不改，默认是"纵向"
        mRecyclerView.setAdapter(mAdapter = new Adapter(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());           // 默认的"项"动作的"动画"

    }


    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG,"点击项："+position);
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        Log.i(TAG,"删除项："+position);
        mAdapter.removeData(position);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //在此处理异常， arg1即为捕获到的异常
        Log.i("AAA", "uncaughtException   " + ex);
    }
}
