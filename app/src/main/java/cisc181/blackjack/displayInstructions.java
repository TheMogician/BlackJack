package cisc181.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class displayInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_instructions);
    }

    public void cardGame(View view){

        Intent startNewActivity = new Intent(this, displayCardGame.class);
        startActivity(startNewActivity);

    }
    public void mainMenu(View view){
        finish();
    }
}