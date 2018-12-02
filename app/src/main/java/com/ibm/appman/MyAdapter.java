package com.ibm.appman;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List appName;
    private List packageName;
    private PackageManager pm;
    private Drawable icon;
    private Context adapterContext = null;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;
        public AppCompatButton button;
        private AppCompatActivity myActivity = null;
        public MyViewHolder(View v) {
            super(v);
            mImageView = v.findViewById(R.id.imageView);
            mTextView = v.findViewById(R.id.textView);
            button = v.findViewById(R.id.button);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List appName1, List packageName1, PackageManager packageManager) {
        appName = appName1;
        packageName = packageName1;
        pm = packageManager;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final int pos = position;
        holder.mTextView.setText(appName.get(position).toString());
        System.out.println("Package Name: "+appName.get(position).toString());
        try {
                icon = pm.getApplicationIcon(packageName.get(position).toString());
                holder.mImageView.setImageDrawable(icon);
         //   holder.mTextView.setCompoundDrawablesWithIntrinsicBounds(icon,0,0,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:com.example.mypackage"));
                MyContext.getMyContext().startActivity(intent);

            }

        });

    //    holder.mImageView.setImageResource(R.drawable.ic_launcher_background);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return appName.size();
    }
}