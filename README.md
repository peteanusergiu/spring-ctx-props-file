Sharing and importing properties among different layers and processes in a Spring with Gradle application can raise some questions with some hard to prove answers and effects.

Current project tries to answer in a simplistic way to the following questions:

- how can one pass, in a spring project, parameters, from a properties file to spring runtime
- how can one inject, in a spring project, parameters, from a properties file to an existing bean using app-context.xml

- how can one share a gradle build JVM property with the gradle test JVM
- what is the correct style for sharing properties in between Graddles' JVM   
 

Run with:

gradle build -Dhttps.proxyHost=proxy.host.https -Dhttps.proxyPort=3128 -Dhttp.proxyHost=proxy.host.http -Dhttp.proxyPort=8080