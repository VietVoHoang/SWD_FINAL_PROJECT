package com.example.administrator.waterinc.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.waterinc.R;
import com.example.administrator.waterinc.model.OrderItem;
import com.example.administrator.waterinc.model.Product;
import com.example.administrator.waterinc.stuff.ShoppingList;
import com.example.administrator.waterinc.stuff.Url;

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

    Button btnTitle, btnViewOrder;
    ListView lsProduct;

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<OrderItem> orderItems;

    int realQuantity = 1;
    double price = 0, total = 0;

    Bundle bundle;
    private View.OnClickListener viewOrder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(OrderActivity.this, ViewOrder.class);
            bundle.putParcelableArrayList("cart", orderItems);
            intent.putExtra("extras", bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        final Typeface mon = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        final Typeface monBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        final Typeface monSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.ttf");
        final Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        bundle = getIntent().getExtras().getBundle("extras");
        orderItems = bundle.getParcelableArrayList("cart");

        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }

        getElement();
        setTypeface(mon, monSemiBold, monBold);
        setAnimation(zoomIn);

        new AddToCart().execute(Url.findAllProduct);

        lsProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                total = 0;
                realQuantity = 1;
                final AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                View v = getLayoutInflater().inflate(R.layout.set_quantity, null);

                TextView txtPrice = (TextView) view.findViewById(R.id.txtPrice);
                TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);
                final Button txtTotal = (Button) v.findViewById(R.id.txtTotal);
                final EditText edtQuantity = (EditText) v.findViewById(R.id.edtQuantity);
                Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
                Button btnSub = (Button) v.findViewById(R.id.btnSub);

                price = Double.parseDouble(txtPrice.getText().toString().trim());
                total = realQuantity * price;

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
                            total = realQuantity * price;
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
                            total = realQuantity * price;
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
                            total = realQuantity * price;
                            txtTotal.setText(total + " VND");
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (total == 0) {
                            Toast.makeText(getApplicationContext(), "Add to your cart fail", Toast.LENGTH_SHORT).show();
                        } else {
                            OrderItem orderItem = new OrderItem(realQuantity, products.get(position).getProductID(), products.get(position).getProductName(), total);
                            orderItems.add(orderItem);
                            Toast.makeText(getApplicationContext(), "Add to your cart successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnViewOrder.setOnClickListener(viewOrder);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    protected void getElement() {
        btnTitle = (Button) findViewById(R.id.btnProductTitle);
        btnViewOrder = (Button) findViewById(R.id.btnViewOrder);
        lsProduct = (ListView) findViewById(R.id.listProduct);
    }

    protected void setTypeface(Typeface mon, Typeface monSemiBold, Typeface monBold) {
        btnTitle.setTypeface(monSemiBold);
        btnViewOrder.setTypeface(monBold);
    }

    protected void setAnimation(Animation animation) {
        btnViewOrder.setAnimation(animation);
    }

    private class AddToCart extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(OrderActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Product product = new Product();
                    product.productID = json_data.getInt("id");
                    product.productName = json_data.getString("productName");
                    product.productPrice = json_data.getDouble("productPrice");
                    product.productStatus = json_data.getInt("status");
                    products.add(product);
                }
                ShoppingList mAdapter = new ShoppingList(OrderActivity.this, products);
                lsProduct.setAdapter(mAdapter);
                pdLoading.dismiss();
            } catch (JSONException e) {
                Toast.makeText(OrderActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}
