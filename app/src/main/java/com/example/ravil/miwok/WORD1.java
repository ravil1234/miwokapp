package com.example.ravil.miwok;
public class WORD1 {
    /**default translation*/
    private String mdtrans;
    private String mMtrans;
    private int mimage=notimage;
    private static final int notimage=-1;
    private int maudio;
    public WORD1(String dtrans,String Mtrans,int audio)
    {
        maudio=audio;
        mdtrans=dtrans;
        mMtrans=Mtrans;

    }


        public WORD1(String dtrans,String Mtrans,int image,int audio)
        {
            maudio=audio;
            mdtrans=dtrans;
        mMtrans=Mtrans;
        mimage=image;
    }
    public String getdefault()
    {

        return mdtrans;
    }
    public String getmiwok()
    {
        return mMtrans;
    }
    public int getimage()
    {
        return mimage;
    }
    public boolean hasimage()
    {
        return mimage!=notimage;
    }
    public int getaudio()
    {
        return maudio;
    }
}
