package org.danielcastelao.bdiaznunez.simon_dice;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int checkPosition =0;
    int roundCounter = 0 ;
    int [] colors = new int[50];
    int secuencePosition = 0;
    int score = 0;
    boolean trueOrNot = false;

    //Declaration of buttons:
    public Button btnBlue;
    public Button btnGreen;
    public Button btnYellow;
    public Button btnRed;
    public Button btnStart; //botonCentral

    public TextView welcomeText;

    //Scoreboards:
    TextView score_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //The value of the buttons and scoreboard is asigned by its id in the content_main.xml file

        btnBlue = (Button) findViewById(R.id.btnBlue);
        btnGreen = (Button) findViewById(R.id.btnGreen);
        btnYellow = (Button) findViewById(R.id.btnYellow);
        btnRed = (Button) findViewById(R.id.btnRed);
        btnStart = (Button) findViewById(R.id.btnStart);

        score_value = (TextView) findViewById(R.id.txtPuntos);

        score_value.setText("" + score);

        welcomeText = (TextView) findViewById(R.id.txtBienvenida);

        btnGreen.setBackgroundColor(Color.GREEN);
        btnGreen.setAlpha(0.5f);
        btnYellow.setBackgroundColor(Color.YELLOW);
        btnYellow.setAlpha(0.5f);
        btnBlue.setBackgroundColor(Color.BLUE);
        btnBlue.setAlpha(0.5f);
        btnRed.setBackgroundColor(Color.RED);
        btnRed.setAlpha(0.5f);

        //Manages start button activation
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                newSecuence();
            }

        });

        //Manages new game button activation
        findViewById(R.id.btnNew).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                newGame();
            }
        });

        //Manages blue button activation
        btnBlue.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                colorCheck(Color.BLUE);
                colorHighlight(btnBlue);
            }

        });

        //Manages yellow button activation
        btnYellow.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                colorCheck(Color.YELLOW);
                colorHighlight(btnYellow);
            }

        });

        //Manages red button activation
        btnRed.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                colorCheck(Color.RED);
                colorHighlight(btnRed);
            }

        });

        //Manages green button activation
        btnGreen.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                colorCheck(Color.GREEN);
                colorHighlight(btnGreen);

            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                newSecuence();
            }

        });

    }
    //This metod starts a new game:
        public void newGame() {

            findViewById(R.id.btnStart).setBackgroundColor(Color.GRAY);

            roundCounter = 0;

            score = 0;

            score_value.setText("SCORE: " + score);

            Button startButton = (Button) findViewById(R.id.btnStart);

            startButton.setEnabled(true);

            TextView t = (TextView) findViewById(R.id.round);

            t.setText("ROUND: " + roundCounter);

        }

    //Changes the background color of the central button
    public void updateBackground(int color){

             View background=findViewById(R.id.btnStart);

             background.setBackgroundColor(color);

    }

    /*Uses the start button to light it and make a color secuence that the player should repeat correctly.
    To do that is to important the alpha property, to light the start button

    */
    public void setSecuence(){

        final View background=findViewById(R.id.btnStart);

        secuencePosition =0;

        for(int i = 0; i< roundCounter; i++) {

            if (colors[i] != 0) {

                background.postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        updateBackground(colors[secuencePosition]);

                        background.setAlpha(0.2f);

                    }

                }, (i+1) * 500);


                background.postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        background.setAlpha(0.5f);

                    }

                },(500*(i+1))+100);

                background.postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        background.setAlpha(0.9f);

                    }

                },(500*(i+1))+150);


                background.postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        background.setAlpha(1f);

                    }

                },(500*(i+1))+250);

                background.postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        background.setAlpha(0.9f);

                    }

                },(500*(i+1))+350);

                background.postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        background.setAlpha(0.5f);

                    }

                },(500*(i+1))+400);


                background.postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        background.setAlpha(0.2f);

                        secuencePosition++;

                    }

                },(500*(i+1))+450);

            }
        }

        background.postDelayed(new Runnable() {

            @Override

            public void run() {

                updateBackground(Color.GRAY);

                background.setAlpha(1f);

                btnRed.setEnabled(true);

                btnGreen.setEnabled(true);

                btnYellow.setEnabled(true);

                btnBlue.setEnabled(true);

            }

        },(roundCounter +1)*500);

        Button startButton = (Button)findViewById(R.id.btnStart);
        startButton.setEnabled(false);

    }

    //Generates a new color random secuence:

    public void newSecuence(){

        Random rd=new Random();

        int[] possibleColors ={Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};

        colors[roundCounter] = possibleColors[rd.nextInt(4)];

        TextView t= (TextView) findViewById(R.id.round);

        t.setText("ROUND: "+(roundCounter +1));

        roundCounter++;

        setSecuence();

        trueOrNot =true;

        checkPosition =0;
    }

    public void colorHighlight(Button boton) {

        final Button b = boton;

        b.postDelayed(new Runnable() {

            @Override

            public void run() {
                b.setAlpha(0.5f);
            }

        }, 0);

        b.postDelayed(new Runnable() {

            @Override

            public void run() {
                b.setAlpha(0.75f);
            }

        }, 50);

        b.postDelayed(new Runnable() {

            @Override

            public void run() {
                b.setAlpha(1f);
            }

        }, 100);

        b.postDelayed(new Runnable() {

            @Override

            public void run() {
                b.setAlpha(0.75f);
            }

        }, 250);

        b.postDelayed(new Runnable() {

            @Override

            public void run() {
                b.setAlpha(0.5f);
            }

        }, 300);
    }

    //Checks if the color of the clicked button is the correct color and win/lose managements.
    public void colorCheck(int color){

        Button startButton=(Button)findViewById(R.id.btnStart);

        final Button loseMessage=(Button)findViewById(R.id.btnStart);

        if(color == colors[checkPosition]){

            checkPosition++;
            score += 10;
            score_value.setText("SCORE: " + score);

        }else{
            trueOrNot =false;
        }

        if(checkPosition==roundCounter&&trueOrNot){

            score += roundCounter*2;
            score_value.setText("SCORE: " + score);
            startButton.setEnabled(true);
            btnYellow.setEnabled(false);
            btnBlue.setEnabled(false);
            btnGreen.setEnabled(false);
            btnRed.setEnabled(false);

        }

        if(!trueOrNot && roundCounter >0){

            loseMessage.postDelayed(new Runnable() {

                @Override

                public void run() {

                    btnYellow.setVisibility(View.INVISIBLE);

                    btnGreen.setVisibility(View.INVISIBLE);

                    btnBlue.setVisibility(View.INVISIBLE);

                    btnRed.setVisibility(View.INVISIBLE);

                    TextView t= (TextView) findViewById(R.id.round);

                    t.setVisibility(View.INVISIBLE);

                    loseMessage.setBackgroundColor(Color.BLACK);

                    loseMessage.setText("YOU LOSE");

                    loseMessage.setScaleX(3f);

                    loseMessage.setScaleY(3f);

                }

            },50);

            loseMessage.postDelayed(new Runnable() {

                @Override

                public void run() {

                    loseMessage.setText("");

                    loseMessage.setScaleX(1f);

                    loseMessage.setScaleY(1f);

                    loseMessage.setBackgroundColor(Color.BLACK);

                    TextView t= (TextView) findViewById(R.id.round);

                    t.setText("ROUND: 0");

                    btnYellow.setVisibility(View.VISIBLE);

                    btnGreen.setVisibility(View.VISIBLE);

                    btnBlue.setVisibility(View.VISIBLE);

                    btnRed.setVisibility(View.VISIBLE);

                    t.setVisibility(View.VISIBLE);

                }

            },1500);

            score =0;

            score_value.setText("SCORE: "+ score);

            newGame();

            startButton.setEnabled(true);
        }
    }
}
