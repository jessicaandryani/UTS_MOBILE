package com.jess.utsmobile;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import com.jess.utsmobile.databinding.ActivityMainBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    int itemId = item.getItemId();

                    if (itemId == R.id.navigation_home) {
                        fragment = new HomeFragment();
                    } else if (itemId == R.id.navigation_dompet) {
                        fragment = new WalletFragment();
                    } else if (itemId == R.id.navigation_account) {
                        fragment = new AccountFragment();
                    }
                    if (fragment != null) {
                        switchFragment(fragment);
                        return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState == null) {

            binding.navigation.setSelectedItemId(R.id.navigation_home);
        }
    }
    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_layout, fragment,
                        fragment.getClass().getSimpleName())
                .commit();
    }
}