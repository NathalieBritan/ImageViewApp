package com.nathaliebritan.imageviewapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView image;

    private static int THUMBNAIL_SIZE = 300;
    private static final int SELECT_PICTURE_REQUEST_CODE = 232;

    private static Uri fileUri;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView);

        FileManager.createDefaultFolder(MainActivity.this);
        File file = FileManager.createFile(FileManager.IMAGE_FILE);
        fileUri = Uri.fromFile(file);

    }

    public void onChoosePictClick(View view)
    {
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Image");

        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {
                captureIntent });
        startActivityForResult(chooserIntent, SELECT_PICTURE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == SELECT_PICTURE_REQUEST_CODE) {
                    if (data != null) {
                        fileUri = data.getData();
                    }
                    bmp = ImageManager.getThumbnail(this, fileUri, THUMBNAIL_SIZE);
                    image.setImageBitmap(bmp);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
