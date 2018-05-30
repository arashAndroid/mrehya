package com.mrehya;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mrehya.Helper.LocaleHelper;

import org.json.JSONArray;

import java.util.ArrayList;

import io.paperdb.Paper;

public class MakeResume extends AppCompatActivity {
    ArrayList<String> listProvince;
    ArrayList<String> listJobcat;
    ArrayList<String> listTerms;
    ArrayList<String> listSkill;

    ArrayList<String> listProes;
    ArrayList<Lang> listLang;
    ArrayList<Job> listJobs;
    ArrayList<Education> listEducation;
    ListAdapterProes listAdapterProes;
    ListAdapterLang listAdapterLang;
    ListAdapterJobs listAdapterJobs;
    ListAdapterProvince listAdapterProvince;
    ListAdapterProvince listAdapterJobcat;
    ListAdapterTerms listAdapterTerms;
    ListAdapterTerms listAdapterSkill;
    ListAdapterEducation listAdapterEducation;

    TextView txtSalary,txtAboutMe;
    ImageButton btnAddProes,btnAddJobs,btnAddEducation,btnAddLang ,
            btnAddProvince,btnAddJobcat,btnAddTerms,btnAddSkill,btnEditSalary;
    ListView listViewProes,listViewJobs,listViewEducation,listViewLang,
            listViewProvince,listViewJobcat,listViewTerms,listViewSkill;
    Button btnSaveBenfits,btnSaveAboutMe;

    //new
    TextView txtJobTitle,txtJobTitle2,txtUnderJobTitle,txtUnderJobTitle2,txtLastDegree,txtLastDegreeTitle
            ,txtEdit,txtPrivateInfo,txtEmailAddress,txtEmailAddressTitle,txtPhone,txtPhoneTitle,txtProvinceResume,
            txtProvinceResumeTitle,txtMarriageResume,txtMarriageResumeTitle,txtBirthYearResume,txtBirthYearResumeTitle,
            txtDutyResume,txtDutyResumeTitle,txtAddressResume,txtAddressResumeTitle,txtTalents,txtJobExp,txtGraduateExp,
            txtLanguages,txtJobFavorites,txtJobCategory,txtSelectedProvinces,txtAcceptedContract,txtActivityLevel,
            txtActualRights,txtJobBenefits,txtAboutMe2,txtGenderResumeTitle,txtGenderResume;
    LinearLayout LinearLayoutresume1,LinearLayoutresume2,LinearLayoutresume8,
            LinearLayoutresume11,LinearLayoutresume12,LinearLayoutresume13;

    LinearLayout LinearLayoutMakeResume, LinearLayoutresumepart1,LinearLayoutresumepart2,LinearLayoutresumepart3
            ,LinearLayoutresumepart4,LinearLayoutresumepart5
            ,LinearLayoutresumepart6,LinearLayoutresumepart7,LinearLayoutresumepart8,
    LinearLayoutresumepart9,LinearLayoutresumepart10,LinearLayoutresumepart11
    ,LinearLayoutresumepart12, LinearLayoutresumepart13,LinearLayoutresumepart14;
    ImageView resumeProfilePic;


