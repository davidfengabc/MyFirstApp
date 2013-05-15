package com.example.myfirstapp;

import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.net.Socket;
import java.io.*;

public class XmppSession {
  private Socket socket;
  private XmlSerializer xmlOut;
  private XmlPullParser xmlIn;

  public void connect(String ipaddr, int port) throws Exception {
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    socket = new Socket (ipaddr, port);
    xmlOut = factory.newSerializer();
    xmlOut.setOutput(new BufferedOutputStream(socket.getOutputStream()),"UTF-8");

    xmlIn = factory.newPullParser();
    xmlIn.setInput(new BufferedInputStream(socket.getInputStream()), "UTF-8");

    startStream();
    startDoc();
  }

  public void startStream() throws Exception {

    xmlOut.setPrefix("stream","http://etherx.jabber.org/streams");
    xmlOut.setPrefix("","jabber:client");
    xmlOut.startTag("http://etherx.jabber.org/streams","stream");
    xmlOut.attribute(null,"to","gmail.com");
    xmlOut.attribute(null,"version","1.0");
    xmlOut.flush();

  }

  public void startDoc() throws Exception {
    int event = xmlIn.getEventType();
    if (event != XmlPullParser.START_DOCUMENT) {
      throw new UnexpectedXmlTagException("Expected: Start Doc, actual: event");
    }
    System.out.println("start doc found\n");
    xmlIn.next();
    if (xmlIn.getEventType() != XmlPullParser.START_TAG) {
      throw new UnexpectedXmlTagException("Expected: Start Doc, actual: event");
    }
    System.out.println("Start tag " + xmlIn.getName());
  }

  public void destroy() throws Exception {
    socket.shutdownOutput();
    socket.shutdownInput();
    socket.close();
  }
}

class UnexpectedXmlTagException extends Exception {
  public UnexpectedXmlTagException(String message) {
    super(message);
  }
}
