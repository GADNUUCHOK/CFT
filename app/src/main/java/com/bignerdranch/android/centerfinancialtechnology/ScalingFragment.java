package com.bignerdranch.android.centerfinancialtechnology;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScalingFragment extends Fragment {

    private ImageButton mPhotoButton;
    private ImageButton mPictureButton;
    private ImageView mPhotoView;
    private static final int REQUEST_PHOTO= 123;
    private static final int SELECT_PICTURE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scaling, container, false);
        mPhotoButton = (ImageButton) v.findViewById(R.id.camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                startActivityForResult(captureImage, REQUEST_PHOTO);
                                            }
                                        });
        mPhotoView = (ImageView) v.findViewById(R.id.photo);
        mPictureButton = (ImageButton) v.findViewById(R.id.picture);
        //final Intent pictureImage = new Intent(MediaStore.ACTION_GET_CONTENT);
        mPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });
        return v;

    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mPhotoView.setImageBitmap(photo);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            mPhotoView.setImageURI(selectedImageUri);
        }
    }
}
