JEPLDroid
========
	
Android Based Simple persistent Object Relational Mapping (ORM) API on top of JDBC

Overview
------
JEPLDroid is the port of JEPLayer to Android environment, a feature reduced version of [JEPLayer](https://github.com/jmarranz/jeplayer)
removing stuff not compatible or hard to port to the Android environment like JTA APIs.

JEPLayer is a simple and powerful ORM specifically focused on easy and secure management of JDBC and JTA transactions.

JEPLDroid can works with any Android compatible JDBC driver but specifically it leverages the JDBC API provided by SQLDroid
(automatically detected), the famous JDBC driver for the built-in SQLite database engine included in any Android based device.


News
------

- 2012-11-26 Source code now in GitHub
- 2012-10-25 JEPLDroid 1.1 is out (first release aligned to JEPLayer 1.1)


Download Binaries and Docs
------

[Download](https://sourceforge.net/projects/jepldroid/files/)

Distribution file includes binaries (aar), manual and javadocs.

Artefacts (as of v1.2) are uploaded to [JCenter](https://bintray.com/jmarranz/maven/jepldroid/view) and [Maven Central](https://oss.sonatype.org/content/repositories/releases/com/innowhere/jepldroid/) repositories

Gradle:

```java
compile 'com.innowhere:jepldroid:(version)'
```

Maven: 

```xml
<groupId>com.innowhere</groupId>
<artifactId>jepldroid</artifactId>
<version>(version)</version>
<type>aar</type>
```

JEPLDroid shares code with JEPLayer, so version numbers of releases are the same, excluding JTA, features are identical.

Online Docs Last Version
------

[Manual PDF](http://jepldroid.sourceforge.net/docs/manual/jepldroid_manual.pdf)

[Manual HTML](http://jepldroid.sourceforge.net/docs/manual/jepldroid_manual.htm)

[JavaDocs](http://jepldroid.sourceforge.net/docs/javadoc/)

Examples
------

See the GitHub repository of [JEPLayer Examples](https://github.com/jmarranz/jeplayer_examples), ignore JTA based examples.

In the source code of the "app" application you can find a very simple example of using JEPLDroid in Android.

Questions and discussions
------

There is a [Google Group](https://groups.google.com/forum/#!forum/jepldroid) for JEPLDroid.

Bug Reporting
------

Use this GitHub project.


Related
------

[JEPLayer](https://github.com/jmarranz/jeplayer) the father of JEPLDroid Android port.

