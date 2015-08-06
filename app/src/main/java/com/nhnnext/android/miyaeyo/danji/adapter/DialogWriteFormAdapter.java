package com.nhnnext.android.miyaeyo.danji.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.nhnnext.android.miyaeyo.danji.R;
import com.nhnnext.android.miyaeyo.danji.data.DialogWriteData;

import java.util.ArrayList;

/**
 * Created by miyaeyo on 2015. 8. 7..
 */
public class DialogWriteFormAdapter extends ArrayAdapter<DialogWriteData> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<DialogWriteData> dialogeWirteDataList;

    public DialogWriteFormAdapter(Context context, int layoutResourceId, ArrayList<DialogWriteData> dialogeWirteDataList){
        super(context, layoutResourceId, dialogeWirteDataList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.dialogeWirteDataList = dialogeWirteDataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        EditText character = (EditText)convertView.findViewById(R.id.character);
        EditText dialogQuotation = (EditText)convertView.findViewById(R.id.dialog);
        character.setText(dialogeWirteDataList.get(position).getCharater());
        dialogQuotation.setText(dialogeWirteDataList.get(position).getDialogQuotation());
        return convertView;

    }
}
