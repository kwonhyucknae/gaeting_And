package com.gaetingdev.m.gaetingdev.Main.Contents.PersonalPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.gaetingdev.m.gaetingdev.R;

/**
 * Created by x-note on 2016-11-06.
 */
public class PersonalMainPage extends AppCompatActivity {
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalmainpage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_test_menu, menu);
        return true;
    }

}
