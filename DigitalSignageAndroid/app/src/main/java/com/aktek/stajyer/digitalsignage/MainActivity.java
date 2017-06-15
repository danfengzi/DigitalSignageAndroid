package com.aktek.stajyer.digitalsignage;


import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webService.testSoap.wcf.RTWxmdsBinding;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ae.javax.xml.bind.JAXBContext;
import ae.javax.xml.bind.JAXBException;
import ae.javax.xml.bind.Marshaller;
import ae.javax.xml.bind.Unmarshaller;


public class MainActivity extends AppCompatActivity {
    private static final String serverKey = "z2xNLR";
    private static final String hwKey = "ed2263bea2d5bb7da5ec59cd385f7acf";
    Button b1;
    TextView t1;
    Thread thread;
    RTWxmdsBinding sqSxmdsBinding;
    String st;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        t1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        b1.setOnClickListener(new View.OnClickListener() {
                                  public void onClick(View arg0) {
                                      // TODO Auto-generated method stub
                                      thread = new Thread(new Runnable() {
                                          @Override
                                          public void run() {
                                              sqSxmdsBinding = new RTWxmdsBinding();
                                              try {
                                                  /*st = <files>
                                                   <file type="media" id="9" size="95785" md5="3c9137d88a00b1ae0b41ff6a70571615" download="xmds" path="jquery-1.11.1.min.js"/>
                                                   <file type="media" id="10" size="2466" md5="3659986d00e56c929218084aeb991cca" download="xmds" path="xibo-layout-scaler.js"/>
                                                  */

                                                  st = sqSxmdsBinding.RequiredFiles(serverKey, hwKey, "");
                                                  downloadRequiredFiles(serverKey, hwKey, "");



                                             /*     //Read text from file
                                                  File file = new File(dir.getAbsolutePath() + "/requiredFiles.xml");
                                                  StringBuilder text = new StringBuilder();

                                                  try {
                                                      BufferedReader br = new BufferedReader(new FileReader(file));
                                                      String line;

                                                      while ((line = br.readLine()) != null) {
                                                          text.append(line);
                                                          text.append('\n');
                                                      }
                                                      Log.e("tag", text.toString());
                                                      br.close();
                                                  } catch (IOException e) {
                                                      e.printStackTrace();
                                                  }*/


                                              } catch (
                                                      Exception e
                                                      )

                                              {
                                                  e.printStackTrace();
                                              }

                                              runOnUiThread(new Runnable() {
                                                                public void run() {
                                                                    RequiredFiles requiredFiles4XMLJAXB = null;
                                                                    try {
                                                                        requiredFiles4XMLJAXB = createTemplate4RequiredFile(st);
                                                                    } catch (ParserConfigurationException e) {
                                                                        e.printStackTrace();
                                                                    } catch (IOException e) {
                                                                        e.printStackTrace();
                                                                    } catch (SAXException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    String stringRequired = null;
                                                                    try {
                                                                        stringRequired = createRequiredFiles(requiredFiles4XMLJAXB);
                                                                    } catch (JAXBException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                     /* <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                                                                          <RequiredFiles>
                                                                              <RequiredFileList>
                                                                                  <RequiredFile>
                                                                                      <ChunkOffset>0.0</ChunkOffset>
                                                                                      <ChunkSize>512000.0</ChunkSize>
                                                                                      <Complete>false</Complete>
                                                                                      <Downloading>false</Downloading>
                                                                                      <FileType>media</FileType>
                                                                                      <Http>false</Http>
                                                                      */
                                                                    try {
                                                                        writeToFile(stringRequired);
                                                                    } catch (IOException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    textView2.setText(stringRequired);
                                                                }
                                                            }

                                              );
                                          }
                                      }

                                      );
                                      thread.start();
                                  }
                              }

        );


    }

    public void downloadRequiredFiles(String serverKey, String hwKey, String version) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(RequiredFiles.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File sdcard = Environment.getExternalStorageDirectory();
        File dir = new File(sdcard.getAbsolutePath() + "/digitalsignage/");
        File file = new File(String.valueOf(dir));

        RequiredFiles requiredFiles = (RequiredFiles) unmarshaller.unmarshal(file);

        for (RequiredFile requiredFile : requiredFiles.getRequiredFileList().getRequiredFileAll()) {
            RTWxmdsBinding serviceLocator = new RTWxmdsBinding();

            byte[] byteArray = serviceLocator.GetFile(serverKey, hwKey, requiredFile.getPath(),
                    requiredFile.getFileType(), (int) requiredFile.getChunkOffset(), (int) requiredFile.getChunkSize(), version).getBytes();


            File sdcard2 = Environment.getExternalStorageDirectory();
            File dir2 = new File(sdcard2.getAbsolutePath() + "/digitalsignageTwo/" + requiredFile.getPath());
            dir2.createNewFile();

            FileOutputStream fos = new FileOutputStream(dir2);
            fos.write(byteArray);
            fos.close();
        }

    }

    public void writeToFile(String st) throws IOException {
        File sdcard = Environment.getExternalStorageDirectory();
        File dir = new File(sdcard.getAbsolutePath() + "/digitalsignage/");
        if (!dir.exists()) {
            dir.mkdir();
        }
        //write to file
        File myFile = new File(dir.getAbsolutePath() + "/requiredFiles.xml");
        dir.createNewFile();
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter =
                new OutputStreamWriter(fOut);
        myOutWriter.append(st);
        myOutWriter.close();
        fOut.close();
    }

    public String createRequiredFiles(RequiredFiles requiredFiles4XMLJAXB) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(RequiredFiles.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(requiredFiles4XMLJAXB, sw);

        String resultXML = sw.toString();
        Log.e("oo yee", resultXML);
        return resultXML;
    }


    public RequiredFiles createTemplate4RequiredFile(String st) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(st));
        org.w3c.dom.Document xmlDoc = builder.parse(is);
        NodeList nodes = xmlDoc.getElementsByTagName("file");
        List<RequiredFile> requiredFileList = new ArrayList<RequiredFile>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element fileElement = (Element) nodes.item(i);

            Log.e("Tag", "Adding File Id: " + fileElement.getAttribute("id"));

            RequiredFile requiredFile = new RequiredFile();
            requiredFile.setFileType(fileElement.getAttribute("type"));
            requiredFile.setDownloading(false);
            requiredFile.setComplete(false);
            requiredFile.setLastChecked(new Date());
            requiredFile.setChunkOffset(0);
            requiredFile.setChunkSize(0);
            if (requiredFile.getFileType().equals("media")) {
                requiredFile.setId(Integer.parseInt(fileElement.getAttribute("id")));
                requiredFile.setPath(fileElement.getAttribute("path"));
                requiredFile.setSaveAs((fileElement.getAttribute("saveAs") == null || fileElement.getAttribute("saveAs").equals("")) ? requiredFile.getPath() : fileElement.getAttribute("saveAs"));
                requiredFile.setHttp(fileElement.getAttribute("download").equals("http"));
                requiredFile.setChunkSize(512000);


            } else if (requiredFile.getFileType().equals("layout")) {
                requiredFile.setId(Integer.parseInt(fileElement.getAttribute("id")));
                requiredFile.setPath(fileElement.getAttribute("path"));
                requiredFile.setHttp(fileElement.getAttribute("download").equals("http"));
                if (requiredFile.isHttp()) {
                    requiredFile.setSaveAs(fileElement.getAttribute("saveAs"));
                } else {
                    requiredFile.setPath(requiredFile.getPath() + ".xlf");
                    requiredFile.setSaveAs(requiredFile.getPath());
                }
                requiredFile.setChunkSize(requiredFile.getSize());

            } else if (requiredFile.getFileType().equals("resource")) {
                requiredFile.setId(Integer.parseInt(fileElement.getAttribute("id")));
                requiredFile.setLayoutId(Integer.parseInt(fileElement.getAttribute("layoutid")));
                requiredFile.setRegionId(fileElement.getAttribute("regionid"));
                requiredFile.setMediaId(fileElement.getAttribute("mediaid"));
                requiredFile.setPath(requiredFile.getPath() + ".htm");
                requiredFile.setSaveAs(requiredFile.getPath());
                requiredFile.setSize(10000);

                //TODO AFTER_DEMO
                continue;
            } else {
                continue;
            }

            requiredFile.setMd5(fileElement.getAttribute("md5"));
            requiredFile.setSize(Double.valueOf(fileElement.getAttribute("size")));

            requiredFileList.add(requiredFile);
        }

        RequiredFiles requiredFiles4XML = new RequiredFiles();
        RequiredFileList requiredFileList4XML = new RequiredFileList();
        requiredFileList4XML.setRequiredFileAll(requiredFileList);
        requiredFiles4XML.setRequiredFileList(requiredFileList4XML);

        return requiredFiles4XML;
    }
}



