package com.boomboo.demo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.alibaba.dubbo.rpc.RpcException.TIMEOUT_EXCEPTION;

/**
 * dubbo 调用白名单
 */
@Slf4j
@Activate
public class AuthorityFilter implements Filter {

    @Getter
    @Setter
    private List<String> ipWhiteList;

    private boolean deny = false;

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String side = invoker.getUrl().getParameter(Constants.SIDE_KEY);
        String host = RpcContext.getContext().getRemoteHost();
        log.info("dubbo invoke host:{},side:{}", host, side);
        if (!deny) {
            return invoker.invoke(invocation);
        } else {
            throw new RpcException(TIMEOUT_EXCEPTION, "permission deny");
        }
    }
}
