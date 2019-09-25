package com.example.administrator.buy__test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.csii.csiipay.ChoosePayStyleActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    String content;
    private EditText ed;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        content = "123";
        btn = (Button) findViewById(R.id.btn);
        ed = (EditText) findViewById(R.id.ed_pid);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt = ed.getText().toString();
                if (TextUtils.isEmpty(edt)) {
                    Toast.makeText(MainActivity.this, "预支付id不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, ChoosePayStyleActivity.class);
                intent.putExtra("prePayId", edt);//商户交易信息
                startActivityForResult(intent, 2);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            String result = data.getStringExtra("result");
            if (result.equalsIgnoreCase("success")) {
                tv_result.setText("支付成功");
            }else {
                tv_result.setText("支付失败");
            }
        }
    }
}
