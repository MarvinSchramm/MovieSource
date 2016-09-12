package com.kreait;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakeService {

    public final static String CACHE_NAME = "numberCache";

    @Cacheable(CACHE_NAME)
    public Integer returnRandomNumber() {
        return new Random().nextInt();
    }

    @CacheEvict(CACHE_NAME)
    public void testEvict() {

    }
}
