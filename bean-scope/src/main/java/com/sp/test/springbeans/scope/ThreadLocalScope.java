package com.sp.test.springbeans.scope;


import com.sun.istack.internal.Nullable;
import lombok.NonNull;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地线程级别Scope
 * Created by AlexLc on 2020/5/9.
 */
public class ThreadLocalScope implements Scope {
    public static final String SCOPE_NAME = "thread-local";
    private final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("thread-local-scope"){
      public Map<String, Object> initialValue(){
          return new HashMap<>();
      }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        //非空
        Map<String, Object> context = getContext();
        Object object = context.get(name);

        if(object == null){
            object = objectFactory.getObject();
            context.put(name, object);
        }

        return object;
    }

    @NonNull
    private Map<String, Object> getContext(){
        return threadLocal.get();
    }

    @Override
    public Object remove(String name) {
        return getContext().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //TODO
    }

    @Override
    public Object resolveContextualObject(String key) {
        return getContext().get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
