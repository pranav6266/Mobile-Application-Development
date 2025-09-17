package com.pranav.nutritiondetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadFragment(new BurgerFragment());
        }
        findViewById(R.id.burgerImage).setOnClickListener(v -> loadFragment(new BurgerFragment()));
        findViewById(R.id.pizzaImage).setOnClickListener(v -> loadFragment(new PizzaFragment()));
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nutritionFragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}