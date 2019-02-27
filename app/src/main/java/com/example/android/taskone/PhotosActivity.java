package com.example.android.taskone;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PhotosActivity extends AppCompatActivity {
    private static String URL_REGISTER = "http://dermapedia.net/api/auth/register";
    private static String SOCIAL_URL_REGISTER = "http://dermapedia.net/api/auth/social/register";

    private ImageView frontId;
    private ImageView backId;
    private ImageView frontLin;
    private ImageView backLin;
    private File photoFile;
    private String photoPath;
    private int id;

    private String mFullnameString;
    private String mEmailString;
    private String mMobileString;
    private String mHospitalNameString;
    private String mUniversityString;
    private String mClinicAdressString;
    private String mBirthdateString;
    private String mGovernmentString;
    private String mPasswordString;
    private String mGender;
    private String mIdPic;
    private String mIdBack;
    private String mLicensePic;
    private String mLicenseBack;
    private String encodedString;
    private FacebookUserData userData = null;
    private String socialPhotoUrl;
    private String idToken;
    private String social_type;

    private Bitmap bmp;
    private SignUpTextClass signUpTextClass;
    private byte[] byteArray;
    private byte[] Id;
    private byte[] IdBack;
    private byte[] Lic;
    private byte[] LicBack;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        setToolBar("Sign up",true);

        signUpTextClass = getIntent().getParcelableExtra("extra");
        mFullnameString = signUpTextClass.getmFullnameString().trim();
        mEmailString = signUpTextClass.getmEmailString().trim();
        mMobileString = signUpTextClass.getmMobileString().trim();
        mHospitalNameString = signUpTextClass.getmHospitalNameString().trim();
        mUniversityString = signUpTextClass.getmUniversityString().trim();
        mClinicAdressString = signUpTextClass.getmClinicAdressString().trim();
        mBirthdateString = signUpTextClass.getmBirthdateString().trim();
        mGovernmentString = signUpTextClass.getmGovernmentString().trim();
        mPasswordString = signUpTextClass.getmPasswordString().trim();
        mGender = signUpTextClass.getmGender().trim();


        frontId = findViewById(R.id.front_id);
        backId = findViewById(R.id.back_id);
        frontLin = findViewById(R.id.front_lin);
        backLin = findViewById(R.id.back_lin);


        frontId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(frontId);
            }
        });
        backId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(backId);
            }
        });
        frontLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(frontLin);
            }
        });
        backLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(backLin);
            }
        });

        if (getIntent() != null && getIntent().getParcelableExtra("userData") != null) {
            userData = getIntent().getParcelableExtra("userData");
            idToken = userData.getIdToken();
            socialPhotoUrl = userData.getPhotoUrl();
            social_type = userData.getSocial_type();
        }
    }

    private void openGallery(ImageView view) {
        id = view.getId();
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        File photoDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        String photoPath = photoDirectory.getPath();

        Uri photoUri = Uri.parse(photoPath);

        photoPickerIntent.setDataAndType(photoUri,"image/*");

        startActivityForResult(photoPickerIntent,11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 11){
                Uri photoUri = data.getData();
                if (photoUri != null) {
                    photoPath = photoUri.getPath();
                    photoFile = new File(photoPath);
                   encodedString = getPicture(photoUri);
                }
                if (id == frontId.getId()){
                    frontId.setImageURI(photoUri);
                    mIdPic = encodedString;
                    Id = byteArray;
                }
                else if (id == backId.getId()){
                    backId.setImageURI(photoUri);
                    mIdBack = encodedString;
                    IdBack = byteArray;
                }
                else if (id == frontLin.getId()){
                    frontLin.setImageURI(photoUri);
                    mLicensePic = encodedString;
                    Lic = byteArray;
                }
                else if (id == backLin.getId()){
                    backLin.setImageURI(photoUri);
                    mLicenseBack = encodedString;
                    LicBack = byteArray;
                }
            }
        }
    }
    private String getPicture(Uri data) {

        String encodeString = "";
        try {
            bmp = MediaStore.Images.Media.getBitmap(getContentResolver(),data);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 75, bos);
            byteArray = bos.toByteArray();
            encodeString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return encodeString;
    }

    public void SignUp(View view) {
        // Toast.makeText(this,photoPath,Toast.LENGTH_LONG).show();
        String URL;
        if (userData != null){
            URL = SOCIAL_URL_REGISTER;
        }
        else{
            URL = URL_REGISTER;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(PhotosActivity.this,"Sign Up Successful !",Toast.LENGTH_LONG).show();
                Intent intent = new Intent (PhotosActivity.this,RegisteredData.class);
                intent.putExtra("extra",signUpTextClass);

                //these next put extra lines only work with really small pictures,
                // I didn't have time to figure another way out.
                intent.putExtra("frontID",Id);
                intent.putExtra("backID",IdBack);
                intent.putExtra("frontLin",Lic);
                intent.putExtra("backLin",LicBack);
                startActivity(intent);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //it takes too long when the photos are big
                        Toast.makeText(PhotosActivity.this,"Sign Up Failed :( !",Toast.LENGTH_LONG).show();
                        Log.e("SIGNUPTAG",error.toString());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("full_name",mFullnameString);
                params.put("email",mEmailString);
                params.put("password",mPasswordString);
                params.put("phone_number",mMobileString);
                params.put("hospital_name",mHospitalNameString);
                params.put("university",mUniversityString);
                params.put("clinc_address",mClinicAdressString);
                params.put("date_of_birth",mBirthdateString);
                params.put("governorate",mGovernmentString);
                params.put("gender",mGender);
                params.put("id_pic",mIdPic);
                params.put("id_pic_back",mIdBack);
                params.put("license_pics",mLicensePic);
                params.put("license_pic_back",mLicenseBack);
                if (userData != null){
                    params.put("social_id",idToken);
                    params.put("social_type",social_type);
                    params.put("social_image",socialPhotoUrl);
                }

                //(“full_name”, “email”, “password”, “phone_number”, “hospital_name”, “university”,
                //“clinc_address”, “date_of_birth”, “governorate”, “gender”, “id_pic”  (File), “id_pic_back”  (File),
                //“license_pic”  (File), “license_pic_back”  (File))
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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
                NavUtils.navigateUpFromSameTask(PhotosActivity.this);
            }
        });
    }
}
