package com.example.administrator.waterinc.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.waterinc.R;
import com.example.administrator.waterinc.model.OrderItem;
import com.example.administrator.waterinc.stuff.ShoppingCart;
import com.example.administrator.waterinc.stuff.Url;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewOrder extends AppCompatActivity {

    public ArrayList<OrderItem> orderItems = new ArrayList<>();
    public int orderId;
    public int realQuantity = 1, status = 0, empId = 4;
    Bundle bundle;
    private boolean flag = true;
    private double price = 0, total = 0, finalTotal = 0;
    private String createDate, customerName, customerPhone, customerAddress;
    private Button btnConfirm, btnTitle;
    private TextView txtName, txtPhone, txtAddress, txtCart, txtInfo, txtNotification;
    private ListView lstOrder;
    private ImageView imgNotify;
    private RelativeLayout relativeLayoutNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        final Typeface mon = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        final Typeface monBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        final Typeface monSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.ttf");
        Animation zoomIn = AnimationUtils.loadAnimation(ViewOrder.this, R.anim.zoom_in);

        bundle = getIntent().getExtras().getBundle("extras");
        orderItems = bundle.getParcelableArrayList("cart");

        getElement();
        setTypeface(mon, monSemiBold, monBold);
        setAnimation(zoomIn);
        setDataBundle(bundle);

        if (orderItems.size() == 0 || orderItems == null) {
            btnConfirm.setEnabled(false);
            relativeLayoutNotification.setVisibility(View.VISIBLE);
        } else {
            btnConfirm.setEnabled(true);
            relativeLayoutNotification.setVisibility(View.INVISIBLE);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateNewCart().execute(Url.createNewOrder);
                for (int i = 0; i < orderItems.size(); i++) {
                    new CreateNewOrderItem().execute(Url.createNewOrderItem,
                            String.valueOf(orderItems.get(i).getProductId()),
                            String.valueOf(orderItems.get(i).getQuantity()));
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(ViewOrder.this);
                View view = getLayoutInflater().inflate(R.layout.confirm_dialog, null);
                imgNotify = (ImageView) view.findViewById(R.id.imgResult);
                txtInfo = (TextView) view.findViewById(R.id.txtContent);
                txtInfo.setTypeface(mon);
                if (flag == true) {
                    imgNotify.setImageResource(R.drawable.success);
                    txtInfo.setText("Order Successfully\nWe will contact you soon !");
                } else {
                    imgNotify.setImageResource(R.drawable.fail);
                    txtInfo.setText("Order Unsuccessfully\nPlease try again later !");
                }
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        final ShoppingCart mAdapter = new ShoppingCart(ViewOrder.this, orderItems);
        lstOrder.setAdapter(mAdapter);
        lstOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ViewOrder.this);
                View v = getLayoutInflater().inflate(R.layout.edit_dialog, null);

                final TextView txtPrice = (TextView) view.findViewById(R.id.txtPriceProduct);
                final TextView txtQuantity = (TextView) view.findViewById(R.id.txtQuantityProduct);
                final TextView txtTotalProduct = (TextView) view.findViewById(R.id.txtTotalProduct);

                realQuantity = Integer.parseInt(txtQuantity.getText().toString().trim());
                price = Double.parseDouble(txtPrice.getText().toString().trim());
                total = Double.parseDouble(txtTotalProduct.getText().toString().trim());

                total = realQuantity * price;

                TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);
                final TextView txtTotal = (TextView) v.findViewById(R.id.txtTotal);
                final EditText edtQuantity = (EditText) v.findViewById(R.id.edtQuantity);
                Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
                Button btnSub = (Button) v.findViewById(R.id.btnSub);
                Button btnDelete = (Button) v.findViewById(R.id.btnDelete);
                Button btnUpdate = (Button) v.findViewById(R.id.btnUpdateQuantity);

                txtDescription.setTypeface(mon);
                txtTotal.setTypeface(monBold);
                edtQuantity.setTypeface(monBold);
                btnAdd.setTypeface(mon);
                btnSub.setTypeface(mon);
                txtTotal.setText(total + " VND");
                builder.setView(v);
                edtQuantity.setText(realQuantity + "");

                final AlertDialog dialog = builder.create();
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
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderItem orderItem = mAdapter.getItem(position);
                        orderItems.remove(orderItems.get(position));
                        mAdapter.remove(orderItem);
                        mAdapter.notifyDataSetChanged();
                        if (orderItems.size() == 0 || orderItem == null) {
                            btnConfirm.setEnabled(false);
                            relativeLayoutNotification.setVisibility(View.VISIBLE);
                        }
                        dialog.dismiss();
                    }
                });
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtTotalProduct.setText(total + "");
                        txtQuantity.setText(realQuantity + "");
                        OrderItem orderItem = mAdapter.getItem(position);
                        orderItem.setQuantity(realQuantity);
                        orderItem.setTotal(total);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewOrder.this, OrderActivity.class);
        bundle.putParcelableArrayList("cart", orderItems);
        intent.putExtra("extras", bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    protected void getElement() {
        btnConfirm = (Button) findViewById(R.id.btnPay);
        btnTitle = (Button) findViewById(R.id.btnCartTitle);
        txtName = (TextView) findViewById(R.id.txtNameFinal);
        txtPhone = (TextView) findViewById(R.id.txtPhoneFinal);
        txtAddress = (TextView) findViewById(R.id.txtAddressFinal);
        txtCart = (TextView) findViewById(R.id.txtCart);
        lstOrder = (ListView) findViewById(R.id.lstOrder);
        txtNotification = (TextView) findViewById(R.id.txtError);
        relativeLayoutNotification = (RelativeLayout) findViewById(R.id.notificationLayout);
    }

    protected void setTypeface(Typeface mon, Typeface monSemiBold, Typeface monBold) {
        btnTitle.setTypeface(monSemiBold);
        btnConfirm.setTypeface(monBold);
        txtName.setTypeface(monSemiBold);
        txtPhone.setTypeface(monSemiBold);
        txtAddress.setTypeface(monSemiBold);
        txtCart.setTypeface(monSemiBold);
        txtNotification.setTypeface(monSemiBold);
    }

    protected void setAnimation(Animation animation) {
        btnConfirm.startAnimation(animation);
    }

    protected void setDataBundle(Bundle dataBundle) {
        txtName.setText(dataBundle.getString("name"));
        txtPhone.setText(dataBundle.getString("phone"));
        txtAddress.setText(dataBundle.getString("address"));
        customerName = txtName.getText().toString();
        customerPhone = txtPhone.getText().toString();
        customerAddress = txtAddress.getText().toString();
    }

    protected String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createDate = df.format(c.getTime());
        return createDate;
    }

    protected Double getTotal() {
        for (int i = 0; i < orderItems.size(); i++) {
            finalTotal += orderItems.get(i).getTotal();
        }
        return finalTotal;
    }

    public class CreateNewCart extends AsyncTask<String, Void, String> {
        ProgressDialog pdLoading = new ProgressDialog(ViewOrder.this);

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        public String doInBackground(String... params) {
            HttpParams httpParams = new BasicHttpParams();
            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(params[0]);
            String json = "";
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("createDate", getCurrentDate()));
                nameValuePairs.add(new BasicNameValuePair("total", String.valueOf(getTotal())));
                nameValuePairs.add(new BasicNameValuePair("status", String.valueOf(status)));
                nameValuePairs.add(new BasicNameValuePair("cusName", customerName));
                nameValuePairs.add(new BasicNameValuePair("cusPhone", customerPhone));
                nameValuePairs.add(new BasicNameValuePair("cusAddress", customerAddress));
                nameValuePairs.add(new BasicNameValuePair("empId", String.valueOf(empId)));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = client.execute(post);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String chunk = null;
                while ((chunk = br.readLine()) != null) {
                    sb.append(chunk);
                }
                json = sb.toString();
                pdLoading.dismiss();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                /*flag = false;*/
            } catch (IOException e) {
                e.printStackTrace();
                /*flag = false;*/
            }
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                orderId = jsonObject.getInt("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        public void onPostExecute(String result) {
            //super.onPostExecute(result);
            pdLoading.dismiss();
        }
    }

    public class CreateNewOrderItem extends AsyncTask<String, Void, String> {
        ProgressDialog pdLoading = new ProgressDialog(ViewOrder.this);

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        public String doInBackground(String... params) {
            HttpParams httpParams = new BasicHttpParams();
            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(params[0]);
            String json = "";
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("orderId", String.valueOf(orderId)));
                nameValuePairs.add(new BasicNameValuePair("productId", params[1]));
                nameValuePairs.add(new BasicNameValuePair("quantity", params[2]));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = client.execute(post);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String chunk = null;
                while ((chunk = br.readLine()) != null) {
                    sb.append(chunk);
                }
                json = sb.toString();
                pdLoading.dismiss();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                /*flag = false;*/
            } catch (IOException e) {
                e.printStackTrace();
                /*flag = false;*/
            }
            return json;
        }

        @Override
        public void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}

