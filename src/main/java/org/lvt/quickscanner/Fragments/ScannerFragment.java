package org.lvt.quickscanner.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import org.lvt.quickscanner.Database.RecordRepository;
import org.lvt.quickscanner.Others.Type;
import org.lvt.quickscanner.R;

import java.util.List;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class ScannerFragment extends BaseFragment {

    private CompoundBarcodeView barcodeScannerView;
    TextView qrText;
    LinearLayout contactHolder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.scanner_fragment, null);
        initView(v);
        return v;
    }

    private void initView(View view){
        barcodeScannerView = (CompoundBarcodeView) view.findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.decodeContinuous(callback);
        qrText = (TextView) view.findViewById(R.id.qr_res);
        qrText.setMovementMethod(LinkMovementMethod.getInstance());
        contactHolder = (LinearLayout) view.findViewById(R.id.contact_holder);
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                parseResult(result);
            }
        }
        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    private void parseResult(BarcodeResult result){
        String contents = result.getText();
        Type   type     = Type.TEXT;
        if(contents.startsWith("BEGIN:")){
            qrText.setVisibility(View.GONE);
            contactHolder.setVisibility(View.VISIBLE);
            type = Type.CONTACT;
            save(contents,type);
        }else if(contents.startsWith("http://")||(contents.startsWith("www.")||contents.startsWith("https://"))){
            type = Type.URL;
            save(contents,type);
            qrText.setVisibility(View.GONE);
            contactHolder.setVisibility(View.GONE);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(contents));
            startActivity(browserIntent);
        }else{
            save(contents,type);
            qrText.setText(contents);
            qrText.setVisibility(View.VISIBLE);
            contactHolder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        barcodeScannerView.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        barcodeScannerView.pause();
        super.onPause();
    }

    private void save(String content, Type type){
        RecordRepository recordRepository = new RecordRepository(dataHelper,sqLiteDatabase);
        recordRepository.insert(content,type);
    }
}
