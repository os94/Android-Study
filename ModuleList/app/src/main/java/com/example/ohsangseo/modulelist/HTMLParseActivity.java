package com.example.ohsangseo.modulelist;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLParseActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmlparse);

        textView = (TextView)findViewById(R.id.textview);
        HTMLParse htmlParse = new HTMLParse();
        htmlParse.execute();
    }

    private class HTMLParse extends AsyncTask<Void, Void, Void> {
        private Elements elements;
        private Document document;
        private String str;

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                document = Jsoup.connect("http://www.naver.com").get();
                elements = document.select("span.ah_k");
                Log.i("doc:", document.html());
                Log.i("doc:", document.text());
            } catch (IOException e) {
                e.printStackTrace();
            }
            str = "";
            int cnt=0;
            for(Element element:elements) {
                cnt++;
                str += cnt + ". " + element.text() + "\n";
                if(cnt == 10) break;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            textView.setText(str);
        }
    }


}
