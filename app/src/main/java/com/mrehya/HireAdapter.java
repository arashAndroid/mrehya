package com.mrehya;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrehya.Helper.LocaleHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.paperdb.Paper;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class HireAdapter extends RecyclerView.Adapter<HireAdapter.MyViewHolder> {

    private Context mContext;
    private List<HireCompanies> hireCompaniesList;

    //new
    private com.mrehya.VerticalTextView text1;
    private LinearLayout LinearLayoutRecyclerItem_hire;
    private ImageView thumbnail;
    private TextView title;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, city,timeAgo;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            timeAgo = (TextView) view.findViewById(R.id.timeAgo);
            city = (TextView) view.findViewById(R.id.city);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public HireAdapter(Context mContext, List<HireCompanies> hireCompaniesList) {
        this.mContext = mContext;
        this.hireCompaniesList = hireCompaniesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hire_recycler_item, parent, false);


        //new
        Paper.init(mContext);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        title = itemView.findViewById(R.id.title);
        text1 = itemView.findViewById(R.id.text1);
        LinearLayoutRecyclerItem_hire = itemView.findViewById(R.id.LinearLayoutRecyclerItem_hire);
        String Language = updateLanguage();
        updateView(Language);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        HireCompanies HireCompany = hireCompaniesList.get(position);
        holder.title.setText(HireCompany.getCompanyName());
        holder.city.setText(HireCompany.getPlace());
        holder.timeAgo.setText(HireCompany.getTime());

        // loading album cover using Glide library
        Glide.with(mContext)
                .load(HireCompany.getImage())
                .placeholder(R.drawable.logo_eng)
                .error(R.drawable.logo_eng)
                .into(holder.thumbnail);
        //Glide.with(mContext).load(HireCompany.getImage()).into(holder.thumbnail);
    }




    @Override
    public int getItemCount() {
        return hireCompaniesList.size();
    }



    private String updateLanguage(){
        //Default language is fa
        String language = Paper.book().read("language");
        if(language==null)
            Paper.book().write("language", "fa");
        return language;
    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(mContext, language);
        Resources resources = context.getResources();

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams thumbnailparams = new RelativeLayout.LayoutParams(160, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams titleparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams LinearLayoutrecyclerItemhireparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        if(language.equals("fa")){
            text1.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
            text1.setLayoutParams(params);

            thumbnailparams.addRule(RelativeLayout.RIGHT_OF, R.id.text1);
            thumbnailparams.setMargins(2,0,2,0);
            thumbnail.setLayoutParams(thumbnailparams);

            titleparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            titleparams.setMargins(0,0,15,0);
            title.setLayoutParams(titleparams);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                LinearLayoutRecyclerItem_hire.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
            LinearLayoutrecyclerItemhireparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            LinearLayoutrecyclerItemhireparams.addRule(RelativeLayout.BELOW, R.id.title);
            LinearLayoutrecyclerItemhireparams.setMargins(15,10,0,0);
            LinearLayoutRecyclerItem_hire.setLayoutParams(LinearLayoutrecyclerItemhireparams);
        }
        else{
            text1.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
            text1.setLayoutParams(params);

            thumbnailparams.addRule(RelativeLayout.LEFT_OF, R.id.text1);
            thumbnail.setLayoutParams(thumbnailparams);

            titleparams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            titleparams.setMargins(15,10,0,0);
            title.setLayoutParams(titleparams);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                LinearLayoutRecyclerItem_hire.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
            LinearLayoutrecyclerItemhireparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            LinearLayoutrecyclerItemhireparams.addRule(RelativeLayout.BELOW, R.id.title);
            LinearLayoutrecyclerItemhireparams.setMargins(0,10,35,0);
            LinearLayoutRecyclerItem_hire.setLayoutParams(LinearLayoutrecyclerItemhireparams);

            text1.setText(resources.getString(R.string.Hire));
        }
    }
}