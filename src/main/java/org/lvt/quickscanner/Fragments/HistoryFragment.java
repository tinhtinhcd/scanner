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
import org.lvt.quickscanner.Others.Contact;
import org.lvt.quickscanner.Others.CustomAdapter;
import org.lvt.quickscanner.Others.Type;
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
        listView.setItemsCanFocus(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showViewItem(position);
            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.delete);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDeleteDialog();
            }
        });
    }

    private void showViewItem(int position){
        final Dialog dialog = new Dialog(getActivity());
        RecordEntity item   = recordEntities.get(position);
        if(Contact.isContact(item.getContent()) && Type.CONTACT.equals(item.getType())){
            dialog.setContentView(R.layout.contact);
            Contact contact = new Contact(item.getContent());

            TextView type = (TextView) dialog.findViewById(R.id.type);
            TextView name = (TextView) dialog.findViewById(R.id.name);
            TextView org = (TextView) dialog.findViewById(R.id.org);
            TextView tel = (TextView) dialog.findViewById(R.id.tel);
            TextView url = (TextView) dialog.findViewById(R.id.url);
            TextView email = (TextView) dialog.findViewById(R.id.email);
            TextView adr = (TextView) dialog.findViewById(R.id.adr);

            type.setText(contact.getType());
            name.setText(contact.getName());
            org.setText(contact.getOrg());
            tel.setText(contact.getTel());
            url.setText(contact.getUrl());
            email.setText(contact.getEmail());
            adr.setText(contact.getAdr());

        }else{
            dialog.setContentView(R.layout.recode);
            TextView recode = (TextView) dialog.findViewById(R.id.recode);
            recode.setText(item.getContent());
        }
        dialog.show();
    }

    private void showDeleteDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);
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

}
