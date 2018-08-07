package nnk.com.passecure;

public class Encrypt {

     public static String one(String pass,String Upass)
     {
         String fi="";
         char[] ch=Upass.toCharArray();
         for(int i=0;i<ch.length;i++)
         {
             int num=ch[i];
             if(num>=15 && num<=127)
             {
                 num=num-1;
             }
             System.out.println(num);
             ch[i]=(char)num;
         }

         Upass=String.valueOf(ch);
         StringBuffer str=new StringBuffer(Upass);

         char[] ch1=pass.toCharArray();
         for(int j=0;j<ch1.length;j=j+2)
         {
             fi=fi+String.valueOf(ch1[j]);
         }
         System.out.println(fi);
         str.append(fi);
         str.append("659");
         str.reverse();
         Upass=str.toString();
         return Upass;
     }

}
