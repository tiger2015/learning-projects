package com.tiger.multithread.reference;

@FunctionalInterface
public interface CacheLoader<K, V> {
    V load(K k);
}
