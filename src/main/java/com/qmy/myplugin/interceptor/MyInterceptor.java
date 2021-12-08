package com.qmy.myplugin.interceptor;

import com.qmy.myplugin.myinvocation.MyInvocation;

public interface MyInterceptor {


  Object intercept(MyInvocation invocation) throws Exception;

  Object plugin(Object target);
}
