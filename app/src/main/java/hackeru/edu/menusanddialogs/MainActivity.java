package hackeru.edu.menusanddialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton fab;
    private EditText etName;
    private Button btnColor;
    private TextView tvOutPut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etName = (EditText) findViewById(R.id.etName);

        btnColor = (Button) findViewById(R.id.btnColor);

        tvOutPut = (TextView) findViewById(R.id.tvOutPut);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No internet connection")
                .setMessage("This app needs internet connection")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                }).show();
    }


    public void pickAColor(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = new String[]{"red", "green", "blue"};
        builder.setTitle("Change button color to:")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Toast.makeText(MainActivity.this, "You choose RED", Toast.LENGTH_SHORT).show();
                                btnColor.setBackgroundResource(android.R.color.holo_red_light);
                                break;
                            case 1:
                                Toast.makeText(MainActivity.this, "You choose GREEN", Toast.LENGTH_SHORT).show();
                                btnColor.setBackgroundResource(android.R.color.holo_green_light);
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this, "You choose BLUE", Toast.LENGTH_SHORT).show();
                                btnColor.setBackgroundResource(android.R.color.holo_blue_light);
                                break;
                        }
                    }
                }).show();
    }

    public void checkBox(final View view){
        final ArrayList<Integer> mSelectedItems = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your meal composition")
                .setMultiChoiceItems(R.array.meal_composition, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                            mSelectedItems.add(which);
                        }
                        else if (mSelectedItems.contains(which)){
                            mSelectedItems.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int arrayLength = mSelectedItems.size();
                        for (int i = 0; i < arrayLength; i++) {
                            String sSelectedItems = (String) mSelectedItems.get(i).toString();
                            tvOutPut.append(sSelectedItems);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void radioButton(final View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = new String[]{"Espresso", "Mocha", "Americano", "Macchiato"};
        builder.setTitle("Hot Drinks")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvOutPut.setText("You choose: " + items[which]);
                        dialog.dismiss();
                    }
                }).show();
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
        switch (id) {
            case R.id.search:
                Snackbar.make(fab, etName.getText(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.refresh:
                etName.setText("");
                tvOutPut.setText("");
                btnColor.setBackgroundResource(android.R.color.darker_gray);
                return true;
            case R.id.settings:
                Toast.makeText(this, "You press on settings", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
