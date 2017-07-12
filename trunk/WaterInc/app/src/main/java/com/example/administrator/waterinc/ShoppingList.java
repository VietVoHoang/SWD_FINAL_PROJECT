package com.example.administrator.waterinc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/8/2017.
 */

public class ShoppingList extends ArrayAdapter<Product> {

    TextView txtName;
    TextView txtPrice;

    public ShoppingList(Context context, ArrayList<Product> products) {
        super(context, 0, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = getItem(position);
        Typeface mon = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Light.ttf");

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
        txtName.setTypeface(mon);
        txtPrice.setTypeface(mon);
        txtName.setText(product.productName);
        txtPrice.setText(product.productPrice + "");
        return convertView;
    }
}
