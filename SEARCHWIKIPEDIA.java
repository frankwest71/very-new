import java.util.Scanner;
import java.io.*;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import java.net.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.RefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.lang.Object;
import java.lang.Throwable;
import java.lang.Exception;
import java.lang.RuntimeException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.namespace.QName;
import org.w3c.dom.Node;
import java.io.IOException;
import java.lang.Object;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTML;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.lang.StringBuilder;
import java.net.URL;
/**
 * Returns the introduction paragraph of any wikipedia website
 * The System.in is for users to input through keyboard
 * by using the Scanner class
 * StringBuilder is used to append original website text 
 * with the user's input
 * URl connections are used to get system error and interact with it
 * by printing out appropriate messages
 * HtmlPage class is used to convert the page to XML and Jsoup
 * were used to parse XML into document and easily return first paragraph with 
 * first() method from Jsoup Document class
 * @return The toString() of the web page first paragraph is printed to the system
 * Approach: First I used HtmlUnit found online to render the string that looks
 * like a website to return text from the web page. It gets harder because this class
 * doesn't offer useful methods, so I move on to Jsoup libaries downloaded online to find
 * the method to return the first paragraph of the website. StringBuilder is needed to 
 * concatenate the website with what the user want to enter. URL class is used to check
 * against computer response to a bad link. Java.io is needed because of events and the use
 * of Scanner library
 */
public class SEARCHWIKIPEDIA
{
    private static int begin=0;
    private static int end=0;
    private static char[] updatedtext;
    private static final int approxlenintroparagraph=600;
    private static String subtext;
    private static StringBuilder original= new StringBuilder();
    public static void main(String[] args) throws FailingHttpStatusCodeException , MalformedURLException, IOException
    {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        Scanner fulltext = new Scanner(System.in);
        System.out.println("Enter the topic to wiki search: ");
        subtext = fulltext.next();
        while (subtext.equals(null))
        {
        System.out.println("Try again: ");
        subtext = fulltext.next();
        }
        original.append("https://en.wikipedia.org/wiki/");
        original.append(subtext);
        
        final URL url = new URL(original.toString());
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int responseCode = huc.getResponseCode();

        if (responseCode == 200) {
        System.out.println("Website exists");
        HtmlPage page = webClient.getPage(original.toString());
        System.out.println(original.toString());
        String pageContent=page.asXml(); //or asText();
        String html = pageContent;
        Document doc = Jsoup.parse(html);
        Element p= doc.select("p").first();
        //doc.select("p").append("\r\n");
        String text = doc.body().text(); // "An example link"
        System.out.println("Introduction paragraph " + p.text());
        char[] textarray = new char[text.length()];
        text.getChars(0,text.length(),textarray,0);
        updatedtext= new char[end-begin];
        text.getChars(begin,end,updatedtext, 0);
        //System.out.println(updatedtext);
            System.exit(0);
        } else {
            System.out.println("Not found");
            System.exit(0);
        }
        
     
    }

}