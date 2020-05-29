package com.mostafa.bittask.Base;

import android.content.SharedPreferences;
import android.os.Bundle;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    MaterialDialog dialog;
    protected BaseActivity activity;

    public static final String baseUrl =  "http://i0sa.com/bit/task/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
    }


    public MaterialDialog showMessageString(String title,String message,String posText){

        dialog= new MaterialDialog.Builder(this)
                .title(title)
                .content(message)
                .positiveText(posText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
        return dialog;
    }

    public MaterialDialog showMessageInt(int titleResId,int messageResId,int posResText){

        new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(messageResId)
                .positiveText(posResText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
        return dialog;
    }

    public MaterialDialog showConfirmationMessageInt(int titleResId,
                                                     int contentResId,
                                                     int posTextResId,
                                                     MaterialDialog.SingleButtonCallback onPos
    ){
        dialog= new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(contentResId)
                .positiveText(posTextResId)
                .onPositive(onPos)
                .show();
        return dialog;
    }

    public MaterialDialog showConfirmationMessageString(String titleResId,
                                                        String contentResId,
                                                        String posTextResId,
                                                        MaterialDialog.SingleButtonCallback onPos
    ){
        dialog= new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(contentResId)
                .positiveText(posTextResId)
                .onPositive(onPos)
                .show();
        return dialog;
    }


    public MaterialDialog showProgressBar(int titleResId, int contentResId){
        dialog= new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(contentResId)
                .progress(true,0)
                .cancelable(false)
                .show();
        return dialog;
    }

    public void hideProgressBar(){
        if(dialog!=null&&dialog.isShowing())
            dialog.dismiss();
    }

    public void saveStringValue(String key,String value){
        SharedPreferences.Editor editor=
                getSharedPreferences("chatAppFile",MODE_PRIVATE)
                        .edit();
        editor.putString(key,value);
        editor.apply();

    }
    public String getStringValue(String key){
        SharedPreferences sharedPreferences= getSharedPreferences("chatAppFile",
                MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }


    public void clearStringValue (String key){

        SharedPreferences.Editor editor =
                getSharedPreferences("chatAppFile", MODE_PRIVATE)
                        .edit();

        editor.remove(key);

        editor.apply();
    }
}
