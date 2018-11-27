package com.boomboo.demo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * dubbo组件调用TradeId跟踪
 */
@Activate
@Slf4j
public class DubboTradeIdFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String role = invoker.getUrl().getParameter(Constants.SIDE_KEY);
        log.info("role:{}", role);
        if (Constants.PROVIDER.equals(role)) {
            String tradeId = UUID.randomUUID().toString();
            RpcContext.getContext().setAttachment("tradeId", tradeId);
            MDC.put("tradeId", tradeId);
        } else {
            String tradeId = RpcContext.getContext().getAttachment("tradeId");
            MDC.put("tradeId", tradeId);
        }

        try {
            return invoker.invoke(invocation);
        } catch (RpcException re) {
            log.error("rpcEx", re);
            throw re;
        } finally {
            MDC.clear();
        }
    }
}
