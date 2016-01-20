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

    public static final String EXTRA_PERSON = "person";
    TextView messageView;
    EditText resultView;
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_AGE = "age";
    public static final String RESULT_MESSAGE = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        messageView = (TextView)findViewById(R.id.text_message);
        resultView = (EditText)findViewById(R.id.edit_result);

        Intent intent = getIntent();
//        String message = intent.getStringExtra(EXTRA_MESSAGE);
//        String name = intent.getStringExtra(EXTRA_NAME);
//        int age = intent.getIntExtra(EXTRA_AGE, 0);

//        Person p = (Person)intent.getSerializableExtra(EXTRA_PERSON);

        Person p = intent.getParcelableExtra(EXTRA_PERSON);

        messageView.setText(p.message);

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
