package com.company;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.commons.text.similarity.LevenshteinDistance;

import javax.swing.filechooser.FileSystemView;

public class Main {

    public static void main(String[] args) {

        // Work out where the shortcut directory should be
        Path shortcutDir = Path.of(System.getProperty("user.home") + "\\shortcuts\\");

        // Get desktop directory
        Path desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().toPath();
        Path sharedDesktop = Path.of("C:\\Users\\Public\\Desktop");

        // Create the filename filter
        FilenameFilter lnkFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return (name.matches("(.*\\.lnk)") || name.matches("(.*\\.url)"));
            }
        };

        // For each file in the desktop directory
        String[] newShortcuts = desktopPath.toFile().list(lnkFilter);
        String[] sharedShortcuts = sharedDesktop.toFile().list(lnkFilter);

        if(newShortcuts.length > 0 || sharedShortcuts.length > 0) {
            System.out.println("New Shortcuts Found!");
            for(String s : newShortcuts){
                System.out.println("\t" + s);
                try {
                    Files.move(desktopPath.resolve(s), shortcutDir.resolve(s), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Failed to move " + s);
                }
            }
            for(String s : sharedShortcuts){
                System.out.println("\t" + s);
                try {
                    Files.move(sharedDesktop.resolve(s), shortcutDir.resolve(s), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Failed to move " + s);
                }
            }
        }


        // Get program to open
	    String programName = "";
        Boolean listPrograms = false;
        Boolean betterDebug = false;

        for(String s : args) {
            switch(s) {

                case "--list":
                case "-l":
                    listPrograms = true;
                    break;

                case "--debug":
                case "-d":
                    betterDebug = true;
                    break;

                case "--help":
                case "-h":
                case "-?":
                    System.out.println("USAGE:\n\t" +
                            "pg <program>    : opens specified program or closest match\n\t" +
                            "pg --list       : Lists the available programs\n\t" +
                            "pg --debug      : Provides better debugging\n\t");

                default:
                    programName += s + " ";
                    break;
            }
        }
        programName = programName.trim();

        // Check if user shortcuts directory exists
        if(!Files.exists(shortcutDir)) {
            // Create it
            try {
                Files.createDirectory(shortcutDir);
            } catch (IOException e) {
                System.out.println("[ERROR] Shortcut directory not found and attempts to create it failed");
                if(betterDebug) { e.printStackTrace(); }
            }
        }

        // Get a list of all programs in the shortcutDir directory
        File dirFile = new File(shortcutDir.toString());
        String[] programs = dirFile.list();

        // List the programs available
        if(listPrograms && programs.length > 0) {
            System.out.println("Programs Available: ");
            for(String p : programs) {
                String pstr = p.split("\\.")[0];
                System.out.println("\t" + pstr);
            }
        } else {
            // Get Levenshtein distance of each possible match
            LevenshteinDistance ld = new LevenshteinDistance();

            String closestProgram = "";
            int bestDistance = Integer.MAX_VALUE;
            for (String p : programs) {
                String compString = p.toLowerCase().split("\\.")[0];
                int dist = ld.apply(programName, compString);
                if (dist < bestDistance) {
                    closestProgram = p;
                    bestDistance = dist;
                }
            }

            // Run program
            File programToRunFile = new File(shortcutDir.toString() + "\\" + closestProgram);
            if(programToRunFile.exists()) {
                System.out.println("Opening " + closestProgram.split("\\.")[0]);
                try {
                    //Runtime.getRuntime().exec(programToRunFile.getAbsolutePath());
                    ProcessBuilder pb = new ProcessBuilder("cmd", "/c", programToRunFile.getAbsolutePath(), "-n", "100");
                    Process process = pb.start();
                } catch (IOException e) {
                    System.out.println("[ERROR] error executing " + programToRunFile.getAbsolutePath());
                    if(betterDebug) { e.printStackTrace(); }
                }
            } else {
                System.out.println("[ERROR] could not locate " + programToRunFile.getAbsolutePath());
            }
        }
    }
}
