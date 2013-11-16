This is program implements a Web Crawler 11/11/2013

GENERAL USAGE NOTES
-----------------------
- The files and folders are structured as the following tree structure: 
  src/
     crawler/
        -CrawlerEngine.java
        -SearchHashMap.java
     gui/
        -Canvas.java
     webcr/
        -HashSerializer.java
        -WebCr.java
     javadoc/
        ...
     run.bat
     run.sh
     README.tct
     
    dist
       WebCr.jar

- This program implements the webcrawler to help search for an a string keyword from the website www.textfiles.com
  there are the index search and the document search, and both are implemented using various data hash maps, linked lists, set and arrays.

Requirements
----------------
Java 1.6.0 or higher

Launching the Program
---------------------------------
-on Windows OS, double click on run.bat or double click on dist/WebCr.jar
-on UNIX, call sh run.sh in the command line on double click on dist\webCr.jar

The program was compiled and built using  NetBeans IDE 7.4 (Build 201310111528) running 
 Java 1.7.0._45 on Windows Version 6.2

How to Use the Program
-------------------------------
When the program starts, it crawls 30 pages from http://www.textfiles.com and builds two hash maps for the tops and works that appear on the website.
For ease of functionality, cached copies of the hashmaps are included in the files and the user can search them while the program is crawling.
Once, the crawling is done. the program overrides the cache files.
The index search:
    it returns the URL location of the search topic input in the provided textfield.

Document search:
    it returns the links of all the archive documents (here .TXT files) that contain the searched key word.

Limitations:
------------------------------
The search does not handle searching for null string. When multiple key words are input, 
The program handle both as single string and as an aggregate of  strings; and returns all the files that contain all those string composing the keyword
The crawling is slow there is need to added a thread or multiple threads to increase the speed of crawling

Programmers:
------------------------------
Ivy Waininana
Vivens Mutangana
Concord Habineza