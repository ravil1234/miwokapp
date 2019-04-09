package com.example.ravil.miwok;

public class Word {
    /**default translation*/
    private String mdtrans;
    private String mMtrans;
    public Word(String dtrans,String Mtrans)
    {
        mdtrans=dtrans;
        mMtrans=Mtrans;
    }
    public String getdefault()
    {
        return mdtrans;
    }
    public String getmiwok()
    {
        return mMtrans;
    }
}
