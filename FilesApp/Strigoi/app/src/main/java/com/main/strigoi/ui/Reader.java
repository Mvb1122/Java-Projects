package com.main.strigoi.ui;

import android.content.Context;

import com.main.strigoi.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Reader {
    private Context context = MainActivity.getContext();

    public Reader() {
      // Literally do nothing.
    };

    public void writeFile(String data, String fileName) {
        // Context context = getApplicationContext();
        File path = context.getFilesDir();
        File file = new File(path, "/" + fileName);

        System.out.println(file.toString());

        try (FileOutputStream stream = new FileOutputStream(file)) {
            try {
                stream.write(data.getBytes());
            } finally {
                stream.close();
            }
            System.out.println("File written.");
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public String getPath() {
        // Context context = getApplicationContext();
        File path = context.getFilesDir();
        return path.toString();
    }

    public String readFile(String inputPath) throws IOException {

        File path = new File(context.getFilesDir(), "/" + inputPath);
        int length = (int) path.length();
        byte[] bytes = new byte[length];

        try (FileInputStream in = new FileInputStream(path)) {
            try {
                in.read(bytes);
            } catch (IOException e) {
                in.close();
                return e.toString();
            }
        } catch (IOException e) {
            throw e;
        }
        return new String(bytes);
    }

    public boolean fileExists(String path, String type) {
        String file = null;
        try {
            file = this.readFile(path + type);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
