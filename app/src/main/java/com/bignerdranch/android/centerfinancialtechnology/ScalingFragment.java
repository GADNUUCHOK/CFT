package com.bignerdranch.android.centerfinancialtechnology;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class ScalingFragment extends Fragment {

    private ImageButton mButtonTurn;
    private ImageButton mButtonGamma;
    private ImageButton mButtonMirror;
    private Button mButton;
    private ImageView mPhotoView;
    private RecyclerView mPoleRecyclerView;
    private SpisokAdapter mAdapter;
    private Uri mSelectedImageUri;
    private ProgressBar mProgressBar;
    private static final int REQUEST_PHOTO = 123;
    private static final int SELECT_PICTURE = 1;
    private static final int URI_PICTURE = 12;
    private String URIIMG = "http://www.nanonewsnet.ru/files/thumbs/2013/004_47.jpg";
    private Bitmap bitmap;
    private Image contentImage=null;
    private static final String URI_IMAGE = "com.bignerdranch.android.centerfinancialtechnology.uri_image";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mPhotoView = new ImageView(getActivity());
    }

    /**@Override
    public void onPause() {
        super.onPause();

        //PoleLab.get(getActivity()).updatePole(mPole);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scaling, container, false);
        mButtonTurn = (ImageButton) v.findViewById(R.id.turn);
        mButtonGamma = (ImageButton) v.findViewById(R.id.gamma);
        mButtonMirror = (ImageButton) v.findViewById(R.id.mirror);
        mButton = (Button) v.findViewById(R.id.download);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(getActivity());
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_image)
                        .setView(editText)
                        .setPositiveButton(R.string.photo,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(captureImage, REQUEST_PHOTO);
                                    }
                                })
                        .setNegativeButton(R.string.gallery,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final Intent intent = new Intent();
                                        intent.setType("image/*");
                                        intent.setAction(Intent.ACTION_GET_CONTENT);
                                        startActivityForResult(Intent.createChooser(intent,
                                                "Select Picture"), SELECT_PICTURE);
                                    }
                                })
                        .setNeutralButton(R.string.uri,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri uri = Uri.parse(editText.getText().toString());
                                        URIIMG = uri.toString();
                                        ProgressTask prTask = new ProgressTask(mPhotoView);
                                        prTask.execute(URIIMG);
                                    }
                                });
                builder.show();
                mButton.setVisibility(View.INVISIBLE);
                mPhotoView.setVisibility(View.VISIBLE);
            }
        });
        mPhotoView = (ImageView) v.findViewById(R.id.photo);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        mProgressBar.setMax(100);
        mPhotoView.setVisibility(View.INVISIBLE);
        if (mSelectedImageUri != null) {
            mPhotoView.setImageURI(mSelectedImageUri);
        }
        mPoleRecyclerView = (RecyclerView) v.findViewById(R.id.listView);
        mPoleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        mPhotoView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final EditText editText = new EditText(getActivity());
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                                builder.setTitle(R.string.dialog_image)
                                                        .setView(editText)
                                                        .setPositiveButton(R.string.photo,
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                                        startActivityForResult(captureImage, REQUEST_PHOTO);
                                                                    }
                                                                })
                                                        .setNegativeButton(R.string.gallery,
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        final Intent intent = new Intent();
                                                                        intent.setType("image/*");
                                                                        intent.setAction(Intent.ACTION_GET_CONTENT);
                                                                        startActivityForResult(Intent.createChooser(intent,
                                                                                "Select Picture"), SELECT_PICTURE);
                                                                    }
                                                                })
                                                        .setNeutralButton(R.string.uri,
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        Uri uri = Uri.parse(editText.getText().toString());
                                                                        URIIMG = uri.toString();
                                                                        ProgressTask prTask = new ProgressTask(mPhotoView);
                                                                        prTask.execute(URIIMG);
                                                                    }
                                                                });
                                                builder.show();
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
            mAdapter.setPoles(poles);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void update(ImageView imageView) {
        PoleLab poleLab = PoleLab.get(getActivity());
        List<Pole> poles = poleLab.getPoles();
        if (poles.size() % 2 == 0) {
            imageView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            imageView.setBackgroundColor(getResources().getColor(R.color.colorWhite));
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
        if (requestCode == URI_PICTURE && resultCode == Activity.RESULT_OK) {
            Bitmap url = (Bitmap) data.getExtras().get("data");
            new ProgressTask(mPhotoView).execute(URIIMG);
            onReceivedTitle(mPhotoView, "Download");
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void onProgressChanged(ImageView imageView, int newProgress) {
        if (newProgress == 100) {
            mProgressBar.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(newProgress);
        }
    }
    public void onReceivedTitle(ImageView imageView, String title) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(title);
    }

    public Bitmap getBitmapUrl(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap mBitmap = BitmapFactory.decodeStream(input);
            return mBitmap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class ProgressTask extends AsyncTask<String, Void, Bitmap> {

        ImageView imgV;
        Bitmap bitmap;

        public ProgressTask(ImageView imgV) {
            this.imgV = imgV;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onProgressChanged(mPhotoView, 0);
            onReceivedTitle(mPhotoView, "Download");
        }

        @Override
        protected Bitmap doInBackground(String... url){
            String urldisplay = url[0];
            bitmap = null;
            try{
                InputStream srt = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(srt);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {

            super.onPostExecute(bitmap);
            imgV.setImageBitmap(bitmap);
            onProgressChanged(mPhotoView, 100);
            onReceivedTitle(mPhotoView, null);
        }

    }


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
                    /**PoleLab poleLab = PoleLab.get(getActivity());
                    List<Pole> poles = poleLab.getPoles();
                    mImageView.setBackground(null);
                    if (poles.size() % 2 == 0) {
                        itemView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    }*/
                    update(mImageView);
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

            /**Bitmap bitmap = Bitmap.createBitmap(idImage.getWidth(),
                    idImage.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);

            Paint paint = new Paint();
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(bitmap, 0, 0, paint);*/

        }

        private void animation() {
            RotateAnimation ra = new RotateAnimation(0f, 90f);
            ra.setDuration(1000);
            ra.setFillAfter(true);
            mImageView.startAnimation(ra);
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

        public void setPoles(List<Pole> poles) {
            mPoles = poles;
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
