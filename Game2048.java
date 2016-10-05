package com.acmegames.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import com.acmegames.game2048.SwipeScreen.SimpleGestureListener;

import java.util.Random;

public class Game2048 extends AppCompatActivity implements SimpleGestureListener {

    private SwipeScreen detector;
    Button jb[][]=new Button[4][4];
    private int score=0;
    TextView sc;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2048);
        jb[0][0]=(Button)findViewById(R.id.b1);
        jb[0][1]=(Button)findViewById(R.id.b2);
        jb[0][2]=(Button)findViewById(R.id.b3);
        jb[0][3]=(Button)findViewById(R.id.b4);
        jb[1][0]=(Button)findViewById(R.id.b5);
        jb[1][1]=(Button)findViewById(R.id.b6);
        jb[1][2]=(Button)findViewById(R.id.b7);
        jb[1][3]=(Button)findViewById(R.id.b8);
        jb[2][0]=(Button)findViewById(R.id.b9);
        jb[2][1]=(Button)findViewById(R.id.b10);
        jb[2][2]=(Button)findViewById(R.id.b11);
        jb[2][3]=(Button)findViewById(R.id.b12);
        jb[3][0]=(Button)findViewById(R.id.b13);
        jb[3][1]=(Button)findViewById(R.id.b14);
        jb[3][2]=(Button)findViewById(R.id.b15);
        jb[3][3]=(Button)findViewById(R.id.b16);
        sc=(TextView)findViewById(R.id.score);
        num_generator();
        num_generator();
        detector=new SwipeScreen(this,this);
    }
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }
    public void onSwipe(int direction) {
        switch (direction) {

            case SwipeScreen.SWIPE_RIGHT:
                dir('r');

                break;
            case SwipeScreen.SWIPE_LEFT:
                dir('l');
                break;
            case SwipeScreen.SWIPE_DOWN:
                dir('d');
                break;
            case SwipeScreen.SWIPE_UP:
                dir('u');
                break;
        }
        sc.setText("Score:"+Integer.toString(score));
        num_generator();
    }
        public void num_generator()
        {
            Random r=new Random();
            int n=r.nextInt(4)+0;
            int n1=r.nextInt(4)+0;
            int n2=r.nextInt(2)+0;
            int cnt=0;
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                {
                    if(!(jb[i][j].getText().equals("")))
                        cnt++;
                }
            }
            if(cnt<16)
            {
                if(jb[n][n1].getText().toString().equals(""))
                {
                    if(n2==0)
                        jb[n][n1].setText("2");
                    else if(n2==1)
                        jb[n][n1].setText("4");
                }
                else
                    num_generator();
            }
            else
            {
                endGame();
            }
        }
    public void dir(char d)
    {
        if(d=='r')
        {
            for(int i=0;i<4;i++)
            {
                String str1=jb[i][0].getText().toString();
                String str2=jb[i][1].getText().toString();
                String str3=jb[i][2].getText().toString();
                String str4=jb[i][3].getText().toString();
                if(str4.equals(str3))
                {
                    if(str4.equals(""))
                    {
                        if(str1.equals(str2) && !str1.equals(""))
                        {
                            int n=Integer.parseInt(str1);
                            jb[i][3].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][1].setText("");
                            jb[i][0].setText("");
                        }
                        else if(!str1.equals(str2))
                        {
                            if(!str1.equals("") && !str2.equals(""))
                            {
                                jb[i][3].setText(jb[i][1].getText().toString());
                                jb[i][2].setText(jb[i][0].getText().toString());
                                jb[i][1].setText("");
                                jb[i][0].setText("");
                            }
                            else if(str1.equals("") && !str2.equals(""))
                            {
                                jb[i][3].setText(jb[i][1].getText().toString());
                                jb[i][1].setText("");
                            }
                            else if(!str1.equals("") && str2.equals(""))
                            {
                                jb[i][3].setText(jb[i][0].getText().toString());
                                jb[i][0].setText("");
                            }
                        }
                    }
                    else if(!str4.equals(""))
                    {
                        int n=Integer.parseInt(str4);
                        jb[i][3].setText(Integer.toString(n*2));
                        score=score+(n*2);
                        if((str2.equals(str1) && !str2.equals("")))
                        {
                            n=Integer.parseInt(str2);
                            jb[i][2].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][1].setText("");
                            jb[i][0].setText("");
                        }
                        else if(!str2.equals("") && !str1.equals(""))
                        {
                            jb[i][2].setText(jb[i][1].getText().toString());
                            jb[i][1].setText(jb[i][0].getText().toString());
                            jb[i][0].setText("");
                        }
                        else if(!str2.equals("") && str1.equals(""))
                        {
                            jb[i][2].setText(jb[i][1].getText().toString());
                            jb[i][1].setText("");
                        }
                        else if(str2.equals("") && !str1.equals(""))
                        {
                            jb[i][2].setText(jb[i][0].getText().toString());
                            jb[i][0].setText("");
                        }
                        else if(str2.equals("") && str1.equals(""))
                        {
                            jb[i][2].setText("");
                        }
                    }
                }
                else if(!str4.equals(str3))
                {
                    if(!str4.equals("") && !str3.equals(""))
                    {
                        if(str1.equals(str2) && !str1.equals(""))
                        {
                            int n=Integer.parseInt(str1);
                            jb[i][1].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][0].setText("");
                        }
                        else if(str2.equals("")&&str3.equals(str1))
                        {
                            int n=Integer.parseInt(str1);
                            jb[i][2].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][0].setText("");
                        }
                        else if(str2.equals(""))
                        {
                            jb[i][1].setText(jb[i][0].getText().toString());
                            jb[i][0].setText("");
                        }
                        else if(str2.equals(str3))
                        {
                            int n=Integer.parseInt(str2);
                            jb[i][2].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][1].setText(jb[i][0].getText().toString());
                            jb[i][0].setText("");
                        }
                    }
                    else if(str4.equals("") && !str3.equals(""))
                    {
                        if(str3.equals(str2))
                        {
                            int n=Integer.parseInt(str2);
                            jb[i][3].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][2].setText(jb[i][0].getText().toString());
                            jb[i][1].setText("");
                            jb[i][0].setText("");
                        }
                        else if(str2.equals(str1) && !str2.equals(""))
                        {
                            jb[i][3].setText(jb[i][2].getText().toString());
                            int n=Integer.parseInt(str2);
                            score=score+(n*2);
                            jb[i][2].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][1].setText("");
                            jb[i][0].setText("");
                        }
                        else if(str2.equals(""))
                        {
                            if(str3.equals(str1))
                            {
                                int n=Integer.parseInt(str1);
                                jb[i][3].setText(Integer.toString(n*2));
                                score=score+(n*2);
                                jb[i][2].setText("");
                                jb[i][0].setText("");
                            }
                            else
                            {
                                jb[i][3].setText(jb[i][2].getText().toString());
                                jb[i][2].setText(jb[i][0].getText().toString());
                                jb[i][0].setText("");
                            }
                        }
                        else
                        {
                            jb[i][3].setText(jb[i][2].getText().toString());
                            jb[i][2].setText(jb[i][1].getText().toString());
                            jb[i][1].setText(jb[i][0].getText().toString());
                            jb[i][0].setText("");
                        }
                    }
                    else if(!str4.equals("") && str3.equals(""))
                    {
                        if(str2.equals(str1) && !str2.equals(""))
                        {
                            int n=Integer.parseInt(str2);
                            jb[i][2].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][1].setText("");
                            jb[i][0].setText("");
                        }
                        else if(str4.equals(str2))
                        {
                            int n=Integer.parseInt(str2);
                            jb[i][3].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][2].setText(jb[i][0].getText().toString());
                            jb[i][1].setText("");
                            jb[i][0].setText("");
                        }
                        else if(str2.equals(""))
                        {
                            if(str4.equals(str1))
                            {
                                int n=Integer.parseInt(str1);
                                jb[i][3].setText(Integer.toString(n*2));
                                score=score+(n*2);
                                jb[i][2].setText("");
                                jb[i][0].setText("");
                            }
                            else
                            {
                                jb[i][2].setText(jb[i][0].getText().toString());
                                jb[i][0].setText("");
                            }
                        }
                        else
                        {
                            jb[i][2].setText(jb[i][1].getText().toString());
                            jb[i][1].setText(jb[i][0].getText().toString());
                            jb[i][0].setText("");
                        }
                    }
                }
            }
        }
        if(d=='l')
        {
            for(int i=0;i<4;i++)
            {
                String str1=jb[i][0].getText().toString();
                String str2=jb[i][1].getText().toString();
                String str3=jb[i][2].getText().toString();
                String str4=jb[i][3].getText().toString();
                if(str1.equals(str2))
                {
                    if(str1.equals(""))
                    {
                        if(str4.equals(str3) && !str4.equals(""))
                        {
                            int n=Integer.parseInt(str4);
                            jb[i][0].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][2].setText("");
                            jb[i][3].setText("");
                        }
                        else if(!str4.equals(str3))
                        {
                            if(!str4.equals("") && !str3.equals(""))
                            {
                                jb[i][0].setText(jb[i][2].getText().toString());
                                jb[i][1].setText(jb[i][3].getText().toString());
                                jb[i][2].setText("");
                                jb[i][3].setText("");
                            }
                            else if(str4.equals("") && !str3.equals(""))
                            {
                                jb[i][0].setText(jb[i][2].getText().toString());
                                jb[i][2].setText("");
                            }
                            else if(!str4.equals("") && str3.equals(""))
                            {
                                jb[i][0].setText(jb[i][3].getText().toString());
                                jb[i][3].setText("");
                            }
                        }
                    }
                    else if(!str1.equals(""))
                    {
                        int n=Integer.parseInt(str1);
                        jb[i][0].setText(Integer.toString(n*2));
                        score=score+(n*2);
                        if((str3.equals(str4) && !str3.equals("")))
                        {
                            n=Integer.parseInt(str3);
                            jb[i][1].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][2].setText("");
                            jb[i][3].setText("");
                        }
                        else if(!str3.equals("") && !str4.equals(""))
                        {
                            jb[i][1].setText(jb[i][2].getText().toString());
                            jb[i][2].setText(jb[i][3].getText().toString());
                            jb[i][3].setText("");
                        }
                        else if(!str3.equals("") && str4.equals(""))
                        {
                            jb[i][1].setText(jb[i][2].getText().toString());
                            jb[i][2].setText("");
                        }
                        else if(str3.equals("") && !str4.equals(""))
                        {
                            jb[i][1].setText(jb[i][3].getText().toString());
                            jb[i][3].setText("");
                        }
                        else if(str3.equals("") && str4.equals(""))
                        {
                            jb[i][1].setText("");
                        }
                    }
                }
                else if(!str1.equals(str2))
                {
                    if(!str1.equals("") && !str2.equals(""))
                    {
                        if(str4.equals(str3) && !str4.equals(""))
                        {
                            int n=Integer.parseInt(str4);
                            jb[i][2].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][3].setText("");
                        }
                        else if(str3.equals("")&&str2.equals(str4))
                        {
                            int n=Integer.parseInt(str4);
                            jb[i][1].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][3].setText("");
                        }
                        else if(str3.equals(""))
                        {
                            jb[i][2].setText(jb[i][3].getText().toString());
                            jb[i][3].setText("");
                        }
                        else if(str3.equals(str2))
                        {
                            int n=Integer.parseInt(str3);
                            jb[i][1].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][2].setText(jb[i][3].getText().toString());
                            jb[i][3].setText("");
                        }
                    }
                    else if(str1.equals("") && !str2.equals(""))
                    {
                        if(str2.equals(str3))
                        {
                            int n=Integer.parseInt(str3);
                            jb[i][0].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][1].setText(jb[i][3].getText().toString());
                            jb[i][2].setText("");
                            jb[i][3].setText("");
                        }
                        else if(str3.equals(str4) && !str3.equals(""))
                        {
                            jb[i][0].setText(jb[i][1].getText().toString());
                            int n=Integer.parseInt(str3);
                            jb[i][1].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][2].setText("");
                            jb[i][3].setText("");
                        }
                        else if(str3.equals(""))
                        {
                            if(str2.equals(str4))
                            {
                                int n=Integer.parseInt(str4);
                                jb[i][0].setText(Integer.toString(n*2));
                                score=score+(n*2);
                                jb[i][1].setText("");
                                jb[i][3].setText("");
                            }
                            else
                            {
                                jb[i][0].setText(jb[i][1].getText().toString());
                                jb[i][1].setText(jb[i][3].getText().toString());
                                jb[i][3].setText("");
                            }
                        }
                        else
                        {
                            jb[i][0].setText(jb[i][1].getText().toString());
                            jb[i][1].setText(jb[i][2].getText().toString());
                            jb[i][2].setText(jb[i][3].getText().toString());
                            jb[i][3].setText("");
                        }
                    }
                    else if(!str1.equals("") && str2.equals(""))
                    {
                        if(str3.equals(str4) && !str3.equals(""))
                        {
                            int n=Integer.parseInt(str3);
                            jb[i][1].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][2].setText("");
                            jb[i][3].setText("");
                        }
                        else if(str1.equals(str3))
                        {
                            int n=Integer.parseInt(str3);
                            jb[i][0].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[i][1].setText(jb[i][3].getText().toString());
                            jb[i][2].setText("");
                            jb[i][3].setText("");
                        }
                        else if(str3.equals(""))
                        {
                            if(str1.equals(str4))
                            {
                                int n=Integer.parseInt(str4);
                                jb[i][0].setText(Integer.toString(n*2));
                                score=score+(n*2);
                                jb[i][1].setText("");
                                jb[i][3].setText("");
                            }
                            else
                            {
                                jb[i][1].setText(jb[i][3].getText().toString());
                                jb[i][3].setText("");
                            }
                        }
                        else
                        {
                            jb[i][1].setText(jb[i][2].getText().toString());
                            jb[i][2].setText(jb[i][3].getText().toString());
                            jb[i][3].setText("");
                        }
                    }
                }
            }
        }
        if(d=='d')
        {
            for(int i=0;i<4;i++)
            {
                String str1=jb[0][i].getText().toString();
                String str2=jb[1][i].getText().toString();
                String str3=jb[2][i].getText().toString();
                String str4=jb[3][i].getText().toString();
                if(str4.equals(str3))
                {
                    if(str4.equals(""))
                    {
                        if(str1.equals(str2) && !str1.equals(""))
                        {
                            int n=Integer.parseInt(str1);
                            jb[3][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[1][i].setText("");
                            jb[0][i].setText("");
                        }
                        else if(!str1.equals(str2))
                        {
                            if(!str1.equals("") && !str2.equals(""))
                            {
                                jb[3][i].setText(jb[1][i].getText().toString());
                                jb[2][i].setText(jb[0][i].getText().toString());
                                jb[1][i].setText("");
                                jb[0][i].setText("");
                            }
                            else if(str1.equals("") && !str2.equals(""))
                            {
                                jb[3][i].setText(jb[1][i].getText().toString());
                                jb[1][i].setText("");
                            }
                            else if(!str1.equals("") && str2.equals(""))
                            {
                                jb[3][i].setText(jb[0][i].getText().toString());
                                jb[0][i].setText("");
                            }
                        }
                    }
                    else if(!str4.equals(""))
                    {
                        int n=Integer.parseInt(str4);
                        jb[3][i].setText(Integer.toString(n*2));
                        score=score+(n*2);
                        if((str2.equals(str1) && !str2.equals("")))
                        {
                            n=Integer.parseInt(str2);
                            jb[2][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[1][i].setText("");
                            jb[0][i].setText("");
                        }
                        else if(!str2.equals("") && !str1.equals(""))
                        {
                            jb[2][i].setText(jb[1][i].getText().toString());
                            jb[1][i].setText(jb[0][i].getText().toString());
                            jb[0][i].setText("");
                        }
                        else if(!str2.equals("") && str1.equals(""))
                        {
                            jb[2][i].setText(jb[1][i].getText().toString());
                            jb[1][i].setText("");
                        }
                        else if(str2.equals("") && !str1.equals(""))
                        {
                            jb[2][i].setText(jb[0][i].getText().toString());
                            jb[0][i].setText("");
                        }
                        else if(str2.equals("") && str1.equals(""))
                        {
                            jb[2][i].setText("");
                        }
                    }
                }
                else if(!str4.equals(str3))
                {
                    if(!str4.equals("") && !str3.equals(""))
                    {
                        if(str1.equals(str2) && !str1.equals(""))
                        {
                            int n=Integer.parseInt(str1);
                            jb[1][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[0][i].setText("");
                        }
                        else if(str2.equals("")&&str3.equals(str1))
                        {
                            int n=Integer.parseInt(str1);
                            jb[2][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[0][i].setText("");
                        }
                        else if(str2.equals(""))
                        {
                            jb[1][i].setText(jb[0][i].getText().toString());
                            jb[0][i].setText("");
                        }
                        else if(str2.equals(str3))
                        {
                            int n=Integer.parseInt(str2);
                            jb[2][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[1][i].setText(jb[0][i].getText().toString());
                            jb[0][i].setText("");
                        }
                    }
                    else if(str4.equals("") && !str3.equals(""))
                    {
                        if(str3.equals(str2))
                        {
                            int n=Integer.parseInt(str2);
                            jb[3][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[2][i].setText(jb[0][i].getText().toString());
                            jb[1][i].setText("");
                            jb[0][i].setText("");
                        }
                        else if(str2.equals(str1) && !str2.equals(""))
                        {
                            jb[3][i].setText(jb[2][i].getText().toString());
                            int n=Integer.parseInt(str2);
                            jb[2][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[1][i].setText("");
                            jb[0][i].setText("");
                        }
                        else if(str2.equals(""))
                        {
                            if(str3.equals(str1))
                            {
                                int n=Integer.parseInt(str1);
                                jb[3][i].setText(Integer.toString(n*2));
                                score=score+(n*2);
                                jb[2][i].setText("");
                                jb[0][i].setText("");
                            }
                            else
                            {
                                jb[3][i].setText(jb[2][i].getText().toString());
                                jb[2][i].setText(jb[0][i].getText().toString());
                                jb[0][i].setText("");
                            }
                        }
                        else
                        {
                            jb[3][i].setText(jb[2][i].getText().toString());
                            jb[2][i].setText(jb[1][i].getText().toString());
                            jb[1][i].setText(jb[0][i].getText().toString());
                            jb[0][i].setText("");
                        }
                    }
                    else if(!str4.equals("") && str3.equals(""))
                    {
                        if(str2.equals(str1) && !str2.equals(""))
                        {
                            int n=Integer.parseInt(str2);
                            jb[2][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[1][i].setText("");
                            jb[0][i].setText("");
                        }
                        else if(str4.equals(str2))
                        {
                            int n=Integer.parseInt(str2);
                            jb[3][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[2][i].setText(jb[0][i].getText().toString());
                            jb[1][i].setText("");
                            jb[0][i].setText("");
                        }
                        else if(str2.equals(""))
                        {
                            if(str4.equals(str1))
                            {
                                int n=Integer.parseInt(str1);
                                jb[3][i].setText(Integer.toString(n*2));
                                score=score+(n*2);
                                jb[2][i].setText("");
                                jb[0][i].setText("");
                            }
                            else
                            {
                                jb[2][i].setText(jb[0][i].getText().toString());
                                jb[0][i].setText("");
                            }
                        }
                        else
                        {
                            jb[2][i].setText(jb[1][i].getText().toString());
                            jb[1][i].setText(jb[0][i].getText().toString());
                            jb[0][i].setText("");
                        }
                    }
                }
            }
        }
        if(d=='u')
        {
            for(int i=0;i<4;i++)
            {
                String str1=jb[0][i].getText().toString();
                String str2=jb[1][i].getText().toString();
                String str3=jb[2][i].getText().toString();
                String str4=jb[3][i].getText().toString();
                if(str1.equals(str2))
                {
                    if(str1.equals(""))
                    {
                        if(str4.equals(str3) && !str4.equals(""))
                        {
                            int n=Integer.parseInt(str4);
                            jb[0][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[2][i].setText("");
                            jb[3][i].setText("");
                        }
                        else if(!str4.equals(str3))
                        {
                            if(!str4.equals("") && !str3.equals(""))
                            {
                                jb[0][i].setText(jb[2][i].getText().toString());
                                jb[1][i].setText(jb[3][i].getText().toString());
                                jb[2][i].setText("");
                                jb[3][i].setText("");
                            }
                            else if(str4.equals("") && !str3.equals(""))
                            {
                                jb[0][i].setText(jb[2][i].getText().toString());
                                jb[2][i].setText("");
                            }
                            else if(!str4.equals("") && str3.equals(""))
                            {
                                jb[0][i].setText(jb[3][i].getText().toString());
                                jb[3][i].setText("");
                            }
                        }
                    }
                    else if(!str1.equals(""))
                    {
                        int n=Integer.parseInt(str1);
                        jb[0][i].setText(Integer.toString(n*2));
                        score=score+(n*2);
                        if((str3.equals(str4) && !str3.equals("")))
                        {
                            n=Integer.parseInt(str3);
                            jb[1][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[2][i].setText("");
                            jb[3][i].setText("");
                        }
                        else if(!str3.equals("") && !str4.equals(""))
                        {
                            jb[1][i].setText(jb[2][i].getText().toString());
                            jb[2][i].setText(jb[3][i].getText().toString());
                            jb[3][i].setText("");
                        }
                        else if(!str3.equals("") && str4.equals(""))
                        {
                            jb[1][i].setText(jb[2][i].getText().toString());
                            jb[2][i].setText("");
                        }
                        else if(str3.equals("") && !str4.equals(""))
                        {
                            jb[1][i].setText(jb[3][i].getText().toString());
                            jb[3][i].setText("");
                        }
                        else if(str3.equals("") && str4.equals(""))
                        {
                            jb[1][i].setText("");
                        }
                    }
                }
                else if(!str1.equals(str2))
                {
                    if(!str1.equals("") && !str2.equals(""))
                    {
                        if(str4.equals(str3) && !str4.equals(""))
                        {
                            int n=Integer.parseInt(str4);
                            jb[2][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[3][i].setText("");
                        }
                        else if(str3.equals("")&&str2.equals(str4))
                        {
                            int n=Integer.parseInt(str4);
                            jb[1][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[3][i].setText("");
                        }
                        else if(str3.equals(""))
                        {
                            jb[2][i].setText(jb[3][i].getText().toString());
                            jb[3][i].setText("");
                        }
                        else if(str3.equals(str2))
                        {
                            int n=Integer.parseInt(str3);
                            jb[1][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[2][i].setText(jb[3][i].getText().toString());
                            jb[3][i].setText("");
                        }
                    }
                    else if(str1.equals("") && !str2.equals(""))
                    {
                        if(str2.equals(str3))
                        {
                            int n=Integer.parseInt(str3);
                            jb[0][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[1][i].setText(jb[3][i].getText().toString());
                            jb[2][i].setText("");
                            jb[3][i].setText("");
                        }
                        else if(str3.equals(str4) && !str3.equals(""))
                        {
                            jb[0][i].setText(jb[1][i].getText().toString());
                            int n=Integer.parseInt(str3);
                            jb[1][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[2][i].setText("");
                            jb[3][i].setText("");
                        }
                        else if(str3.equals(""))
                        {
                            if(str2.equals(str4))
                            {
                                int n=Integer.parseInt(str4);
                                jb[0][i].setText(Integer.toString(n*2));
                                score=score+(n*2);
                                jb[1][i].setText("");
                                jb[3][i].setText("");
                            }
                            else
                            {
                                jb[0][i].setText(jb[1][i].getText().toString());
                                jb[1][i].setText(jb[3][i].getText().toString());
                                jb[3][i].setText("");
                            }
                        }
                        else
                        {
                            jb[0][i].setText(jb[1][i].getText().toString());
                            jb[1][i].setText(jb[2][i].getText().toString());
                            jb[2][i].setText(jb[3][i].getText().toString());
                            jb[3][i].setText("");
                        }
                    }
                    else if(!str1.equals("") && str2.equals(""))
                    {
                        if(str3.equals(str4) && !str3.equals(""))
                        {
                            int n=Integer.parseInt(str3);
                            jb[1][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[2][i].setText("");
                            jb[3][i].setText("");
                        }
                        else if(str1.equals(str3))
                        {
                            int n=Integer.parseInt(str3);
                            jb[0][i].setText(Integer.toString(n*2));
                            score=score+(n*2);
                            jb[1][i].setText(jb[3][i].getText().toString());
                            jb[2][i].setText("");
                            jb[3][i].setText("");
                        }
                        else if(str3.equals(""))
                        {
                            if(str1.equals(str4))
                            {
                                int n=Integer.parseInt(str4);
                                jb[0][i].setText(Integer.toString(n*2));
                                score=score+(n*2);
                                jb[1][i].setText("");
                                jb[3][i].setText("");
                            }
                            else
                            {
                                jb[1][i].setText(jb[3][i].getText().toString());
                                jb[3][i].setText("");
                            }
                        }
                        else
                        {
                            jb[1][i].setText(jb[2][i].getText().toString());
                            jb[2][i].setText(jb[3][i].getText().toString());
                            jb[3][i].setText("");
                        }
                    }
                }
            }
        }
    }
    public void endGame()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                jb[i][j].setEnabled(false);
            }
        }
    }
}