    CheckBox chkPromo,chkInsur,chkTrainingCourse,chkTransporting,chkFood,chkFlexiableHours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_resume);
        setViews();
        setProesList();
        setLangList();
        setJobsList();
        setEducationList();
        setProesList();
        setProvinceList();
        setJobcatList();
        setTermsList();
        setSkillList();


        listViewProes.setAdapter(listAdapterProes);
        listViewLang.setAdapter(listAdapterLang);
        listViewJobs.setAdapter(listAdapterJobs);
        listViewEducation.setAdapter(listAdapterEducation);
        listViewProvince.setAdapter(listAdapterProvince);
        listViewJobcat.setAdapter(listAdapterJobcat);
        listViewTerms.setAdapter(listAdapterTerms);
        listViewSkill.setAdapter(listAdapterSkill);


        justifyListViewHeightBasedOnChildren(listViewProes);
        justifyListViewHeightBasedOnChildren(listViewJobs);
        justifyListViewHeightBasedOnChildren(listViewEducation);
        justifyListViewHeightBasedOnChildren(listViewLang);
        justifyListViewHeightBasedOnChildren(listViewProvince);
        justifyListViewHeightBasedOnChildren(listViewJobcat);
        justifyListViewHeightBasedOnChildren(listViewTerms);
        justifyListViewHeightBasedOnChildren(listViewSkill);

        btnAddProes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddProes(listViewProes);
            }
        });
        btnAddJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddJobs(listViewJobs);
            }
        });
        btnAddEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddEducation(listViewEducation);
            }
        });
        btnAddLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddLang(listViewLang);
            }
        });

        btnAddProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddProvince(listViewProvince);
            }
        });

        btnAddJobcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddJobcat(listViewJobcat);
            }
        });

        btnAddTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddTerms(listViewTerms);
            }
        });

        btnAddSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddSkill(listViewSkill);
            }
        });

        btnEditSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEditSalary(txtSalary.getText().toString());
            }
        });
        btnSaveAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEditAbout(txtAboutMe.getText().toString());
            }
        });

        updateView(updateLanguage());
    }
    //****Setting the Views****//
    private void setViews(){
        //ListViews
        listViewProes = (ListView) findViewById(R.id.listViewProes);
        listViewJobs = (ListView) findViewById(R.id.listViewJobs);
        listViewEducation = (ListView) findViewById(R.id.listViewEducation);
        listViewLang = (ListView) findViewById(R.id.listViewLang);
        listViewProvince = (ListView) findViewById(R.id.listViewProvince);
        listViewJobcat = (ListView) findViewById(R.id.listViewJobcat);
        listViewTerms = (ListView) findViewById(R.id.listViewTerms);
        listViewSkill = (ListView) findViewById(R.id.listViewSkill);

        //checkbox
        chkPromo = (CheckBox) findViewById(R.id.chkPromo);
        chkInsur = (CheckBox) findViewById(R.id.chkInsur);
        chkTrainingCourse = (CheckBox) findViewById(R.id.chkTrainingCourse);
        chkTransporting = (CheckBox) findViewById(R.id.chkTransporting);
        chkFood = (CheckBox) findViewById(R.id.chkFood);
        chkFlexiableHours = (CheckBox) findViewById(R.id.chkFlexiableHours);

        //Buttons
        btnAddProes = (ImageButton) findViewById(R.id.btnAddProes);
        btnAddJobs = (ImageButton) findViewById(R.id.btnAddJobs);
        btnAddEducation= (ImageButton) findViewById(R.id.btnAddEducation);
        btnAddLang= (ImageButton) findViewById(R.id.btnAddLang);
        btnAddProvince = (ImageButton) findViewById(R.id.btnAddProvince);
        btnAddJobcat = (ImageButton) findViewById(R.id.btnAddJobcat);
        btnAddTerms = (ImageButton) findViewById(R.id.btnAddTerms);
        btnAddSkill = (ImageButton) findViewById(R.id.btnAddSkill);
        btnEditSalary = (ImageButton) findViewById(R.id.btnEditSalary);
        btnSaveBenfits = (Button) findViewById(R.id.btnSaveBenfits);
        btnSaveAboutMe = (Button) findViewById(R.id.btnSaveAboutMe);

        txtSalary = (TextView) findViewById(R.id.txtSalary);
        txtAboutMe = (TextView) findViewById(R.id.txtAboutMe);


        //new
        txtJobTitle= (TextView) findViewById(R.id.txtJobTitle);
        txtJobTitle2= (TextView) findViewById(R.id.txtJobTitle2);
        txtUnderJobTitle= (TextView) findViewById(R.id.txtUnderJobTitle);
        txtUnderJobTitle2= (TextView) findViewById(R.id.txtUnderJobTitle2);
        txtLastDegree= (TextView) findViewById(R.id.txtLastDegree);
        txtLastDegreeTitle= (TextView) findViewById(R.id.txtLastDegreeTitle);
        txtEdit= (TextView) findViewById(R.id.txtEdit);
        txtPrivateInfo= (TextView) findViewById(R.id.txtPrivateInfo);
        txtEmailAddress= (TextView) findViewById(R.id.txtEmailAddress);
        txtEmailAddressTitle= (TextView) findViewById(R.id.txtEmailAddressTitle);
        txtPhone= (TextView) findViewById(R.id.txtPhone);
        txtPhoneTitle= (TextView) findViewById(R.id.txtPhoneTitle);
        txtProvinceResume= (TextView) findViewById(R.id.txtProvinceResume);
        txtProvinceResumeTitle= (TextView) findViewById(R.id.txtProvinceResumeTitle);
        txtMarriageResume= (TextView) findViewById(R.id.txtMarriageResume);
        txtMarriageResumeTitle= (TextView) findViewById(R.id.txtMarriageResumeTitle);
        txtBirthYearResume= (TextView) findViewById(R.id.txtBirthYearResume);
        txtBirthYearResumeTitle= (TextView) findViewById(R.id.txtBirthYearResumeTitle);
        txtDutyResume= (TextView) findViewById(R.id.txtDutyResume);
        txtDutyResumeTitle= (TextView) findViewById(R.id.txtDutyResumeTitle);
        txtAddressResume= (TextView) findViewById(R.id.txtAddressResume);
        txtAddressResumeTitle= (TextView) findViewById(R.id.txtAddressResumeTitle);
        txtTalents= (TextView) findViewById(R.id.txtTalents);
        txtJobExp= (TextView) findViewById(R.id.txtJobExp);
        txtGraduateExp= (TextView) findViewById(R.id.txtGraduateExp);
        txtLanguages= (TextView) findViewById(R.id.txtLanguages);
        txtJobFavorites= (TextView) findViewById(R.id.txtJobFavorites);
        txtSelectedProvinces= (TextView) findViewById(R.id.txtSelectedProvinces);
        txtJobCategory= (TextView) findViewById(R.id.txtJobCategory);
        txtAcceptedContract= (TextView) findViewById(R.id.txtAcceptedContract);
        txtActivityLevel= (TextView) findViewById(R.id.txtActivityLevel);
        txtActualRights= (TextView) findViewById(R.id.txtActualRights);
        txtJobBenefits= (TextView) findViewById(R.id.txtJobBenefits);
        txtAboutMe2= (TextView) findViewById(R.id.txtAboutMe2);
        txtGenderResumeTitle= (TextView) findViewById(R.id.txtGenderResumeTitle);
        txtGenderResume= (TextView) findViewById(R.id.txtGenderResume);

        //LinearLayout
        LinearLayoutMakeResume = (LinearLayout) findViewById(R.id.LinearLayoutMakeResume);
        LinearLayoutresume1 = (LinearLayout) findViewById(R.id.LinearLayoutresume1);
        LinearLayoutresume2 = (LinearLayout) findViewById(R.id.LinearLayoutresume2);

        //LinearLayoutparts
        LinearLayoutresumepart1 =(LinearLayout) findViewById(R.id.LinearLayoutresumepart1);
        LinearLayoutresumepart2 =(LinearLayout) findViewById(R.id.LinearLayoutresumepart2);
        LinearLayoutresumepart3 =(LinearLayout) findViewById(R.id.LinearLayoutresumepart3);
        LinearLayoutresumepart4 =(LinearLayout) findViewById(R.id.LinearLayoutresumepart4);
        LinearLayoutresumepart4 =(LinearLayout) findViewById(R.id.LinearLayoutresumepart4);
        LinearLayoutresumepart5 =(LinearLayout) findViewById(R.id.LinearLayoutresumepart5);
        LinearLayoutresumepart6 =(LinearLayout) findViewById(R.id.LinearLayoutresumepart6);
        LinearLayoutresumepart7=(LinearLayout) findViewById(R.id.LinearLayoutresumepart7);
        LinearLayoutresumepart8=(LinearLayout) findViewById(R.id.LinearLayoutresumepart8);
        LinearLayoutresumepart9=(LinearLayout) findViewById(R.id.LinearLayoutresumepart9);
        LinearLayoutresumepart10=(LinearLayout) findViewById(R.id.LinearLayoutresumepart10);
        LinearLayoutresumepart11=(LinearLayout) findViewById(R.id.LinearLayoutresumepart11);
        LinearLayoutresumepart12=(LinearLayout) findViewById(R.id.LinearLayoutresumepart12);
        LinearLayoutresumepart13=(LinearLayout) findViewById(R.id.LinearLayoutresumepart13);
        LinearLayoutresumepart14=(LinearLayout) findViewById(R.id.LinearLayoutresumepart14);


        //Image View
        resumeProfilePic = (ImageView) findViewById(R.id.resumeProfilePic);

        //RelativeLayoutresume1

    }

    //****Setting the List Items****//
    private void setProesList(){

        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        // Create a new instance of Gson
        int[] numbers = {1, 1, 2, 3, 5, 8, 13};
        Gson gson = new Gson();

        // Convert numbers array into JSON string.
        String numbersJson = gson.toJson(numbers);
        String daysJson = gson.toJson(days);
       // numbersJson = [1,1,2,3,5,8,13]
       // daysJson = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"]


        listProes = new ArrayList<>();
        listProes.add("طراحی سایت");
        listProes.add("طراحی سایت");
        listProes.add("طراحی سایت");
        listProes.add("طراحی سایت");
        listProes.add("طراحی سایت");
        listAdapterProes = new ListAdapterProes(listProes,getApplicationContext(),MakeResume.this,listViewProes);

        ///new
        JSONArray jsonAraay = new JSONArray(listProes);
        String ja= gson.toJson(jsonAraay);
    }
    private void setLangList(){
        listLang = new ArrayList<>();
        Lang lang = new Lang("نگلیسی","متوسط");
        listLang.add(lang);
        listLang.add(lang);

        listAdapterLang = new ListAdapterLang(listLang,getApplicationContext(),MakeResume.this,listViewLang);

    }
    private void setJobsList(){
        listJobs = new ArrayList<>();
        Job job = new Job("۱۳۹۰","۱۳۹۶","مدیر شرکت","نما اندیشان");
        listJobs.add(job);
        Job job2 = new Job("۱۳۹۰","۱۳۹۶","مدیر شرکت","نما اندیشان");
        listJobs.add(job2);
        job = new Job("۱۳۹۰","۱۳۹۶","مدیر شرکت","نما اندیشان");
        listJobs.add(job);
        job = new Job("۱۳۹۰","۱۳۹۶","مدیر شرکت","نما اندیشان");
        listJobs.add(job);
        listAdapterJobs = new ListAdapterJobs(listJobs,getApplicationContext(),MakeResume.this,listViewJobs);
    }
    private void setEducationList(){
        listEducation = new ArrayList<>();
        Education edu = new Education("مهندس نرم‌افزار","دانشگاه زنجان","۱۳۹۲","۱۳۹۶");
        listEducation.add(edu);
        listEducation.add(edu);
        listEducation.add(edu);
        listEducation.add(edu);
        listAdapterEducation = new ListAdapterEducation(listEducation,getApplicationContext(),MakeResume.this,listViewEducation);
    }

    private void setProvinceList(){
        listProvince = new ArrayList<>();
        listProvince.add("زنگان");
        listProvince.add("زنگان1");
        listProvince.add("زنگان2");

        listAdapterProvince = new ListAdapterProvince(listProvince,getApplicationContext(),MakeResume.this,listViewProvince);

    }

    private void setJobcatList(){
        listJobcat = new ArrayList<>();
        listJobcat.add("دسته");
        listJobcat.add("دسته1");
        listJobcat.add("دسته2");

        listAdapterJobcat = new ListAdapterProvince(listJobcat,getApplicationContext(),MakeResume.this,listViewJobcat);

    }

    private void setTermsList(){
        listTerms = new ArrayList<>();
        listTerms.add("تماموقت۱");
        listTerms.add("تماموقت۲");
        listTerms.add("تماموقت۳");

        listAdapterTerms = new ListAdapterTerms(listTerms,getApplicationContext(),MakeResume.this,listViewTerms);

    }

    private void setSkillList(){
        listSkill = new ArrayList<>();
        listSkill.add("مدیر");
        listSkill.add("مدیر۱");

        listAdapterSkill = new ListAdapterTerms(listSkill,getApplicationContext(),MakeResume.this,listViewSkill);

    }

    //****Show the Add Dialogs****//
    private void showDialogAddProes(final ListView listView){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_proes);
        final EditText txtEdit = dialog.findViewById(R.id.txtEditDialogPro);
        ImageButton btnEdit = dialog.findViewById(R.id.btnEditDialogPro);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProes.add(txtEdit.getText().toString());
                listAdapterProes.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listView);
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    private void showDialogAddJobs(final ListView listView){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_jobs);
        final EditText txtEditRole = dialog.findViewById(R.id.txtEditDialogJobRole);
        final EditText txtEditCompany = dialog.findViewById(R.id.txtEditDialogJobCompany);
        final Spinner txtEditFrom = dialog.findViewById(R.id.spinnerFromJob);
        final Spinner txtEditTo = dialog.findViewById(R.id.spinnerToJob);

        ImageButton btnEdit = dialog.findViewById(R.id.btnEditDialogJob);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Job job = new Job(txtEditFrom.getSelectedItem().toString(),txtEditTo.getSelectedItem().toString(),txtEditRole.getText().toString(),txtEditCompany.getText().toString());
                listJobs.add(job);
                listAdapterJobs.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listView);
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    private void showDialogAddLang(final ListView listView){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_lang);
        final EditText txtEdit = dialog.findViewById(R.id.txtEditDialogLang);
        final Spinner spinnerLevel = dialog.findViewById(R.id.spinnerLangLevel);

        ImageButton btnEdit = dialog.findViewById(R.id.btnEditDialogLang);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lang lang = new Lang(txtEdit.getText().toString(),spinnerLevel.getSelectedItem().toString());
                listLang.add(lang);
                listAdapterLang.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listView);
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    private void showDialogAddEducation(final ListView listView){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_education);
        final EditText txtEditField = dialog.findViewById(R.id.txtEditDialogEducationField);
        final EditText txtEditPlace = dialog.findViewById(R.id.txtEditDialogEducationPlace);
        final Spinner txtEditFrom = dialog.findViewById(R.id.spinnerFromEducation);
        final Spinner txtEditTo = dialog.findViewById(R.id.spinnerToEducation);

        ImageButton btnEdit = dialog.findViewById(R.id.btnEditDialogEducation);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Education edu = new Education(txtEditField.getText().toString(),txtEditPlace.getText().toString(),txtEditFrom.getSelectedItem().toString(),txtEditTo.getSelectedItem().toString());
                listEducation.add(edu);
                listAdapterEducation.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listView);
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public static void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }



    private void showDialogAddJobcat(final ListView listView){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_jobcat);
        final TextView txtJobcat = dialog.findViewById(R.id.txtEditDialogJobcat);
        final ImageButton txtedit = dialog.findViewById(R.id.btnEditDialogJobcat);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;


        txtedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jobcat = txtJobcat.getText().toString();
                listJobcat.add(jobcat);
                listAdapterJobcat.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listView);
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showDialogAddProvince(final ListView listView){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_provice);
        final Spinner spinnerProvince = dialog.findViewById(R.id.spinnerProvince);
        final ImageButton txtedit = dialog.findViewById(R.id.btnEditDialogProvince);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;


        txtedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String province = spinnerProvince.getSelectedItem().toString();
                listProvince.add(province);
                listAdapterProvince.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listView);
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    private void showDialogAddTerms(final ListView listView){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_terms);
        final Spinner spinnerTerms = dialog.findViewById(R.id.spinnerTerms);
        final ImageButton txtedit = dialog.findViewById(R.id.btnEditDialogTerms);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;


        txtedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean exists=false;

                String Terms = spinnerTerms.getSelectedItem().toString();

                for (int i=0 ;i<listTerms.size();i++){
                    if (Terms.equalsIgnoreCase(listTerms.get(i))){
                        exists=true;
                        break;
                    }
                }
                if(!exists) {
                    listTerms.add(Terms);
                    listAdapterTerms.notifyDataSetChanged();
                    justifyListViewHeightBasedOnChildren(listView);
                }
                    dialog.dismiss();

            }
        });

        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    private void showDialogAddSkill(final ListView listView){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_skill);
        final EditText txtEdit = dialog.findViewById(R.id.txtEditDialogskill);

        ImageButton btnEdit = dialog.findViewById(R.id.btnEditDialogSkill);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String skill = txtEdit.getText().toString();
                listSkill.add(skill);
                listAdapterSkill.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listView);
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showDialogEditSalary(String oldSalary){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_salary);
        final EditText txtEditDialogSalary = dialog.findViewById(R.id.txtEditDialogSalary);
        final ImageButton txtedit = dialog.findViewById(R.id.btnEditDialogSalary);
        txtEditDialogSalary.setText(oldSalary);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;


        txtedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSalary.setText(txtEditDialogSalary.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    private void showDialogEditAbout(String oldAbout){
        final Dialog dialog = new Dialog(MakeResume.this);
        dialog.setContentView(R.layout.list_dialog_about);
        final EditText txtEditDialogAbout = dialog.findViewById(R.id.txtEditDialogAbout);
        final ImageButton txtedit = dialog.findViewById(R.id.btnEditDialogAbout);
        txtEditDialogAbout.setText(oldAbout);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;


        txtedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSalary.setText(txtEditDialogAbout.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout((13 * width)/14, ViewGroup.LayoutParams.WRAP_CONTENT);
    }



    //new
    private String updateLanguage(){
        //Default language is fa
        String language = Paper.book().read("language");
        if(language==null)
            Paper.book().write("language", "fa");
        return language;
    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(getApplicationContext(), language);
        Resources resources = context.getResources();

        //CHECK ANDROID VERSION
        //condition to check language
        if(Paper.book().read("language").equals("fa")) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                LinearLayoutMakeResume.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

                LinearLayoutresume1.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutresume1.setGravity(Gravity.RIGHT);

                LinearLayoutresume2.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutresume2.setGravity(Gravity.RIGHT);

                LinearLayoutresumepart12.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutresume2.setGravity(Gravity.RIGHT);

                chkFlexiableHours.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                chkFood.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                chkInsur.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                chkPromo.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                chkTrainingCourse.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                chkTransporting.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

                ///////////////////////////////////RelativeLayout1//////////////////////////////////////
                //resumeProfilePic
                RelativeLayout.LayoutParams resumeProfilePicparams = (RelativeLayout.LayoutParams)resumeProfilePic.getLayoutParams();
                resumeProfilePicparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                resumeProfilePic.setLayoutParams(resumeProfilePicparams);

                //LinearLayoutresume1
                RelativeLayout.LayoutParams LinearLayoutresume1params = (RelativeLayout.LayoutParams)LinearLayoutresume1.getLayoutParams();
                LinearLayoutresume1params.addRule(RelativeLayout.LEFT_OF, R.id.resumeProfilePic);
                LinearLayoutresume1params.setMargins(1,0,10,0);
                LinearLayoutresume1.setLayoutParams(LinearLayoutresume1params);



                ///////////////////////////////////LinearLayoutresumeParts//////////////////////////////////////
                LinearLayout.LayoutParams LinearLayoutresumepartparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayoutresumepartparams.gravity=Gravity.RIGHT;
                LinearLayoutresumepart1.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart2.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart3.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart4.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart5.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart6.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart7.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart8.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart9.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart10.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart11.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart12.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart13.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart14.setLayoutParams(LinearLayoutresumepartparams);

                Set_Texts("fa",resources,17);
            }
            //LANGUAGE IS FA
            //ELSE IF API IS UNDER 17
            else{
                LinearLayoutresume1.setGravity(Gravity.RIGHT);
                LinearLayoutresume2.setGravity(Gravity.RIGHT);


                chkFlexiableHours.setGravity(Gravity.RIGHT);
                chkFood.setGravity(Gravity.RIGHT);
                chkInsur.setGravity(Gravity.RIGHT);
                chkPromo.setGravity(Gravity.RIGHT);
                chkTrainingCourse.setGravity(Gravity.RIGHT);
                chkTransporting.setGravity(Gravity.RIGHT);
                ///////////////////////////////////RelativeLayout1//////////////////////////////////////
                //resumeProfilePic
                RelativeLayout.LayoutParams resumeProfilePicparams = (RelativeLayout.LayoutParams)resumeProfilePic.getLayoutParams();
                resumeProfilePicparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                resumeProfilePic.setLayoutParams(resumeProfilePicparams);

                //LinearLayoutresume1
                RelativeLayout.LayoutParams LinearLayoutresume1params = (RelativeLayout.LayoutParams)LinearLayoutresume1.getLayoutParams();
                LinearLayoutresume1params.addRule(RelativeLayout.LEFT_OF, R.id.resumeProfilePic);
                LinearLayoutresume1params.setMargins(1,0,10,0);
                LinearLayoutresume1.setLayoutParams(LinearLayoutresume1params);



                ///////////////////////////////////LinearLayoutresumeParts//////////////////////////////////////
                LinearLayout.LayoutParams LinearLayoutresumepartparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayoutresumepartparams.gravity=Gravity.RIGHT;
                LinearLayoutresumepart1.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart2.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart3.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart4.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart5.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart6.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart7.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart8.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart9.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart10.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart11.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart12.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart13.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart14.setLayoutParams(LinearLayoutresumepartparams);
                Set_Texts("fa",resources,16);
            }


        }
        //ENGLISH LANGUAGE
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                LinearLayoutMakeResume.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

                LinearLayoutresume1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutresume1.setGravity(Gravity.LEFT);

                LinearLayoutresume2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                LinearLayoutresume2.setGravity(Gravity.LEFT);

                LinearLayoutresumepart12.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                LinearLayoutresume2.setGravity(Gravity.LEFT);


                chkFlexiableHours.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                chkFood.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                chkInsur.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                chkPromo.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                chkTrainingCourse.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                chkTransporting.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                ///////////////////////////////////RelativeLayout1//////////////////////////////////////
                //resumeProfilePic
                RelativeLayout.LayoutParams resumeProfilePicparams = new RelativeLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.resumeProfilePic), getResources().getDimensionPixelSize(R.dimen.resumeProfilePic));
                resumeProfilePicparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                resumeProfilePic.setLayoutParams(resumeProfilePicparams);

                //LinearLayoutresume1
                RelativeLayout.LayoutParams LinearLayoutresume1params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayoutresume1params.addRule(RelativeLayout.RIGHT_OF, R.id.resumeProfilePic);
                LinearLayoutresume1params.setMargins(10, 0, 1, 0);
                LinearLayoutresume1.setLayoutParams(LinearLayoutresume1params);



                ///////////////////////////////////LinearLayoutresumeParts//////////////////////////////////////
                LinearLayout.LayoutParams LinearLayoutresumepartparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayoutresumepartparams.gravity = Gravity.LEFT;
                LinearLayoutresumepart1.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart2.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart3.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart4.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart5.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart6.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart7.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart8.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart9.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart10.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart11.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart12.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart13.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart14.setLayoutParams(LinearLayoutresumepartparams);

                Set_Texts("en",resources,17);
            }
            //LANGUAGE IS EN
            //LANGUAGE IS API<17
            else {
                LinearLayoutresume1.setGravity(Gravity.LEFT);
                LinearLayoutresume2.setGravity(Gravity.LEFT);

                chkFlexiableHours.setGravity(Gravity.LEFT);
                chkFood.setGravity(Gravity.LEFT);
                chkInsur.setGravity(Gravity.LEFT);
                chkPromo.setGravity(Gravity.LEFT);
                chkTrainingCourse.setGravity(Gravity.LEFT);
                chkTransporting.setGravity(Gravity.LEFT);
                ///////////////////////////////////RelativeLayout1//////////////////////////////////////
                //resumeProfilePic
                RelativeLayout.LayoutParams resumeProfilePicparams = new RelativeLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.resumeProfilePic), getResources().getDimensionPixelSize(R.dimen.resumeProfilePic));
                resumeProfilePicparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                resumeProfilePic.setLayoutParams(resumeProfilePicparams);

                //LinearLayoutresume1
                RelativeLayout.LayoutParams LinearLayoutresume1params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayoutresume1params.addRule(RelativeLayout.RIGHT_OF, R.id.resumeProfilePic);
                LinearLayoutresume1params.setMargins(10, 0, 1, 0);
                LinearLayoutresume1.setLayoutParams(LinearLayoutresume1params);



                ///////////////////////////////////LinearLayoutresumeParts//////////////////////////////////////
                LinearLayout.LayoutParams LinearLayoutresumepartparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayoutresumepartparams.gravity = Gravity.LEFT;
                LinearLayoutresumepart1.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart2.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart3.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart4.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart5.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart6.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart7.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart8.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart9.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart10.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart11.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart12.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart13.setLayoutParams(LinearLayoutresumepartparams);
                LinearLayoutresumepart14.setLayoutParams(LinearLayoutresumepartparams);

                Set_Texts("en",resources,16);
            }

        }



    }


