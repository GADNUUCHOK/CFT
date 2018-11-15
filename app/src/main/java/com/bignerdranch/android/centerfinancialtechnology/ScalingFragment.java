package com.bignerdranch.android.centerfinancialtechnology;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;
import java.util.UUID;


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
    private Uri mSelectedImageUri;
    private static final int REQUEST_PHOTO= 123;
    private static final int SELECT_PICTURE = 1;
    private List<Pole> mSaveList;
    private PoleLab mSaveLab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mPhotoView = new ImageView(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scaling, container, false);
        mButtonTurn = (ImageButton) v.findViewById(R.id.turn);
        mButtonGamma = (ImageButton) v.findViewById(R.id.gamma);
        mButtonMirror = (ImageButton) v.findViewById(R.id.mirror);
        mPhotoView = (ImageView) v.findViewById(R.id.photo);
        if (mSelectedImageUri != null) {
            mPhotoView.setImageURI(mSelectedImageUri);
        }
        mPoleRecyclerView = (RecyclerView) v.findViewById(R.id.listView);
        mPoleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        mPhotoView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                startActivityForResult(captureImage, REQUEST_PHOTO);
                                                final Intent intent = new Intent();
                                                intent.setType("image/*");
                                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                                startActivityForResult(Intent.createChooser(intent,
                                                        "Select Picture"), SELECT_PICTURE);
                                            }
                                        });

        return v;
        /**if (savedInstanceState != null) {
            Bitmap bitmap = savedInstanceState.getParcelable("image");
            mPhotoView.setImageBitmap(bitmap);
        }*/

    }

    private void updateUI() {
        PoleLab poleLab = PoleLab.get(getActivity());
        List<Pole> poles = poleLab.getPoles();
        if (mAdapter == null){
            mAdapter = new SpisokAdapter(poles);
            mPoleRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
    }
    public void setDrawable(Drawable drawable) {
        mPhotoView.setImageDrawable(drawable);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mPhotoView.setImageBitmap(photo);
            mSelectedImageUri = data.getData();
        }
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            mPhotoView.setImageURI(selectedImageUri);
            mSelectedImageUri = data.getData();
        }
    }
    /**@Override
    protected void onSaveInstanceState(Bundle outState) {
        BitmapDrawable drawable = (BitmapDrawable) mPhotoView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        outState.putParcelable("image", bitmap);
        super.onSaveInstanceState(outState);
    }*/

    public class SpisokHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView mImageView;
        private Image Image1;
        private Pole mPole;
        private static final String DIALOG_POLE = "DialogPole";

        public SpisokHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.row_fragment, parent, false));
            itemView.setOnClickListener(this);
            mImageView = (ImageView) itemView.findViewById(R.id.idImage);
            mButtonTurn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pole pole = new Pole();
                    PoleLab.get(getActivity()).addPole(pole);
                    PoleLab poleLab = PoleLab.get(getActivity());
                    List<Pole> poles = poleLab.getPoles();
                    mImageView.setBackground(null);
                    if (poles.size() % 2 == 0) {
                        itemView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    }
                    Drawable drawable = mPhotoView.getDrawable();
                    mImageView.setImageDrawable(drawable);
                    mImageView.animate().rotation(90);

                }
            });
            mButtonGamma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pole pole = new Pole();
                    PoleLab.get(getActivity()).addPole(pole);
                    PoleLab poleLab = PoleLab.get(getActivity());
                    List<Pole> poles = poleLab.getPoles();
                    //setDrawable(mPhotoView.getDrawable());
                    mImageView.setBackground(null);
                    if (poles.size() % 2 == 0) {
                        itemView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    }
                    Drawable drawable = mPhotoView.getDrawable();
                    Drawable photo = drawable;
                    mImageView.setImageDrawable(photo);
                    setBlackAndWhite(mImageView);

                    //mAdapter.setItems(poles);
                    //mAdapter.notifyDataSetChanged();

                }
            });
            mButtonMirror.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pole pole = new Pole();
                    PoleLab.get(getActivity()).addPole(pole);
                    PoleLab poleLab = PoleLab.get(getActivity());
                    List<Pole> poles = poleLab.getPoles();
                    mImageView.setBackground(null);
                    if (poles.size() % 2 == 0) {
                        itemView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    }
                    mImageView.setImageDrawable(mPhotoView.getDrawable());
                    setRotation(mImageView);
                    //mImageView.setRotationY(180);

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

        private void setRotation(ImageView idImage) {
            idImage.setRotationY(180);
        }

        public void bind(Pole pole) {
            mPole = pole;
        }

        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.dialog_pole_title)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mPhotoView.setImageDrawable(mImageView.getDrawable());
                                }
                            })
                    .setNegativeButton(android.R.string.cut,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mAdapter.deleteItem(getAdapterPosition());
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
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

        public void setItems(List<Pole> poles) {
            this.mPoles = poles;
        }

        public void deleteItem(int index) {
            mPoles.remove(index);
            notifyItemRemoved(index);
        }
    }
}
