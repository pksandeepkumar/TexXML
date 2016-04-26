# TexXML
Simple SAX parser helper for Java.

```
TexXmlParser 
```

Helps you to parse any structured XML in simple way. This is implemented using SAX parser.

##Example
```
<bookstore>
    <book category="cooking">
        <title lang="en">Everyday Italian</title>
        <author>Giada De Laurentiis</author>
        <year>2005</year>
        <price>30.00</price>
    </book>
    <book category="children">
        <title lang="en">Harry Potter</title>
        <author>J K. Rowling</author>
        <year>2005</year>
        <price>29.99</price>
    </book>
    <book category="web">
        <title lang="en">XQuery Kick Start</title>
        <author>James McGovern</author>
        <author>Per Bothner</author>
        <author>Kurt Cagle</author>
        <author>James Linn</author>
        <author>Vaidyanathan Nagarajan</author>
        <year>2003</year>
        <price>49.99</price>
    </book>
    <book category="web" cover="paperback">
        <title lang="en">Learning XML</title>
        <author>Erik T. Ray</author>
        <year>2003</year>
        <price>39.95</price>
    </book>
</bookstore>

```

The above xml can be parsed as following way.

```
public static ArrayList<Book> parseXML( String xml) {

        ArrayList<Book> books = new ArrayList<Book>();

        TexXmlParser parser = new TexXmlParser(xml);

        //Root element
        TexXmlElement rootElement = parser.rootElement;

        //rootElement.children holds all child book elements
        for(TexXmlElement bookElement :  rootElement.children) {
            Book book = new Book();
            //Reading an attribute value
            book.mCategory = bookElement.getAttribute("category");

            //Get Element with name 'title'
            TexXmlElement elementTitle = bookElement.getSingleElementByName("title");
            if(elementTitle != null) {
                book.mTitle = elementTitle.nodeValue;
                book.mLanguage = elementTitle.getAttribute("lang");
            }

            //Get all elements with name 'author'		
            ArrayList<TexXmlElement> elementAuthors = bookElement.getElementsByName("author");
            if(elementAuthors != null) {
                for( TexXmlElement elementAuthor : elementAuthors) {
                    book.mAuthors.add(elementAuthor.nodeValue);
                }
            }

            //Reading a node value
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

```




