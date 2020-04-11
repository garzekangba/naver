package test2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangjihe
 *
 * @date 2020-04-11
 */
public class CachedData {

    private volatile Object cachedData;

    private final Lock readLock;
    private final Lock writeLock;

    public CachedData(){
        ReadWriteLock lock=new ReentrantReadWriteLock();
        readLock=lock.readLock();
        writeLock=lock.writeLock();
    }

    private void updateData(){
        // update the cache object according to its data source
    }

    private boolean isValid(){
        return cachedData!=null;
    }

    public Object processCachedData(){
        readLock.lock();
        if(!isValid()){
            readLock.unlock();
            writeLock.lock();
            if(!isValid()){
                updateData();
            }
            readLock.lock();
            writeLock.unlock();
        }
        try {
            return cachedData;
        }finally {
            readLock.unlock();
        }
    }
}