public void Set_Texts(String Language, Resources resources, int api){

    chkPromo.setText(resources.getString(R.string.chkPromo));
    chkInsur.setText(resources.getString(R.string.chkInsur));
    chkTrainingCourse.setText(resources.getString(R.string.chkTrainingCourse));
    chkTransporting.setText(resources.getString(R.string.chkTransporting));
    chkFood.setText(resources.getString(R.string.chkFood));
    chkFlexiableHours.setText(resources.getString(R.string.chkFlexiableHours));
    //Buttons
    btnSaveBenfits.setText(resources.getString(R.string.SaveBenfits));
    btnSaveAboutMe.setText(resources.getString(R.string.AboutMe));
    txtSalary.setText(resources.getString(R.string.Salary));
    txtAboutMe.setText(resources.getString(R.string.AboutMe));
    //new
    if(api==17  || Language.equals("fa")){
        txtJobTitle.setText(resources.getString(R.string.JobTitle));
        txtJobTitle2.setText(resources.getString(R.string.JobTitle2));

        txtUnderJobTitle.setText(resources.getString(R.string.UnderJobTitle));
        txtUnderJobTitle2 .setText(resources.getString(R.string.UnderJobTitle2));

        txtLastDegree.setText(resources.getString(R.string.LastDegree));
        txtLastDegreeTitle.setText(resources.getString(R.string.LastDegreeTitle));

        txtEmailAddress.setText(resources.getString(R.string.EmailAddress));
        txtEmailAddressTitle.setText(resources.getString(R.string.EmailAddressTitle));

        txtPhone.setText(resources.getString(R.string.Phone));
        txtPhoneTitle.setText(resources.getString(R.string.PhoneTitle));

        txtProvinceResume.setText(resources.getString(R.string.ProvinceResume));
        txtProvinceResumeTitle.setText(resources.getString(R.string.ProvinceResumeTitle));

        txtMarriageResume.setText(resources.getString(R.string.MarriageResume));
        txtMarriageResumeTitle.setText(resources.getString(R.string.MarriageResumeTitle));


        txtBirthYearResume.setText(resources.getString(R.string.BirthYearResume));
        txtBirthYearResumeTitle.setText(resources.getString(R.string.BirthYearResumeTitle));

        txtDutyResume.setText(resources.getString(R.string.DutyResume));
        txtDutyResumeTitle.setText(resources.getString(R.string.DutyResumeTitle));

        txtAddressResume.setText(resources.getString(R.string.AddressResume));
        txtAddressResumeTitle.setText(resources.getString(R.string.AddressResumeTitle));
    }
    else{
        txtJobTitle.setText(resources.getString(R.string.JobTitle2));
        txtJobTitle2.setText(resources.getString(R.string.JobTitle));

        txtUnderJobTitle.setText(resources.getString(R.string.UnderJobTitle2));
        txtUnderJobTitle2 .setText(resources.getString(R.string.UnderJobTitle));

        txtLastDegree.setText(resources.getString(R.string.LastDegreeTitle));
        txtLastDegreeTitle.setText(resources.getString(R.string.LastDegree));

        txtEmailAddress.setText(resources.getString(R.string.EmailAddressTitle));
        txtEmailAddressTitle.setText(resources.getString(R.string.EmailAddress));

        txtPhone.setText(resources.getString(R.string.PhoneTitle));
        txtPhoneTitle.setText(resources.getString(R.string.Phone));

        txtProvinceResume.setText(resources.getString(R.string.ProvinceResumeTitle));
        txtProvinceResumeTitle.setText(resources.getString(R.string.ProvinceResume));

        txtMarriageResume.setText(resources.getString(R.string.MarriageResumeTitle));
        txtMarriageResumeTitle.setText(resources.getString(R.string.MarriageResume));

        txtBirthYearResume.setText(resources.getString(R.string.BirthYearResumeTitle));
        txtBirthYearResumeTitle.setText(resources.getString(R.string.BirthYearResume));

        txtDutyResume.setText(resources.getString(R.string.DutyResumeTitle));
        txtDutyResumeTitle.setText(resources.getString(R.string.DutyResume));

        txtAddressResume.setText(resources.getString(R.string.AddressResumeTitle));
        txtAddressResumeTitle.setText(resources.getString(R.string.AddressResume));
    }


    txtEdit.setText(resources.getString(R.string.Edit));
    txtPrivateInfo.setText(resources.getString(R.string.PrivateInfo));
    txtTalents.setText(resources.getString(R.string.Talents));
    txtJobExp.setText(resources.getString(R.string.JobExp));
    txtGraduateExp.setText(resources.getString(R.string.GraduateExp));
    txtLanguages.setText(resources.getString(R.string.Languages));
    txtJobFavorites.setText(resources.getString(R.string.JobFavorites));
    txtSelectedProvinces.setText(resources.getString(R.string.SelectedProvinces));
    txtJobCategory.setText(resources.getString(R.string.JobCategory));
    txtAcceptedContract.setText(resources.getString(R.string.AcceptedContract));
    txtActivityLevel.setText(resources.getString(R.string.ActivityLevel));
    txtActualRights.setText(resources.getString(R.string.ActualRights));
    txtSalary.setText(resources.getString(R.string.Salary));
    txtJobBenefits.setText(resources.getString(R.string.JobBenefits));
    txtAboutMe2.setText(resources.getString(R.string.AboutMe2));
    txtGenderResumeTitle.setText(resources.getString(R.string.GenderResumeTitle));
    txtGenderResume.setText(resources.getString(R.string.GenderResume));
}


}
