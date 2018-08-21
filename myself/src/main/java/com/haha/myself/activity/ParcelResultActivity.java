package com.haha.myself.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.haha.myself.R;
import com.haha.myself.entity.Entity;
import com.haha.myself.manage.Single;

import org.json.JSONException;

/**
 * 1.Parcel study
 *
 * 2.GC and Memery Leaky Study
 */
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
        //Single.getInstance(this); //uncomment to this statments,Memeory Leaky because of single instance hold reference of activity
    }

    /**
     * 如果内存泄露，本方法将不会被调用：表示activity没有被回收；
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        Log.i("XJ", "---gc---");
        super.finalize();
    }

}
