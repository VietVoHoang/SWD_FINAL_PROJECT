package com.example.administrator.waterinc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String PREFS_NAME = "information";
    public static String PREF_NAME = "name";
    public static String PREF_PHONE = "phone";
    public static String PREF_ADDRESS = "address";

    EditText edtName, edtPhone, edtAddress;
    Button btnOrder;
    TextView txtName, txtPhone, txtAddress;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface mon = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        Typeface monBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface monSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.ttf");
        Animation zoomIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);

        getElement();
        setTypeFace(mon, monSemiBold, monBold);
        imgLogo.startAnimation(zoomIn);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                System.out.println(validateData(name, phone, address));
                if (validateData(name, phone, address) == false) {
                    Toast.makeText(getApplicationContext(), "Incorrect information", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                rememberInformation(name, phone, address);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    protected void getElement() {
        btnOrder = (Button) findViewById(R.id.btnOrder);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        txtName = (TextView) findViewById(R.id.txtName);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
    }

    protected void setTypeFace(Typeface mon, Typeface monSemiBold, Typeface monBold) {
        btnOrder.setTypeface(monSemiBold);
        edtName.setTypeface(mon);
        edtPhone.setTypeface(mon);
        edtAddress.setTypeface(mon);
        txtName.setTypeface(monBold);
        txtPhone.setTypeface(monBold);
        txtAddress.setTypeface(monBold);
    }

    public void rememberInformation(String name, String phone, String address) {
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(PREF_NAME, name)
                .putString(PREF_PHONE, phone)
                .putString(PREF_ADDRESS, address)
                .apply();
    }

    public void getInformation() {
        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        edtName.setText(pref.getString(PREF_NAME, null));
        edtPhone.setText(pref.getString(PREF_PHONE, null));
        edtAddress.setText(pref.getString(PREF_ADDRESS, null));
    }

    @Override
    protected void onStart() {
        super.onStart();
        getInformation();
    }

    public boolean validateData(String name, String phone, String address) {
        boolean flag = true;
        if (name.length() == 0 || name.length() >= 30 || name == null) {
            flag = false;
        }
        if (phone.length() < 10 || phone == null) {
            flag = false;
        }
        if (address.length() > 100 || address == null) {
            flag = false;
        }
        return flag;
    }
}
