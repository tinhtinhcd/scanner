package org.lvt.quickscanner.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.lvt.quickscanner.Fragments.HistoryFragment;
import org.lvt.quickscanner.Fragments.ScannerFragment;
import org.lvt.quickscanner.R;

public class MainActivity extends BaseActivity {

    FragmentManager     fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView(){
        setContentView(R.layout.activity_main);
        loadAdv();

        fragmentManager = getFragmentManager();
        ScannerFragment scannerFragment = new ScannerFragment();
        loadFragment(scannerFragment);
    }

    private void loadFragment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragments, fragment, null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scanner_menu:
                ScannerFragment scannerFragment = new ScannerFragment();
                loadFragment(scannerFragment);
                return true;
            case R.id.history_menu:
                HistoryFragment historyFragment = new HistoryFragment();
                loadFragment(historyFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
