package com.example.dongja94.sampleapplicationcomponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    TextView messageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        messageView = (TextView)findViewById(R.id.text_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        messageView.setText(message);

        Button btn = (Button)findViewById(R.id.btn_finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
