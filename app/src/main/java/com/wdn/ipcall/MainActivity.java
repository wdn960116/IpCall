package com.wdn.ipcall;

import android.Manifest;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private EditText et_number;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        performCodeWithPermission("往外拨打电话时获取权限",new PermissionCallback() {
            @Override
            public void hasPermission() {

            }

            @Override
            public void noPermission() {

            }
        },Manifest.permission.PROCESS_OUTGOING_CALLS);
        et_number = findViewById(R.id.et_number);
        sp = getSharedPreferences("config", MODE_PRIVATE);

        String ipnumber = sp.getString("ipnumber", "");
        et_number.setText(ipnumber);
    }
    public void save(View view){
        String ipnumber = et_number.getText().toString().trim();
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("ipnumber",ipnumber);
        edit.commit();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


}
