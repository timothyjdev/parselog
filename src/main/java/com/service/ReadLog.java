/**
 * This class is used to search a log file for a term and write
 * the line from the log to a text file if and when the term is
 * found.
 */

package com.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class ReadLog {

/**
 * Reference to the log4j logger
 */
private static final Logger LOG = Logger.getLogger(ReadLog.class);	

    /**
     * A file name, path and search term are passed to this class at runtime.
     * The file is searched for the term. If the term is found the line
     * from the log is written to a text file.
     */
    public static void main(String[] args) {

        FileInputStream fstream = null;
        BufferedReader reader = null;
        Writer writer = null;

        try {
            fstream = new FileInputStream(args[0]);
            reader = new BufferedReader(new InputStreamReader(fstream));
            writer = new BufferedWriter(new FileWriter(args[1] + ".txt"));
            String line = "";

            while ((line = reader.readLine()) != null)   {

                if (line.indexOf(args[1]) != -1) {
                	writer.write(line);
                	if (LOG.isDebugEnabled()) {
                		LOG.debug(line);
                	}
                }				   				  
            }
        } catch (IOException e) {
            LOG.error("Error: " + e.getMessage());

        } finally {
        	IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(fstream);
        }
    }
}