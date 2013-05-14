package com.example.myfirstapp;

import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmppSession {
  private Socket socket;
  private XmlSerializer xmlOut;
  private XmlPullParser xmlIn;

  public void connect(String ipaddr, int port) throws Exception {
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    socket = new Socket (server, port);
    xmlOut = factory.newSerializer();
    xmlOut.setOutput(new BufferedOutputStream(soc.getOutputStream),"UTF-8");

    xmlIn = factory.newPullParser();
    xmlIn.setInput(new BufferedInputStream(soc.getInputStream), "UTF-8");
  }

  public void startStream() {

    xmlOut.setPrefix("stream","http://etherx.jabber.org/streams");
    xmlOut.setPrefix("","jabber:client");
    xmlOut.startTag("http://etherx.jabber.org/streams","stream");
    xmlOut.attribute(null,"to","gmail.com");
    xmlOut.attribute(null,"version","1.0");
    xmlOut.flush();

  }
}
