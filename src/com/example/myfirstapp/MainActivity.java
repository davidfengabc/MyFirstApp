package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import android.view.View;
import android.widget.TextView;
import android.os.Environment;
import java.io.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void connect(View view) throws Exception {
      String state = Environment.getExternalStorageState();
      TextView textview = (TextView) findViewById(R.id.edit_message);
      File file = null;
      OutputStream fileOut = null;

      if (Environment.MEDIA_MOUNTED.equals(state)) {
	file = new File(getExternalFilesDir(null), "DemoFile.xml");
	fileOut = new BufferedOutputStream(new FileOutputStream(file));
	textview.setText("success mounting external dir");

      } else {
	// unable to write to external storage
	textview.setText("error mounting external dir");
	return;
      }
      

      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
      XmlSerializer xmlOut = factory.newSerializer();
      xmlOut.setOutput(fileOut, "UTF-8");
      xmlOut.setPrefix("stream","http://etherx.jabber.org/streams");
      xmlOut.setPrefix("","jabber:client");
      xmlOut.startTag("stream","stream");
      xmlOut.attribute(null,"to","gmail.com");
      xmlOut.attribute(null,"version","1.0");
      xmlOut.flush();
    }
}
