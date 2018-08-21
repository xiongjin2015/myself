package com.haha.myself.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haha.myself.R;
import com.haha.myself.entity.Entity;

public class ParcelTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_test);
        Entity entity = new Entity(1,"haha");
        entity.setName("zhangsan");

    }

    public void onClick(View view){
        Intent intent = new Intent(this, ParcelResultActivity.class);
        Entity entity = new Entity(2,"haha");
        entity.setName("lishi");
        intent.putExtra("Parcel", entity);
        startActivity(intent);

    }


}
