package org.exchangerates.factory;


import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BankServiceFactory {
  @Autowired
  private List<BankService> services;

  private static final Map<BankType, BankService> serviceCache = new HashMap<>();

  @PostConstruct
  public void init() {
    services.forEach(service -> serviceCache.put(service.getType(), service));
  }

  public static BankService getService(BankType type) {
    return Optional.ofNullable(serviceCache.get(type)).orElseThrow(() -> new RuntimeException("Unknown service type: " + type));
  }
}
