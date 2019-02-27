package com.example.android.taskone;

import android.os.Parcel;
import android.os.Parcelable;

public class FacebookUserData implements Parcelable {
    private String photoUrl;
    private String idToken;
    private String social_type;

    public FacebookUserData(Parcel in) {
        photoUrl = in.readString();
        idToken = in.readString();
        social_type = in.readString();
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getSocial_type() {
        return social_type;
    }

    public void setSocial_type(String social_type) {
        this.social_type = social_type;
    }

    public FacebookUserData(String photoUrl, String idToken, String social_type) {
        this.photoUrl = photoUrl;
        this.idToken = idToken;
        this.social_type = social_type;
    }

    public final static Parcelable.Creator<FacebookUserData> CREATOR = new Creator<FacebookUserData>() {


        @Override
        public FacebookUserData createFromParcel(Parcel source) {
            return new FacebookUserData(source);
        }

        @Override
        public FacebookUserData[] newArray(int size) {
            return new FacebookUserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photoUrl);
        dest.writeString(idToken);
        dest.writeString(social_type);
        }
}
