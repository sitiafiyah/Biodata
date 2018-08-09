package com.siti.asyst.biodatasiti;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.siti.asyst.biodatasiti.Utility.Constant;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView namaTV, tempatLahirTV, tanggalLahirTV, jeniskelaminTV, alamatTV, pendidikanTV, hobiTV, hobilainTV, emailTV;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        namaTV = findViewById(R.id.nama_textview);
        tempatLahirTV = findViewById(R.id.tempatlahir_textview);
        tanggalLahirTV = findViewById(R.id.date_textview);
        jeniskelaminTV = findViewById(R.id.gender_textview);
        alamatTV = findViewById(R.id.alamat_textview);
        pendidikanTV = findViewById(R.id.pendidikan_textview);
        hobiTV = findViewById(R.id.hobi_textview);
        hobilainTV = findViewById(R.id.hobilain_textview);
        emailTV = findViewById(R.id.email_textview);

        backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();

            namaTV.setText(bundle.getString(Constant.KEY_NAMA, ""));
            tempatLahirTV.setText(bundle.getString(Constant.KEY_TEMPAT_LAHIR, ""));
            tanggalLahirTV.setText(bundle.getString(Constant.KEY_TANGGAL_LAHIR, ""));
            jeniskelaminTV.setText(bundle.getString(Constant.KEY_JENIS_KELAMIN, ""));
            alamatTV.setText(bundle.getString(Constant.KEY_ALAMAT, ""));
            pendidikanTV.setText(bundle.getString(Constant.KEY_PENDIDIKAN, ""));
            hobiTV.setText(bundle.getString(Constant.KEY_HOBI, ""));
            hobilainTV.setText(bundle.getString(Constant.KEY_HOBI_LAIN, ""));
            emailTV.setText(bundle.getString(Constant.KEY_EMAIL, ""));

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation")
                .setCancelable(false)
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                        finish();

                    }
                })
                .setNegativeButton("NO", null)
                .show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_back:
                //Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
        }
    }

}
