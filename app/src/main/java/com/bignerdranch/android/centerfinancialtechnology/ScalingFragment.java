package com.bignerdranch.android.centerfinancialtechnology;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScalingFragment extends Fragment {

    private ImageButton mButtonTurn;
    private ImageButton mButtonGamma;
    private ImageButton mButtonMirror;
    private ImageView mPhotoView;
    private RecyclerView mPoleRecyclerView;
    private SpisokAdapter mAdapter;
    private static final int REQUEST_PHOTO= 123;
    private static final int SELECT_PICTURE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scaling, container, false);
        mButtonTurn = (ImageButton) v.findViewById(R.id.turn);
        mButtonGamma = (ImageButton) v.findViewById(R.id.gamma);
        mButtonMirror = (ImageButton) v.findViewById(R.id.mirror);
        mPhotoView = (ImageView) v.findViewById(R.id.photo);
        mPoleRecyclerView = (RecyclerView) v.findViewById(R.id.listView);
        mPoleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        mPhotoView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                startActivityForResult(captureImage, REQUEST_PHOTO);
                                                Intent intent = new Intent();
                                                intent.setType("image/*");
                                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                                startActivityForResult(Intent.createChooser(intent,
                                                        "Select Picture"), SELECT_PICTURE);
                                            }
                                        });
        return v;

    }

    private void updateUI() {
        PoleLab poleLab = PoleLab.get(getActivity());
        List<Pole> poles = poleLab.getPoles();
        mAdapter = new SpisokAdapter(poles);
        mPoleRecyclerView.setAdapter(mAdapter);
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

    public class SpisokHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView mImageView;
        private Pole mPole;

        public SpisokHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.row_fragment, parent, false));
            itemView.setOnClickListener(this);
            mImageView = (ImageView) itemView.findViewById(R.id.idImage);
            mButtonTurn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pole pole = new Pole();
                    PoleLab.get(getActivity()).addPole(pole);
                    mImageView.setImageDrawable(mPhotoView.getDrawable());
                    mImageView.animate().rotation(90);

                }
            });
            mButtonGamma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pole pole = new Pole();
                    PoleLab.get(getActivity()).addPole(pole);
                    mImageView.setImageDrawable(mPhotoView.getDrawable());
                    setBlackAndWhite(mImageView);
                }
            });
            mButtonMirror.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pole pole = new Pole();
                    PoleLab.get(getActivity()).addPole(pole);
                    mImageView.setImageDrawable(mPhotoView.getDrawable());
                    mImageView.setRotationY(180);

                }
            });
        }

        private void setBlackAndWhite(ImageView idImage){

            float brightness = (float)(0 / 255);

            float[] colorMatrix = {
                    0.33f, 0.33f, 0.33f, 0, brightness, //red
                    0.33f, 0.33f, 0.33f, 0, brightness, //green
                    0.33f, 0.33f, 0.33f, 0, brightness, //blue
                    0, 0, 0, 1, 0    //alpha
            };

            ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
            idImage.setColorFilter(colorFilter);

        }

        public void bind(Pole pole) {
            mPole = pole;
        }

        @Override
        public void onClick(View view) {

        }

    }
    private class SpisokAdapter extends RecyclerView.Adapter<SpisokHolder> {

        private List<Pole> mPoles;

        public SpisokAdapter(List<Pole> poles) {
            mPoles = poles;
        }

        @NonNull
        @Override
        public SpisokHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SpisokHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SpisokHolder holder, int position) {
            Pole pole = mPoles.get(position);
            holder.bind(pole);

        }

        @Override
        public int getItemCount() {
            return mPoles.size();
        }
    }
}
