package com.mrehya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowProduct extends AppCompatActivity {
    TextView txtPrice,txtSalePrice,txtCount,txtTitle,txtDescription,txtSalePriceRial;
    ImageButton btnMinusOne,btnPlusOne;
    Button btnBuy,btnToCart;
    ImageView imgProduct;
    Product product;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String CART_TAG = "سبد خرید (";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        product =(Product) getIntent().getSerializableExtra("Product");
        product.setStock(5);
        prefs = getApplicationContext().getSharedPreferences("ehya", 0);
        editor = prefs.edit();

        setViews();
        setTexts();
        setCartCount();
        setClickListeners();

    }
    private void setCartCount(){
        ArrayList<Integer> cartList = loadArray("cart_list",getApplicationContext());
        btnToCart.setText(CART_TAG + translateNumToFa(String.valueOf(cartList.size())) + ")");
    }
    private void setViews(){
        txtCount = (TextView) findViewById(R.id.txtCount);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtSalePrice = (TextView) findViewById(R.id.txtSalePrice);
        txtSalePriceRial = (TextView) findViewById(R.id.txtSalePriceRial);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        btnMinusOne = (ImageButton) findViewById(R.id.btnMinusOne);
        btnPlusOne = (ImageButton) findViewById(R.id.btnPlusOne);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnToCart = (Button) findViewById(R.id.btnToCart);
        imgProduct = (ImageView) findViewById(R.id.imgProduct);
    }
    private void setTexts(){
        txtCount.setText("۰");
        txtPrice.setText(Integer.toString(product.getPrice()));
        txtTitle.setText(product.getTitle());
        txtDescription.setText(product.getShort_description());
        if (product.getSale() == 0){
            txtSalePrice.setText(" ");
            txtSalePriceRial.setText(" ");
        }else{
            txtSalePrice.setText(Integer.toString(product.getSale_price()));
            txtSalePriceRial.setText("ریال");
        }

    }
    private void setClickListeners(){
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean exists = false;
                ArrayList<Integer> cartList = loadArray("cart_list",getApplicationContext());
                ArrayList<Integer> cartListCount = loadArray("cart_list_count",getApplicationContext());
                for (int i=0;i<cartList.size();i++){
                    if (cartList.get(i) == product.getId()){
                        exists = true;
                        break;
                    }
                }
                if(exists){
                    Toast.makeText(getApplicationContext(),"محصول در سبد خرید وجود دارد",Toast.LENGTH_SHORT).show();
                }else{

                    cartList.add(product.getId());
                    cartListCount.add(Integer.parseInt(txtCount.getText().toString()));
                    saveArray(cartList,"cart_list",getApplicationContext());
                    saveArray(cartListCount,"cart_list_count",getApplicationContext());
                }

                Log.e("cartListSize: ","size= "+cartList.size());
                Log.e("cartListSizeCount: ","sizeCount= "+cartListCount.size());
                for (int i=0;i<cartList.size();i++){
                    Log.e("cartList: ","id= "+cartList.get(i));
                    Log.e("cartListCount: ","count= "+cartListCount.get(i));
                }

            }
        });
        btnMinusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(txtCount.getText().toString());
                if (count>0){
                    count--;
                    txtCount.setText(String.valueOf(count));
                }
            }
        });
        btnPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = translateNumToEng(txtCount.getText().toString());
                if (count < product.getStock()){
                    count++;
                    txtCount.setText(String.valueOf(count));
                }else{
                    Toast.makeText(getApplicationContext(),"بیش از این مقدار موجود نیست",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowProduct.this,Cart.class);
                startActivity(intent);
            }
        });
    }
    public boolean saveArray(ArrayList<Integer> array, String arrayName, Context mContext) {
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putInt(arrayName + "_" + i, array.get(i));
        editor.commit();
        return true;
    }
    public ArrayList<Integer> loadArray(String arrayName, Context mContext) {
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<Integer> array = new ArrayList<>();
        for(int i=0;i<size;i++)
            array.add(prefs.getInt(arrayName + "_" + i, 0));
        return array;
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
    private String translateNumToFa(String num){
        String str = num.replace('0','۰');
        str = str.replace('1','۱');
        str = str.replace('2','۲');
        str = str.replace('3','۳');
        str = str.replace('4','۴');
        str = str.replace('5','۵');
        str = str.replace('6','۶');
        str = str.replace('7','۷');
        str = str.replace('8','۸');
        str = str.replace('9','۹');

        return str;

    }
}









