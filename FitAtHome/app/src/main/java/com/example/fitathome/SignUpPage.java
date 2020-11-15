package com.example.fitathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUpPage extends AppCompatActivity {
    private Button btnMon, btnTues, btnWeds, btnThurs, btnFri, btnSat, btnSun, btnSubmit;
    private Spinner spType;
    private EditText txtName, txtAge, txtGender, txtFrequency;
    private RadioGroup rdGpLevel;
    private String workout, name, gender, level;
    private Integer age, frequency, days = 7;
    private ArrayList<Boolean> day;
    private User user;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        spType = (Spinner) findViewById(R.id.spType);

        btnMon = (Button) findViewById(R.id.btnMon);
        btnTues = (Button) findViewById(R.id.btnTues);
        btnWeds = (Button) findViewById(R.id.btnWed);
        btnThurs = (Button) findViewById(R.id.btnThurs);
        btnFri = (Button) findViewById(R.id.btnFri);
        btnSat = (Button) findViewById(R.id.btnSat);
        btnSun = (Button) findViewById(R.id.btnSun);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        rdGpLevel = (RadioGroup) findViewById(R.id.rdGpLevel);

        txtName = (EditText) findViewById(R.id.txtName);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtGender = (EditText) findViewById(R.id.txtGender);
        txtFrequency = (EditText) findViewById(R.id.txtFrequency);

        day = new ArrayList<Boolean>();
        for (int i = 0; i < days; i++) {
            day.add(false);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.workouts, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spType.setAdapter(adapter);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                workout = parent.getItemAtPosition(position).toString();
                InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rdGpLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdBtnBeginner:
                        level = "Beginner";
                        break;
                    case R.id.rdBtnIntermediate:
                        level = "Intermediate";
                        break;
                    case R.id.rdBtnProfessional:
                        level = "Professional";
                        break;
                    default:
                        level = "";
                        break;
                }
            }
        });


        btnMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day.get(0) == false) {
                    btnMon.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDescription)));
                    day.set(0, true);
                } else {
                    btnMon.setBackgroundTintList(ColorStateList.valueOf(0));
                    day.set(0, false);
                }
            }

        });
        btnTues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day.get(1) == false) {
                    btnTues.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDescription)));
                    day.set(1, true);
                } else {
                    btnTues.setBackgroundTintList(ColorStateList.valueOf(0));
                    day.set(1, false);
                }
            }
        });
        btnWeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day.get(2) == false) {
                    btnWeds.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDescription)));
                    day.set(2, true);
                } else {
                    btnWeds.setBackgroundTintList(ColorStateList.valueOf(0));
                    day.set(2, false);
                }
            }
        });
        btnThurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day.get(3) == false) {
                    btnThurs.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDescription)));
                    day.set(3, true);
                } else {
                    btnThurs.setBackgroundTintList(ColorStateList.valueOf(0));
                    day.set(3, false);
                }
            }
        });
        btnFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day.get(4) == false) {
                    btnFri.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDescription)));
                    day.set(4, true);
                } else {
                    btnFri.setBackgroundTintList(ColorStateList.valueOf(0));
                    day.set(4, false);
                }
            }
        });
        btnSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day.get(5) == false) {
                    btnSat.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDescription)));
                    day.set(5, true);
                } else {
                    btnSat.setBackgroundTintList(ColorStateList.valueOf(0));
                    day.set(5, false);
                }
            }
        });
        btnSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day.get(6) == false) {
                    btnSun.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDescription)));
                    day.set(6, true);
                } else {
                    btnSun.setBackgroundTintList(ColorStateList.valueOf(0));
                    day.set(6, false);
                }
            }
        });

        database = FirebaseDatabase.getInstance().getReference();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtName.getText().toString();
                age = Integer.parseInt(txtAge.getText().toString());
                gender = txtGender.getText().toString();
                frequency = Integer.parseInt(txtFrequency.getText().toString());
                if (!name.equals("") || age != null || !gender.equals("") || frequency != null || !level.equals("") || !workout.equals("")) {
                    user = new User(name, age, gender, level, workout, frequency, day);
                    database.child("User").setValue(user);
                    Intent i = new Intent(SignUpPage.this, WorkoutPage.class);
                    i.putExtra("Workout", workout);
                    i.putExtra("Level", level);
                    i.putExtra("Frequency", frequency);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(SignUpPage.this, "Please Fill Out All Items", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}