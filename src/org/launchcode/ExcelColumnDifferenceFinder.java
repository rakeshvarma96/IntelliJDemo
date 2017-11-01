package org.launchcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ExcelColumnDifferenceFinder  {
    public static void main(String[] args) throws FileNotFoundException {
        String a = "654,897,4677,46776,B,A,img:9087,img:45";
        String b = "897,4677,654,78,A,img:45";
        String c[] = a.split(",");
        String d[] = b.split(",");
        ArrayList<String> ar = new ArrayList<String>();
        PrintWriter pw = new PrintWriter(new File("D:\\testout.txt"));
        for(int i = 0; i < c.length; i++)
        {
            if(!Arrays.asList(d).contains(c[i]))
            {
                ar.add(c[i]);
                pw.write(c[i]+" ");
            }
        }
        pw.close();
        System.out.println(ar);
    }

}
