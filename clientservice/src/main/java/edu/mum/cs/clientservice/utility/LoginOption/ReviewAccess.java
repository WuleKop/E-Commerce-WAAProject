package edu.mum.cs.clientservice.utility.LoginOption;

import lombok.extern.java.Log;

public class ReviewAccess implements Login {
    @Override
    public String url(String url) {
        return url;
    }
}
