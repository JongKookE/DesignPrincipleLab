package com.subject.designprinciplelab.problem.seq04_aop;

import org.springframework.stereotype.Service;

@Service
public class S4TestService {

    @TrackTime
    public String checkAop(){
        String str = "";
        for(int i = 0; i < 10; i++) str += sendStr();
        return str;
    }

    public String sendStr(){
        return "V";
    }
}