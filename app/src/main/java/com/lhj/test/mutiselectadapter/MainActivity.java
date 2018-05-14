package com.lhj.test.mutiselectadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lhj.multiselect.MutilSelectAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    Button button,button1,button2;
    MutilSelectAdapter mutilSelectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
        initRecyclerView();
    }

    private void initButton(){
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().toString().equals("打开选择功能")){
                    button.setText("关闭选择功能");
                    mutilSelectAdapter.setSelect(true);
                }else{
                    button.setText("打开选择功能");
                    mutilSelectAdapter.setSelect(false);
                }
            }
        });
        button1 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mutilSelectAdapter.setFutureGenerations();
            }
        });
        button2 = findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mutilSelectAdapter.clearAllGenerations();
            }
        });
    }

    private void initRecyclerView() {
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(initMultiSelect());
    }

    private RecyclerView.Adapter initMultiSelect(){
        mutilSelectAdapter = new MutilSelectAdapter.MutiSelectAdapterBuilder()
                .setContext(this)
                .setStyle(MutilSelectAdapter.MutiSelectAdapterBuilder.Style.Center)
                .setAdapter(new StringAdapter(this,getList()))
                .addOnItemClickListerns(new MutilSelectAdapter.MutiSelectAdapterBuilder.OnItemClickMultiListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this, getList().get(position), Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        return mutilSelectAdapter;
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(i+"");
        }
        return list;
    }
}
