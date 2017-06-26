package org.lvt.quickscanner.Others;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.lvt.quickscanner.Database.RecordEntity;
import org.lvt.quickscanner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class CustomAdapter extends BaseAdapter{

    List<RecordEntity> recordEntities;
    Context context;
    public List<Integer> checked = new ArrayList<>();

    public CustomAdapter(Context context, List<RecordEntity> recordEntities){
        this.context = context;
        this.recordEntities = recordEntities;
    }

    public List<Integer> getCheckedItems(){
        return checked;
    }

    public void setChecked(RecordEntity entity){
        if (checked.contains(entity.getId()))
            checked.remove(checked.indexOf(entity.getId()));
        else
            checked.add(entity.getId());
    }

    @Override
    public int getCount() {
        return recordEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return recordEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return recordEntities.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.list_item, null);
            }

            TextView content = (TextView) convertView.findViewById(R.id.record_content);
            TextView date    = (TextView) convertView.findViewById(R.id.record_date);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        setChecked(recordEntities.get(position));
                    }
                }
            });

            String recode = recordEntities.get(position).getContent();
            if(Contact.isContact(recode)){
                Contact contact = new Contact(recode);
                content.setText(contact.getType() + ": " + contact.getName());
            }
            else {
                content.setText(recode);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
            date.setText(simpleDateFormat.format(recordEntities.get(position).getRecordDate()));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showViewItem(position);
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }

    private void showViewItem(int position){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        RecordEntity item   = recordEntities.get(position);
//        if(Contact.isContact(item.getContent())){
//            dialog.setContentView(R.layout.contact);
//            Contact contact = new Contact(item.getContent());
//            TextView type = (TextView) dialog.findViewById(R.id.type);
//            TextView name = (TextView) dialog.findViewById(R.id.name);
//            TextView org = (TextView) dialog.findViewById(R.id.org);
//            TextView tel = (TextView) dialog.findViewById(R.id.tel);
//            TextView url = (TextView) dialog.findViewById(R.id.url);
//            TextView email = (TextView) dialog.findViewById(R.id.email);
//            TextView adr = (TextView) dialog.findViewById(R.id.adr);
//
//            type.setText(contact.getType());
//            name.setText(contact.getName());
//            org.setText(contact.getOrg());
//            tel.setText(contact.getTel());
//            url.setText(contact.getUrl());
//            email.setText(contact.getEmail());
//            adr.setText(contact.getAdr());

//        }else{
            dialog.setContentView(R.layout.recode);
            TextView recode = (TextView) dialog.findViewById(R.id.recode);
            recode.setText(item.getContent());
//        }
        Log.e("content:",item.getContent());
        dialog.show();
    }
}
