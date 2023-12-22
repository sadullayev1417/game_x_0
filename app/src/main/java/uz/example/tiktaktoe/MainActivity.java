package uz.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean playerOneActive ;
    private TextView playerOne,playerTwo,playerStatus;
    private Button[] buttons = new Button[9];
    private Button reset,playagain,change_bg,score_btn;
    private int playerOneScoreCount=0,playerTwoScoreCount=0;
    int rounds ,change_bg_count;
    private RelativeLayout main_activity;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    String result_total,total_score="";
    int[][] winningPositions = {
            {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
    };
//
// 0 1 2
// 3 4 5
// 6 7 8
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playerOne = findViewById(R.id.text_score1);
        playerTwo = findViewById(R.id.text_score2);
        playerStatus = findViewById(R.id.textStatus);
        reset = findViewById(R.id.btn_reset);
        playagain = findViewById(R.id.btn_play_again);

        change_bg = findViewById(R.id.change_bg);
        main_activity = findViewById(R.id.mainActivity);
        score_btn = findViewById(R.id.score);

        buttons[0] = findViewById(R.id.btn0);
        buttons[1] = findViewById(R.id.btn1);
        buttons[2] = findViewById(R.id.btn2);
        buttons[3] = findViewById(R.id.btn3);
        buttons[4] = findViewById(R.id.btn4);
        buttons[5] = findViewById(R.id.btn5);
        buttons[6] = findViewById(R.id.btn6);
        buttons[7] = findViewById(R.id.btn7);
        buttons[8] = findViewById(R.id.btn8);

        for(int i=0 ;i<buttons.length;i++){
            buttons[i].setOnClickListener(this);
        }
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        playerOneActive = true;
        rounds = 0;
        change_bg_count = 1 ;

        score_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScore();
            }
        });

        change_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            switch(change_bg_count){
                case 0: main_activity.setBackgroundResource(R.drawable.image1);
                    break;
                case 1: main_activity.setBackgroundResource(R.drawable.image2);
                    break;
                case 2: main_activity.setBackgroundResource(R.drawable.image3);
                    break;

            }
                change_bg_count ++;
                change_bg_count = change_bg_count % 3;
//                change_bg_count = change_bg_count % 10;

            }
        });

    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return ;
        }
        else if(checkWinner()){
            return ;
        }
        String buttonId = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length()-1,buttonId.length()));

        if(playerOneActive){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#ffc34a"));
            gameState[gameStatePointer] = 0;
        }
        else {

            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#ffc34a"));
            gameState[gameStatePointer] = 1;
        }
        rounds ++;
        if(checkWinner()){
            if(playerOneActive){
                playerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText(R.string.oneWinner);
            }
            else{
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText(R.string.twoWinner);
            }
        }
        else if(rounds==9){
            playerStatus.setText(R.string.noWinner);
        }
        else{
            playerOneActive = !playerOneActive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result_total = Integer.toString(playerOneScoreCount)+" : "+Integer.toString(playerTwoScoreCount);
                total_score = total_score+"\n"+result_total;
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
            }
        });
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();

            }
        });




    }

    private boolean checkWinner() {
        boolean winnerResults = false;
        for(int[] winningPositions:winningPositions){
            if(gameState[winningPositions[0]] == gameState[winningPositions[1]]  &&
                    gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]]!=2){
                winnerResults = true;
            }

        }
        return winnerResults;
    }

    private void playAgain() {
        rounds = 0;
        playerOneActive = true;
        for(int i=0 ;i< buttons.length;i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
        playerStatus.setText(R.string.status);
    }

    private void updatePlayerScore() {
        playerOne.setText(Integer.toString(playerOneScoreCount));
        playerTwo.setText(Integer.toString(playerTwoScoreCount));

    }

    private  void startScore(){

        Intent i = new Intent(MainActivity.this, MainActivity2.class);

        i.putExtra("result_total",total_score);

        startActivity(i);
    }

}



