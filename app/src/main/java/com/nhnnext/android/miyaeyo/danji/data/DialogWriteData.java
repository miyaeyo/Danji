package com.nhnnext.android.miyaeyo.danji.data;

/**
 * Created by miyaeyo on 2015. 8. 7..
 */
public class DialogWriteData {
    private String charater;
    private String dialogQuotation;

    public DialogWriteData(String charater, String dialogQuotation){
        this.charater = charater;
        this.dialogQuotation = dialogQuotation;
    }

    public String getCharater() {
        return charater;
    }

    public String getDialogQuotation() {
        return dialogQuotation;
    }
}
