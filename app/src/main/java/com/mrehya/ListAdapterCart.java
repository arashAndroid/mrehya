package com.mrehya;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Rubick on 2/15/2018.
 */

public class ListAdapterCart extends BaseAdapter implements ListAdapter {
    private ArrayList<Product> list = new ArrayList<>();
    private Context context;
    private Activity activity;


    public ListAdapterCart(ArrayList<Product> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_cart, null);
        }

        //Handle TextView and display string from your list
        TextView txtTitleCart = (TextView)view.findViewById(R.id.txtTitleCart);
        TextView txtPriceCart = (TextView)view.findViewById(R.id.txtPriceCart);
        TextView txtSalePriceCart = (TextView)view.findViewById(R.id.txtSalePriceCart);
        final TextView txtCountCart = (TextView)view.findViewById(R.id.txtCountCart);
        ImageButton btnPlusOneCart = view.findViewById(R.id.btnPlusOneCart);
        ImageButton btnMinusOneCart = view.findViewById(R.id.btnMinusOneCart);
        ImageView imgProductCart = view.findViewById(R.id.imgProductCart);

        txtTitleCart.setText(list.get(position).getTitle());
        txtPriceCart.setText(list.get(position).getPrice());
        if (list.get(position).getSale()==1){
            txtSalePriceCart.setText(list.get(position).getSale_price());

        }else{
            txtSalePriceCart.setText(" ");
        }


        //Handle buttons and add onClickListeners
        ImageButton btnDeleteCart =view.findViewById(R.id.btnDeleteCart);

        btnDeleteCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        btnMinusOneCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(txtCountCart.getText().toString());
                if (count>0){
                    count--;
                    txtCountCart.setText(String.valueOf(count));
                }
            }
        });
        btnPlusOneCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = translateNumToEng(txtCountCart.getText().toString());
                if (count < list.get(position).getStock()){
                    count++;
                    txtCountCart.setText(String.valueOf(count));
                }else{
                    Toast.makeText(context,"بیش از این مقدار موجود نیست",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private int translateNumToEng(String num){
        String str = num.replace('۰','0');
        str = str.replace('۱','1');
        str = str.replace('۲','2');
        str = str.replace('۳','3');
        str = str.replace('۴','4');
        str = str.replace('۵','5');
        str = str.replace('۶','6');
        str = str.replace('۷','7');
        str = str.replace('۸','8');
        str = str.replace('۹','9');
        int n;
        n = Integer.parseInt(str);
        return n;

    }
}
