package com.spring.professional.exam.jdbc.springprofessional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class ConnectionInvocationHandler implements InvocationHandler {

    private final Connection connection;

    public ConnectionInvocationHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
    	if(method.toGenericString().contains("getConnection") || method.toGenericString().contains("close")) {
    		System.out.println("Connection Trace: " + method.toGenericString());	
    	}
        
        return method.invoke(connection, args);
    }
}
