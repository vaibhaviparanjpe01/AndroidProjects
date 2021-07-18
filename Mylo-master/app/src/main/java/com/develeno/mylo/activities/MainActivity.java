package com.develeno.mylo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.crashlytics.android.Crashlytics;
import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.adapters.MyPagerAdapter;
import com.develeno.mylo.fragments.FragmentHome;
import com.develeno.mylo.pojo.MyHelper;
import com.develeno.mylo.pojo.UserObject;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.MiniDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REFERRAL_CREDIT = 10;
    public static ViewPager pager;
    public static boolean loggedIn;
    public static Location location;
    public static MainActivity mainActivity;
    public View bottomBar;
    public TextView lastTv;
    private ImageView home;
    private ImageView categories;
    private ImageView more;
    private FragmentHome fragmentHome;
    private Drawer drawer;
    private ExpandableDrawerItem expandableDrawerItem;
    private String currentCity;
    private String token;
    private DocumentSnapshot documentSnapshot;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//         binding.text.setText("Hello World"); // you should use resources!
        mainActivity = this;
        setupBottomBar();
        setupViewPager();
//        mainActivity = this;
//        setSelected(0);

        setupDrawer();
        setupFCM();

        UserObject userObject = FireBaseInteract.userObject;
        if (userObject.getReferralCode() == null || userObject.getReferralCode().isEmpty()) {
            generateReferralCode();
        }

        String code = getIntent().getStringExtra("code");
        // code = "TJTRP0";
        if (code != null && !code.isEmpty()) {
            giveCredits(code);
        }

        //Crashlytics.getInstance().crash(); // Force a crash
        // NotificationHelper.sendSimpleNotification("Hello World!!", "Sample Description", getApplicationContext());

        //FireBaseInteract.sendSMS("Test Message", "7566427991", this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkForLatestVersion();
    }

    private void checkForLatestVersion() {
        if (documentSnapshot == null) {
            new FireBaseInteract(this).checkForUpdate(this::onGetVersionInformation);
        } else {
            onGetVersionInformation(documentSnapshot);
        }
    }

    private void onGetVersionInformation(DocumentSnapshot documentSnapshot) {
        this.documentSnapshot = documentSnapshot;
        long currentVersionCode = 0;
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            currentVersionCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        long versionCode = 0;
        boolean force = false;
        if (documentSnapshot != null) {
            versionCode = documentSnapshot.getLong("versionCode");
            force = documentSnapshot.getBoolean("force");
        }
        if (versionCode > currentVersionCode) {
            showUpdateDialog(force);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private void showUpdateDialog(boolean force) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("New Update Available");
        builder.setMessage("Update app to latest version for best experience and get new features");
        builder.setPositiveButton("Update", (dialogInterface, i) -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            alertDialog.dismiss();
        });
        if (!force) {
            builder.setNegativeButton("Later", null);
        } else {
            builder.setCancelable(false);
        }
        alertDialog = builder.create();
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    private void giveCredits(String code) {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Assigning credits");
        dialog.show();
        FireBaseInteract fireBaseInteract = new FireBaseInteract(this);
        fireBaseInteract.fetchUserByReferralCode(code, new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                dialog.dismiss();
                if (queryDocumentSnapshots.size() > 0) {
                    UserObject userObject = queryDocumentSnapshots.getDocuments().get(0).toObject(UserObject.class);
                    if (userObject != null) {
                        // Toast.makeText(MainActivity.this, userObject.getNumber()+"", Toast.LENGTH_SHORT).show();
                        userObject.setCredits(userObject.getCredits() + REFERRAL_CREDIT);
                        fireBaseInteract.saveUser(userObject, aVoid -> {

                        });
                        FireBaseInteract.userObject.setCredits(FireBaseInteract.userObject.getCredits() + REFERRAL_CREDIT);
                        FireBaseInteract.userObject.setFcm(token);
                        fireBaseInteract.saveUser(aVoid -> {

                        });
                    } else {
                        Toast.makeText(MainActivity.this, "user null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No user found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void generateReferralCode() {
        String code = MyHelper.createRandomCode(6).toUpperCase();
        FireBaseInteract.userObject.setReferralCode(code);
        new FireBaseInteract(this).saveUser(aVoid -> {
            //Toast.makeText(MainActivity.this, "referralCode generated: "+code, Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setupFCM() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    private static final String TAG = "FCM";

                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();

                        // Log and toast
                        String msg = "Token: " + token;
                        Log.d(TAG, msg);
                        // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        new FireBaseInteract(MainActivity.this).updateToken(token, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    private void setupDrawer() {
        final String name = FireBaseInteract.userObject.getName();
        final String email = FireBaseInteract.userObject.getNumber();

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color1 = generator.getRandomColor();
        // generate color based on a key (same key returns the same color), useful for list/grid views
        int color2 = generator.getColor(name);

        final String s = name.charAt(0) + "";
        final TextDrawable drawable2 = TextDrawable.builder().beginConfig().textColor(Color.BLACK).bold().endConfig()
                .buildRound(s.toUpperCase(), Color.WHITE);

        final AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(new ProfileDrawerItem().withName(name).withEmail(email).withIcon(drawable2))
                .withOnAccountHeaderListener((view, profile, currentProfile) -> {
                    //headerResult.setBackground(R.drawable.ven);
                    return false;
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        Drawer.OnDrawerItemClickListener drawerListener = (view, position, drawerItem) -> {
            final int identifier = (int) drawerItem.getIdentifier();
            switch (identifier) {
                case 1:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    break;
                case 2:
                    //   startActivity(new Intent(getApplicationContext(), DealsActivity.class));
                    startActivity(new Intent(getApplicationContext(), MyBookingsActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getApplicationContext(), BookmarkActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                    break;
                case 5:
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Logout?");
                    builder.setMessage("Are you sure you want to logout?");
                    builder.setPositiveButton("Logout", (dialogInterface, i) -> {
                        new FireBaseInteract(MainActivity.this).logout();
                        LoginManager.getInstance().logOut();
                    });
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                    break;
                case 6:
                    BrowserActivity.setLink("http://mylocalpay.com/faq");
                    startActivity(new Intent(getApplicationContext(), BrowserActivity.class));
                    break;
                case 7:
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "mylocalpay@gmail.in", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Customer Help");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(emailIntent);
                    break;
                case 8:
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String s1 = "Download the MYLO app and get exciting discounts from nearby cafe's, restaurants, gyms and many more. Download Now:\n\nUse my referral code: " + FireBaseInteract.userObject.getReferralCode() + "\n\n";
                    String shareBody = s1 + Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Download Mylo App");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(sharingIntent);
                    break;
                case 9:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                    break;
                case 10:
                    BrowserActivity.setLink("http://mylocalpay.com/");
                    startActivity(new Intent(getApplicationContext(), BrowserActivity.class));
                    break;
                case 11:
                    setupDrawer();
                    expandableDrawerItem.withDescription("Jabalpur");
                    currentCity = "jbp";
                    break;
                case 12:
                    setupDrawer();
                    expandableDrawerItem.withDescription("Indore");
                    currentCity = "ind";
                    break;
                case 13:
                    setupDrawer();
                    expandableDrawerItem.withDescription("Bhopal");
                    currentCity = "bhp";
                    break;

            }
            return true;
        };
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(R.drawable.home).withSetSelected(true).withOnDrawerItemClickListener(drawerListener);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("My Bookings").withIcon(R.drawable.deals_menu).withSelectable(false).withOnDrawerItemClickListener(drawerListener);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Favourites").withIcon(R.drawable.heart_black).withSelectable(false).withOnDrawerItemClickListener(drawerListener);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("My Account").withIcon(R.drawable.user).withSelectable(false).withOnDrawerItemClickListener(drawerListener);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Logout").withIcon(R.drawable.logout).withSelectable(false).withOnDrawerItemClickListener(drawerListener);
        PrimaryDrawerItem item11 = new PrimaryDrawerItem().withIdentifier(11).withName("Credits: " + FireBaseInteract.userObject.getCredits()).withIcon(R.drawable.coins).withSelectable(false).withOnDrawerItemClickListener(drawerListener);

        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(6).withName("FAQ").withOnDrawerItemClickListener(drawerListener).withSelectable(false);
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(7).withName("Contact us").withOnDrawerItemClickListener(drawerListener).withSelectable(false);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withIdentifier(8).withName("Share App").withOnDrawerItemClickListener(drawerListener).withSelectable(false);
        SecondaryDrawerItem item9 = new SecondaryDrawerItem().withIdentifier(9).withName("Rate us").withOnDrawerItemClickListener(drawerListener).withSelectable(false);
        SecondaryDrawerItem item10 = new SecondaryDrawerItem().withIdentifier(10).withName("About us").withOnDrawerItemClickListener(drawerListener).withSelectable(false);

        //create the drawer and remember the `Drawer` drawer object
        List<IDrawerItem> items = new ArrayList<>();
        items.add(new SecondaryDrawerItem().withName("Jabalpur").withOnDrawerItemClickListener(drawerListener).withIdentifier(11));
        items.add(new SecondaryDrawerItem().withName("Indore").withOnDrawerItemClickListener(drawerListener).withIdentifier(12));
        items.add(new SecondaryDrawerItem().withName("Bhopal").withOnDrawerItemClickListener(drawerListener).withIdentifier(13));
        expandableDrawerItem = new ExpandableDrawerItem().withName("Current City").withDescription("Jabalpur").withSubItems(items).withSelectable(false).withSetSelected(false);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .addDrawerItems(item11, new DividerDrawerItem(), expandableDrawerItem, new DividerDrawerItem(),
                        item1, item2, item3, item4, item5, new DividerDrawerItem(), item6, item7, item8, item9, item10, new MiniDrawerItem()
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    // do something with the clicked item :D
                    return false;
                })
                .build();
    }

    private void setupViewPager() {
        setSelected(0);
        pager = findViewById(R.id.pager);
        ArrayList<Pair<String, Fragment>> fragments = new ArrayList<>();
        fragmentHome = new FragmentHome();
        fragments.add(new Pair<>("", fragmentHome));
//        fragments.add(new Pair<String, Fragment>("", new FragmentCategories()));
        // fragments.add(new Pair<String, Fragment>("", new FragmentChat()));
        MyPagerAdapter adapter = new MyPagerAdapter(fragments, getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(5);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelected(position);
               /* if (position != 0) {
                    mtoolbar.setVisibility(View.VISIBLE);
                    bottomBar.setVisibility(View.VISIBLE);
                }*/

                //FragmentCategories.scrollToTop();
                if (position == 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        //Do something after 100ms
                        //FragmentHome.animateFAB();
                    }, 100);
                } else {
                    //FragmentHome.fab.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupBottomBar() {
        bottomBar = findViewById(R.id.bottom);
        home = findViewById(R.id.ic_home);
        categories = findViewById(R.id.ic_categories);
        more = findViewById(R.id.ic_more);
        home.setOnClickListener(getOnClickListener(0));
        categories.setOnClickListener(getOnClickListener(1));
        more.setOnClickListener(getOnClickListener(2));
    }

    private View.OnClickListener getOnClickListener(final int i) {
        return view -> {
            pager.setCurrentItem(i);
            setSelected(i);
        };
    }

    public void setSelected(int selected) {
        home.setImageResource(R.drawable.home_light);
        categories.setImageResource(R.drawable.explore_light);
        more.setImageResource(R.drawable.user_light);
        switch (selected) {
            case 0:
                home.setImageResource(R.drawable.home);
                break;
            case 1:
                categories.setImageResource(R.drawable.explore);
                break;
            case 2:
                more.setImageResource(R.drawable.user);
                break;
        }
    }

    public void loadCategory(View view) {
        if (lastTv != null) {
            lastTv.setBackgroundResource(R.drawable.layout_bg);
        }
        TextView tv = (TextView) view;
        tv.setBackgroundResource(R.drawable.layout_bg_selected);
        lastTv = tv;
//        Toast.makeText(this, tv.getText() + "", Toast.LENGTH_SHORT).show();
        final String category = tv.getText().toString();
        if (!category.equalsIgnoreCase("All")) {
            if (!fragmentHome.locationSwitch.isChecked()) {
                fragmentHome.fetchVendorsAround(category);
            } else {
                fragmentHome.fetchVendorsNearBy(location, category);
            }
        } else {
            if (!fragmentHome.locationSwitch.isChecked()) {
                fragmentHome.fetchVendorsAround(null);
            } else {
                fragmentHome.fetchVendorsNearBy(location, null);
            }
        }
    }

    public void openChat(View view) {
        //startActivity(new Intent(this, ChatActivity.class));
        pager.setCurrentItem(1);
    }

    public void openSearch(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void openDrawer(View view) {
        drawer.openDrawer();
    }

    public void backtoMain(View view) {
        pager.setCurrentItem(0);
    }
}
