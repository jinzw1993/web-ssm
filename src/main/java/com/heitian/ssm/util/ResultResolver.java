package com.heitian.ssm.util;

import com.heitian.ssm.bo.Result;

/**
 * Created by oasis on 2016/12/23.
 */
public class ResultResolver {
    public static Result returnRes(int i) {
        Result result = new Result();
        if(i!=0) {
            result.setStatus(1);
            result.setMessage("success");
        } else {
            result.setMessage("failed");
            result.setStatus(0);
        }
        return result;
    }
}
