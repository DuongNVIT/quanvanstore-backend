package com.quanvanstorebackend.controller;

import com.quanvanstorebackend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController<T extends BaseService> {

    @Autowired
    public T service;

}
