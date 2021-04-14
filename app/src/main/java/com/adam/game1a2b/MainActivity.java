package com.adam.game1a2b;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView log;
    String answer;
    int t = 4;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        log = findViewById(R.id.log);
        log.setMovementMethod(ScrollingMovementMethod.getInstance()); //textView 滾輪上下滑動
        initGame();
    }

    private void initGame() {  //初始化
        counter = 0;
        answer = createAnswer(t);
        System.out.println("----------" + answer + "-----------");
        log.setText("");
        input.setText("");
    }

    public String createAnswer(int i) {  //創造答案
        HashSet<Integer> ans = new HashSet<Integer>(i);
        while (ans.size() < i) {
            ans.add((int) (Math.random() * 10));
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (Integer k : ans) {
            list.add(k);
        }
        Collections.shuffle(list);
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer k : list) {
            stringBuffer.append(k);
        }
        return stringBuffer.toString();
    }

    public void guess(View view) {  //點下Guess的動作
        counter++;
        String text = input.getText().toString();
        String result = chenckAnswer(text);
        if (result.equals(t + "A0B")) {
            showResult(true);
        } else if (counter == t * t) {
            showResult(false);
        } else {
            showMessage(result);
        }
        log.append(counter + "." + text + " -----> " + result + "\n");
        input.setText("");
    }

    private String chenckAnswer(String text) {  //對答案
        int a, b;
        a = b = 0;
        for (Integer k = 0; k < text.length(); k++) {
            if (text.charAt(k) == answer.charAt(k)) {
                a++;
            } else if (answer.contains("" + text.charAt(k))) {
                b++;
            }
        }
        return String.format("%dA%dB", a, b);
    }

    private void showMessage(String result) {  //猜題結果
        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage(result)
                .create()
                .show();
    }

    private void showResult(boolean win) {  //表示輸贏
        new AlertDialog.Builder(this)
                .setTitle(win ? "Winner" : "Loser")
                .setMessage(win ? "Congratulations" : "Game Over" + "\n" + "Anser is " + answer)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initGame();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();
    }

    public void reset(View view) {  //重新遊戲
        new AlertDialog.Builder(this)
                .setTitle("Restart Game?")
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initGame();
                    }
                })
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .create()
                .show();
    }

    public void exit(View view) {
        finish();
    }  //離開

    private long lastBackTime = 0;

    @Override
    public void onBackPressed() {  //返回鍵(三秒內要按兩次才會返回)
        if (System.currentTimeMillis() - lastBackTime < 3 * 1000) {
            super.onBackPressed();
        } else {
            lastBackTime = System.currentTimeMillis();
            Toast.makeText(this, "Please back again to exit", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    String[] items = {"3", "4", "5", "6"};
    int tempWhich;
    public void setting(View view) {  //調整難易度
        new AlertDialog.Builder(this)
                .setTitle("Setting")
                .setSingleChoiceItems(items, t-3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tempWhich=which;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        t=tempWhich+3;
                        initGame();
                    }
                })
                .create()
                .show();
    }
}