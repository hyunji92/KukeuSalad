package hyunji.kukeusalad.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;
import hyunji.kukeusalad.view.fragment.MainFragment;

/**
 * Created by hyunji
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar vToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout vDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView vNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(vToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, vDrawerLayout, vToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        vDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        vNavigationView.setNavigationItemSelectedListener(this);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, new MainFragment()).commit();
    }


    @Override
    public void onBackPressed() {
        if (vDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            vDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {// Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;

            default:
        }

        vDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
