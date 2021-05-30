package com.main.strigoi.ui;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.main.strigoi.MainActivity;

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

    public String readFile(String inputPath) {

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
            return e.toString();
        }
        return new String(bytes);
    }

    public boolean fileExists(String path, String type) {
        String file = this.readFile(path + type);
        if (file.length() >= 1) {
            /*
            if (file.startsWith("java.io.FileNotFoundException:")) {
                return false;
            } else {
                */return true;
            // }
        } else {
            return false;
        }
    }
}
