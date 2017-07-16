package com.example.administrator.waterinc.stuff;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.waterinc.R;
import com.example.administrator.waterinc.model.OrderItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/13/2017.
 */

public class ShoppingCart extends ArrayAdapter<OrderItem> {

    TextView txtName, txtTotal, txtNameTitle, txtTotalTitle, txtQuantity, txtQuantityTitle, txtPrice, txtPriceTitle;

    public ShoppingCart(Context context, ArrayList<OrderItem> orderItems) {
        super(context, 0, orderItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OrderItem orderItem = getItem(position);

        Typeface mon = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Light.ttf");
        Typeface monBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface monSemiBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-SemiBold.ttf");

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_cart, parent, false);
        }
        txtName = (TextView) convertView.findViewById(R.id.txtNameProduct);
        txtTotal = (TextView) convertView.findViewById(R.id.txtTotalProduct);
        txtQuantity = (TextView) convertView.findViewById(R.id.txtQuantityProduct);
        txtNameTitle = (TextView) convertView.findViewById(R.id.txtNameItem);
        txtTotalTitle = (TextView) convertView.findViewById(R.id.txtTotalItem);
        txtQuantityTitle = (TextView) convertView.findViewById(R.id.txtQuantityItem);
        txtPrice = (TextView) convertView.findViewById(R.id.txtPriceProduct);
        txtPriceTitle = (TextView) convertView.findViewById(R.id.txtPriceItem);

        txtName.setTypeface(mon);
        txtTotal.setTypeface(mon);
        txtQuantity.setTypeface(mon);
        txtPrice.setTypeface(mon);
        txtNameTitle.setTypeface(monBold);
        txtQuantityTitle.setTypeface(monBold);
        txtTotalTitle.setTypeface(monBold);
        txtPriceTitle.setTypeface(monBold);

        txtName.setText(orderItem.getProductName());
        txtQuantity.setText(orderItem.getQuantity() + "");
        txtTotal.setText(orderItem.getTotal() + "");
        txtPrice.setText(orderItem.getTotal() / orderItem.getQuantity() + "");
        return convertView;
    }
}
