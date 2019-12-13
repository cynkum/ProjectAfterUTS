package com.example.aplikasikrs;

import androidx.annotation.NonNull;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;
//import javax.security.auth.callback.Callback;

public class EditDosenActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText nidn, nama, gelar, alamat, email, foto;
    String image;
    GetDataService service;
    ImageView imgDosen;
    private static final int GALLERY_REQUEST_CODE=58;
    private static final int FILE_ACCESS_REQUEST_CODE=58;//permission codenya 58
    private boolean isUpdate = false;
    private String idDosen = "";
    private String stringImg = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dosen);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, FILE_ACCESS_REQUEST_CODE);
        }

        nama = (EditText) findViewById(R.id.txtEditNamaDosen);
        nidn = (EditText) findViewById(R.id.txtEditNidnDosen);
        alamat = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText4);
        gelar = (EditText) findViewById(R.id.editText3);
        foto = (EditText) findViewById(R.id.txtFoto);
        imgDosen = findViewById(R.id.imgDosen);
//        Button btnBrowse = (Button) findViewById(R.id.btnBrowseDosen);
//        btnBrowse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent,0);
//            }
//        });
        Button btnUpload = findViewById(R.id.btnUplaodDosen);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }
        });

        progressDialog = new ProgressDialog(this);

        checkUpdate();
        Button btnSave = (Button) findViewById(R.id.btnSaveDosen);
        if(isUpdate){
            btnSave.setText("Update");
        }

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Boolean isValid = true;
                //Validation

                if(nama.getText().toString().matches("")){
                    nama.setError("Silahkan mengisi nama dosen");
                    isValid = false;
                }

                if(nidn.getText().toString().matches("")){
                    nidn.setError("Silahkan mengisi NIDN dosen");
                    isValid = false;
                }

                if(alamat.getText().toString().matches("")){
                    alamat.setError("Silahkan mengisi alamat dosen");
                    isValid = false;
                }

                if(email.getText().toString().matches("")){
                    email.setError("Silahkan mengisi email dosen");
                    isValid = false;
                }

                if(gelar.getText().toString().matches("")){
                    gelar.setError("Silahkan mengisi gelar dosen");
                }

                if(foto.getText().toString().matches("")){
                    foto.setError("Silahkan mengisi foto dosen");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EditDosenActivity.this);

                builder.setMessage("Mengubah data?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditDosenActivity.this, "Tidak jadi Diubah", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if(!isUpdate){
                            progressDialog.setMessage("Send Data");
                            progressDialog.show();



                            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<DefaultResult> call = service.insert_foto_dosen(
                                    nama.getText().toString(),
                                    nidn.getText().toString(),
                                    alamat.getText().toString(),
                                    email.getText().toString(),
                                    gelar.getText().toString(),
                                    /*foto.getText().toString()*/
                                    stringImg,
                                    "72170101");
                            call.enqueue(new Callback<com.example.aplikasikrs.Model.DefaultResult>() {
                                @Override
                                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(EditDosenActivity.this, "Data Tersimpan",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(EditDosenActivity.this, RecyclerViewDosenActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<DefaultResult> call, Throwable t) {
                                    Toast.makeText(EditDosenActivity.this, "Gagal Menyimpan",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            progressDialog.setMessage("Send Data");
                            progressDialog.show();

                            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<DefaultResult> call = service.update_foto_dosen(
                                    idDosen,
                                    nama.getText().toString(),
                                    nidn.getText().toString(),
                                    alamat.getText().toString(),
                                    email.getText().toString(),
                                    gelar.getText().toString(),
                                    /*foto.getText().toString()*/
                                    stringImg,
                                    "72170101");
                            call.enqueue(new Callback<com.example.aplikasikrs.Model.DefaultResult>() {
                                @Override
                                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(EditDosenActivity.this, "Data Terubah",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(EditDosenActivity.this, RecyclerViewDosenActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<DefaultResult> call, Throwable t) {
                                    Toast.makeText(EditDosenActivity.this, "Gagal Terubah",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }



                    }
            });
                AlertDialog dialog = builder.create();
                dialog.show();

        }
    });
}

    void checkUpdate(){
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }

        //get data via the key
        isUpdate = extras.getBoolean("is_update");
        idDosen = extras.getString("id_dosen");
        nama.setText(extras.getString("nama"));
        nidn.setText(extras.getString("nidn"));
        alamat.setText(extras.getString("alamat"));
        email.setText(extras.getString("email"));
        gelar.setText(extras.getString("gelar"));
        foto.setText(extras.getString("foto"));
        stringImg = extras.getString("foto");
        Picasso.with(EditDosenActivity.this).load("https://kpsi.fti.ukdw.ac.id/progmob/%7B%7Bfotoanda%7D%7D%22" + extras.getString("foto"));

}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case FILE_ACCESS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    //PERMISSION_GRANTED
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    imgDosen.setImageURI(selectedImage);
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
//        if(data != null && requestCode==0){
//            if(resultCode == RESULT_OK){
//                Uri targetUri = data.getData();
//                Bitmap bitmap;
//                try{
//                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
//                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
//                    image = ConvertBitmapToString(resizedBitmap);
//                    Upload();
//                } catch(FileNotFoundException e){
//                    e.printStackTrace();
//                }
//            }
//        }
    }


//    public static String ConvertBitmapToString(Bitmap bitmap){
//        String encodedImage="";
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        try{
//            encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
//        } catch(UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
//        return encodedImage;
//    }
//
//    private void Upload(){
//        try{
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
//                new UploadFile().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "/api/progmob/dosen/updatefoto");
//            } else{
//                new UploadFile().execute("/api/progmob/dosen/updatefoto");
//            }
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    private class UploadFile extends AsyncTask<String, Void, Void>{
//        private String Content;
//        private String Error = null;
//        String data = "";
//        private BufferedReader reader;
//
//        protected void onPreExecute(){
//            progressDialog.show();
//            try{
//                data += "&" + URLEncoder.encode("image", "UTF-8") + "=" + "data:image/png;base64," + image;
//            } catch(UnsupportedEncodingException e){
//                e.printStackTrace();
//            }
//        }
//        @Override
//        protected Void doInBackground(String... urls) {
//            HttpURLConnection connection = null;
//            try{
//                URL url = new URL(urls[0]);
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//                con.setRequestMethod("POST");
//                con.setUseCaches(false);
//                con.setDoInput(true);
//                con.setDoOutput(false);
//                con.setRequestProperty("Content-Length", "" + data.getBytes().length);
//                con.setRequestProperty("Connection", "Keep Alive");
//                con.setDoOutput(true);
//
//                OutputStream os = con.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//
//                writer.write(data);
//                writer.flush();
//                writer.close();
//                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//                while((line=reader.readLine()) != null){
//                    sb.append(line);
//                }
//                Content = sb.toString();
//            } catch(Exception e){
//                Error = e.getMessage();
//            }
//            return null;
//        }
//        protected void onPostExecute(Void unused){
//            progressDialog.dismiss();
//            try{
//                if(Content != null){
//                    JSONObject jsonResponse = new JSONObject(Content);
//                    String status = jsonResponse.getString("status");
//                    if("10000".equals(status)){
//                        Toast.makeText(getApplicationContext(), "File uploaded successfully", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Something is wrong! Please try again", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
    }


}
