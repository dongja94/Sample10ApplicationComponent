package com.example.dongja94.sampleapplicationcomponent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    TextView messageView;
    EditText resultView;
    public static final String EXTRA_MESSAGE = "message";
    public static final String RESULT_MESSAGE = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        messageView = (TextView)findViewById(R.id.text_message);
        resultView = (EditText)findViewById(R.id.edit_result);

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        messageView.setText(message);

        Button btn = (Button)findViewById(R.id.btn_finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = resultView.getText().toString();
                // ...
                Intent data = new Intent();
                data.putExtra(RESULT_MESSAGE, result);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}
