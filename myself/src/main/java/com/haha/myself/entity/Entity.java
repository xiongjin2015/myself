package com.haha.myself.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by haha on 16/3/14.
 *
 * 实现Parcelable接口
 */
public class Entity implements Parcelable{

    private int id;
    private String name;

    public Entity(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public Entity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Log.i("XJ","set name:"+name);
        this.name = name;
    }

    //默认返回“0”就行
    @Override
    public int describeContents() {
        return 0;
    }

    //将对象数据写入到Parcel中，如intent.putExtra("Parcel",entity)将调用；
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.i("xj","start write to Parcel");
        dest.writeInt(id);
        dest.writeString(name);

    }

    // 定义Parcelable.Creator内部类，注意：类名只能为CREATOR，全大写，不能为小写或者其他名
    // public static final 这些修饰符一个都不能少
    public static final Parcelable.Creator<Entity> CREATOR = new Parcelable.Creator<Entity>(){

        //反序列将调用该方法还原，如Entity entity = （Entity）intent.getParcelableExtra("Parcel")将调用该方法
        @Override
        public Entity createFromParcel(Parcel source) {
            return new Entity(source);
        }

        // 默认返回new Entity[size]
        @Override
        public Entity[] newArray(int size) {
            return new Entity[size];
        }
    };

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        return json;
    }


}
