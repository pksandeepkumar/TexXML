package com.texus.xml;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sandeep on 26/4/16.
 */
public class Book {

    public String mTitle;
    public String mCategory;
    public String mLanguage;
    public String mYear;
    public String mPrice;
    public ArrayList<String> mAuthors;

//    <bookstore>
//    <book category="cooking">
//    <title lang="en">Everyday Italian</title>
//    <author>Giada De Laurentiis</author>
//    <year>2005</year>
//    <price>30.00</price>
//    </book>
//    <book category="children">
//    <title lang="en">Harry Potter</title>
//    <author>J K. Rowling</author>
//    <year>2005</year>
//    <price>29.99</price>
//    </book>
//    <book category="web">
//    <title lang="en">XQuery Kick Start</title>
//    <author>James McGovern</author>
//    <author>Per Bothner</author>
//    <author>Kurt Cagle</author>
//    <author>James Linn</author>
//    <author>Vaidyanathan Nagarajan</author>
//    <year>2003</year>
//    <price>49.99</price>
//    </book>
//    <book category="web" cover="paperback">
//    <title lang="en">Learning XML</title>
//    <author>Erik T. Ray</author>
//    <year>2003</year>
//    <price>39.95</price>
//    </book>
//    </bookstore>


    public static ArrayList<Book> parseXML( String xml) {
        Log.e("Xml","-------------------------------------------");
        Log.e("Xml","XML:" + xml);
        ArrayList<Book> books = new ArrayList<Book>();

        TexXmlParser parser = new TexXmlParser(xml);
        TexXmlElement rootElement = parser.rootElement;

        for(TexXmlElement bookElement :  rootElement.children) {
            Book book = new Book();
            book.mCategory = bookElement.getAttribute("category");

            TexXmlElement elementTitle = bookElement.getSingleElementByName("title");
            if(elementTitle != null) {
                book.mTitle = elementTitle.nodeValue;
                book.mLanguage = elementTitle.getAttribute("lang");
            }

            ArrayList<TexXmlElement> elementAuthors = bookElement.getElementsByName("author");
            if(elementAuthors != null) {
                for( TexXmlElement elementAuthor : elementAuthors) {
                    book.mAuthors.add(elementAuthor.nodeValue);
                }
            }

            TexXmlElement elementYear = bookElement.getSingleElementByName("year");
            if(elementYear != null) {
                book.mYear = elementYear.nodeValue;
            }

            TexXmlElement elementPrice = bookElement.getSingleElementByName("price");
            if(elementPrice != null) {
                book.mPrice = elementPrice.nodeValue;
            }

            books.add(book);
        }
        return books;
    }


}
