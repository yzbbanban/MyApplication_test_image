package com.wangban.yzbbanban.myapplication_test_image;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends Activity {
    private TextView tvPath;
    private TextView tvWidth;
    private TextView tvHeght;
    private Thread thread;
    private static final String TAG="supergirl";
    private String width2;
    private String height2;
    private String path2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPath= (TextView) findViewById(R.id.path);
        tvWidth= (TextView) findViewById(R.id.width);
        tvHeght= (TextView) findViewById(R.id.height);

        thread=new InnerThread();
        thread.start();
    }
    class InnerThread extends Thread {
        @Override
        public void run() {
            try {
                String webPath = "http://m.xxxiao.com/25033";
                Document doc = Jsoup.connect(webPath).get();
                Log.i(TAG, "run: 111111");
                Elements e = doc.getElementsByClass("rgg-imagegrid");

                Log.i(TAG, "run: 222222");
                for (int i = 0; i < e.size(); i++) {
                //Log.i(TAG, "run: "+i+""+i+""+i);
                    //<img width="760" height="500"
                    Elements a = e.first().getElementsByTag("a");
                Log.i(TAG, "run: 333333");

                    width2 = a.first().getElementsByTag("img").attr("width");
                Log.i(TAG, "run: 444444");
                    height2 = a.first().getElementsByTag("img").attr("height");
                Log.i(TAG, "run: 555555");
                    path2 = a.first().getElementsByTag("img").attr("src");
                    Log.i(TAG, "getData: title:" + 0 + ";" + path2);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvPath.setText(path2);
                            tvWidth.setText(width2);
                            tvHeght.setText(height2);
                        }
                    });

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
