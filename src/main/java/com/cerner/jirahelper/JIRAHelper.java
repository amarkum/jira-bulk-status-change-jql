package com.cerner.jirahelper;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class JIRAHelper {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("JIRA Helper v1.0\n");
        System.out.println("Username:");
        String userName = scanner.nextLine();

        System.out.println("Password:");
        String password = scanner.nextLine();

        byte[] bytesEncoded = Base64.encodeBase64((userName + ":" + password).getBytes());
        Resources.basicAuth = "Basic " + new String(bytesEncoded);

        System.out.println("Enter JQL:");
        String JQL = scanner.nextLine();

        String uri = (Resources.Query_JQL1 + JQL + Resources.Query_JQL2).replaceAll(" ", "%20");

        List < String > jiras = Commons.getJIRAList(uri);
        for (int i = 0; i < jiras.size(); i++) {
            System.out.println(jiras.get(i));
        }

        for (int i = 0; i < jiras.size(); i++) {
            Commons.closeJIRA(Resources.COMPANY_JIRA_BASE_URL + Resources.ISSUE + jiras.get(i));
        }
    }

}