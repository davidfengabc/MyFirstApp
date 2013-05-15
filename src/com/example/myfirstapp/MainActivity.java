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
import android.widget.EditText;

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
    TextView textview = (TextView) findViewById(R.id.info_box);
    EditText ipEntry = (EditText) findViewById(R.id.ip_entry);
    String ipAddr = ipEntry.getText().toString();

    xmppSession = new XmppSession();
    try {
      textview.setText("connecting");
      xmppSession.connect(ipAddr,5222);
      textview.append("start stream");
      //xmppSession.startStream();
      textview.append("stream started");
      xmppSession.destroy();
      textview.append("socket closed");
    } catch (Exception e) {
      textview.append(e.getMessage());
      return;
    }
  }
}
