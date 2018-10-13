package com.boomboo.demo.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class RpcFramework {

    /**
     * rpc暴露服务
     *
     * @param service
     * @param port
     */
    public void export(Object service, int port) throws Exception {
        if (service == null) {
            log.error("service is null");
            throw new IllegalArgumentException("service is null");
        }
        if (port < 0 || port > 65536) {
            log.error("illegal port input,port:{}", port);
            throw new IllegalArgumentException("Invalid port " + port);
        }
        ServerSocket serverSocket = new ServerSocket(port);
        for (; ; ) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = socket.getInputStream();
                            ObjectInputStream input = new ObjectInputStream(inputStream);
                            //Deserializer
                            //todo stream input handler
                            String message = input.readUTF();
                            Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                            Object[] arguments = (Object[]) input.readObject();
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                            try {
                                //invoke this
                                Method method = service.getClass().getMethod(message, parameterTypes);
                                Object result = method.invoke(service, arguments);

                                output.writeObject(result);
                            } catch (Exception e) {
                                log.error("invoke error", e);
                            } finally {
                                output.close();
                                input.close();
                            }
                        } catch (IOException e) {
                            log.error("socket inputStream get error", e);
                            //todo pick this
                        } catch (ClassNotFoundException e) {
                            log.error("socket inputStream classNotFound", e);
                            //todo pick this
                        }
                    }
                }).start();
            } catch (Exception ex) {
                log.error("socket handle error", ex);
            }
        }
    }


    /**
     * 引用服务
     */
    public <T> T refer(final Class<T> interfaceClass, final String host, final int port) {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("interface is null");
        }
        if (StringUtils.isEmpty(host)) {
            throw new IllegalArgumentException("host is null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                Socket socket = new Socket(host, port);
                try {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(arguments);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object result = input.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }


}
