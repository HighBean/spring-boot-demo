package com.boomboo.demo.java.serialize;//package serialize;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.tools.internal.ws.processor.model.java.JavaType;
//import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
//
//import java.util.HashMap;
//
//public class FastJsonSerializer {
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    public void test() {
//        // 方式1
//        Pager<User> userPager1 = mapper.readValue(json, new TypeReference<Pager<User>>() {
//        });
//        // 方式2
//        Type[] types = new Type[1];
//        types[0] = User.class;
//        final ParameterizedTypeImpl type = ParameterizedTypeImpl.make(Pager.class, types, Pager.class.getDeclaringClass());
//        TypeReference typeReference = new TypeReference<Pager>() {
//            @Override
//            public Type getType() {
//                return type;
//            }
//        };
//        Pager<User> userPager2 = mapper.readValue(json, typeReference);
//        // 方式3
//        JavaType javaType = mapper.getTypeFactory().constructParametrizedType(Pager.class, Pager.class, User.class);
//        Pager<User> userPager3 = mapper.readValue(json, javaType);
//        // 方式4
//        JavaType javaType1 = mapper.getTypeFactory().constructParametricType(Pager.class, User.class);
//        Pager<User> userPager4 = mapper.readValue(json, javaType1);
//        // 方式5，新建另一个指定具体泛型T的参数的类
//        PagerAppoint userPager5 = mapper.readValue(json, PagerAppoint.class);
//        // 数组泛型的序列化和反序列化
//        String json1 = mapper.writeValueAsString(users);
//        JavaType javaType2 = mapper.getTypeFactory().constructParametricType(List.class, User.class);
//        List<User> users1 = mapper.readValue(json1, javaType2);
//        // HashMap
//        Map<String, User> map = new HashMap<String, User>(16);
//        map.put("test", user);
//        String json2 = mapper.writeValueAsString(map);
//        // 1
//        Map<String, User> users2 = mapper.readValue(json2, new TypeReference<Map<String, User>>() {
//        });
//        // 2
//        JavaType javaType3 = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, User.class);
//        Map<String, User> users3 = mapper.readValue(json2, javaType3);
//    }
//}
