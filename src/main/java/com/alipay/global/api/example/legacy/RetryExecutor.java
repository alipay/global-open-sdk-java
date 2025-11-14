package com.alipay.global.api.example.legacy;

import com.alipay.global.api.example.model.Callback;

public class RetryExecutor {

  public static Object execute(int retryCount, Callback callback) {
    // TODO To simulate the retry
    //        for(int curPayRetryCount = 0; curPayRetryCount < retryCount; curPayRetryCount++){
    //            RetryResult retryResult =  callback.doProcess();
    //            if(retryResult.isRetry()){
    //                continue;
    //            }
    //            return retryResult.getObj();
    //        }
    return null;
  }
}
