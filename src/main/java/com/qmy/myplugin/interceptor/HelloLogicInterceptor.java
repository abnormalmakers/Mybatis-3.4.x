package com.qmy.myplugin.interceptor;

import com.qmy.myplugin.MyInvocationHandler;
import com.qmy.myplugin.myinvocation.MyInvocation;

public class HelloLogicInterceptor implements MyInterceptor {
  @Override
  public Object intercept(MyInvocation invocation) throws Exception {
    System.out.println("HelloLogic 前置");
    Object process = invocation.process();
    System.out.println("HelloLogic 后置");
    return process;
  }

  @Override
  public Object plugin(Object target) {
    return MyInvocationHandler.wrap(target, this);
  }
}
