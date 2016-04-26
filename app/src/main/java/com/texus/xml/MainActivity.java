package com.texus.xml;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        TextView tvXML = (TextView) this.findViewById(R.id.tvXML);
        TextView tvOutput = (TextView) this.findViewById(R.id.tvOutput);
        String xml = readFromAssets("book.xml",this);

        ArrayList<Book> books = Book.parseXML(xml);

        StringBuffer stringBuffer = new StringBuffer();
        for( Book book : books) {
            stringBuffer.append("Book:" + book.mTitle + "\n");
            stringBuffer.append("Category:" + book.mCategory + "\n");
            stringBuffer.append("Language:" + book.mLanguage + "\n");
            for( String author : book.mAuthors) {
                stringBuffer.append("Author:" + author + "\n");
            }
            stringBuffer.append("Year:" + book.mYear + "\n");
            stringBuffer.append("Price:" + book.mPrice + "\n");

        }
        tvOutput.setText(stringBuffer.toString());

    }

    public static String readFromAssets(String filename, Context context) {
        String content = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            String word;
            while((word=br.readLine()) != null)
                content = content  + word;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close(); //stop reading
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        return content;
    }
}
