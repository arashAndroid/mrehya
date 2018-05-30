package com.mrehya;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mrehya.Helper.LocaleHelper;

import io.paperdb.Paper;


public class ProfileFragment extends Fragment {

    SessionManager sessionManager;
    Button btnSignInOrUp,showPurchases,hireRequestStatus,btnEditProfile,BtnInstagram,btnTelegram;

    //new
    MyTextView profileType,mytextUserInfo,mytextUserInfo2;
    LinearLayout LinearLayoutprofile1,LinearLayoutprofile2,LinearLayoutprofile3,LinearLayoutprofile4
            ,LinearLayoutprofile5,LinearLayoutprofile6;


    TextView txtEditName,txtEditLastName,txtEditEmail,txtEditMobile,txtEditPhone,txtEditZip,txtEditAddress;
    TextView txtEditProfile,txtName,txtLastname,txtEmail,txtPhoneNumber,txtTelephone,txtPostcode,txtAddress;
    private ProgressDialog pDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        btnSignInOrUp = view.findViewById(R.id.btnSignInOrUp);
        showPurchases = view.findViewById(R.id.showPurchases);
        hireRequestStatus = view.findViewById(R.id.hireRequestStatus);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);

        //new
        profileType = view.findViewById(R.id.profileType);
        mytextUserInfo = view.findViewById(R.id.mytextUserInfo);
        mytextUserInfo2 = view.findViewById(R.id.mytextUserInfo2);
        //layouts
        LinearLayoutprofile1 = (LinearLayout) view.findViewById(R.id.LinearLayoutprofile1);
        LinearLayoutprofile2 = (LinearLayout) view.findViewById(R.id.LinearLayoutprofile2);
        LinearLayoutprofile3 = (LinearLayout) view.findViewById(R.id.LinearLayoutprofile3);
        LinearLayoutprofile4 = (LinearLayout) view.findViewById(R.id.LinearLayoutprofile4);
        LinearLayoutprofile5 = (LinearLayout) view.findViewById(R.id.LinearLayoutprofile5);
        LinearLayoutprofile6 = (LinearLayout) view.findViewById(R.id.LinearLayoutprofile6);

        btnTelegram = view.findViewById(R.id.btnTelegram);
        BtnInstagram = view.findViewById(R.id.BtnInstagram);

        updateLanguage();
        updateView((String) Paper.book().read("language"));

        sessionManager = new SessionManager(getContext());
        btnSignInOrUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LoginOrSignup.class);
                startActivity(intent);
            }
        });
        hireRequestStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sessionManager.isLoggedIn()) {
                    Intent intent = new Intent(getActivity(), ShowHireStatus.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), LoginOrSignup.class);
                    startActivity(intent);
                }
            }
        });
        showPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sessionManager.isLoggedIn()) {
                    Intent intent = new Intent(getActivity(), ShowPurchase.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), LoginOrSignup.class);
                    startActivity(intent);
                }
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sessionManager.isLoggedIn()) {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog_edit_profile);

                    //txts
                    txtEditProfile = dialog.findViewById(R.id.txtEditProfile);
                    txtName = dialog.findViewById(R.id.txtName);
                    txtLastname = dialog.findViewById(R.id.txtLastname);
                    txtEmail = dialog.findViewById(R.id.txtEmail);
                    txtPhoneNumber = dialog.findViewById(R.id.txtPhoneNumber);
                    txtTelephone = dialog.findViewById(R.id.txtTelephone);
                    txtPostcode = dialog.findViewById(R.id.txtPostcode);
                    txtAddress = dialog.findViewById(R.id.txtAddress);

                    txtEditName = dialog.findViewById(R.id.txtEditName);
                    txtEditLastName = dialog.findViewById(R.id.txtEditLastName);
                    txtEditEmail = dialog.findViewById(R.id.txtEditEmail);
                    txtEditMobile = dialog.findViewById(R.id.txtEditMobile);
                    txtEditPhone = dialog.findViewById(R.id.txtEditPhone);
                    txtEditZip = dialog.findViewById(R.id.txtEditZip);
                    txtEditAddress = dialog.findViewById(R.id.txtEditAddress);



                    //user credentials
                    String token = sessionManager.getUserDetails().getToken();
                    User user = sessionManager.getUserDetails();
                    txtEditName.setText(user.getFirstname());
                    txtEditLastName.setText(sessionManager.getUserDetails().getLastname());
                    txtEditEmail.setText(sessionManager.getUserDetails().getEmail());
                    txtEditMobile.setText(sessionManager.getUserDetails().getMobile());
                    txtEditPhone.setText(sessionManager.getUserDetails().getPhone());
                    txtEditZip.setText(sessionManager.getUserDetails().getZip());
                    txtEditAddress.setText(sessionManager.getUserDetails().getAddress());

                    updateView_dialog(updateLanguage());

                    DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
                    int width = metrics.widthPixels;
                    int height = metrics.heightPixels;

                    dialog.show();
                    dialog.getWindow().setLayout((12 * width)/13, ViewGroup.LayoutParams.WRAP_CONTENT);


                }else{
                    Toast.makeText(getContext(), "not logged in", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    private String updateLanguage(){
        //Default language is fa
        String language = Paper.book().read("language");
        if(language==null)
            Paper.book().write("language", "fa");
        return language;
    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(getActivity(), language);
        Resources resources = context.getResources();

        //Linear Layouts
        if(language.equals("fa")){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                LinearLayoutprofile1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile3.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile4.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile5.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile6.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                mytextUserInfo.setGravity(Gravity.RIGHT);
                mytextUserInfo2.setGravity(Gravity.RIGHT);
            }
            else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LinearLayoutprofile1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile3.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile4.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile5.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutprofile6.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                mytextUserInfo.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                mytextUserInfo2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                mytextUserInfo.setGravity(Gravity.RIGHT);
                mytextUserInfo2.setGravity(Gravity.RIGHT);

            }

            btnSignInOrUp.setGravity(Gravity.RIGHT|Gravity.CENTER);
            showPurchases.setGravity(Gravity.RIGHT|Gravity.CENTER);
            hireRequestStatus.setGravity(Gravity.RIGHT|Gravity.CENTER);
            btnEditProfile.setGravity(Gravity.RIGHT|Gravity.CENTER);
            BtnInstagram.setGravity(Gravity.RIGHT|Gravity.CENTER);
            btnTelegram.setGravity(Gravity.RIGHT|Gravity.CENTER);
        }
        else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                LinearLayoutprofile1.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile2.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile3.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile4.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile5.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile6.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                mytextUserInfo.setGravity(Gravity.LEFT);
                mytextUserInfo2.setGravity(Gravity.LEFT);
            }
            else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LinearLayoutprofile1.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile2.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile3.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile4.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile5.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutprofile6.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                mytextUserInfo.setGravity(Gravity.LEFT);
                mytextUserInfo2.setGravity(Gravity.LEFT);
            }

            btnSignInOrUp.setGravity(Gravity.LEFT|Gravity.CENTER);
            showPurchases.setGravity(Gravity.LEFT|Gravity.CENTER);
            hireRequestStatus.setGravity(Gravity.LEFT|Gravity.CENTER);
            btnEditProfile.setGravity(Gravity.LEFT|Gravity.CENTER);
            BtnInstagram.setGravity(Gravity.LEFT|Gravity.CENTER);
            btnTelegram.setGravity(Gravity.LEFT|Gravity.CENTER);
        }

        btnSignInOrUp.setText(resources.getString(R.string.SignInOrUp));
        showPurchases.setText(resources.getString(R.string.showPurchases));
        hireRequestStatus.setText(resources.getString(R.string.hireRequestStatus));
        btnEditProfile.setText(resources.getString(R.string.EditProfile));
        BtnInstagram.setText(resources.getString(R.string.Instagram));
        btnTelegram.setText(resources.getString(R.string.Telegram));
        profileType.setText(resources.getString(R.string.profileType));
        mytextUserInfo.setText(resources.getString(R.string.UserInfo));
        mytextUserInfo2.setText(resources.getString(R.string.UserInfo2));


    }


    public void updateView_dialog(String language){
        Context context = LocaleHelper.setLocale(getActivity(), language);
        Resources resources = context.getResources();
        txtEditProfile.setText(resources.getString(R.string.EditProfile));
        txtName.setText(resources.getString(R.string.Name));
        txtLastname.setText(resources.getString(R.string.Lastname));
        txtEmail.setText(resources.getString(R.string.Email));
        txtPhoneNumber.setText(resources.getString(R.string.PhoneNumber));
        txtTelephone.setText(resources.getString(R.string.Telephone));
        txtPostcode.setText(resources.getString(R.string.Postcode));
        txtAddress.setText(resources.getString(R.string.Address));
    }

}
