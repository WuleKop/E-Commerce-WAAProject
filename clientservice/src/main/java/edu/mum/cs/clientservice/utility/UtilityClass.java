package edu.mum.cs.clientservice.utility;

import org.springframework.stereotype.Component;

public class UtilityClass {

    public String choosenOne(String wholeUrl){
        return wholeUrl.split("\n")[0];
    }
}
