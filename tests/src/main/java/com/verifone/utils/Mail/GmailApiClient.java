package com.verifone.utils.Mail;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.jcabi.aspects.RetryOnFailure;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GmailApiClient {
    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER =  System.getProperty("user.dir") +
            "\\src\\main\\java\\com\\verifone\\utils\\Mail\\credentials";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM);
    private static final String CLIENT_SECRET_DIR = System.getProperty("user.dir") +
            "\\src\\main\\java\\com\\verifone\\utils\\Mail\\client_secret.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If there is no client_secret.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        // Load client secrets.
        File initialFile = new File(CLIENT_SECRET_DIR);
        InputStream in = new FileInputStream(initialFile);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }


    public static String getLastMessage() throws Exception {
        Gmail service = getService();
        ListMessagesResponse response = service.users().messages().list("me").setQ("is:unread").execute();
        String id = response.getMessages().get(0).getId();
        return service.users().messages().get("me", id).execute().getSnippet();
    }


    @RetryOnFailure(attempts = 4, delay = 2, unit = TimeUnit.MINUTES)
    public static void validateMessage(String text) throws Exception {
        Gmail service = getService();
        ListMessagesResponse response = service.users().messages().list("me").setQ("is:unread").execute();
        String id = response.getMessages().get(0).getId();
        String message = service.users().messages().get("me", id).execute().getSnippet();
        if (!message.contains(text)){
            org.testng.Assert.fail("Message with text: " + text + " Not found");
        }
    }


    public static List<String> getMessages() throws Exception {
        Gmail service = getService();
        ListMessagesResponse response = service.users().messages().list("me").setQ("is:unread").execute();
        List<String> snippets = new ArrayList<>();
        for (Message message : response.getMessages()) {
            System.out.println(message.getId());
            snippets.add(service.users().messages().get("me", message.getId()).execute().getSnippet());
        }
        return snippets;
    }

    private static Gmail getService() throws Exception {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

//    private static ListMessagesResponse execute() throws Exception {
//        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//        return service.users().messages().list("me").setQ("is:unread").execute();
//    }
//
//    public static List<String> getMessages() throws Exception {
//        // Build a new authorized API client service.
//        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//       ListMessagesResponse response = service.users().messages().list("me").setQ("is:unread").execute();
////        List<Message> messages = new ArrayList<Message>();
////        while (response.getMessages() != null) {
////            messages.addAll(response.getMessages());
////            if (response.getNextPageToken() != null) {
////                String pageToken = response.getNextPageToken();
////                response = service.users().messages().list("me").setQ("is:unread")
////                        .setPageToken(pageToken).execute();
////            } else break;
////        }
//        List<String> snippets = new ArrayList<String>();
//        System.out.println(response.getMessages().get(0).getId());
//        System.out.println(service.users().messages().get("me", response.getMessages().get(0).getId()).execute().getSnippet());
//        for (Message d : response.getMessages()) {
////            System.out.println(message.getSnippet());
//
//
////            snippets.add(service.users().messages().get("me", message.getId()).execute().getSnippet());
////            System.out.println(service.users().messages().get("me", message.getId()).execute().getPayload());
////            System.out.println(service.users().messages().get("me", message.getId()).execute().getSnippet());
//        }
//        return snippets;
//




//    }
}