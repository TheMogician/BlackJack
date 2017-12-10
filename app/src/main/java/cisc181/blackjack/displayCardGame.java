package cisc181.blackjack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class displayCardGame extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_card_game);


    }
    public void quitFunc (View view) {
        Intent startNewActivity = new Intent(this, BlackJack.class);
            startActivity(startNewActivity);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void drawCard(View view) {
        int total = 0;
        Random rand = new Random();
        List<String> suites = Arrays.asList("spades", "hearts", "clubs", "diamonds");
        List<String> royalty = Arrays.asList("jack", "queen", "king", "ace");
        String numDrawn1 = String.valueOf(rand.nextInt(14)+2);
        String numDrawn2 = String.valueOf(rand.nextInt(14)+2);
        String numDrawn3 = String.valueOf(rand.nextInt(14)+2);
        total = total + (Integer.parseInt(numDrawn1) + Integer.parseInt(numDrawn2) + Integer.parseInt(numDrawn3));
        if (Integer.parseInt(numDrawn1)>10) {
            total = (total+11) - (Integer.parseInt(numDrawn1));
            numDrawn1 = royalty.get(rand.nextInt(4));
        }
        if (Integer.parseInt(numDrawn2)>10) {
            total = (total+11) - (Integer.parseInt(numDrawn2));
            numDrawn2 = royalty.get(rand.nextInt(4));
        }
        if (Integer.parseInt(numDrawn3)>10) {
            total = (total+11) - (Integer.parseInt(numDrawn3));
            numDrawn3 = royalty.get(rand.nextInt(4));
        }
        if ((numDrawn1.equals("ace")|| numDrawn2.equals("ace")||numDrawn3.equals("ace"))&& total > 21){
            total = total - 10;
        }
        TextView drawnCard1 = findViewById(R.id.textView9);
        TextView drawnCard2 = findViewById(R.id.textView10);
        TextView drawnCard3 = findViewById(R.id.textView6);
        TextView totalView = findViewById(R.id.textView5);
        TextView endBanner = findViewById(R.id.endBanner);
        drawnCard1.setText("a " + numDrawn1 + " of " + suites.get(rand.nextInt(4)));
        drawnCard2.setText("a " + numDrawn2 + " of " + suites.get(rand.nextInt(4)));
        drawnCard3.setText("a " + numDrawn3 + " of " + suites.get(rand.nextInt(4)));

        totalView.setText(String.valueOf(total));
        if (total > 21){
            endBanner.setText("You Lost!!!");
        }
        else {
            endBanner.setText("You Won!!!");
        }

        try {
            PrintWriter writer = new PrintWriter("scores.txt", "UTF-8");
            writer.println(total);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



    }

}