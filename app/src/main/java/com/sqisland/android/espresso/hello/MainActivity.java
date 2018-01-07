package com.sqisland.android.espresso.hello;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import static java.lang.Thread.sleep;

public class MainActivity extends Activity {
  private TextView greetingView;
  private EditText title;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    greetingView = findViewById(R.id.greeting);
    title = findViewById(R.id.title);
  }

  public void click(View v) {
    greetingView.setText("Seu texto: "+title.getText());
    this.takeScreenshotView();
  }

  private void takeScreenshotView(){
    Date now = new Date();
    android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

    try {
      for (int i = 0; i < 2; i++) {
        // image naming and path  to include sd card  appending name you choose for file
        String mPath = null;
        Bitmap bitmap = null;

        if(i == 0){
          bitmap = this.screenShotView(this.getWindow().getDecorView().getRootView());
          mPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Pictures/" + now + ".jpg";
        }else if(i == 1){
          bitmap = this.screenShotView(this.getWindow().getDecorView().findViewById(R.id.greet_button));
          mPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Pictures/" + now + "-view.jpg";
        }

        if(bitmap != null){
          File imageFile = new File(mPath);

          FileOutputStream outputStream = new FileOutputStream(imageFile);
          int quality = 100;
          bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
          outputStream.flush();
          outputStream.close();

          openScreenshot(imageFile);
        }

      }
    } catch (Throwable e) {
      // Several error may come out with file handling or DOM
      e.printStackTrace();
    }
  }

  private void openScreenshot(File imageFile) {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    Uri uri = Uri.fromFile(imageFile);
    intent.setDataAndType(uri, "image/*");
    startActivity(intent);
  }

  public Bitmap screenShotView(View view) {
    Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    view.draw(canvas);
    return bitmap;
  }

  /*private void takeScreenshot() {
    Date now = new Date();
    android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

    try {
      // image naming and path  to include sd card  appending name you choose for file
      String mPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Pictures/" + now + ".jpg";

      // create bitmap screen capture
      View v1 = getWindow().getDecorView().getRootView();
      v1.setDrawingCacheEnabled(true);
      Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
      v1.setDrawingCacheEnabled(false);

      File imageFile = new File(mPath);

      FileOutputStream outputStream = new FileOutputStream(imageFile);
      int quality = 100;
      bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
      outputStream.flush();
      outputStream.close();

      openScreenshot(imageFile);
    } catch (Throwable e) {
      // Several error may come out with file handling or DOM
      e.printStackTrace();
    }
  }

  private void takeScreenshotView() {
    Date now = new Date();
    android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

    try {
      // image naming and path  to include sd card  appending name you choose for file
      String mPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Pictures/" + now + ".jpg";

      // create bitmap screen capture

      Bitmap bitmap = this.screenShotView(this.getWindow().getDecorView().findViewById(R.id.greet_button));

      File imageFile = new File(mPath);

      FileOutputStream outputStream = new FileOutputStream(imageFile);
      int quality = 100;
      bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
      outputStream.flush();
      outputStream.close();

      openScreenshot(imageFile);
    } catch (Throwable e) {
      // Several error may come out with file handling or DOM
      e.printStackTrace();
    }
  }*/
}