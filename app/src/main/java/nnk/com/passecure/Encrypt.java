package nnk.com.passecure;

public class Encrypt {

     public static String one(String pass,String Upass)
     {
         String fi="";
         int length=Upass.length();

         String first=Upass.substring(0,length/2);
         String last=Upass.substring(length/2,length);
         StringBuilder str=new StringBuilder(first);
         StringBuilder str1=new StringBuilder(last);
         str.reverse();
         str1.reverse();
         str.append(str1);
         str.reverse();
         Upass=String.valueOf(str);
         char[] ch=Upass.toCharArray();
         for(int i=0;i<ch.length;i++)
         {
             int val=ch[i];
             if(val>=15 && val<=127)
             {
                 val=val-1;
             }

             ch[i]=(char)val;
         }

         Upass=String.valueOf(ch);
         int Min=100;
         int Max=999;
         String val1=String.valueOf((Min + (int)(Math.random() * ((Max - Min)))));
         Upass=val1+Upass;
         String val3=String.valueOf((Min + (int)(Math.random() * ((Max - Min)))));
         StringBuilder str2=new StringBuilder(Upass);

         str2.append(val3);
         str2.reverse();
         Upass=String.valueOf(str2);
         return Upass;
     }

}
