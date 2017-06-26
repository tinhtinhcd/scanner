package org.lvt.quickscanner.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.lvt.quickscanner.Database.RecordEntity;
import org.lvt.quickscanner.Database.RecordRepository;
import org.lvt.quickscanner.Others.CustomAdapter;
import org.lvt.quickscanner.R;

import java.util.List;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class HistoryFragment extends BaseFragment {

    List<RecordEntity> recordEntities;
    ListView listView;
    RecordRepository recordRepository;
    CustomAdapter customAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordRepository = new RecordRepository(dataHelper,sqLiteDatabase);
        recordEntities = recordRepository.get();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.history_fragment, null);
        initView(v);
        return v;
    }

    private void initView(View view){
        listView = (ListView) view.findViewById(R.id.listView);
        customAdapter = new CustomAdapter(getActivity().getBaseContext(),recordEntities);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.delete);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog);
                TextView text = (TextView) dialog.findViewById(R.id.dialog_text);
                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                Button delete = (Button) dialog.findViewById(R.id.delete);
                if(customAdapter.checked.size()>0){
                    dialog.setTitle("Delete selected!");
                }else{
                    dialog.setTitle("Clear all history");
                }
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer[] ids = new Integer[customAdapter.checked.size()];
                        customAdapter.checked.toArray(ids);
                        recordRepository.delete(customAdapter.checked.toArray(ids));
                        recordEntities = recordRepository.get();
                        customAdapter = new CustomAdapter(getActivity().getBaseContext(),recordEntities);
                        listView.setAdapter(customAdapter);
                        listView.invalidate();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

}
