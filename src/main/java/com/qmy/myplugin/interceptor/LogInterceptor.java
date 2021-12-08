package com.qmy.myplugin.interceptor;

import com.qmy.myplugin.MyInvocationHandler;
import com.qmy.myplugin.myinvocation.MyInvocation;

public class LogInterceptor implements MyInterceptor {
  @Override
  public Object intercept(MyInvocation invocation) throws Exception{
    System.out.println("Log 前置置");
    Object process = invocation.process();
    System.out.println("Log 后置");
    return process;
  }

  @Override
  public Object plugin(Object target) {
    return MyInvocationHandler.wrap(target,this);
  }
}
