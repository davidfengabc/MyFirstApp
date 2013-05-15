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
  XmppSession xmppSession;

  /** Called when the activity is first created. */
  @Override
    public void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
    }

  public void connect(View view) {
    TextView textview = (TextView) findViewById(R.id.edit_message);
    xmppSession = new XmppSession();
    try {
      textview.setText("connecting");
      xmppSession.connect("130.76.191.156",9000);
      textview.setText("start stream");
      xmppSession.startStream();
      textview.setText("stream started");
      xmppSession.destroy();
      textview.setText("socket closed");
    } catch (Exception e) {
      textview.setText(e.getMessage());
      return;
    }
  }
}
