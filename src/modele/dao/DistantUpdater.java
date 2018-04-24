/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author wquentel
 */
public class DistantUpdater {

    public static void writeFile(String requete) throws FileNotFoundException, IOException {
        File fout = new File("properties/distantDtbUpdate");
        try (FileWriter fw = new FileWriter(fout, true);
                BufferedWriter bw = new BufferedWriter(fw);) {
            bw.write(requete);
            bw.newLine();

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static int countLines() throws FileNotFoundException, IOException {
        int result = 0;
        BufferedReader reader = new BufferedReader(new FileReader("properties/distantDtbUpdate"));
        while (reader.readLine() != null) {
            result++;
        }
        reader.close();
        return result;
    }

    public static String[] fileReader() throws IOException {
        int nbLines = countLines();
        String[] fileContent = new String[nbLines];
        try {
            BufferedReader in = new BufferedReader(new FileReader("properties/distantDtbUpdate"));
            String str;
            int i = 0;
            while ((str = in.readLine()) != null) {
                fileContent[i] = str;
                System.out.println(str);
                i++;
            }
            in.close();
        } catch (IOException e) {
        }
        return fileContent;
    }

    public static void deleteFile() {
        String thePath = "properties/distantDtbUpdate";
        Path xPath = Paths.get(thePath);
        try {
            Files.delete(xPath);
            System.out.println("File deleted");
            File fout = new File("properties/distantDtbUpdate");
            try (FileWriter fw = new FileWriter(fout, false);
                    BufferedWriter bw = new BufferedWriter(fw);) {
                bw.write("");
            } catch (IOException ex) {
                Logger.getLogger(DistantUpdater.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("File not deleted");
            }
        } catch (IOException ex) {
            Logger.getLogger(DistantUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void executeCommand(String command) {
        String[] cmdarray = {"/bin/sh","-c",command};
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmdarray);
            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sqlScriptCreator() {
            executeCommand("rm properties/localDtb.sql");
            executeCommand("touch properties/localDtb.sql");
            executeCommand("mysqldump -h172.15.6.240 -uwquentel_festiva -pUraret98 wquentel_festival2 > properties/localDtb.sql");
    }
    
    public static void pushToDistant(){
        executeCommand("mysql -hlocalhost -uwquentel_festiva -pUraret98 wquentel_festival2 < properties/localDtb.sql");
        JOptionPane.showMessageDialog(null, "Base locale remplacée par la base distante");
    }
}
