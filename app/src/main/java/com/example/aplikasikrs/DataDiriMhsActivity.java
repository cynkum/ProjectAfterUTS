package com.example.aplikasikrs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasikrs.Model.DefaultResult;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;
public class DataDiriMhsActivity extends AppCompatActivity {

    private boolean isInsert = false;
    ProgressDialog progressDialog;
    EditText nim, nama, alamat, email, foto;
    ImageView imgMhs;
    private String idMhs = "";
    private String stringImg = "";
    private static final int GALLERY_REQUEST_CODE = 58;
    private static final int FILE_ACCESS_REQUEST_CODE = 58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_diri_mhs);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, FILE_ACCESS_REQUEST_CODE);
        }
        Button btnUpload = findViewById(R.id.btnUploadDataDiriMhs);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });
        Button btnSaveDataDiri = (Button) findViewById(R.id.btnSaveDataDiriMhs);

        btnSaveDataDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValid = true;
                //Validation

                if (nama.getText().toString().matches("")) {
                    nama.setError("Silahkan mengisi nama mahasiswa");
                    isValid = false;
                }

                if (nim.getText().toString().matches("")) {
                    nim.setError("Silahkan mengisi NIM mahasiswa");
                    isValid = false;
                }

                if (alamat.getText().toString().matches("")) {
                    alamat.setError("Silahkan mengisi alamat mahasiswa");
                    isValid = false;
                }

                if (email.getText().toString().matches("")) {
                    email.setError("Silahkan mengisi email mahasiswa");
                    isValid = false;
                }

                if (foto.getText().toString().matches("")) {
                    foto.setError("Silahkan mengisi foto mahasiswa");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(DataDiriMhsActivity.this);
                nama = (EditText) findViewById(R.id.txtNamaMhs);
                nim = (EditText) findViewById(R.id.txtNim);
                alamat = (EditText) findViewById(R.id.txtAlamatMhs);
                email = (EditText) findViewById(R.id.editText4);
                foto = (EditText) findViewById(R.id.txtFoto);
                imgMhs = findViewById(R.id.imgMhs);
                builder.setMessage("Menyimpan data?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DataDiriMhsActivity.this, "Tidak jadi disimpan", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (!isInsert) {
                            progressDialog.setMessage("Send Data");
                            progressDialog.show();


                            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<DefaultResult> call = service.insert_foto_mhs(
                                    nama.getText().toString(),
                                    nim.getText().toString(),
                                    alamat.getText().toString(),
                                    email.getText().toString(),
                                    /*foto.getText().toString()*/
                                    stringImg,
                                    "72170101");
                            call.enqueue(new Callback<DefaultResult>() {
                                @Override
                                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(DataDiriMhsActivity.this, "Data Tersimpan",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DataDiriMhsActivity.this, RecyclerViewMhsActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<DefaultResult> call, Throwable t) {
                                    Toast.makeText(DataDiriMhsActivity.this, "Gagal Menyimpan",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            progressDialog.setMessage("Send Data");
                            progressDialog.show();

                            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<DefaultResult> call = service.update_foto_mhs(
                                    idMhs,
                                    nama.getText().toString(),
                                    nim.getText().toString(),
                                    alamat.getText().toString(),
                                    email.getText().toString(),
                                    /*foto.getText().toString()*/
                                    stringImg,
                                    "72170101");
                            call.enqueue(new Callback<com.example.aplikasikrs.Model.DefaultResult>() {
                                @Override
                                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(DataDiriMhsActivity.this, "Data Terubah",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DataDiriMhsActivity.this, RecyclerViewDosenActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<DefaultResult> call, Throwable t) {
                                    Toast.makeText(DataDiriMhsActivity.this, "Gagal Terubah",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });


            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    imgMhs.setImageURI(selectedImage);
                    //proses konversi
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            /*mendapatkan realpath*/   filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodeableString = cursor.getString(columnIndex);
                    foto.setText(imgDecodeableString);
                    cursor.close();
                    //convert ke bitmap, lalu array, lalu stringnya pakai base64
                    Bitmap bm = BitmapFactory.decodeFile(imgDecodeableString);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();

                    stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                    break;
            }
    }
}