package com.cerner.jirahelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Commons {

    public static void postComment(String uri, String comment) {
        String comment_uri = uri + "/comment";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"body\":\"");
        stringBuilder.append(comment);
        stringBuilder.append("\"}");
        System.out.println("Posting Comment...");
        Connection.postRequest(comment_uri, stringBuilder.toString());
    }

    public static void closeJIRA(String uri) {
        String transition_uri = uri + "/transitions";
        System.out.println("Closing JIRA...");
        Connection.postRequest(transition_uri, Resources.CLOSE_TASK_TRANSITION);
    }

    public static List < String > getJIRAList(String uri) throws IOException {
        System.out.println("Getting List of JIRA...");
        List < String > jiras = new ArrayList < > ();
        String response = Connection.getRequest(uri);
        if (response != null) {
            try {
                final JSONObject parentJsonObject = new JSONObject(response);
                final JSONArray issuesArray = parentJsonObject.getJSONArray("issues");

                for (int i = 0; i < issuesArray.length(); i++) {
                    jiras.add(issuesArray.getJSONObject(i).getString("key"));
                }
                return jiras;

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}