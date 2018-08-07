package nnk.com.passecure;

public class Decrypt {

    public String Dec_one(String pass,String upass)
    {
        int p=3;
        for(int i=0;i<pass.length();i=i+2)
        {
            p++;
        }
        StringBuffer str=new StringBuffer(upass);
        str.reverse();
        str.delete(upass.length()-p,upass.length());
        upass=String.valueOf(str);
        char[] ch=upass.toCharArray();
        for(int j=0;j<ch.length;j++)
        {
            int num=ch[j];
            if(num>=14 && num<=126)
                num=num+1;
            ch[j]=(char)num;
        }
        upass=String.valueOf(ch);
        return upass;
    }


}
