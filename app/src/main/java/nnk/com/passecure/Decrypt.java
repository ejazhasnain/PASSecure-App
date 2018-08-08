package nnk.com.passecure;

public class Decrypt {

    public String Dec_one(String pass,String upass)
    {
        StringBuffer str=new StringBuffer(upass);
        str.reverse();
        str.delete(0,3);
        str.delete(str.length()-3,str.length());
        upass=String.valueOf(str);
        char[] ch=upass.toCharArray();
        for(int i=0;i<ch.length;i++)
        {
            int val=ch[i];
            if(val>=14 && val<=126)
            {
                val=val+1;
            }
            ch[i]=(char)val;
        }
        upass=String.valueOf(ch);

        StringBuffer str2=new StringBuffer(upass);
        str2.reverse();
        upass=String.valueOf(str2);

        int length=upass.length();

        String first=upass.substring(0,length/2);
        String last=upass.substring(length/2,length);

        StringBuilder str3=new StringBuilder(first);
        StringBuilder str1=new StringBuilder(last);

        str3.reverse();
        str1.reverse();
        str3.append(str1);
        upass=String.valueOf(str3);
        return upass;
    }


}
