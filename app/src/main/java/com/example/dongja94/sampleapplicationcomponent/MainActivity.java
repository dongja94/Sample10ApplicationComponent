package com.example.dongja94.sampleapplicationcomponent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputView;
    private static final int REQUEST_CODE_OTHER = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputView = (EditText)findViewById(R.id.edit_input);
        Button btn = (Button)findViewById(R.id.btn_other);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputView.getText().toString();
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                Person p = new Person();
                p.message = message;
                p.name = "ysi";
                p.age = 42;

                intent.putExtra(OtherActivity.EXTRA_PERSON, p);
//                intent.putExtra(OtherActivity.EXTRA_MESSAGE, message);
//                intent.putExtra(OtherActivity.EXTRA_NAME, "ysi");
//                intent.putExtra(OtherActivity.EXTRA_AGE, 42);

                startActivityForResult(intent, REQUEST_CODE_OTHER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OTHER) {
            if (resultCode == Activity.RESULT_OK) {
                String resultMessage = data.getStringExtra(OtherActivity.RESULT_MESSAGE);
                Toast.makeText(this, "result : " + resultMessage, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Result Cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
