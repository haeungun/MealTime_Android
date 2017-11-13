package com.example.haeunkim.mealtime.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.haeunkim.mealtime.R;
import com.example.haeunkim.mealtime.model.Auth;
import com.example.haeunkim.mealtime.model.Util;
import com.example.haeunkim.mealtime.model.Waiting;
import com.example.haeunkim.mealtime.viewmodel.WaitingAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private View popupView;
    private PopupWindow popupWindow;
    private Spinner spinCategory;
    private TextView txtCount;

    private WaitingAdapter waitingAdapter;
    private Auth auth;

    private String uid;
    private String name;
    private String major;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("");

        txtCount = (TextView) findViewById(R.id.txt_count);

        RecyclerView recyclerWaiting = (RecyclerView) findViewById(R.id.recycle_waiting);
        waitingAdapter = new WaitingAdapter(this, R.layout.main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerWaiting.setHasFixedSize(true);
        recyclerWaiting.setLayoutManager(layoutManager);
        recyclerWaiting.setAdapter(waitingAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // FloatingActionButton for pop up window
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((v) -> popup());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        auth = new Auth();

        auth.getReference().child("waiting").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Waiting w = dataSnapshot.getValue(Waiting.class);
                waitingAdapter.add(w);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Waiting w = dataSnapshot.getValue(Waiting.class);
                waitingAdapter.change(w);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Waiting w = dataSnapshot.getValue(Waiting.class);
                waitingAdapter.remove(w);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        auth.getReference().child("waiting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Setting each time the size of the Waiting node changes
                int size = (int) dataSnapshot.getChildrenCount();
                txtCount.setText(String.valueOf(size));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        uid = auth.getCurrentUid();
        auth.getReference().child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> user = (HashMap<String, String>) dataSnapshot.getValue();
                name = user.get("nickname");
                major = user.get("major");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Auth auth = new Auth();
            auth.signOutUser();
            Util.goActivity(this, LoginActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // show message
            Util.showMessage(this, "Home");
        } else if (id == R.id.nav_my_page) {
            // Go to my-page-activity
            Util.goActivity(this, MyPageActivity.class);
        } else if (id == R.id.nav_chat) {
            // Go to chat-activity
            Util.goActivity(this, ChatActivity.class);
        } else if (id == R.id.nav_sign_out) {
            // Sign out
            Auth auth = new Auth();
            auth.signOutUser();
            Util.goActivity(this, LoginActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Pop up window
    private void popup() {
        popupView = getLayoutInflater().inflate(R.layout.pop_up, null);
        popupWindow = new PopupWindow(popupView,
                RelativeLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT);

        popupWindow.setAnimationStyle(-1);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        this.getCategory();
    }

    // Pop up window close
    public void onClickClose(View v) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    // Pop up window submit button event
    public void onClickSubmit(View v) {
        Waiting waiting = new Waiting(uid, category, name, major);
        auth.getReference().child("waiting").child(uid).setValue(waiting);
        this.onClickClose(v);
    }

    // Pop-up window remove button event
    public void onClickCancel(View v) {
        auth.getReference().child("waiting").child(uid).removeValue();
        this.onClickClose(v);
    }

    // Import firebase 'categories' data and set it to spinner
    public void getCategory() {
        List<String> categoryList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categoryList);

        Auth auth = new Auth();
        auth.getReference().child("categories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> categories = (ArrayList<String>) dataSnapshot.getValue();
                for (String category : categories) {
                    categoryList.add(category);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        spinCategory = (Spinner) popupView.findViewById(R.id.spin_category);
        spinCategory.setAdapter(adapter);
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SELECTED_CATEGORY" , parent.getItemAtPosition(position).toString());
                category = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
