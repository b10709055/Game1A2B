package com.adam.game1a2b;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void rule(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Rule")
                .setMessage(
                        "1. 猜數字題目的謎底是一個n位數，n個數字互不相同。\n" +
                        "2. 1A代表所猜的數字中有一個數數謎底相同，且數字的位置也相同。\n" +
                        "3. 1B代表所猜的數字中有一個數數謎底相同，但這數字卻不在正確的位置上。\n" +
                        "4. 例如：謎底是147，所猜數字為157，則會回應2A。\n" +
                        "5. 例如：謎底是147，所猜數字為418，則會回應2B。\n" +
                        "6. 例如：謎底是147，所猜數字為174，則會回應1A2B。\n"+
                        "7. 勝利的條件是達到nA0B，玩家有n*n次機會挑戰")
                .setPositiveButton("OK",null)
                .create()
                .show();
    }

    public void play(View view) {
        Intent intent = new Intent();
        intent.setClass(Welcome.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}