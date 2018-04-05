package com.cayhualla.lab_8_permisosandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class CalificadoActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALERRY = 0013;

    private Button btnshow;
    private TextView txtview;

    private CircleImageView pofileImage;
    private String[] items = {"Camara", "Galeria"};
   // final String URL = "https://www.websa100.com/wp-content/uploads/2016/05/foto-de-perfil-adecuada.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificado);


        btnshow = (Button) findViewById(R.id.btn_showimage);
        txtview = (TextView) findViewById(R.id.txt_view);

        pofileImage = (CircleImageView) findViewById(R.id.profile_image);
      //  Picasso.with(getApplicationContext()).load(URL).into(pofileImage);


        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abririmagen();

            }
        });
    }

    public void abririmagen() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opciones");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camara")) {
                    EasyImage.openCamera(CalificadoActivity.this, REQUEST_CODE_CAMERA);

                } else if (items[i].equals("Galeria")) {
                    EasyImage.openGallery(CalificadoActivity.this, REQUEST_CODE_GALERRY);

                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                switch (type) {
                    case REQUEST_CODE_CAMERA:
                        Glide.with(CalificadoActivity.this)
                                .load(imageFiles)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(pofileImage);
                        break;
                    case REQUEST_CODE_GALERRY:
                        Glide.with(CalificadoActivity.this)
                                .load(imageFiles)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(pofileImage);
                        break;

                }
            }

        });
    }
}
