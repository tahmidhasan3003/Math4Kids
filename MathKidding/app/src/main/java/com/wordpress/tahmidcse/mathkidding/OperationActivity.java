package com.wordpress.tahmidcse.mathkidding;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class OperationActivity extends Activity {

    MyDB myDB;
    ImageButton count,add,sub,mul,div,sorol;
    ImageView[] iv = new ImageView[6];
    String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);

        myDB = new MyDB(OperationActivity.this);
        count = (ImageButton) findViewById(R.id.im_count);
        add = (ImageButton) findViewById(R.id.im_add);
        sub = (ImageButton) findViewById(R.id.im_sub);
        mul = (ImageButton) findViewById(R.id.im_mul);
        div = (ImageButton) findViewById(R.id.im_div);
        sorol =(ImageButton) findViewById(R.id.im_sorol);
        iv[0]=(ImageView)findViewById(R.id.im_op_1) ;
        iv[1]=(ImageView)findViewById(R.id.im_op_2) ;
        iv[2]=(ImageView)findViewById(R.id.im_op_3) ;
        iv[3]=(ImageView)findViewById(R.id.im_op_4) ;
        iv[4]=(ImageView)findViewById(R.id.im_op_5) ;
        iv[5]=(ImageView)findViewById(R.id.im_op_6) ;

        final int check;

        setImage();

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getVal(11)!= -1){
                    op="1";
                    Intent i = new Intent(OperationActivity.this,LevelActivity.class);
                    i.putExtra("passOperator",op);
                    startActivity(i);
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getVal(21)!= -1){
                    op="2";
                    Intent i = new Intent(OperationActivity.this,LevelActivity.class);
                    i.putExtra("passOperator",op);
                    startActivity(i);
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getVal(31)!= -1){
                    op="3";
                    Intent i = new Intent(OperationActivity.this,LevelActivity.class);
                    i.putExtra("passOperator",op);
                    startActivity(i);
                }
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVal(41)!= -1){
                    op="4";
                    Intent i = new Intent(OperationActivity.this,LevelActivity.class);
                    i.putExtra("passOperator",op);
                    startActivity(i);
                }
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVal(51)!= -1){
                    op="5";
                    Intent i = new Intent(OperationActivity.this,LevelActivity.class);
                    i.putExtra("passOperator",op);
                    startActivity(i);
                }
            }
        });
        sorol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVal(61)!= -1){
                    op="6";
                    Intent i = new Intent(OperationActivity.this,LevelActivity.class);
                    i.putExtra("passOperator",op);
                    startActivity(i);
                }
            }
        });
    }

    public void setImage(){
        int check;
        for (int i=1; i<=6; i++){
            check = getVal((i*10)+5);
            if(check!=3){
                check = getVal((i*10)+3);
                if(check!=3){
                    check = getVal((i*10)+1);
                    if (check==3){
                        iv[i-1].setImageResource(R.drawable.star_1);
                    }
                    else if (check== -1 || check== -2){
                        iv[i-1].setImageResource(R.drawable.lock);
                    }
                    else {
                        iv[i-1].setImageResource(R.drawable.star_0);
                    }
                }
                else {
                    iv[i-1].setImageResource(R.drawable.star_2);
                }
            }
            else {
                iv[i-1].setImageResource(R.drawable.star_3);
            }
        }
    }

    public int getVal(int temp){
        Cursor result = myDB.getInfo();

        if (result.getCount()==0){
            Toast.makeText(this,"No Table Found",Toast.LENGTH_SHORT).show();
        }
        result.moveToFirst();

        do {
            if (Integer.parseInt(result.getString(0)) == temp){
                return Integer.parseInt(result.getString(1));
            }
        }while (result.moveToNext());
        return -2;
    }

}
