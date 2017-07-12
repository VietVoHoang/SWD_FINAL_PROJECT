package com.example.administrator.waterinc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "id";
    private static final String TAG_PRICE = "name";
    String[] product = {"Na-O-Mi 330Ml", "Na-O-Mi 500Ml", "Na-O-Mi 1.5L", "Loc Na-O-Mi 330Ml"};
    String[] price = {"15000 VND", "25000 VND", "35000 VND", "45000 VND"};

    Button btnTitle, btnOrder;
    ListView lsProduct;
    RelativeLayout relativeLayout;


    String myJSON;
    int realQuantity = 1;
    int total = 0;
    private Bundle information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        final Typeface mon = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        final Typeface monBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        final Typeface monSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.ttf");
        final Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        getElement();

        information = getIntent().getExtras();

        btnTitle.setTypeface(monSemiBold);
        btnOrder.setTypeface(monBold);


        relativeLayout.setAnimation(zoomIn);
        new AsyncFetch().execute();

        /*ShoppingList shoppingList = new ShoppingList(OrderActivity.this, product, price);
        ListView listItem = (ListView) findViewById(R.id.listProduct);
        listItem.setAdapter(shoppingList);
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                View v = getLayoutInflater().inflate(R.layout.set_quantity, null);

                total = realQuantity * 1500;
                TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);
                final TextView txtTotal = (TextView) v.findViewById(R.id.txtTotal);
                final EditText edtQuantity = (EditText) v.findViewById(R.id.edtQuantity);
                Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
                Button btnSub = (Button) v.findViewById(R.id.btnSub);
                txtDescription.setTypeface(mon);
                txtTotal.setTypeface(monBold);
                edtQuantity.setTypeface(monBold);
                btnAdd.setTypeface(mon);
                btnSub.setTypeface(mon);
                txtTotal.setText(total + " VND");
                edtQuantity.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (edtQuantity.length() == 0) {
                            total = 0;
                            txtTotal.setText(total + " VND");
                            return;
                        }
                        String quantity = edtQuantity.getText().toString().trim();
                        realQuantity = Integer.valueOf(quantity);
                        if (realQuantity > 0) {
                            total = realQuantity * 1500;
                            txtTotal.setText(total + " VND");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String quantity = edtQuantity.getText().toString().trim();
                        realQuantity = Integer.valueOf(quantity);
                        if (realQuantity > 0) {
                            realQuantity++;
                            edtQuantity.setText(realQuantity + "");
                            total = realQuantity * 1500;
                            txtTotal.setText(total + " VND");
                        }
                    }
                });
                btnSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String quantity = edtQuantity.getText().toString().trim();
                        realQuantity = Integer.valueOf(quantity);
                        if (realQuantity > 1) {
                            realQuantity--;
                            edtQuantity.setText(realQuantity + "");
                            total = realQuantity * 1500;
                            txtTotal.setText(total + " VND");
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (total == 0) {
                            Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });*/

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                View view = getLayoutInflater().inflate(R.layout.confirm_dialog, null);
                TextView txtInfo = (TextView) view.findViewById(R.id.txtContent);
                txtInfo.setText(information.getString("name"));
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(OrderActivity.this);
        HttpURLConnection con;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL(Url.findAllProduct);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }

            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                con = (HttpURLConnection) url.openConnection();
                con.setReadTimeout(READ_TIMEOUT);
                con.setConnectTimeout(CONNECTION_TIMEOUT);
                con.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                con.setDoOutput(true);

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("Connect to server unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                con.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();
            ArrayList<Product> data = new ArrayList<>();
            pdLoading.dismiss();
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Product product = new Product();
                    product.productID = json_data.getInt("productId");
                    product.productName = json_data.getString("productName");
                    product.productPrice = json_data.getDouble("cat_name");
                    product.productStatus = json_data.getInt("size_name");
                    data.add(product);
                }

                ShoppingList mAdapter = new ShoppingList(OrderActivity.this, data);
                lsProduct.setAdapter(mAdapter);

            } catch (JSONException e) {
                Toast.makeText(OrderActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void getElement(){
        btnTitle = (Button) findViewById(R.id.txtTitle);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        lsProduct = (ListView) findViewById(R.id.listProduct);
        relativeLayout = (RelativeLayout) findViewById(R.id.relOrderList);
    }
}
