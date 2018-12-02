package com.ibm.appman;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyContext.setMyContext(this);

        mRecyclerView = findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(mRecyclerView.getContext(),mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(itemDecor);

        PackageManager pm=getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);/*pm.getInstalledApplications(PackageManager.GET_META_DATA);*/

        List appList =new ArrayList();
        List packageList =new ArrayList();
        for (PackageInfo packageInfo : packages) {
           appList.add(packageInfo.applicationInfo.loadLabel(getPackageManager()));
            packageList.add(packageInfo.applicationInfo.packageName);

        }
        mAdapter = new MyAdapter(appList, packageList, pm);
        mRecyclerView.setAdapter(mAdapter);


    }
}
