package andrewdev.de.count;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private Button button1;
    private Button reset;
    private int howManyTimesBeenClicked = 0;
    private static final String NUMBER_OF_TIMES_RUN_KEY = "NUMBER_OF_TIMES_RUN_KEY";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button1 = (Button) findViewById(R.id.button);
        reset = (Button) findViewById(R.id.button2);
        textView1 = (TextView) findViewById(R.id.textView);

        sharedPreferences = getSharedPreferences("Klick",Context.MODE_PRIVATE);
        howManyTimesBeenClicked = sharedPreferences.getInt("klick_1", 0);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putInt(NUMBER_OF_TIMES_RUN_KEY, howManyTimesBeenClicked);
        //editor.commit();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howManyTimesBeenClicked++;
                saveCount();
                textView1.setText(String.valueOf(howManyTimesBeenClicked));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howManyTimesBeenClicked = 0;
                saveCount();
                textView1.setText(String.valueOf(howManyTimesBeenClicked));
            }
        });
        if(howManyTimesBeenClicked == 0){
            Toast.makeText(this, "Welcome Andrew :D", Toast.LENGTH_LONG).show();
        }

        textView1.setText(String.valueOf(howManyTimesBeenClicked));
    }

    private void saveCount(){

        sharedPreferences = getSharedPreferences("Klick",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putInt("klick_1", howManyTimesBeenClicked);
        editor.commit();
    }
    @Override
    protected void onResume() {
        textView1.setText(String.valueOf(howManyTimesBeenClicked));
        super.onResume();
    }

    @Override
    protected void onPause() {
        saveCount();
        super.onPause();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
