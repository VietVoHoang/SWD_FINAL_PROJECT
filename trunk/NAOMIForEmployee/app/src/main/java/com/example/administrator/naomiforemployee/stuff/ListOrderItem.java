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
import com.example.administrator.naomiforemployee.model.OrderItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/13/2017.
 */

public class ListOrderItem extends ArrayAdapter<OrderItem> {

    TextView txtNameTitle, txtQuantityTitle, txtTotalTitle, txtName, txtQuantity, txtTotal;

    public ListOrderItem(Context context, ArrayList<OrderItem> orderItems) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
        }

        txtNameTitle = convertView.findViewById(R.id.txtNameTitle);
        txtQuantityTitle = convertView.findViewById(R.id.txtQuantityTitle);
        txtTotalTitle = convertView.findViewById(R.id.txtTotalTitle);
        txtName = convertView.findViewById(R.id.txtName);
        txtQuantity = convertView.findViewById(R.id.txtQuantity);
        txtTotal = convertView.findViewById(R.id.txtTotal);


        txtName.setTypeface(mon);
        txtQuantity.setTypeface(mon);
        txtTotal.setTypeface(mon);
        txtNameTitle.setTypeface(monBold);
        txtQuantityTitle.setTypeface(monBold);
        txtTotalTitle.setTypeface(monBold);

        txtName.setText(orderItem.getProductName());
        txtQuantity.setText(orderItem.getQuantity() + "");
        txtTotal.setText(orderItem.getTotal() + "");
        return convertView;
    }
}
