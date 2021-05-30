package com.main.reader;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Reader {
    public void writeFile(String data, String fileName, Context context) throws IOException {
        // Context context = getApplicationContext();
        File path = context.getFilesDir();
        File file = new File(path, "/" + fileName);

        System.out.println(file.toString());

        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write(data.getBytes());
        } finally {
            stream.close();
        }
        System.out.println("File written.");
    }

    public String getPath(Context context) {
        // Context context = getApplicationContext();
        File path = context.getFilesDir();
        return path.toString();
    }

    public String readFile(String inputPath, Context context) throws IOException {

        File path = new File(context.getFilesDir(), "/" + inputPath);
        int length = (int) path.length();
        byte[] bytes = new byte[length];

        FileInputStream in = new FileInputStream(path);
        try {
            in.read(bytes);
        } catch (IOException e) {
            in.close();
            return e.toString();
        }
        return new String(bytes);
    }
}
