package com.wjf.github.commons.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ThreadUtil implements Runnable {

	private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

	private final AtomicInteger atomicInteger;

	private final Thread thread;

	public ThreadUtil(AtomicInteger atomicInteger, Thread thread) {
		this.atomicInteger = atomicInteger;
		this.thread = thread;
	}

	@Override
	public void run() {
		Integer i;
		i = THREAD_LOCAL.get();
		while (i == null && (i = THREAD_LOCAL.get()) == null) {
			THREAD_LOCAL.set(1);
			Thread.yield();
		}

		for (; (i = THREAD_LOCAL.get()) < 100; i++, THREAD_LOCAL.set(i)) {
			log.info("{}----{}", Thread.currentThread().getName(), i);
		}

		int k = atomicInteger.get();

		while (!atomicInteger.compareAndSet(k, k - 1) && (k = atomicInteger.get()) > 0) {}
		LockSupport.unpark(thread);
		log.info("处理完成");
	}
}
