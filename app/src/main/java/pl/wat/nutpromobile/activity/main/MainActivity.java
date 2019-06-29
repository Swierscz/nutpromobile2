package pl.wat.nutpromobile.activity.main;

import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.wat.nutpromobile.R;
import pl.wat.nutpromobile.ble.Connection;
import pl.wat.nutpromobile.fragments.connection.FragmentConnection;

public class MainActivity extends AppCompatActivity implements FragmentConnection.OnFragmentInteractionListener {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private Connection connection;

    private MainActivityLifecycleObserver mainActivityLifecycleObserver;
    private Permission permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new MainActivityLifecycleObserver(getLifecycle()));
        permission = new Permission(this);
        permission.startPermissionRequest();
        ButterKnife.bind(this);


        NavigationUI.setupWithNavController(navigation, Navigation.findNavController(this, R.id.nav_host_fragment));
        connection = new Connection(this);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permission.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
