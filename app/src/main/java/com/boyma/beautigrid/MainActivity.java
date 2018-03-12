package com.boyma.beautigrid;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.askerov.dynamicgrid.DynamicGridView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private DynamicGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);

        //String[] ss = {"browser","book","cash","city","credit"};
        ArrayList<org.askerov.dynamicgrid.MyObj> al = new ArrayList<>();
        al.add(new org.askerov.dynamicgrid.MyObj(1,"browser",null));
        al.add(new org.askerov.dynamicgrid.MyObj(1,"book",null));
        al.add(new org.askerov.dynamicgrid.MyObj(1,"cash",null));
        al.add(new org.askerov.dynamicgrid.MyObj(1,"city",null));
        al.add(new org.askerov.dynamicgrid.MyObj(1,"credit",null));

        //final TypedArray imgs = getResources().obtainTypedArray(R.array.random_imgs);

        gridView.setAdapter(new CheeseDynamicAdapter(this,
                //new ArrayList<MyObj>(Arrays.asList(ss)),
                al,
                3));
//        add callback to stop edit mode if needed
        gridView.setOnDropListener(new DynamicGridView.OnDropListener()
        {
            @Override
            public void onActionDrop()
            {
                gridView.stopEditMode();
            }
        });
        gridView.setOnDragListener(new DynamicGridView.OnDragListener() {
            @Override
            public void onDragStarted(int position) {
                Log.d("asd", "drag started at position " + position);
            }

            @Override
            public void onDragPositionsChanged(int oldPosition, int newPosition) {
                Log.d("asd", String.format("drag item position changed from %d to %d", oldPosition, newPosition));
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.startEditMode(position);
                return true;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, parent.getAdapter().getItem(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (gridView.isEditMode()) {
            gridView.stopEditMode();
        } else {
            super.onBackPressed();
        }
    }
}
