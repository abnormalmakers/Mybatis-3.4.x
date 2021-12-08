package com.qmy.myplugin;

import com.qmy.myplugin.interceptor.MyInterceptor;
import com.qmy.myplugin.myinvocation.MyInvocation;
import com.sun.corba.se.impl.interceptors.InterceptorList;
import org.apache.ibatis.plugin.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class MyInvocationHandler implements InvocationHandler {

  private Object target;

  private List<MyInterceptor> interceptorList = new ArrayList();

  public MyInterceptor interceptor;

  public MyInvocationHandler(Object target,List<MyInterceptor> interceptorList) {
    this.target = target;
    this.interceptorList = interceptorList;
  }

  public MyInvocationHandler(Object target,MyInterceptor interceptor) {
    this.target = target;
    this.interceptor = interceptor;
  }

/*  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("前置处理");
    Object invoke = method.invoke(target, args);
    System.out.println("后置处理");
    return invoke;
  }*/


/*  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    for (MyInterceptor interceptor : interceptorList) {
      interceptor.intercept();
    }
    Object invoke = method.invoke(target, args);
    return invoke;
  }*/


  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    MyInvocation myInvocation = new MyInvocation(target, method, args);

    return interceptor.intercept(myInvocation);
  }

  public static Object wrap(Object target,MyInterceptor interceptor){
    InvocationHandler myInvocation = new MyInvocationHandler(target,interceptor);
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), myInvocation);
  }
}
