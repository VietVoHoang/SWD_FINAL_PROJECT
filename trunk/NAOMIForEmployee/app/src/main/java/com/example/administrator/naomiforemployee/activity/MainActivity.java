package com.example.administrator.naomiforemployee.activity;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.naomiforemployee.R;
import com.example.administrator.naomiforemployee.model.Order;
import com.example.administrator.naomiforemployee.model.OrderItem;
import com.example.administrator.naomiforemployee.model.Product;
import com.example.administrator.naomiforemployee.stuff.ListOrder;
import com.example.administrator.naomiforemployee.stuff.ListOrderItem;
import com.example.administrator.naomiforemployee.stuff.Url;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static int status = 3;
    ArrayList<Order> orders = new ArrayList<>();
    ArrayList<OrderItem> orderItems = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    ListView lsOrder, lsOrderItem;
    Button btnTitle, btnTitleOrderItem, btnConfirm;
    ListOrder orderAdapter;
    ListOrderItem listOrderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Typeface monSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.ttf");
        final Typeface monBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");

        lsOrder = (ListView) findViewById(R.id.lsOrder);
        btnTitle = (Button) findViewById(R.id.btnOrderTitle);
        btnTitle.setTypeface(monSemiBold);

        new GetAllOrders().execute(Url.findAllOrder);
        new GetAllProduct().execute(Url.findAllProduct);

        lsOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View v = getLayoutInflater().inflate(R.layout.list_order_item, null);

                final int orderId = orderAdapter.getItem(i).getOrderId();

                lsOrderItem = (ListView) v.findViewById(R.id.lsOrderItem);
                btnTitleOrderItem = (Button) v.findViewById(R.id.btnOrderItemTitle);
                btnConfirm = (Button) v.findViewById(R.id.btnConfirm);

                btnConfirm.setTypeface(monSemiBold);
                btnTitleOrderItem.setTypeface(monSemiBold);
                builder.setView(v);
                final AlertDialog dialog = builder.create();
                if (orderItems.size() != 0 || orderItems != null) {
                    orderItems = new ArrayList<>();
                }
                new GetAllOrderItemById().execute(Url.findOrderItemByOrderId, String.valueOf(orderId));
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (orders.size() != 0 || orders != null) {
                            orders = new ArrayList<>();
                        }
                        new UpdateOrdersList().execute(Url.UpdateOrderList,
                                String.valueOf(orderId),
                                String.valueOf(status));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    protected String findProductNameById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == id) {
                return products.get(i).getProductName();
            }
        }
        return null;
    }

    protected double findProductPriceById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == id) {
                return products.get(i).getProductPrice();
            }
        }
        return 0;
    }

    private class GetAllOrders extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
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
                    Order order = new Order();
                    order.orderId = json_data.getInt("id");
                    order.customerName = json_data.getString("customerName");
                    order.customerPhone = json_data.getString("customerPhone");
                    order.customerAddress = json_data.getString("customerAddress");
                    order.total = json_data.getDouble("orderTotal");
                    orders.add(order);
                }
                orderAdapter = new ListOrder(MainActivity.this, orders);
                lsOrder.setAdapter(orderAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetAllOrderItemById extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            HttpParams httpParams = new BasicHttpParams();
            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(params[0]);
            String json = "";
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("id", params[1]));
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
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    OrderItem orderItem = new OrderItem();
                    orderItem.productId = json_data.getInt("productId");
                    orderItem.quantity = json_data.getInt("itemQuantity");
                    orderItem.productName = findProductNameById(json_data.getInt("productId"));
                    orderItem.total = findProductPriceById(json_data.getInt("productId") * json_data.getInt("itemQuantity")) * 1.0;
                    orderItems.add(orderItem);
                }
                listOrderItem = new ListOrderItem(MainActivity.this, orderItems);
                lsOrderItem.setAdapter(listOrderItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetAllProduct extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, e.toString() + "2222222222222222", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class UpdateOrdersList extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpParams httpParams = new BasicHttpParams();
            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(params[0]);
            String json = "";
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("orderId", params[1]));
                nameValuePairs.add(new BasicNameValuePair("status", params[2]));
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
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray jArray = new JSONArray(s);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Order order = new Order();
                    order.orderId = json_data.getInt("id");
                    order.customerName = json_data.getString("customerName");
                    order.customerPhone = json_data.getString("customerPhone");
                    order.customerAddress = json_data.getString("customerAddress");
                    order.total = json_data.getDouble("orderTotal");
                    orders.add(order);
                }
                ListOrder mAdapter = new ListOrder(MainActivity.this, orders);
                lsOrder.setAdapter(mAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {

    }
}
