package com.wordpress.tahmidcse.mathkidding;

import java.util.Random;

public class RandCreate {
    public int x;
    public int randCreate(int a,int b){

        Random randVal = new Random();

        if(a==0){
            x=randVal.nextInt();
        }
        else if(a==1){
            switch (b){
                case 1:
                    x=randVal.nextInt(10);
                    break;
                case 2:
                    x=15+randVal.nextInt(25);
                    break;
                case 3:
                    x=60+randVal.nextInt(140);
                    break;
                case 4:
                    x=400+randVal.nextInt(600);
                    break;
                case 5:
                    x=2000+randVal.nextInt(8000);
                    break;
            }
        }
        else if(a==2 || a==3){
            switch (b){
                case 1:
                    x=randVal.nextInt(10);
                    break;
                case 2:
                    x=15+randVal.nextInt(25);
                    break;
                case 3:
                    x=60+randVal.nextInt(140);
                    break;
                case 4:
                    x=400+randVal.nextInt(600);
                    break;
                case 5:
                    x=2000+randVal.nextInt(8000);
                    break;
            }
        }
        else if (a==4 || a==5){
            switch (b){
                case 1:
                    x=randVal.nextInt(10);
                    break;
                case 2:
                    x=randVal.nextInt(100);
                    break;
                case 3:
                    x=10+randVal.nextInt(90);
                    break;
                case 4:
                    x=100+randVal.nextInt(900);
                    break;
                case 5:
                    x=10+randVal.nextInt(990);
                    break;
            }
        }
        return x;
    }
}
