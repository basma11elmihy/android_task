package com.example.android.taskone;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SignUp extends AppCompatActivity {
    private EditText mFullname;
    private EditText mEmail;
    private EditText mMobile;
    private EditText mHospitalName;
    private EditText mUniversity;
    private EditText mClinicAddress;
    private EditText mBirthdate;
    private EditText mGovermint;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private RadioGroup mRadioGroup;
    private RadioButton mMale;
    private RadioButton mFemale;

    private String mFullnameString;
    private String mEmailString;
    private String mMobileString;
    private String mHospitalNameString;
    private String mUniversityString;
    private String mClinicAdressString;
    private String mBirthdateString;
    private String mGovernmentString;
    private String mPasswordString;
    private String mConfirmPasswordString;
    private String mGender;

    private SignUpTextClass signUpTextClass;

    private String photoUrl;
    private String idToken;
    private String social_type;
    private FacebookUserData userData = null;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setToolBar("Sign up",true);
        mFullname = findViewById(R.id.full_name);
        mEmail = findViewById(R.id.email);
        mMobile = findViewById(R.id.mobile_number);
        mHospitalName = findViewById(R.id.hospital_name);
        mUniversity = findViewById(R.id.university);
        mClinicAddress = findViewById(R.id.clinic_address);
        mBirthdate = findViewById(R.id.birth_date);
        mGovermint = findViewById(R.id.government);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirm_password);
        mMale = findViewById(R.id.male);
        mFemale = findViewById(R.id.female);

        mRadioGroup = findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mMale.getId())
                {
                    mGender = "male";
                }
                else if (checkedId == mFemale.getId()){
                    mGender = "female";
                }
            }
        });
        if (getIntent() != null && getIntent().getParcelableExtra("userData") != null) {
            userData = getIntent().getParcelableExtra("userData");
            idToken = userData.getIdToken();
            photoUrl = userData.getPhotoUrl();
            social_type = userData.getSocial_type();
        }

    }

    public void next(View view) {

        if (checkFields()) {
            Intent intent = new Intent(this, PhotosActivity.class);
            intent.putExtra("extra", signUpTextClass);
            if (userData != null) {
                intent.putExtra("userData", userData);
            }
            startActivity(intent);
        }
    }

    private void collectDataFromFields() {
            if (mFullname.getText() != null)
                mFullnameString = mFullname.getText().toString().trim();
            if (mEmail.getText() != null)
                mEmailString = mEmail.getText().toString().trim();
            if (mMobile.getText() != null)
                mMobileString = mMobile.getText().toString().trim();
            if (mHospitalName.getText() != null)
                mHospitalNameString = mHospitalName.getText().toString().trim();
            if (mUniversity.getText() != null)
                mUniversityString = mUniversity.getText().toString().trim();
            if (mClinicAddress.getText() != null)
                mClinicAdressString = mClinicAddress.getText().toString().trim();
            if (mBirthdate.getText() != null)
                mBirthdateString = mBirthdate.getText().toString().trim();
            if (mGovermint.getText() != null)
                mGovernmentString = mGovermint.getText().toString().trim();
            if (mPassword.getText() != null)
                mPasswordString = mPassword.getText().toString().trim();
            if (mConfirmPassword != null)
                mConfirmPasswordString = mConfirmPassword.getText().toString().trim();
            signUpTextClass = new SignUpTextClass(mFullnameString, mEmailString, mMobileString,
                    mHospitalNameString, mUniversityString, mClinicAdressString, mBirthdateString,
                    mGovernmentString, mPasswordString, mConfirmPasswordString, mGender);

    }

    private Boolean checkFields() {
        collectDataFromFields();
            if (TextUtils.isEmpty(mFullnameString)) {
                mFullname.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mEmailString)) {
                mEmail.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mMobileString)) {
                mMobile.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mHospitalNameString)) {
                mHospitalName.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mUniversityString)) {
                mUniversity.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mClinicAdressString)) {
                mClinicAddress.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mBirthdateString)) {
                mBirthdate.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mGovernmentString)) {
                mGovermint.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mPasswordString)) {
                mPassword.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mConfirmPasswordString)) {
                mConfirmPassword.setError(getString(R.string.error_field_required));
            }
            if (mRadioGroup.getCheckedRadioButtonId() == -1)
            {
                Toast.makeText(this,"Please select a gender",Toast.LENGTH_LONG).show();
            }
            if (!mPasswordString.equals(mConfirmPasswordString)){
                Toast.makeText(this,"Password and Confirm Password aren't matching",Toast.LENGTH_LONG).show();
            }
            else {
                return true;
            }

        return false;
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
        ImageView backArrow = findViewById(R.id.back_arrow);
        if (!b){
            backArrow.setVisibility(View.GONE);
        }
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(SignUp.this);
            }
        });
    }
}
