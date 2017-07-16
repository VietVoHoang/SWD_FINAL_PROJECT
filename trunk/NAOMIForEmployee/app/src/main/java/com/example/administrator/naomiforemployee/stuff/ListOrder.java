package com.example.administrator.naomiforemployee.stuff;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.naomiforemployee.R;
import com.example.administrator.naomiforemployee.model.Order;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/13/2017.
 */

public class ListOrder extends ArrayAdapter<Order> {

    TextView txtName, txtPhone, txtAddress, txtTotal;

    public ListOrder(Context context, ArrayList<Order> orders) {
        super(context, 0, orders);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Order order = getItem(position);

        Typeface mon = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Light.ttf");
        Typeface monBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface monSemiBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-SemiBold.ttf");

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order, parent, false);
        }
        txtName = convertView.findViewById(R.id.txtName);
        txtPhone = convertView.findViewById(R.id.txtPhone);
        txtAddress = convertView.findViewById(R.id.txtAddress);
        txtTotal = convertView.findViewById(R.id.txtTotalCart);

        txtName.setTypeface(mon);
        txtPhone.setTypeface(mon);
        txtAddress.setTypeface(mon);
        txtTotal.setTypeface(monSemiBold);

        txtName.setText(order.getCustomerName());
        txtPhone.setText(order.getCustomerPhone());
        txtAddress.setText(order.getCustomerAddress());
        txtTotal.setText(order.getTotal() + " VND");
        return convertView;
    }
}
