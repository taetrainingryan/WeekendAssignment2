package com.example.ryan.weekendassignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ryan.weekendassignment2.views.ClassicFragment;
import com.example.ryan.weekendassignment2.views.PopFragment;
import com.example.ryan.weekendassignment2.views.RockFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private TextView mTextMessage;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {


                case R.id.navigation_home:

                    //mTextMessage.setText(R.string.title_classic);
                    switchToFragment1();
                    break;


                case R.id.navigation_dashboard:

                    //mTextMessage.setText(R.string.title_rock);
                    switchToFragment3();
                    break;


                case R.id.navigation_notifications:

                    //mTextMessage.setText(R.string.title_pop);
                    switchToFragment2();
                    break;

                    default: return false;

            }

            updateNavigationBarState(item.getItemId());
            return true;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();


        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation);
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new ClassicFragment())
                .commit();


    }

    public void switchToFragment1() {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ClassicFragment()).commit();
    }

    public void switchToFragment2() {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new PopFragment()).commit();
    }

    public void switchToFragment3() {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new RockFragment()).commit();
    }

    private void updateNavigationBarState(int actionId){
        Menu menu = navigation.getMenu();

        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == actionId);
        }
    }
}
