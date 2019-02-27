package com.example.android.taskone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Objects;

public class RegisteredData extends AppCompatActivity {
    private SignUpTextClass signUpTextClass;

    private TextView mFullname;
    private TextView mEmail;
    private TextView mMobile;
    private TextView mHospitalName;
    private TextView mUniversity;
    private TextView mClinicAddress;
    private TextView mBirthdate;
    private TextView mGovermint;
    private TextView mPassword;
    private TextView mGender;

    private String mFullnameString;
    private String mEmailString;
    private String mMobileString;
    private String mHospitalNameString;
    private String mUniversityString;
    private String mClinicAdressString;
    private String mBirthdateString;
    private String mGovernmentString;
    private String mPasswordString;
    private String mGenderString;

    private String mIdPic;
    private String mIdBack;
    private String mLicensePic;
    private String mLicenseBack;

    private byte[] Id;
    private byte[] IdBack;
    private byte[] Lic;
    private byte[] LicBack;

    private Bitmap IdBit;
    private Bitmap IdBackBit;
    private Bitmap LicBit;
    private Bitmap LicBackBit;

    private ImageView frontId;
    private ImageView backId;
    private ImageView frontLin;
    private ImageView backLin;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_data);
        setToolBar("Your Profile Data",false);

        signUpTextClass = getIntent().getParcelableExtra("extra");
        Id = getIntent().getByteArrayExtra("frontID");
        IdBack = getIntent().getByteArrayExtra("backID");
        Lic = getIntent().getByteArrayExtra("frontLin");
        LicBack = getIntent().getByteArrayExtra("backLin");

        IdBit = BitmapFactory.decodeByteArray(Id, 0, Id.length);
        IdBackBit = BitmapFactory.decodeByteArray(IdBack, 0, IdBack.length);
        LicBit = BitmapFactory.decodeByteArray(Lic, 0, Lic.length);
        LicBackBit = BitmapFactory.decodeByteArray(LicBack, 0, LicBack.length);

        frontId = findViewById(R.id.reg_front_id);
        backId = findViewById(R.id.reg_back_id);
        frontLin = findViewById(R.id.reg_front_lin);
        backLin = findViewById(R.id.reg_back_lin);

        frontId.setImageBitmap(IdBit);
        backId.setImageBitmap(IdBackBit);
        frontLin.setImageBitmap(LicBit);
        backLin.setImageBitmap(LicBackBit);


        mFullnameString = signUpTextClass.getmFullnameString().trim();
        mEmailString = signUpTextClass.getmEmailString().trim();
        mMobileString = signUpTextClass.getmMobileString().trim();
        mHospitalNameString = signUpTextClass.getmHospitalNameString().trim();
        mUniversityString = signUpTextClass.getmUniversityString().trim();
        mClinicAdressString = signUpTextClass.getmClinicAdressString().trim();
        mBirthdateString = signUpTextClass.getmBirthdateString().trim();
        mGovernmentString = signUpTextClass.getmGovernmentString().trim();
        mPasswordString = signUpTextClass.getmPasswordString().trim();
        mGenderString = signUpTextClass.getmGender().trim();

        mFullname = findViewById(R.id.reg_full_name);
        mFullname.setText(mFullnameString);

        mEmail = findViewById(R.id.reg_email);
        mEmail.setText(mEmailString);

        mMobile = findViewById(R.id.reg_mobile);
        mMobile.setText(mMobileString);

        mHospitalName = findViewById(R.id.reg_Hospital);
        mHospitalName.setText(mHospitalNameString);

        mUniversity = findViewById(R.id.reg_university);
        mUniversity.setText(mUniversityString);

        mClinicAddress = findViewById(R.id.reg_clinic);
        mClinicAddress.setText(mClinicAdressString);

        mBirthdate = findViewById(R.id.reg_birth_date);
        mBirthdate.setText(mBirthdateString);

        mGovermint = findViewById(R.id.reg_gover);
        mGovermint.setText(mGovernmentString);

        mPassword = findViewById(R.id.reg_pass);
        mPassword.setText(mPasswordString);

        mGender = findViewById(R.id.reg_gender);
        mGender.setText(mGenderString);





    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setToolBar(String text, boolean b) {
        Toolbar myToolbar = (Toolbar) this.findViewById(R.id.toolbar);

        TextView titleText = findViewById(R.id.titleToolbar);
        Toolbar.LayoutParams params = (Toolbar.LayoutParams) titleText.getLayoutParams();
        params.gravity = Gravity.CENTER;
        titleText.setLayoutParams(params);
        titleText.setText(text);

        setSupportActionBar(myToolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        if (!b){
            ImageView backArrow = findViewById(R.id.back_arrow);
            backArrow.setVisibility(View.GONE);
        }
    }
}
