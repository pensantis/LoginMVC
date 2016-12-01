package org.academiadecodigo.loginmvcmaven.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codecadet on 28/11/16.
 */
public class ServiceRegistry {
    private static ServiceRegistry serviceRegistry;

    private Map<String, Service> registryMap = new HashMap<>();

    private ServiceRegistry() {
    }

    public Service getService(String serviceName) {
        return registryMap.get(serviceName);
    }

    public void addService(Service service) {
        registryMap.put(service.getServiceName(), service);
    }



    public static synchronized ServiceRegistry getServiceRegistry() {
        if (serviceRegistry == null) {
            serviceRegistry = new ServiceRegistry();
        }
        return serviceRegistry;
    }
}
