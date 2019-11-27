package com.supernova.jetpack.movietron.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.supernova.jetpack.movietron.R;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigation();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_movie,
                R.id.navigation_tv,
                R.id.navigation_favorite).build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.navigation_favorite) {
                    showAppBarShadow(false);
                } else {
                    showAppBarShadow(true);
                }
            }
        });
    }

    private void showAppBarShadow(Boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setElevation(show ? 8 : 0);
    }
}
