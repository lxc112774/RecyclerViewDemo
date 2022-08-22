package com.example.lxc.recyclerviewdemo;

import android.content.Intent;
import android.preference.SwitchPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WaterFallActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    private WaterFallAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private boolean connect=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruirts();//初始化水果数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//卡片布局 或用GridLayoutManager
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WaterFallAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);//悬浮按钮
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("点击开始横向布局", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(WaterFallActivity.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(WaterFallActivity.this, "sada2", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);//下拉刷新
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);//下拉刷新条颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }



    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);//本地刷新太快，不沉睡看不到刷新过程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {//将线程切回主线程
                    @Override
                    public void run() {
                        if(connect){
                            connect = false;
                        }else {
                            connect = true;
                        }
                        initFruirts();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);//刷新结束，隐藏刷新进度条

                    }
                });
            }
        }).start();

    }




    private void initFruirts(){
        fruitList.clear();//数据清空
        for(int i = 0;i<20;i++){
            //Fruit apple = new Fruit(getRandomLengthName("Apple"),R.drawable.applp_pic);
            if(connect){
                Fruit apple = new Fruit("Apple",R.drawable.lxc);
                fruitList.add(apple);
            }else {
                Fruit apple = new Fruit("Apple2",R.drawable.lxc2);
                fruitList.add(apple);
            }

        }

    }

//    private String getRandomLengthName(String name){
//        Random random = new Random();
//        int length = random.nextInt(20)+1;
//        StringBuilder builder = new StringBuilder();
//        for(int i=0;i<length;i++){
//            builder.append(name);
//        }
//        return builder.toString();
//    }
}
