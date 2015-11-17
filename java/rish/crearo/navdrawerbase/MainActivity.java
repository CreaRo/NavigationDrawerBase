package rish.crearo.navdrawerbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_tool_bar)
    Toolbar toolbar;

    @Bind(R.id.main_frame_layout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupDrawer();
    }

    private void setupDrawer() {

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.snackbar_background)
                .build();

//        Drawable home = new IconicsDrawable(this).icon(FontAwesome.Icon.faw_home).color(Color.GRAY).sizeDp(24);

        final PrimaryDrawerItem pHome = new PrimaryDrawerItem().withName("Home");
        final SecondaryDrawerItem sSettings = new SecondaryDrawerItem().withName("Settings");
        final SecondaryDrawerItem sSupport = new SecondaryDrawerItem().withName("Support");
        final SecondaryDrawerItem sHelpFeedback = new SecondaryDrawerItem().withName("Help & Feedback");

        sSettings.setCheckable(false);
        sSupport.setCheckable(false);
        sHelpFeedback.setCheckable(false);

        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        pHome,
                        new DividerDrawerItem(),
                        sSettings,
                        sSupport,
                        sHelpFeedback
                ).withDelayOnDrawerClose(200)
                .withCloseOnClick(true)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem.equals(pHome)) {
                            Snackbar.make(frameLayout, "Home", Snackbar.LENGTH_LONG).show();
                        } else if (drawerItem.equals(sSettings)) {
                            startActivity(new Intent(MainActivity.this, Settings.class));
                        } else if (drawerItem.equals(sHelpFeedback)) {
                            startActivity(new Intent(MainActivity.this, HelpAndFeedback.class));
                        } else if (drawerItem.equals(sSupport)) {
                            startActivity(new Intent(MainActivity.this, Support.class));
                        } else {
                            Snackbar.make(frameLayout, "None", Snackbar.LENGTH_LONG).show();
                        }
                        return false;
                    }
                })
                .build();

        drawer.setSelection(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

    }
}