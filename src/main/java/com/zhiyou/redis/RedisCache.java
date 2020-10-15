package com.zhiyou.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisCache implements Cache{
	//定义一个id
	private final String id;
	//创建读写锁
	private final ReadWriteLock rwl = new ReentrantReadWriteLock();
	//创建一个redis连接
	private static JedisConnectionFactory jedisConnectionFactory;
	
	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
		RedisCache.jedisConnectionFactory=jedisConnectionFactory;
	}
	
	public RedisCache(String id){
		this.id=id;
	}
	//
	public String getId() {	
		return id;
	}
	//将数据写入到缓存中
	public void putObject(Object key, Object value) {
		rwl.writeLock().lock();//写数据的时候 加入锁，防止自己写的时候别人也写
		RedisConnection redisConnection = jedisConnectionFactory.getConnection();//获取到redis连接
		RedisSerializer<Object> ser = new JdkSerializationRedisSerializer();//用于序列化数据(key,values)
		redisConnection.set(ser.serialize(key), ser.serialize(value));
		System.out.println("添加二级缓存成功key"+key+"value"+value);
		redisConnection.close();
		rwl.writeLock().unlock();//写入成功后放开锁
		
	}
	//从缓存中删除数据
	public Object getObject(Object key) {
		rwl.readLock().lock();//读锁
		RedisConnection redisConnection = jedisConnectionFactory.getConnection();//获取到redis连接
		RedisSerializer<Object> ser = new JdkSerializationRedisSerializer();//用于序列化数据(key,values)
		//根据序列化后的key从redis中取值，然后再将redis中取出的数据进行反序列化
		Object object = ser.deserialize(redisConnection.get(ser.serialize(key)));
		System.out.println("命中二级缓存成功value"+object);
		redisConnection.close();
		rwl.readLock().unlock();	
		return object;
	}
	//从缓存中删除数据
	public Object removeObject(Object key) {
		rwl.writeLock().lock();//读锁
		RedisConnection redisConnection = jedisConnectionFactory.getConnection();//获取到redis连接
		RedisSerializer<Object> ser = new JdkSerializationRedisSerializer();//用于序列化数据(key,values)
		Boolean expire = redisConnection.expire(ser.serialize(key), 0);
		redisConnection.close();
		rwl.writeLock().unlock();	
		return expire;
	}
	//清空缓存
	public void clear() {
		rwl.readLock().lock();
		RedisConnection redisConnection = jedisConnectionFactory.getConnection();
		redisConnection.flushDb();
		redisConnection.flushAll();
		redisConnection.close();
		rwl.readLock().unlock();
		
	}
	//获得缓存中的数据量
	public int getSize() {
		RedisConnection redisConnection = jedisConnectionFactory.getConnection();
		Integer size = Integer.valueOf(redisConnection.dbSize().toString());
		redisConnection.close();
		return size;
	}
	//
	public ReadWriteLock getReadWriteLock() {		
		return rwl;
	}

}
