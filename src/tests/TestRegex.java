/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew
 */
public class TestRegex {

    public static void main(String[] args) {
        String filename = "/home/mspace/NetBeansProjects/emailsource/src/java/mspace/viewSentEmail.java";
        String content = null;
        File file = new File(filename);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            String g = content.replaceAll("[/][*]\\s[0-9]+\\s[*][/]|[/][*][0-9]+[*][/]|[/][*]\\s\\s[0-9]+\\s[*][/]|[/][*]\\s\\s\\s[0-9]+\\s[*][/]", "");
            String h = g.replaceAll("[/-/]", "");
            String j = h.replaceAll("[*]*-*", "");
            String output = "/home/mspace/NetBeansProjects/emailsource/src/java/mspace/viewSentEmail.java";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
                bw.write(j);
            } catch (IOException ex) {
                Logger.getLogger(TestRegex.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("regex..: " + j);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(TestRegex.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
