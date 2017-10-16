/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.nadia.exos.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class ExosFile {

    public static void main(String[] args)  {
        //lecture d un fichier
        try {
            // listFiles("c:/xampp");
            // listFiles("c:/windows");
            writeTextFile("liste.txt","Un frigidaire\n" );
            writeTextFile("liste.txt", "un evier en rouge\n");
            writeTextFile("data/list.txt", "pomme");
        } catch (IOException ex) {
            Logger.getLogger(ExosFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //lecture d un fchier
            String fileContent = readTextFile("liste8.txt");
           
            System.out.println("fileContent");
        } catch (FileNotFoundException ex) { 
            System.out.println("le fichier est introuvable");
        } catch (IOException ex) {
            Logger.getLogger(ExosFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } 
    public static String readTextFile(String path) throws FileNotFoundException, IOException{
        String content = "";
        
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        
        String line;
        
        while((line = br.readLine()) != null){
            content += line;
            
        }
        
        
        
        return content;
    }
    /**
     * ecrit un contenu dans un  ficher texte
     * @param path 
     * @param Content
     */
    public static void writeTextFile(String path, String content) throws IOException{
        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(content);
        bw.close();
        fw.close();
    }

    public static void listFiles(String path) {

        File f = new File(path);

        File[] contentList = f.listFiles();
        for (File file : contentList) {
            String fileInfo;
            if (file.isDirectory()) {
                fileInfo = "d";

            } else {
                fileInfo = "f";
            }
            // fileInfo egal fileInfo+ file.getName
            fileInfo += file.getName();
            fileInfo += " (" + file.length() + ")";

            System.out.println(fileInfo);

        }

    }

}
