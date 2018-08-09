package com.siti.asyst.biodatasiti;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siti.asyst.biodatasiti.Utility.Constant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {


    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    EditText namaET, tempatLahirET, alamatET, emailET;
    TextView tglLahirTV, genderTV, hobiTV;
    ImageView dateIV;

    RadioGroup genderRG;

    Spinner pendidikanSpinner;
    CheckBox membacaCB, menulisCB, lainCB;

    EditText hobiET;

    String nama, tempatLahir, alamat, email;
    String hobi;
    String selectedGender = "Perempuan";

    ArrayList<String> listHobi = new ArrayList<String>();
    ArrayList<String> listPendidikan = new ArrayList<>();
    String selectedPendidikan;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaET = findViewById(R.id.nama_edittext);
        tempatLahirET = findViewById(R.id.tempatlahir_edittext);
        alamatET = findViewById(R.id.alamat_edittext);

        emailET = findViewById(R.id.email_edittext);


        tglLahirTV = findViewById(R.id.date_textview);
        dateIV = findViewById(R.id.date_imageview);
        genderTV = findViewById(R.id.gender_textview);
        hobiTV = findViewById(R.id.hobi_textview);

        genderRG = findViewById(R.id.gender_radiogroup);
        ((RadioButton) findViewById(R.id.radio_male)).setChecked(true);

        pendidikanSpinner = findViewById(R.id.spinner_pendidikan);
        pendidikanSpinner.setOnItemSelectedListener(this);

        membacaCB = findViewById(R.id.checkbox_membaca);
        menulisCB = findViewById(R.id.checkbox_menulis);
        lainCB = findViewById(R.id.checkbox_lain);

        hobiET = findViewById(R.id.hobi_edittext);

        membacaCB.setOnCheckedChangeListener(this);
        menulisCB.setOnCheckedChangeListener(this);
        lainCB.setOnCheckedChangeListener(this);

        submitButton = findViewById(R.id.sumbit_button);
        submitButton.setOnClickListener(this);
        dateIV.setOnClickListener(this);

        listPendidikan.add("TK");
        listPendidikan.add("SD");
        listPendidikan.add("SMP");
        listPendidikan.add("SMA/SMK");
        listPendidikan.add("D1");
        listPendidikan.add("D2");
        listPendidikan.add("D3");
        listPendidikan.add("S1");
        listPendidikan.add("S2");
        listPendidikan.add("S3");
        listPendidikan.add("Tidak Sekolah");

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPendidikan);
        pendidikanSpinner.setAdapter(cityAdapter);

        Calendar now = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(
                MainActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.date_imageview:
                //Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.sumbit_button:
                //Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();

                nama = namaET.getText().toString();
                tempatLahir = tempatLahirET.getText().toString();
                String date = tglLahirTV.getText().toString();
                alamat = alamatET.getText().toString();
                email = emailET.getText().toString();

                String email = emailET.getText().toString().trim();
                Pattern pattern1 = Pattern.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");
                Matcher matcher1 = pattern1.matcher(email);

                if (TextUtils.isEmpty(nama)) {
                    Toast.makeText(getApplicationContext(), "Isikan Form Nama", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(tempatLahir)) {
                    Toast.makeText(getApplicationContext(), "Isikan Form Tempat Lahir", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(alamat)) {
                    Toast.makeText(getApplicationContext(), "Isikan Form Alamat", Toast.LENGTH_SHORT).show();
                } else if (!matcher1.matches() || TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Form Email belum Diisi / Format Salah ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(date)) {
                    Toast.makeText(getApplicationContext(), "Isikan Form Tanggal Lahir", Toast.LENGTH_SHORT).show();
                } else {

                    hobi = "";
                    for (int i = 0; i < listHobi.size(); i++) {
                        hobi = hobi + " " + listHobi.get(i);
                    }
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Confirmation")
                            .setCancelable(false)
                            .setMessage("Are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                                    String nama = namaET.getText().toString();
                                    String tempatLahir = tempatLahirET.getText().toString();
                                    String tanggalLahir = tglLahirTV.getText().toString();
                                    //String jenisKelamin = genderTV.getText().toString();
                                    String alamat = alamatET.getText().toString();
                                    //String pendidikan = pendidikanSpinner();
                                    //String hobi = hobiET.getText().toString();
                                    String email = emailET.getText().toString();
                                    String lain = hobiET.getText().toString();

                                    intent.putExtra(Constant.KEY_NAMA, nama);
                                    intent.putExtra(Constant.KEY_TEMPAT_LAHIR, tempatLahir);
                                    intent.putExtra(Constant.KEY_TANGGAL_LAHIR, tanggalLahir);
                                    intent.putExtra(Constant.KEY_JENIS_KELAMIN, selectedGender);
                                    intent.putExtra(Constant.KEY_ALAMAT, alamat);
                                    intent.putExtra(Constant.KEY_PENDIDIKAN, selectedPendidikan);
                                    intent.putExtra(Constant.KEY_HOBI, hobi);
                                    intent.putExtra(Constant.KEY_HOBI_LAIN, lain);
                                    intent.putExtra(Constant.KEY_EMAIL, email);

                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();
                }

                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();

        switch (id) {
            case R.id.checkbox_membaca:
                if (isChecked) {
                    listHobi.add("Membaca");
                } else {
                    listHobi.remove("Membaca");
                }
                break;
            case R.id.checkbox_menulis:
                if (isChecked) {
                    listHobi.add("Menulis");
                } else {
                    listHobi.remove("Menulis");
                }
                break;
            case R.id.checkbox_lain:
                if (isChecked) {
                    hobiET.setEnabled(true);

                    //hobiTV = lain;
                }
                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_male:
                selectedGender = "Male";
                break;
            case R.id.radio_female:
                selectedGender = "Female";
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPendidikan = pendidikanSpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        tglLahirTV.setText(date);
    }
}
