package com.nathaliebritan.imageviewapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Nathalie Britan on 08.07.2016.
 */
public class FileManager {
    public static final String IMAGE_FILE = "sample_file.jpg";
    private static String APPLICATION_TAG = "GALLERY_CAMERA_DEMO";
    private static String path;

    private static void initPath(Context context) {

        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ImageViewer/images";
        }
        else {
            path = context.getCacheDir().getAbsolutePath();
        }
    }

    public static void createDefaultFolder(Context context) {
        initPath(context);
        File file = new File(path);
        boolean success = file.mkdirs();
        Log.i(APPLICATION_TAG, success + "");
    }

    public static File createFile(String filename) {
        File file = new File(path + "/" + filename);
        return file;
    }
}
