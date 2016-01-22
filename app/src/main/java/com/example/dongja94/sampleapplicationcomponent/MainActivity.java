package com.example.dongja94.sampleapplicationcomponent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
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

        btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("count", 100);
                startService(intent);
            }
        });

        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

        btn = (Button)findViewById(R.id.btn_count);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myService != null) {
                    try {
                        int count = myService.getCount();
                        Toast.makeText(MainActivity.this, "count : " + count, Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }

    IMyService myService;
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = IMyService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }
    };

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
