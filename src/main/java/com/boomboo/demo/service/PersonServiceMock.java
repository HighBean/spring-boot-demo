package com.boomboo.demo.service;

import com.boomboo.demo.dto.PersonDTO;

/**
 * dubbo mock 服务降级
 *  @See MockClusterInvoker#invoke RPCException中非业务异常，使用mock数据
 *      public static final int UNKNOWN_EXCEPTION = 0;
        public static final int NETWORK_EXCEPTION = 1;
        public static final int TIMEOUT_EXCEPTION = 2;
        public static final int BIZ_EXCEPTION = 3;
        public static final int FORBIDDEN_EXCEPTION = 4;
        public static final int SERIALIZATION_EXCEPTION = 5;
 */
public class PersonServiceMock implements PersonService {
    @Override
    public Integer getAgeByName(String name) {
        return null;
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return null;
    }
}
