package com.inesv.digiccy.common.cache.redis;

/**
 * @author balthie 10050 分布式缓存通用异常类
 */
public class RemoteCacheException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public RemoteCacheException()
    {
        super();
    }
    
    public RemoteCacheException(String string)
    {
        super(string);
    }
}
