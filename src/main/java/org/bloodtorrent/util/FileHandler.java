package org.bloodtorrent.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 4/5/13
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileHandler {
    public File open(String s) throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    public boolean save(File file) throws IOException {
        file.createNewFile();
        return true;
    }

    public void mkdir(File folder) {
        if(! folder.exists()) {
           folder.mkdir();
        }
    }
}
