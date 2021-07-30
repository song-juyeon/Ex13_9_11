package kr.hs.emirim.w2034.ex13_9_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seek1, seek2;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Thread 기본");

        seek1 = findViewById(R.id.seek1);
        seek2 = findViewById(R.id.seek2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        Button btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        for (int i=seek1.getProgress(); i < 100; i=i+2) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seek1.setProgress(seek1.getProgress() + 2);
                                    tv1.setText(R.string.tv1);
                                    tv1.append(" "+seek1.getProgress() + "%");
                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                Thread thread2 = new Thread(){
                    @Override
                    public void run() {
                        for (int i=seek2.getProgress(); i < 100; i++) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seek2.setProgress(seek2.getProgress() + 1);
                                    tv2.setText(R.string.tv2);
                                    tv2.append(" "+seek2.getProgress() + "%");
                                }
                            });

                            SystemClock.sleep(100);
                        }
                    }
                };
                thread2.start();
            }
        });
    }
}