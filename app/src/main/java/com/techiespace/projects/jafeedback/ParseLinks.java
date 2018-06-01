package com.techiespace.projects.jafeedback;

public class ParseLinks {
    String facebook;
    String twitter;
    String youtube;
    String id;
    String str;

    public ParseLinks(String str) {
        if (str.contains("facebook.com") || str.contains("fb.com"))
            facebook = str;
        else if (str.contains("twitter.com"))
            twitter = str;
        else if (str.contains("youtube.com"))
            youtube = str;
        this.str = str;


    }

    public String getFbId(String str) {
        int index = str.indexOf(".com/") + 5;
        return str.substring(index);
    }
}
