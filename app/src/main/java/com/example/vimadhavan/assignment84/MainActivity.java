package com.example.vimadhavan.assignment84;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private CustomListAdapter adapter;
    private List<Details> details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        details = new ArrayList<Details>();

        addData();

        list = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, details);
        list.setAdapter(adapter);

    }

    private void addData() {

        Details d1 = new Details("Vignesh", "01928374", "21/01/2017");
        Details d2 = new Details("Nishanth", "1234689", "10/05/2014");
        details.add(d1);
        details.add(d2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.add){
            LayoutInflater inflater = LayoutInflater.from(this);

            View dialogView = inflater.inflate(R.layout.dialog, null);

            final AlertDialog alert =  new AlertDialog.Builder(this).create();

            final EditText name = (EditText) dialogView.findViewById(R.id.name);
            final EditText ph = (EditText) dialogView.findViewById(R.id.phone);
            final EditText dob = (EditText) dialogView.findViewById(R.id.dob);

            Button save = (Button) dialogView.findViewById(R.id.save);
            Button cancel = (Button) dialogView.findViewById(R.id.cancel);

            save.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String strName = name.getText().toString();
                    String strPh = ph.getText().toString();
                    String strDOB = dob.getText().toString();

                    if(strName.isEmpty() || strPh.isEmpty() || strDOB.isEmpty()){
                        name.setHint(getString(R.string.hintTxt));
                        ph.setHint(getString(R.string.hintTxt));
                        dob.setHint(getString(R.string.hintTxt));
                    }else{
                        Details d = new Details(strName, strPh, strDOB);
                        details.add(d);
                        adapter.notifyDataSetChanged();
                        alert.dismiss();
                    }


                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    alert.cancel();
                }
            });

            alert.setTitle("Enter the Details");

            alert.setView(dialogView);

            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
