
/**
 * Write a description of class StreamInputOutput here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStreamReader;
public class StreamInputOutput{
    public static void main(String[] args) throws IOException{
        FileInputStream fis = null;
        FileOutputStream fos = null;
        
        char c;
        int i;
        try{
            // New input stream Reader
            fis = new FileInputStream("test1.txt");
            fos = new FileOutputStream("OutTest.txt");
            while((i = fis.read()) != 1){
                System.out.println("Binary Number: " + i);
                fos.write(i);
                
                c = (char)i;
                // print char
                System.out.println("Character Read: " + c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null) fis.close();
            if(fos != null) fos.close();
        }
        
        RandomAccessFile raf = new RandomAccessFile("OutText.txt","rw");
        
        raf.seek(3);
        int n = raf.read();
        n = n + 1;
        
        raf.seek(3);
        raf.write(n);
        n = 13;
        raf.seek(raf.length());
        raf.write(n);
        n=10;
        raf.write(n);
        n=65;
        raf.write(n);
        raf.close();
        
        String a = "Hello World";
        byte[] b = {'e','x','a','m','p','l','e'};
        
        //create a new file with an ObjectOutputStream
        
        FileOutputStream out = new FileOutputStream("test2.txt");
        ObjectOutputStream oout = new ObjectOutputStream(out);
        
        oout.writeObject(s);
        oout.writeObject(b);
        
        oout.flush();
        oout.close();
        out.close();
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test2.txt"));
        
        System.out.println("First Object: " + (String)ois.readObject() );
        byte[] read = (byte[])ois.readObject();
        String s2 = new String(read);
        System.out.println("Second Object: " + s2);
        ois.close();
    }{
    }
}
