package com.haha.myself.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.haha.myself.R;
import com.haha.myself.entity.Entity;

import org.json.JSONException;

public class ParcelResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Entity entity = (Entity) intent.getParcelableExtra("Parcel");
        TextView tv = new TextView(this);
        // tv.setText("entity [id="+entity.getId()+",name="+entity.getName()+"]");
        try {
            tv.setText(entity.toJson().toString());
        } catch (JSONException e) {
            tv.setText("entity [id=" + entity.getId() + ",name=" + entity.getName() + "]");
            e.printStackTrace();
        }
        setContentView(tv);
        // Single.getInstance(this);
    }

    @Override
    protected void finalize() throws Throwable {
        Log.i("XJ", "---gc---");
        super.finalize();
    }

}
