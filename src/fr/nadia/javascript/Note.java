package fr.nadia.javascript;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrateur
 */
public class Note {

    private String title;
    private String text;
    private String FileName;
    private String FolderName = "notes";

    private String fileExtension = ".txt";

    public Note() {

    }

    public Note(String FileName) {
        this.FileName = FileName;
    }

    public Note(String title, String text, String FileName) {
        this.title = title;
        this.text = text;
        this.FileName = FileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String FolderName) {
        this.FolderName = FolderName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String FileExtension) {
        this.fileExtension = FileExtension;
    }

    /**
     * Sauvegarde une note sur le disque dur
     *
     */
    public void saveNote() throws IOException {

       //le contenu de fichier note est egal à 
        StringBuilder sb = new StringBuilder();
        sb.append(this.title);
        sb.append("\n");
        sb.append(this.text);

       // fileContent: c est la variable
        String fileContent = sb.toString();

       // instanciation de fichier
        File noteFile = new File(this.FolderName, this.FileName + this.fileExtension);
        //! pas existe si le fichier n existe pas on le créé
        if (!noteFile.exists()) {
            noteFile.createNewFile();

        }

        // ecriture dans le fichier
        PrintWriter pw = new PrintWriter(noteFile);
        pw.write(fileContent);
        pw.flush();
        pw.close();

    }
    
    public void newNote() throws IOException{
        //this.FileName = String.valueOf((new Date()).getTime());
        String baseFileName = this.title.replaceAll(" ", "-");
        String fileName = baseFileName;
       int suffix= 0;
       
       File f = new File(this.FolderName, baseFileName + this.fileExtension);
       
       while(f.exists()){
           suffix ++;
          fileName = baseFileName + "-" + suffix;
           f = new File(FolderName, fileName + this.fileExtension);
       }
      // attribution du nom trouvé a la propriète fileName de l objet
       this.FileName = fileName; 
       this.saveNote();
    }

    /**
     * Chargement d'une note
     */
    public void loadNote() throws IOException {
        //INstanciation du fichier
        File noteFile = new File(this.FolderName, this.FileName + this.fileExtension);

        // lire le fichier instanciation de fileReader et un BufferReader ( pour lire le fichier ligne à ligne)
        FileReader fr = new FileReader(noteFile);
        BufferedReader br = new BufferedReader(fr);

        // reccuperation de titre
        this.title = br.readLine();

       //reccuperation du texte
        String line;
        StringBuilder sb = new StringBuilder();

       //boucle ligne à ligne jusqu a la fin de fichier
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        this.text = sb.toString();
        
        br.close();
        fr.close();

    }

    public void deleteNote() {
        //instanciation du fichier
        File noteFile = new File(this.FolderName, this.FileName + this.fileExtension);
        boolean ok = noteFile.delete();
        System.out.println("suppression : " + ok);
    }

}
