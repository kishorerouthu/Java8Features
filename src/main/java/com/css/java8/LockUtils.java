package com.css.java8;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides the static utility methods for executing the callback
 * operations on given unique lock resources within the Locks.
 *
 * Locks are implemented using concurrency locks in java.util.concurrent.locks
 * @see ReentrantLock
 * @see ReentrantReadWriteLock
 *
 * Following are different methods provides execution within the locks
 *  1. executeInReadMode(context, uniqueLockId, operationName, callable)
 *  2. executeInWriteMode(context, uniqueLockId, operationName, callable)
 *  3. execute(context, uniqueLockId, operationName, callable)
 *  4. executeIfLockAlreadyNotAcquired(context, uniqueLockId, operationName, callable)
 *
 *  All above methods takes same type of parameters described below
 *  Parameters :
 *    context       - context of in which lock required
 *    uniqueLockId  - unique lock identifier within the context
 *    operationName - name of the operation to perform within the lock
 *    callable      - callback of the operation
 *  Return :
 *    V         - Result type given from the callable result
 *
 *  throws Exception
 *
 *
 * @author Kishore Routhu on 14/3/18 7:08 PM.
 */
public class LockUtils {

    private static final Logger logger = LoggerFactory.getLogger(LockUtils.class);

    private static final Map<String, Object> resources = new WeakHashMap<>();


    /**
     * This method executes the given operation within the ReadLock from ReentrantReadWriteLock.readLock()
     */
    public static <V> V executeInReadMode(String context, String uniqueLockId, String operationName, Callable<V> callable) throws Exception {
        String uniqueLockResource = getUniqueLockResource(context, uniqueLockId);
        ReadWriteLock reentrantReadWriteLock = getReentrantReadWriteLock(uniqueLockResource);
        Lock lock = reentrantReadWriteLock.readLock();
        return LockUtils.executeInLock(uniqueLockResource, "read", operationName, lock, callable);
    }

    /**
     * This method executes the given operation within the WriteLock from ReentrantReadWriteLock.writeLock()
     */
    public static <V> V executeInWriteMode(String context, String uniqueLockId, String operationName, Callable<V> callable) throws Exception {
        String uniqueLockResource = getUniqueLockResource(context, uniqueLockId);
        ReadWriteLock reentrantReadWriteLock = getReentrantReadWriteLock(uniqueLockResource);
        Lock lock = reentrantReadWriteLock.writeLock();
        return LockUtils.executeInLock(uniqueLockResource, "write", operationName, lock, callable);
    }

    /**
     * This method executes the given operation within the generic lock ReentrantLock
     */
    public static <V> V execute(String context, String uniqueLockId, String operationName, Callable<V> callable) throws Exception {
        String uniqueLockResource = getUniqueLockResource(context, uniqueLockId);
        Lock lock = getLock(uniqueLockResource);
        return LockUtils.executeInLock(uniqueLockResource, "", operationName, lock, callable);
    }

    /**
     * This method executes the given operation only if the lock has not been acquired on given unique resource.
     * It throws exception if the lock has already acquired on the given resource
     */
    public static <V> V executeIfLockAlreadyNotAcquired(String context, String uniqueLockId, String operationName, Callable<V> callable) throws Exception {
        String uniqueLockResource = getUniqueLockResource(context, uniqueLockId);
        final Lock lock = getLock(uniqueLockResource);
        if (lock.tryLock()) {
            return LockUtils.executeInLock(uniqueLockResource, "", operationName, lock, callable);
        }
        throw new Exception("Lock on resource " + uniqueLockResource + " is already acquired");
    }

    private static <V> V executeInLock(String uniqueLockResource, String accessMode, String operationName, Lock lock, Callable<V> callable) throws Exception {
        logger.debug("Acquiring {} lock {} for operation {}", accessMode, uniqueLockResource, operationName);
        lock.lock();
        logger.debug("Acquired {} lock {} for operation {}", accessMode, uniqueLockResource, operationName);
        try {
            return callable.call();
        } finally {
            logger.debug("Releasing {} lock {} for operation {}", accessMode, uniqueLockResource, operationName);
            lock.unlock();
            logger.debug("Released {} lock {} for operation {}", accessMode, uniqueLockResource, operationName);
        }
    }

    private static Lock getLock(String uniqueLockResource) {
        Lock lock;
        synchronized (uniqueLockResource) {
            lock = (Lock) resources.get(uniqueLockResource);
            if (lock == null) {
                lock = new ReentrantLock();
                resources.put(uniqueLockResource, lock);
            }
        }
        return lock;
    }

    private static ReentrantReadWriteLock getReentrantReadWriteLock(String uniqueLockResource) {
        ReentrantReadWriteLock reentrantReadWriteLock;
        synchronized (uniqueLockResource) {
            reentrantReadWriteLock = (ReentrantReadWriteLock) resources.get(uniqueLockResource);
            if (reentrantReadWriteLock == null) {
                reentrantReadWriteLock = new ReentrantReadWriteLock(true);
                resources.put(uniqueLockResource, reentrantReadWriteLock);
            }
        }
        return reentrantReadWriteLock;
    }

    private static String getUniqueLockResource(String context, String uniqueLockId) {
        return (context + uniqueLockId).intern();
    }
}
