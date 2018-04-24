/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.IOException;
import modele.dao.DistantUpdater;

/**
 *
 * @author wquentel
 */
public class testDistantUpdater {

    public static void main(String args[]) throws IOException {
        System.out.println("-----------------------------------------------------------");
        System.out.println("comptage des lines");
        System.out.println("-----------------------------------------------------------");
        System.out.println(DistantUpdater.countLines());
        System.out.println("-----------------------------------------------------------");
        System.out.println("test d'ajout dans le fichier");
        System.out.println("-----------------------------------------------------------");
        String add = "test d'ajout d'une line";
        boolean isNew = false;
        for (int i = 0; i < 5; ++i) {
            if (isNew) {
                DistantUpdater.writeFile(add);
                System.out.println(i);
            } else {
                DistantUpdater.writeFile(add);
                isNew = true;
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("comptage des lines");
        System.out.println("-----------------------------------------------------------");
        System.out.println(DistantUpdater.countLines());
        System.out.println("-----------------------------------------------------------");
        System.out.println("affichage");
        System.out.println("-----------------------------------------------------------");
        String[] content=DistantUpdater.fileReader();
        for (int i=0;i<content.length;i++){
            System.out.println(content[i]);
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("suppréssion du fichier");
        System.out.println("-----------------------------------------------------------");
        DistantUpdater.deleteFile();
        System.out.println("-----------------------------------------------------------");
        System.out.println("Création du script dans le dossier properties");
        System.out.println("-----------------------------------------------------------");
        DistantUpdater.sqlScriptCreator();
        System.out.println("-----------------------------------------------------------");
        System.out.println("push du script de base 1 vers base 2");
        System.out.println("-----------------------------------------------------------");
        DistantUpdater.pushToDistant();
    }
}
