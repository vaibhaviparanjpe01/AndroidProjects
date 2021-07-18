package com.develeno.mylo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.pojo.UserObject;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;

public class RegisterSellerActivity extends AppCompatActivity {

    private EditText fullname;
    private EditText mobile;
    private EditText email;
    private EditText password;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ProfileTracker mProfileTracker;
    private EditText code;
    private String referralCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);
        setupToolbar();
        findViews();
        password.setText("123456");
        password.setVisibility(View.GONE);

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button);
        loginButton.setLoginBehavior(LoginBehavior.WEB_ONLY);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        onFBSuccess(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(RegisterSellerActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(RegisterSellerActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        // App code
                    }
                });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            String name = Profile.getCurrentProfile().getFirstName();
            Toast.makeText(this, "Logged in as " + name, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Logged in ", Toast.LENGTH_SHORT).show();
        }
    }

    private void onFBSuccess(LoginResult loginResult) {
        try {
            Toast.makeText(RegisterSellerActivity.this, "Success", Toast.LENGTH_SHORT).show();
            // App code
            final Profile currentProfile = Profile.getCurrentProfile();
//                        String name = currentProfile.getFirstName() + " " + currentProfile.getLastName();

            final String accessToken = loginResult.getAccessToken().getUserId();

            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject jsonObject,
                                                GraphResponse response) {

                            // Getting FB User Data and checking for null
                            Bundle facebookData = getFacebookData(jsonObject);
                            String email = "";
                            String first_name = "";
                            String last_name = "";
                            String profile_pic = "";

                            if (facebookData.getString("email") != null && !TextUtils.isEmpty(facebookData.getString("email")))
                                email = facebookData.getString("email");
                            else
                                email = "";

                            if (facebookData.getString("first_name") != null && !TextUtils.isEmpty(facebookData.getString("first_name")))
                                first_name = facebookData.getString("first_name");
                            else
                                first_name = "";

                            if (facebookData.getString("last_name") != null && !TextUtils.isEmpty(facebookData.getString("last_name")))
                                last_name = facebookData.getString("last_name");
                            else
                                last_name = "";

                            if (facebookData.getString("profile_pic") != null && !TextUtils.isEmpty(facebookData.getString("profile_pic")))
                                profile_pic = facebookData.getString("profile_pic");
                            else
                                profile_pic = "";

                            //sendValues(first_name+" "+last_name,email, "", "", accessToken, "Facebook",profile_pic);
                            Toast.makeText(RegisterSellerActivity.this, first_name + " " + last_name, Toast.LENGTH_SHORT).show();
                            EditText fullname = findViewById(R.id.fullname);
                            EditText emailET = findViewById(R.id.email);

                            fullname.setText(first_name + " " + last_name);
                            emailET.setText(email);
                        }
                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,first_name,last_name,email,gender");
            request.setParameters(parameters);
            request.executeAsync();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();
        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));


        } catch (Exception e) {
            Log.d("TAG", "BUNDLE Exception : " + e.toString());
        }
        return bundle;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private void findViews() {
        fullname = findViewById(R.id.fullname);
        code = findViewById(R.id.code);
        mobile = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mobile.setText(getIntent().getExtras().getString("number", null));
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void signUp(View view) {
        UserObject user = new UserObject();
        if (checkFields(user)) {
            signup(user);
        }
    }

    private void signup(UserObject user) {
        FireBaseInteract firebaseInteract = new FireBaseInteract(this);
        firebaseInteract.signUp(user, referralCode);
        MainActivity.loggedIn = true;
    }

    private boolean checkFields(UserObject user) {
        String emailString = email.getText().toString().trim();
        String nameString = fullname.getText().toString().trim();
        String mobileString = mobile.getText().toString().trim();
        String passwordString = password.getText().toString().trim();
        referralCode = code.getText().toString().trim();

        if (nameString.isEmpty()) {
            fullname.setError("Cannot be empty");
            return false;
        }
        if (nameString.length() < 3) {
            fullname.setError("Name too short");
            return false;
        }
        if (mobileString.isEmpty()) {
            mobile.setError("Cannot be empty");
            return false;
        }
        if (mobileString.length() < 10) {
            mobile.setError("Invalid Number");
            return false;
        }
        if (emailString.isEmpty()) {
            email.setError("Cannot be empty");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            email.setError("Invalid Email");
            return false;
        }
        if (passwordString.isEmpty()) {
            password.setError("Cannot be empty");
            return false;
        }
        if (passwordString.length() < 6) {
            password.setError("Password must be at least 6 characters long");
            return false;
        }
        user.setName(nameString);
        user.setNumber(mobileString);
        user.setEmail(emailString);
        user.setPassword(passwordString);
        user.setCreatedOn(Calendar.getInstance().getTime());
        return true;
    }

    public void updatePassword(View view) {
    }

    public void fbSignIn(View view) {
        loginButton.performClick();
    }

    public void googleSignIn(View view) {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }


}
