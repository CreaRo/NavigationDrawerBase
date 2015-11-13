package rish.crearo.navdrawerbase;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_tool_bar)
    Toolbar toolbar;

    @Bind(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.main_nav_view)
    NavigationView navigationView;

    @Bind(R.id.main_frame_layout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupNavigationDrawer();

    }

    private void setupNavigationDrawer() {
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationIcon(R.drawable.abc_btn_check_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_item_1:
                                Snackbar.make(frameLayout, "Item One", Snackbar.LENGTH_SHORT).show();
//                                mCurrentSelectedPosition = 0;
                                return true;
                            case R.id.navigation_item_2:
                                Snackbar.make(frameLayout, "Item Two", Snackbar.LENGTH_SHORT).show();
//                                mCurrentSelectedPosition = 1;
                                return true;
                            default:
                                return true;
                        }
                    }
                }
        );

    }
}