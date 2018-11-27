package com.boomboo.demo.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.InvokerListenerAdapter;
import lombok.extern.slf4j.Slf4j;


/**
 * 对每次调用添加ref监听
 * @Description 同理，ExporterListenerAdapter对export添加监听
 */
@Slf4j
@Activate
public class InvokerListenerA extends InvokerListenerAdapter {

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        URL url = invoker.getUrl();
        log.info("referred listener:{}", url.toMap());
    }




}
