package com.wordpress.tahmidcse.mathkidding;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity {

    public static final int inf = -99999999;

    MyDB myDB;
    RandCreate r = new RandCreate();
    int operation,level,temp,result,op_a,op_b,op_c,op_d;

    int[] numAr = new int[7];
    int[] numArTmp = new int[7];
    char[] opAr = new char[7];
    char[] opArTmp = new char[7];
    String[] int2str = new String[7];
    String resStr,question;
    TextView textView1,option_a,option_b,option_c,option_d,timer;
    ImageView[] imageViews = new ImageView[5];

    private boolean isPaused = false;
    private boolean isCancelled = false;
    private long remainingTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Bundle bundle = getIntent().getExtras();
        myDB = new MyDB(PlayActivity.this);
        textView1 = (TextView)findViewById(R.id.equation);
        timer = (TextView)findViewById(R.id.timer);
        option_a = (TextView)findViewById(R.id.ans_a);
        option_b = (TextView)findViewById(R.id.ans_b);
        option_c = (TextView)findViewById(R.id.ans_c);
        option_d = (TextView)findViewById(R.id.ans_d);
        imageViews[0] = (ImageView)findViewById(R.id.c_im_1);
        imageViews[1] = (ImageView)findViewById(R.id.c_im_2);
        imageViews[2] = (ImageView)findViewById(R.id.c_im_3);
        imageViews[3] = (ImageView)findViewById(R.id.c_im_4);
        imageViews[4] = (ImageView)findViewById(R.id.c_im_5);

        question="";

        String getoperator = bundle.getString("passOperator");
        String getlevel = bundle.getString("passLevel");
        operation=Integer.parseInt(getoperator);
        level=Integer.parseInt(getlevel);

        result=setAndGet();

        if(result<0)
            resStr="("+Integer.toString(result)+")";
        else
            resStr=Integer.toString(result);
        resStr=resStr.replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

        setOptions();
        setAnswer();



        long milisInFuture = 11000;
        long countDownInterval = 1000;
        isCancelled = false;
        new CountDownTimer(milisInFuture,countDownInterval){

            @Override
            public void onTick(long l) {
                if (isPaused || isCancelled){
                    cancel();
                }
                else {
                    timer.setText(""+l/1000);
                    remainingTime = l;
                }

            }

            @Override
            public void onFinish() {
                timer.setText("Times up!!!");
                cancel();
                //Toast.makeText(PlayActivity.this,"দুঃখিত!!! তোমার সময় শেষ",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PlayActivity.this,LevelActivity.class);
                i.putExtra("passOperator",Integer.toString(operation));
                startActivity(i);
            }
        }.start();

    }

    protected int setAndGet(){

        int tempResult=0;

        if (operation==1){
            textView1.setText("গুনে বলতো মোট কয়টি?");
            if (level==1){
                numAr[0]=(r.randCreate(1,1)%6)+1;
                if (numAr[0]==1){
                    imageViews[0].setImageResource(R.drawable.c1);
                }
                else if (numAr[0]==2){
                    imageViews[0].setImageResource(R.drawable.c2);
                }
                else if (numAr[0]==3){
                    imageViews[0].setImageResource(R.drawable.c3);
                }
                else if (numAr[0]==4){
                    imageViews[0].setImageResource(R.drawable.c4);
                }
                else if (numAr[0]==5){
                    imageViews[0].setImageResource(R.drawable.c5);
                }
                else if (numAr[0]==6){
                    imageViews[0].setImageResource(R.drawable.c6);
                }
                tempResult=numAr[0];
            }
            else if (level==2){
                numAr[0]=(r.randCreate(1,1)%6)+1;
                if (numAr[0]==1){
                    imageViews[0].setImageResource(R.drawable.c8);
                    tempResult=8;
                }
                else if (numAr[0]==2){
                    imageViews[0].setImageResource(R.drawable.c9);
                    tempResult=9;
                }
                else if (numAr[0]==3){
                    imageViews[0].setImageResource(R.drawable.c10);
                    tempResult=10;
                }
                else if (numAr[0]==4){
                    imageViews[0].setImageResource(R.drawable.c12);
                    tempResult=12;
                }
                else if (numAr[0]==5){
                    imageViews[0].setImageResource(R.drawable.c14);
                    tempResult=14;
                }
                else if (numAr[0]==6){
                    imageViews[0].setImageResource(R.drawable.c15);
                    tempResult=15;
                }
            }
            else if (level==3){
                numAr[0]=(r.randCreate(1,1)%6)+1;
                if (numAr[0]==1){
                    imageViews[0].setImageResource(R.drawable.c9);
                    imageViews[1].setImageResource(R.drawable.c11);
                    tempResult=20;
                }
                else if (numAr[0]==2){
                    imageViews[0].setImageResource(R.drawable.c10);
                    imageViews[1].setImageResource(R.drawable.c12);
                    tempResult=22;
                }
                else if (numAr[0]==3){
                    imageViews[0].setImageResource(R.drawable.c15);
                    imageViews[1].setImageResource(R.drawable.c11_2);
                    tempResult=26;
                }
                else if (numAr[0]==4){
                    imageViews[0].setImageResource(R.drawable.c15);
                    imageViews[1].setImageResource(R.drawable.c15_2);
                    tempResult=30;
                }
                else if (numAr[0]==5){
                    imageViews[0].setImageResource(R.drawable.c8);
                    imageViews[1].setImageResource(R.drawable.c25);
                    tempResult=33;
                }
                else if (numAr[0]==6){
                    imageViews[0].setImageResource(R.drawable.c15);
                    imageViews[1].setImageResource(R.drawable.c20_3);
                    tempResult=35;
                }
            }
            else if (level==4){
                numAr[0]=(r.randCreate(1,1)%6)+1;
                if (numAr[0]==1){
                    imageViews[0].setImageResource(R.drawable.c1_2);
                    imageViews[1].setImageResource(R.drawable.c9);
                    imageViews[2].setImageResource(R.drawable.c15_2);
                    imageViews[3].setImageResource(R.drawable.c20_2);
                    tempResult=45;
                }
                else if (numAr[0]==2){
                    imageViews[0].setImageResource(R.drawable.c2);
                    imageViews[1].setImageResource(R.drawable.c12);
                    imageViews[2].setImageResource(R.drawable.c15);
                    imageViews[3].setImageResource(R.drawable.c20);
                    tempResult=49;
                }
                else if (numAr[0]==3){
                    imageViews[0].setImageResource(R.drawable.c1_2);
                    imageViews[1].setImageResource(R.drawable.c11);
                    imageViews[2].setImageResource(R.drawable.c15_2);
                    imageViews[3].setImageResource(R.drawable.c25);
                    tempResult=52;
                }
                else if (numAr[0]==4){
                    imageViews[0].setImageResource(R.drawable.c11_2);
                    imageViews[1].setImageResource(R.drawable.c11_3);
                    imageViews[2].setImageResource(R.drawable.c15_2);
                    imageViews[3].setImageResource(R.drawable.c20_3);
                    tempResult=57;
                }
                else if (numAr[0]==5){
                    imageViews[0].setImageResource(R.drawable.c6);
                    imageViews[1].setImageResource(R.drawable.c15);
                    imageViews[2].setImageResource(R.drawable.c20);
                    imageViews[3].setImageResource(R.drawable.c20_2);
                    tempResult=61;
                }
                else if (numAr[0]==6){
                    imageViews[0].setImageResource(R.drawable.c5);
                    imageViews[1].setImageResource(R.drawable.c15_2);
                    imageViews[2].setImageResource(R.drawable.c20_3);
                    imageViews[3].setImageResource(R.drawable.c25);
                    tempResult=65;
                }
            }
            else if (level==5){
                numAr[0]=(r.randCreate(1,1)%6)+1;
                if (numAr[0]==1){
                    imageViews[0].setImageResource(R.drawable.c3);
                    imageViews[1].setImageResource(R.drawable.c11_3);
                    imageViews[2].setImageResource(R.drawable.c15_2);
                    imageViews[3].setImageResource(R.drawable.c20);
                    imageViews[4].setImageResource(R.drawable.c25);
                    tempResult=74;
                }
                else if (numAr[0]==2){
                    imageViews[0].setImageResource(R.drawable.c8);
                    imageViews[1].setImageResource(R.drawable.c11_3);
                    imageViews[2].setImageResource(R.drawable.c20);
                    imageViews[3].setImageResource(R.drawable.c20_2);
                    imageViews[4].setImageResource(R.drawable.c20_3);
                    tempResult=79;
                }
                else if (numAr[0]==3){
                    imageViews[0].setImageResource(R.drawable.c6);
                    imageViews[1].setImageResource(R.drawable.c20_2);
                    imageViews[2].setImageResource(R.drawable.c12);
                    imageViews[3].setImageResource(R.drawable.c20);
                    imageViews[4].setImageResource(R.drawable.c25);
                    tempResult=83;
                }
                else if (numAr[0]==4){
                    imageViews[0].setImageResource(R.drawable.c14);
                    imageViews[1].setImageResource(R.drawable.c11);
                    imageViews[2].setImageResource(R.drawable.c20_2);
                    imageViews[3].setImageResource(R.drawable.c25);
                    imageViews[4].setImageResource(R.drawable.c20_3);
                    tempResult=90;
                }
                else if (numAr[0]==5){
                    imageViews[0].setImageResource(R.drawable.c8);
                    imageViews[1].setImageResource(R.drawable.c25);
                    imageViews[2].setImageResource(R.drawable.c20_3);
                    imageViews[3].setImageResource(R.drawable.c25);
                    imageViews[4].setImageResource(R.drawable.c20);
                    tempResult=98;
                }
                else if (numAr[0]==6){
                    imageViews[0].setImageResource(R.drawable.c20_2);
                    imageViews[1].setImageResource(R.drawable.c25);
                    imageViews[2].setImageResource(R.drawable.c20_3);
                    imageViews[3].setImageResource(R.drawable.c15_2);
                    imageViews[4].setImageResource(R.drawable.c20);
                    tempResult=100;
                }
            }
        }
        else if(operation==2){
            numAr[0]=r.randCreate(operation,level);
            numAr[1]=r.randCreate(operation,level);
            int2str[0] = Integer.toString(numAr[0]);
            int2str[1] = Integer.toString(numAr[1]);

            int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
            int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

            question+=int2str[0]+" + "+int2str[1]+" = ?";
            textView1.setText(question);
            tempResult=numAr[0]+numAr[1];
        }
        else if(operation==3){
            numAr[0]=r.randCreate(operation,level);
            numAr[1]=r.randCreate(operation,level);
            int2str[0] = Integer.toString(numAr[0]);
            int2str[1] = Integer.toString(numAr[1]);

            int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
            int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

            question+=int2str[0]+" - "+int2str[1]+" = ?";
            textView1.setText(question);
            tempResult=numAr[0]-numAr[1];
        }
        else if(operation==4){
            if(level==1){
                numAr[0]=r.randCreate(operation,1);
                numAr[1]=r.randCreate(operation,1);
            }
            else if(level==2){
                numAr[0]=r.randCreate(operation,3);
                numAr[1]=r.randCreate(operation,1);
            }
            else if(level==3){
                numAr[0]=r.randCreate(operation,3);
                numAr[1]=r.randCreate(operation,3);
            }
            else if(level==4){
                numAr[0]=r.randCreate(operation,4);
                numAr[1]=r.randCreate(0,0);
                if(numAr[1]<0)
                    numAr[1]=-r.randCreate(operation,2);
                else
                    numAr[1]=r.randCreate(operation,2);
            }
            else if(level==5){
                numAr[0]=r.randCreate(0,0);
                if(numAr[0]<0)
                    numAr[0]=-r.randCreate(operation,4);
                else
                    numAr[0]=r.randCreate(operation,4);
                numAr[1]=r.randCreate(0,0);
                if(numAr[1]<0)
                    numAr[1]=-r.randCreate(operation,4);
                else
                    numAr[1]=r.randCreate(operation,4);
            }
            if(numAr[0]<0)
                int2str[0] ="("+Integer.toString(numAr[0])+")";
            else
                int2str[0] =Integer.toString(numAr[0]);

            if(numAr[1]<0)
                int2str[1] ="("+Integer.toString(numAr[1])+")";
            else
                int2str[1] = Integer.toString(numAr[1]);

            int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
            int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

            question+=int2str[0]+" × "+int2str[1]+" = ?";
            textView1.setText(question);
            tempResult=numAr[0]*numAr[1];
        }
        else if(operation==5){
            if(level==1){
                numAr[0]=r.randCreate(operation,1);
                numAr[1]=2+r.randCreate(operation,1);
                numAr[0]/=2;
                numAr[1]/=2;
                numAr[0]*=numAr[1];
            }
            else if(level==2){
                numAr[0]=5+r.randCreate(operation,1);
                numAr[1]=1+r.randCreate(operation,1);
                numAr[0]*=numAr[1];
            }
            else if(level==3){
                numAr[0]=1+r.randCreate(operation,1);
                numAr[1]=1+r.randCreate(operation,1);
                numAr[0]*= -1;
                numAr[0]*=numAr[1];
            }
            else if(level==4){
                numAr[0]=r.randCreate(0,0);
                if(numAr[0]<=0)
                    numAr[0]=-r.randCreate(operation,2);
                else
                    numAr[0]=r.randCreate(operation,2);
                numAr[1]=r.randCreate(0,0);
                if(numAr[1]<=0)
                    numAr[1]=-(1+r.randCreate(operation,3));
                else
                    numAr[1]=1+r.randCreate(operation,3);
                numAr[1]/=2;
                numAr[0]*=numAr[1];
            }
            else if(level==5){
                numAr[0]=r.randCreate(0,0);
                if(numAr[0]<=0)
                    numAr[0]=-r.randCreate(operation,5);
                else
                    numAr[0]=r.randCreate(operation,5);
                numAr[1]=r.randCreate(0,0);
                if(numAr[1]<=0)
                    numAr[1]=-(1+r.randCreate(operation,5));
                else
                    numAr[1]=1+r.randCreate(operation,5);
                numAr[1]/=2;
                numAr[0]*=numAr[1];
            }
            if(numAr[0]<0)
                int2str[0] ="("+Integer.toString(numAr[0])+")";
            else
                int2str[0] =Integer.toString(numAr[0]);

            if(numAr[1]<0)
                int2str[1] ="("+Integer.toString(numAr[1])+")";
            else
                int2str[1] = Integer.toString(numAr[1]);

            int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
            int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

            question+=int2str[0]+" ÷ "+int2str[1]+" = ?";
            textView1.setText(question);
            tempResult=numAr[0]/numAr[1];
        }
        else if(operation==6){
            if(level==1){
                numAr[0]=r.randCreate(0,0);
                if(numAr[0]<=0)
                    numAr[0]=-r.randCreate(4,3);
                else
                    numAr[0]=r.randCreate(4,3);
                numAr[1]=r.randCreate(0,0);
                if(numAr[1]<=0)
                    numAr[1]=-(1+r.randCreate(4,3));
                else
                    numAr[1]=1+r.randCreate(4,3);
                //bracket
                if(numAr[0]<0)
                    int2str[0] ="("+Integer.toString(numAr[0])+")";
                else
                    int2str[0] =Integer.toString(numAr[0]);
                if(numAr[1]<0)
                    int2str[1] ="("+Integer.toString(numAr[1])+")";
                else
                    int2str[1] = Integer.toString(numAr[1]);

                int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

                temp=r.randCreate(0,0);
                if(temp<0){
                    question+=int2str[0]+" - "+int2str[1]+" = ?";
                    textView1.setText(question);
                    tempResult=numAr[0]-numAr[1];
                }
                else{
                    question+=int2str[0]+" + "+int2str[1]+" = ?";
                    textView1.setText(question);
                    tempResult=numAr[0]+numAr[1];
                }
            }
            else if(level==2){
                numAr[0]=r.randCreate(0,0);
                if(numAr[0]<=0)
                    numAr[0]=-r.randCreate(4,4);
                else
                    numAr[0]=r.randCreate(4,4);
                numAr[1]=r.randCreate(0,0);
                if(numAr[1]<=0)
                    numAr[1]=-(1+r.randCreate(4,3));
                else
                    numAr[1]=1+r.randCreate(4,3);
                numAr[2]=r.randCreate(0,0);
                if(numAr[2]<=0)
                    numAr[2]=-(1+r.randCreate(4,4));
                else
                    numAr[2]=1+r.randCreate(4,4);

                //bracket
                if(numAr[0]<0)
                    int2str[0] ="("+Integer.toString(numAr[0])+")";
                else
                    int2str[0] =Integer.toString(numAr[0]);
                if(numAr[1]<0)
                    int2str[1] ="("+Integer.toString(numAr[1])+")";
                else
                    int2str[1] = Integer.toString(numAr[1]);
                if(numAr[2]<0)
                    int2str[2] ="("+Integer.toString(numAr[2])+")";
                else
                    int2str[2] = Integer.toString(numAr[2]);


                int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[2]=int2str[2].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

                temp=r.randCreate(0,0);
                if(temp<0){
                    int2str[0]=int2str[0]+"-"+int2str[1];
                    tempResult=numAr[0]-numAr[1];
                }
                else{
                    int2str[0]=int2str[0]+"+"+int2str[1];
                    tempResult=numAr[0]+numAr[1];
                }
                temp=r.randCreate(0,0);
                if(temp<0){
                    question+=int2str[0]+"-"+int2str[2]+"= ?";
                    textView1.setText(question);
                    tempResult-=numAr[2];
                }
                else{
                    question+=int2str[0]+"+"+int2str[2]+"= ?";
                    textView1.setText(question);
                    tempResult+=numAr[2];
                }
            }
            else if(level==3){
                numAr[0]=r.randCreate(0,0);
                if(numAr[0]<=0)
                    numAr[0]=-r.randCreate(4,4);
                else
                    numAr[0]=r.randCreate(4,4);
                numAr[1]=r.randCreate(0,0);
                if(numAr[1]<=0)
                    numAr[1]=-(1+r.randCreate(4,4));
                else
                    numAr[1]=1+r.randCreate(4,4);
                numAr[2]=r.randCreate(0,0);
                if(numAr[2]<=0)
                    numAr[2]=-(1+r.randCreate(4,4));
                else
                    numAr[2]=1+r.randCreate(4,4);

                //bracket
                if(numAr[0]<0)
                    int2str[0] ="("+Integer.toString(numAr[0])+")";
                else
                    int2str[0] =Integer.toString(numAr[0]);
                if(numAr[1]<0)
                    int2str[1] ="("+Integer.toString(numAr[1])+")";
                else
                    int2str[1] = Integer.toString(numAr[1]);
                if(numAr[2]<0)
                    int2str[2] ="("+Integer.toString(numAr[2])+")";
                else
                    int2str[2] = Integer.toString(numAr[2]);


                int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[2]=int2str[2].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

                temp=r.randCreate(0,0);
                if(temp%2==0){
                    opAr[0]='+';
                    numAr[3]=numAr[0]+numAr[1];
                }
                else{
                    opAr[0]='-';
                    numAr[3]=numAr[0]-numAr[1];
                }
                temp=r.randCreate(0,0);
                if(temp%2==0){
                    opAr[1]='+';
                    numAr[3]+=numAr[2];
                }
                else{
                    opAr[1]='-';
                    numAr[3]-=numAr[2];
                }
                opAr[2]='=';

                if(numAr[3]<0)
                    int2str[3] ="("+Integer.toString(numAr[3])+")";
                else
                    int2str[3] = Integer.toString(numAr[3]);
                int2str[3]=int2str[3].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

                temp=r.randCreate(0,0)%4;
                if(temp<0)
                    temp*= -1;
                for(int i=0;i<4;i++){
                    if(i==0){
                        if(i==temp)
                            int2str[4]="?";
                        else
                            int2str[4]=int2str[0];
                    }
                    else{
                        if(i==temp)
                            int2str[4]+="?";
                        else
                            int2str[4]+=int2str[i];
                    }
                    if(i!=3)
                        int2str[4]+=opAr[i];
                }
                question+=int2str[4];
                textView1.setText(question);
                tempResult=numAr[temp];
            }
            else if(level==4){
                opAr[2]='=';
                temp=r.randCreate(0,0);
                if(temp%2==0){
                    opAr[1]='×';
                    if(r.randCreate(0,0)<0)
                        numAr[1]=-r.randCreate(4,4);
                    else
                        numAr[1]=r.randCreate(4,4);
                    if(r.randCreate(0,0)<0)
                        numAr[2]=-r.randCreate(4,3);
                    else
                        numAr[2]=r.randCreate(4,3);
                    numAr[3]=numAr[1]*numAr[2];
                }
                else {
                    opAr[1] = '/';
                    if(r.randCreate(0,0)<0)
                        numAr[1] = -r.randCreate(4, 3);
                    else
                        numAr[1] = r.randCreate(4, 3);
                    if(r.randCreate(0,0)<0)
                        numAr[2] = -r.randCreate(4, 3);
                    else
                        numAr[2] = r.randCreate(4, 3);
                    numAr[3] = numAr[1];
                    numAr[1]*=numAr[2];
                }
                temp=r.randCreate(0,0);
                if(temp%2==0){
                    opAr[0]='+';
                    if(r.randCreate(0,0)<0)
                        numAr[0]=-r.randCreate(4,4);
                    else
                        numAr[0]=r.randCreate(4,4);
                    numAr[3]+=numAr[0];
                }
                else{
                    opAr[0]='-';
                    if(r.randCreate(0,0)<0)
                        numAr[0]=-r.randCreate(4,4);
                    else
                        numAr[0]=r.randCreate(4,4);
                    numAr[3]=numAr[0]-numAr[3];
                }

                //bracket
                if(numAr[0]<0)
                    int2str[0] ="("+Integer.toString(numAr[0])+")";
                else
                    int2str[0] =Integer.toString(numAr[0]);
                if(numAr[1]<0)
                    int2str[1] ="("+Integer.toString(numAr[1])+")";
                else
                    int2str[1] = Integer.toString(numAr[1]);
                if(numAr[2]<0)
                    int2str[2] ="("+Integer.toString(numAr[2])+")";
                else
                    int2str[2] = Integer.toString(numAr[2]);
                if(numAr[3]<0)
                    int2str[3] ="("+Integer.toString(numAr[3])+")";
                else
                    int2str[3] = Integer.toString(numAr[3]);


                int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[2]=int2str[2].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[3]=int2str[3].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

                temp=r.randCreate(0,0)%4;
                if(temp<0)
                    temp*= -1;
                for(int i=0;i<4;i++){
                    if(i==0){
                        if(i==temp)
                            int2str[4]="?";
                        else
                            int2str[4]=int2str[0];
                    }
                    else{
                        if(i==temp)
                            int2str[4]+="?";
                        else
                            int2str[4]+=int2str[i];
                    }
                    if(i==2)
                        int2str[4]+="}";
                    if(i!=3)
                        int2str[4]+=opAr[i];
                    if(i==0)
                        int2str[4]+="{";
                }
                question+=int2str[4].replaceAll("/","÷");
                textView1.setText(question);
                tempResult=numAr[temp];
            }
            else if(level==5){
                opAr[0]='+';
                opAr[1]='-';
                opAr[2]='*';
                opAr[3]='/';
                for(int i=4;i>=2;i--){
                    temp=r.randCreate(1,1)%i;
                    opAr[4]=opAr[i-1];
                    opAr[i-1]=opAr[temp];
                    opAr[temp]=opAr[4];
                }
                opAr[4]='=';
                for (int i=0;i<7;i++){
                    numAr[i]= inf;
                }

                for(int i=0;i<4;i++){
                    if(opAr[i]=='/'){
                        if (r.randCreate(0,0)<0)
                            numAr[i+1]=-(1+r.randCreate(4,1));
                        else
                            numAr[i+1]=1+r.randCreate(4,1);
                        if (r.randCreate(0,0)<0)
                            temp=-r.randCreate(4,2);
                        else
                            temp=r.randCreate(4,2);
                        numAr[i]=numAr[i+1]*temp;
                        for (int j=0;j<4;j++){
                            if(opAr[j]=='*'){
                                if(numAr[j+1]== inf){
                                    if (r.randCreate(0,0)<0)
                                        numAr[j+1]=-(1+r.randCreate(4,1));
                                    else
                                        numAr[j+1]=1+r.randCreate(4,1);
                                }
                                if (numAr[j]== inf){
                                    if (r.randCreate(0,0)<0)
                                        numAr[j]=-r.randCreate(4,3);
                                    else
                                        numAr[j]=r.randCreate(4,3);
                                }
                                for (int k=0;k<4;k++){
                                    if (opAr[k]=='+'){
                                        if (numAr[k]== inf){
                                            if (r.randCreate(0,0)<0)
                                                numAr[k]=-r.randCreate(4,4);
                                            else
                                                numAr[k]=r.randCreate(4,4);
                                        }
                                        if (numAr[k+1]== inf){
                                            if (r.randCreate(0,0)<0)
                                                numAr[k+1]=-r.randCreate(4,4);
                                            else
                                                numAr[k+1]=r.randCreate(4,4);
                                        }
                                        for (int l=0;l<4;l++){
                                            if (opAr[l]=='-'){
                                                if (numAr[l]== inf){
                                                    if (r.randCreate(0,0)<0)
                                                        numAr[l]=-r.randCreate(4,4);
                                                    else
                                                        numAr[l]=r.randCreate(4,4);
                                                }
                                                if(numAr[l+1]== inf){
                                                    if (r.randCreate(0,0)<0)
                                                        numAr[l+1]=-r.randCreate(4,4);
                                                    else
                                                        numAr[l+1]=r.randCreate(4,4);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for (int i=0;i<4;i++)
                    opArTmp[i]=opAr[i];
                for (int i=0;i<7;i++)
                    numArTmp[i]=numAr[i];
                for (int i=0;i<4;i++){
                    if(opArTmp[i]=='/'){
                        numArTmp[i]=numArTmp[i]/numArTmp[i+1];
                        for (int j=i;j<4;j++){
                            opArTmp[j]=opArTmp[j+1];
                        }
                        for (int j=i+1;j<6;j++){
                            numArTmp[j]=numArTmp[j+1];
                        }
                    }
                }
                for (int i=0;i<3;i++){
                    if(opArTmp[i]=='*'){
                        numArTmp[i]=numArTmp[i]*numArTmp[i+1];
                        for (int j=i;j<3;j++){
                            opArTmp[j]=opArTmp[j+1];
                        }
                        for (int j=i+1;j<5;j++){
                            numArTmp[j]=numArTmp[j+1];
                        }
                    }
                }
                for (int i=0;i<1;i++){
                    if(opArTmp[i]=='+'){
                        numArTmp[i]=numArTmp[i]+numArTmp[i+1];
                        for (int j=i;j<2;j++){
                            opArTmp[j]=opArTmp[j+1];
                        }
                        for (int j=i+1;j<4;j++){
                            numArTmp[j]=numArTmp[j+1];
                        }
                    }
                    else if(opArTmp[i]=='-'){
                        numArTmp[i]=numArTmp[i]-numArTmp[i+1];
                        for (int j=i;j<2;j++){
                            opArTmp[j]=opArTmp[j+1];
                        }
                        for (int j=i+1;j<4;j++){
                            numArTmp[j]=numArTmp[j+1];
                        }
                    }
                }
                for (int i=0;i<1;i++){
                    if(opArTmp[i]=='+'){
                        numArTmp[i]=numArTmp[i]+numArTmp[i+1];
                    }
                    else if(opArTmp[i]=='-'){
                        numArTmp[i]=numArTmp[i]-numArTmp[i+1];
                    }
                }

                numAr[5]=numArTmp[0];
                opAr[4]='=';
                for (int i=0;i<4;i++)
                    if (opAr[i]=='*')
                        opAr[i]='×';

                //Bracket
                if(numAr[0]<0)
                    int2str[0] ="("+Integer.toString(numAr[0])+")";
                else
                    int2str[0] =Integer.toString(numAr[0]);
                if(numAr[1]<0)
                    int2str[1] ="("+Integer.toString(numAr[1])+")";
                else
                    int2str[1] = Integer.toString(numAr[1]);
                if(numAr[2]<0)
                    int2str[2] ="("+Integer.toString(numAr[2])+")";
                else
                    int2str[2] = Integer.toString(numAr[2]);
                if(numAr[3]<0)
                    int2str[3] ="("+Integer.toString(numAr[3])+")";
                else
                    int2str[3] = Integer.toString(numAr[3]);
                if(numAr[4]<0)
                    int2str[4] ="("+Integer.toString(numAr[4])+")";
                else
                    int2str[4] = Integer.toString(numAr[4]);
                if(numAr[5]<0)
                    int2str[5] ="("+Integer.toString(numAr[5])+")";
                else
                    int2str[5] = Integer.toString(numAr[5]);


                int2str[0]=int2str[0].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[1]=int2str[1].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[2]=int2str[2].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[3]=int2str[3].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[4]=int2str[4].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");
                int2str[5]=int2str[5].replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯");

                temp=r.randCreate(0,0)%6;
                if(temp<0)
                    temp*= -1;
                for(int i=0;i<6;i++){
                    if(i==0){
                        if(i==temp)
                            int2str[6]="?";
                        else
                            int2str[6]=int2str[0];
                    }
                    else{
                        if(i==temp)
                            int2str[6]+="?";
                        else
                            int2str[6]+=int2str[i];
                    }
                    if(i!=5)
                        int2str[6]+=opAr[i];
                }
                question+=int2str[6].replaceAll("/","÷");
                textView1.setText(question);
                tempResult=numAr[temp];
            }
        }
        return tempResult;
    }

    protected void setOptions(){
        if(level==1){
            if(r.randCreate(1,1)%2 == 0){
                op_a=result+r.randCreate(1,1);
                while (op_a==result)
                    op_a=result+r.randCreate(1,1);
            }
            else{
                op_a=result-r.randCreate(1,1);
                while (op_a==result)
                    op_a=result-r.randCreate(1,1);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_b=result+r.randCreate(1,1);
                while (op_b==result || op_b==op_a)
                    op_b=result+r.randCreate(1,1);
            }
            else{
                op_b=result-r.randCreate(1,1);
                while (op_b==result || op_b==op_a)
                    op_b=result-r.randCreate(1,1);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_c=result+r.randCreate(1,1);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result+r.randCreate(1,1);
            }
            else{
                op_c=result-r.randCreate(1,1);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result-r.randCreate(1,1);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_d=result+r.randCreate(1,1);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result+r.randCreate(1,1);
            }
            else{
                op_d=result-r.randCreate(1,1);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result-r.randCreate(1,1);
            }
        }
        else if(level==2){
            if(r.randCreate(1,1)%2 == 0){
                op_a=result+r.randCreate(1,2);
                while (op_a==result)
                    op_a=result+r.randCreate(1,2);
            }
            else{
                op_a=result-r.randCreate(1,2);
                while (op_a==result)
                    op_a=result-r.randCreate(1,2);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_b=result+r.randCreate(1,2);
                while (op_b==result || op_b==op_a)
                    op_b=result+r.randCreate(1,2);
            }
            else{
                op_b=result-r.randCreate(1,2);
                while (op_b==result || op_b==op_a)
                    op_b=result-r.randCreate(1,2);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_c=result+r.randCreate(1,2);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result+r.randCreate(1,2);
            }
            else{
                op_c=result-r.randCreate(1,2);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result-r.randCreate(1,2);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_d=result+r.randCreate(1,2);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result+r.randCreate(1,2);
            }
            else{
                op_d=result-r.randCreate(1,2);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result-r.randCreate(1,2);
            }
        }
        else if(level==3){
            if(r.randCreate(1,1)%2 == 0){
                op_a=result+r.randCreate(1,3);
                while (op_a==result)
                    op_a=result+r.randCreate(1,3);
            }
            else{
                op_a=result-r.randCreate(1,3);
                while (op_a==result)
                    op_a=result-r.randCreate(1,3);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_b=result+r.randCreate(1,3);
                while (op_b==result || op_b==op_a)
                    op_b=result+r.randCreate(1,3);
            }
            else{
                op_b=result-r.randCreate(1,3);
                while (op_b==result || op_b==op_a)
                    op_b=result-r.randCreate(1,3);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_c=result+r.randCreate(1,3);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result+r.randCreate(1,3);
            }
            else{
                op_c=result-r.randCreate(1,3);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result-r.randCreate(1,3);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_d=result+r.randCreate(1,3);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result+r.randCreate(1,3);
            }
            else{
                op_d=result-r.randCreate(1,3);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result-r.randCreate(1,3);
            }
        }
        else if(level==4){
            if(r.randCreate(1,1)%2 == 0){
                op_a=result+r.randCreate(1,4);
                while (op_a==result)
                    op_a=result+r.randCreate(1,4);
            }
            else{
                op_a=result-r.randCreate(1,4);
                while (op_a==result)
                    op_a=result-r.randCreate(1,4);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_b=result+r.randCreate(1,4);
                while (op_b==result || op_b==op_a)
                    op_b=result+r.randCreate(1,4);
            }
            else{
                op_b=result-r.randCreate(1,4);
                while (op_b==result || op_b==op_a)
                    op_b=result-r.randCreate(1,4);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_c=result+r.randCreate(1,4);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result+r.randCreate(1,4);
            }
            else{
                op_c=result-r.randCreate(1,4);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result-r.randCreate(1,4);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_d=result+r.randCreate(1,4);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result+r.randCreate(1,4);
            }
            else{
                op_d=result-r.randCreate(1,4);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result-r.randCreate(1,4);
            }
        }
        else if(level==5){
            if(r.randCreate(1,1)%2 == 0){
                op_a=result+r.randCreate(1,5);
                while (op_a==result)
                    op_a=result+r.randCreate(1,5);
            }
            else{
                op_a=result-r.randCreate(1,5);
                while (op_a==result)
                    op_a=result-r.randCreate(1,5);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_b=result+r.randCreate(1,5);
                while (op_b==result || op_b==op_a)
                    op_b=result+r.randCreate(1,5);
            }
            else{
                op_b=result-r.randCreate(1,5);
                while (op_b==result || op_b==op_a)
                    op_b=result-r.randCreate(1,5);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_c=result+r.randCreate(1,5);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result+r.randCreate(1,5);
            }
            else{
                op_c=result-r.randCreate(1,5);
                while (op_c==result || op_c==op_a || op_c==op_b)
                    op_c=result-r.randCreate(1,5);
            }
            if(r.randCreate(1,1)%2 == 0){
                op_d=result+r.randCreate(1,5);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result+r.randCreate(1,5);
            }
            else{
                op_d=result-r.randCreate(1,5);
                while (op_d==result || op_d==op_a || op_d==op_b || op_d==op_c)
                    op_d=result-r.randCreate(1,5);
            }
        }
        if(operation==1){
            if (op_a<0)
                op_a*= -1;
            if (op_b<0)
                op_b*= -1;
            if (op_c<0)
                op_c*= -1;
            if (op_d<0)
                op_d*= -1;
            op_a%=100;
            op_b%=100;
            op_c%=100;
            op_d%=100;
        }
    }

    protected void setAnswer(){
        temp=r.randCreate(1,1)%4;
        if(temp==0)
            option_a.setText(resStr);
        else
            option_a.setText(Integer.toString(op_a).replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯"));
        if(temp==1)
            option_b.setText(resStr);
        else
            option_b.setText(Integer.toString(op_b).replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯"));
        if(temp==2)
            option_c.setText(resStr);
        else
            option_c.setText(Integer.toString(op_c).replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯"));
        if(temp==3)
            option_d.setText(resStr);
        else
            option_d.setText(Integer.toString(op_d).replaceAll("0","০").replaceAll("1","১").replaceAll("2","২").replaceAll("3","৩").replaceAll("4","৪").replaceAll("5","৫").replaceAll("6","৬").replaceAll("7","৭").replaceAll("8","৮").replaceAll("9","৯"));

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

    public void check1(View view){
        int check = getVal((operation*10)+level);
        if (temp==0){
            Toast.makeText(PlayActivity.this,"অভিনন্দন!!! তোমার উত্তরটি  সঠিক",Toast.LENGTH_SHORT).show();
            if(check==0){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"1");
            }
            else if(check==1){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"2");
            }
            else if(check==2 || check==3){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"3");
                if (level<5){
                    myDB.updateInfo(Integer.toString(operation)+Integer.toString(level+1),"0");
                }
                else if (level==5){
                    myDB.updateInfo(Integer.toString(operation+1)+Integer.toString(1),"0");
                }
            }
            Intent i = new Intent(PlayActivity.this,LevelActivity.class);
            i.putExtra("passOperator",Integer.toString(operation));
            startActivity(i);
        }
        else
            Toast.makeText(PlayActivity.this,"দুঃখিত!!! তোমার উত্তরটি  ভুল",Toast.LENGTH_SHORT).show();
    }

    public void check2(View view){
        int check = getVal((operation*10)+level);
        if (temp==1) {
            Toast.makeText(PlayActivity.this,"অভিনন্দন!!! তোমার উত্তরটি  সঠিক",Toast.LENGTH_SHORT).show();
            if(check==0){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"1");
            }
            else if(check==1){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"2");
            }
            else if(check==2 || check==3){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"3");
                if (level<5){
                    myDB.updateInfo(Integer.toString(operation)+Integer.toString(level+1),"0");
                }
                else if (level==5){
                    myDB.updateInfo(Integer.toString(operation+1)+Integer.toString(1),"0");
                }
            }
            Intent i = new Intent(PlayActivity.this,LevelActivity.class);
            i.putExtra("passOperator",Integer.toString(operation));
            startActivity(i);
        }
        else
            Toast.makeText(PlayActivity.this,"দুঃখিত!!! তোমার উত্তরটি  ভুল",Toast.LENGTH_SHORT).show();
    }

    public void check3(View view){
        int check = getVal((operation*10)+level);
        if (temp==2){
            Toast.makeText(PlayActivity.this,"অভিনন্দন!!! তোমার উত্তরটি  সঠিক",Toast.LENGTH_SHORT).show();
            if(check==0){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"1");
            }
            else if(check==1){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"2");
            }
            else if(check==2 || check==3){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"3");
                if (level<5){
                    myDB.updateInfo(Integer.toString(operation)+Integer.toString(level+1),"0");
                }
                else if (level==5){
                    myDB.updateInfo(Integer.toString(operation+1)+Integer.toString(1),"0");
                }
            }
            Intent i = new Intent(PlayActivity.this,LevelActivity.class);
            i.putExtra("passOperator",Integer.toString(operation));
            startActivity(i);
        }
        else
            Toast.makeText(PlayActivity.this,"দুঃখিত!!! তোমার উত্তরটি  ভুল",Toast.LENGTH_SHORT).show();
    }

    public void check4(View view){
        int check = getVal((operation*10)+level);
        if (temp==3){
            Toast.makeText(PlayActivity.this,"অভিনন্দন!!! তোমার উত্তরটি  সঠিক",Toast.LENGTH_SHORT).show();
            if(check==0){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"1");
            }
            else if(check==1){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"2");
            }
            else if(check==2 || check==3){
                myDB.updateInfo(Integer.toString(operation)+Integer.toString(level),"3");
                if (level<5){
                    myDB.updateInfo(Integer.toString(operation)+Integer.toString(level+1),"0");
                }
                else if (level==5){
                    myDB.updateInfo(Integer.toString(operation+1)+Integer.toString(1),"0");
                }
            }
            Intent i = new Intent(PlayActivity.this,LevelActivity.class);
            i.putExtra("passOperator",Integer.toString(operation));
            startActivity(i);
        }
        else
            Toast.makeText(PlayActivity.this,"দুঃখিত!!! তোমার উত্তরটি  ভুল",Toast.LENGTH_SHORT).show();
    }


}
