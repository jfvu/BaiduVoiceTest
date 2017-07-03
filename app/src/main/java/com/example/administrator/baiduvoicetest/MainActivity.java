package com.example.administrator.baiduvoicetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MySpeechRecognizer.ResultsCallback{
    private TextView textView;
    private Button button;
    private MySpeechRecognizer mySpeechRecognizer = new MySpeechRecognizer(this);
    private boolean tag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_show);
        button = (Button) findViewById(R.id.but1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tag){
                    button.setText("停止说话后请点我");
                    tag = true;
                    mySpeechRecognizer.startASR(MainActivity.this);

                }else {
                    mySpeechRecognizer.stopASR();
                    tag = false;
                    button.setText("按下后开始说话");
                }
            }
        });
    }

    @Override
    public void onResults(String result) {
        final String s = result;
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(s);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mySpeechRecognizer.createTool();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mySpeechRecognizer.destroyTool();
    }

}
