package com.wordpress.tahmidcse.mathkidding;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LevelActivity extends Activity {

    MyDB myDB;
    Button b1,b2,b3,b4,b5;
    ImageView[] iv = new ImageView[5];
    TextView opName;

    String lv,op;
    int operator,check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Bundle bundle = getIntent().getExtras();
        op=bundle.getString("passOperator");
        operator=Integer.parseInt(op);


        myDB = new MyDB(LevelActivity.this);
        b1=(Button)findViewById(R.id.but_lv_1);
        b2=(Button)findViewById(R.id.but_lv_2);
        b3=(Button)findViewById(R.id.but_lv_3);
        b4=(Button)findViewById(R.id.but_lv_4);
        b5=(Button)findViewById(R.id.but_lv_5);
        iv[0]=(ImageView)findViewById(R.id.im_lv_1) ;
        iv[1]=(ImageView)findViewById(R.id.im_lv_2) ;
        iv[2]=(ImageView)findViewById(R.id.im_lv_3) ;
        iv[3]=(ImageView)findViewById(R.id.im_lv_4) ;
        iv[4]=(ImageView)findViewById(R.id.im_lv_5) ;
        opName=(TextView)findViewById(R.id.tv_operation) ;

        set(operator);
        setImage();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=(operator*10)+1;
                if (getVal2(check)!= -1){
                    lv = "1";
                    Intent i = new Intent(LevelActivity.this,PlayActivity.class);
                    i.putExtra("passOperator",op);
                    i.putExtra("passLevel",lv);
                    startActivity(i);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=(operator*10)+2;
                if (getVal2(check)!= -1){
                    lv = "2";
                    Intent i = new Intent(LevelActivity.this,PlayActivity.class);
                    i.putExtra("passOperator",op);
                    i.putExtra("passLevel",lv);
                    startActivity(i);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=(operator*10)+3;
                if (getVal2(check)!= -1){
                    lv = "3";
                    Intent i = new Intent(LevelActivity.this,PlayActivity.class);
                    i.putExtra("passOperator",op);
                    i.putExtra("passLevel",lv);
                    startActivity(i);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=(operator*10)+4;
                if (getVal2(check)!= -1){
                    lv = "4";
                    Intent i = new Intent(LevelActivity.this,PlayActivity.class);
                    i.putExtra("passOperator",op);
                    i.putExtra("passLevel",lv);
                    startActivity(i);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=(operator*10)+5;
                if (getVal2(check)!= -1){
                    lv = "5";
                    Intent i = new Intent(LevelActivity.this,PlayActivity.class);
                    i.putExtra("passOperator",op);
                    i.putExtra("passLevel",lv);
                    startActivity(i);
                }
            }
        });
    }

    public void set(int op){

        if (op==1){
            opName.setText("চল গণনা করি");
        }
        else if (op==2){
            opName.setText("চল যোগ করি");
        }
        else if (op==3){
            opName.setText("চল বিয়োগ করি");
        }
        else if (op==4){
            opName.setText("চল গুণ করি");
        }
        else if (op==5){
            opName.setText("চল ভাগ করি");
        }
        else if (op==6){
            opName.setText("চল সরল করি");
        }
    }

    public void setImage(){
        int check;
        for (int i=1; i<=5; i++){
            check = getVal(i);
            if(check== -2){
                Toast.makeText(this,"No Previous Score Found",Toast.LENGTH_SHORT).show();
                iv[i-1].setImageResource(R.drawable.lock);
            }
            else if (check== -1){
                iv[i-1].setImageResource(R.drawable.lock);
            }
            else if (check==0){
                iv[i-1].setImageResource(R.drawable.star_0);
            }
            else if (check==1){
                iv[i-1].setImageResource(R.drawable.star_1);
            }
            else if (check==2){
                iv[i-1].setImageResource(R.drawable.star_2);
            }
            else if (check==3){
                iv[i-1].setImageResource(R.drawable.star_3);
            }
        }
    }

    public int getVal(int lvl){
        Cursor result = myDB.getInfo();
        int temp;

        if (result.getCount()==0){
            Toast.makeText(this,"No Table Found",Toast.LENGTH_SHORT).show();
        }
        result.moveToFirst();

        temp=(operator*10)+lvl;
        do {
            if (Integer.parseInt(result.getString(0)) == temp){
                return Integer.parseInt(result.getString(1));
            }
        }while (result.moveToNext());
        return -2;
    }

    public int getVal2(int temp){
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
