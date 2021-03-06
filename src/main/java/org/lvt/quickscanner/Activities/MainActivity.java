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
    ScannerFragment scannerFragment;
    HistoryFragment historyFragment;

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
        loadFragment(scannerFragment, "scan");
    }

    private void loadFragment(Fragment fragment, String tag){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragments, fragment, tag).commit();
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
                if(scannerFragment == null)
                 scannerFragment = new ScannerFragment();
                loadFragment(scannerFragment, "scan");
                return true;
            case R.id.history_menu:
                if(historyFragment == null)
                 historyFragment = new HistoryFragment();
                loadFragment(historyFragment, "his");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
