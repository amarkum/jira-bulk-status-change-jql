package com.cerner.jirahelper;

public class Resources {
    static String COMPANY_JIRA_BASE_URL = "https://jira.company-url.com/rest/api/2/";
    static String ISSUE = "issue/";
    static String basicAuth;

    static String IN_REVIEW_TRANSITION = "{\"transition\":{\"id\":\"51\"}}";
    static String CLOSE_TASK_TRANSITION = "{\"transition\":{\"id\":\"251\"}}";
    static String ISSUE_DONE_TRANSITION = "{\"transition\":{\"id\":\"131\"}}";

    static String Query_JQL1 = "https://jira.company-url.com/rest/api/2/search?jql=";
    static String Query_JQL2 = "&fields=key&maxResults=1000";

}