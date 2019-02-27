package com.example.android.taskone;

import android.os.Parcel;
import android.os.Parcelable;

public class SignUpTextClass implements Parcelable {
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

    public String getmFullnameString() {
        return mFullnameString;
    }

    public void setmFullnameString(String mFullnameString) {
        this.mFullnameString = mFullnameString;
    }

    public String getmEmailString() {
        return mEmailString;
    }

    public void setmEmailString(String mEmailString) {
        this.mEmailString = mEmailString;
    }

    public String getmMobileString() {
        return mMobileString;
    }

    public void setmMobileString(String mMobileString) {
        this.mMobileString = mMobileString;
    }

    public String getmHospitalNameString() {
        return mHospitalNameString;
    }

    public void setmHospitalNameString(String mHospitalNameString) {
        this.mHospitalNameString = mHospitalNameString;
    }

    public String getmUniversityString() {
        return mUniversityString;
    }

    public void setmUniversityString(String mUniversityString) {
        this.mUniversityString = mUniversityString;
    }

    public String getmClinicAdressString() {
        return mClinicAdressString;
    }

    public void setmClinicAdressString(String mClinicAdressString) {
        this.mClinicAdressString = mClinicAdressString;
    }

    public String getmBirthdateString() {
        return mBirthdateString;
    }

    public void setmBirthdateString(String mBirthdateString) {
        this.mBirthdateString = mBirthdateString;
    }

    public String getmGovernmentString() {
        return mGovernmentString;
    }

    public void setmGovernmentString(String mGovernmentString) {
        this.mGovernmentString = mGovernmentString;
    }

    public String getmPasswordString() {
        return mPasswordString;
    }

    public void setmPasswordString(String mPasswordString) {
        this.mPasswordString = mPasswordString;
    }

    public String getmConfirmPasswordString() {
        return mConfirmPasswordString;
    }

    public void setmConfirmPasswordString(String mConfirmPasswordString) {
        this.mConfirmPasswordString = mConfirmPasswordString;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public SignUpTextClass(String mFullnameString, String mEmailString, String mMobileString, String mHospitalNameString,
                           String mUniversityString, String mClinicAdressString,
                           String mBirthdateString, String mGovernmentString,
                           String mPasswordString, String mConfirmPasswordString, String mGender) {
        this.mFullnameString = mFullnameString;
        this.mEmailString = mEmailString;
        this.mMobileString = mMobileString;
        this.mHospitalNameString = mHospitalNameString;
        this.mUniversityString = mUniversityString;
        this.mClinicAdressString = mClinicAdressString;
        this.mBirthdateString = mBirthdateString;
        this.mGovernmentString = mGovernmentString;
        this.mPasswordString = mPasswordString;
        this.mConfirmPasswordString = mConfirmPasswordString;
        this.mGender = mGender;
    }

    public SignUpTextClass(Parcel in) {
        mFullnameString = in.readString();
        mEmailString  = in.readString();
        mMobileString = in.readString();
        mHospitalNameString = in.readString();
        mUniversityString = in.readString();
        mClinicAdressString = in.readString();
        mBirthdateString = in.readString();
        mGovernmentString = in.readString();
        mPasswordString = in.readString();
        mConfirmPasswordString = in.readString();
        mGender = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFullnameString);
        dest.writeString(mEmailString);
        dest.writeString(mMobileString);
        dest.writeString(mHospitalNameString);
        dest.writeString(mUniversityString);
        dest.writeString(mClinicAdressString);
        dest.writeString(mBirthdateString);
        dest.writeString(mGovernmentString);
        dest.writeString(mPasswordString);
        dest.writeString(mConfirmPasswordString);
        dest.writeString(mGender);
    }

    public final static Parcelable.Creator<SignUpTextClass> CREATOR = new Creator<SignUpTextClass>() {


        @Override
        public SignUpTextClass createFromParcel(Parcel source) {
            return new SignUpTextClass(source);
        }

        @Override
        public SignUpTextClass[] newArray(int size) {
            return new SignUpTextClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
