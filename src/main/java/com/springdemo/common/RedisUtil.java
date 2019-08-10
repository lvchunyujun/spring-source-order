package com.springdemo.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.context.ContextLoader;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/9/25.
 */
public class RedisUtil<T extends Serializable> {

    private static JedisPool jedisReader =(JedisPool) SpringContextUtil.getBean("jedisReader");
    private static JedisPool jedisWriter =(JedisPool) SpringContextUtil.getBean("jedisWriter");

    private static class LazyHolder {
        private static final RedisUtil INSTANCE = new RedisUtil();
    }

    private static final String tagName = "redis:";
    private static final int outTime = 5184000;

    private RedisUtil() {
    }

    public static final RedisUtil getInstance() {
        return LazyHolder.INSTANCE;
    }


    public String get(String key) {
        Jedis reader = null;
        try {
            key = tagName + key;
            reader = jedisReader.getResource();
            String result = reader.get(key);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisReader.returnResourceObject(reader);
        }
    }
    public boolean keyExist(String key) {
        Jedis reader = null;
        try {
            key = tagName + key;
            reader = jedisReader.getResource();
            return reader.exists(key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            jedisReader.returnResourceObject(reader);
        }
    }
    public Double incrbyFloat(String key, Double value) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();
            return writer.incrByFloat(key, value);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }

    public Double incrbyFloat(String key, Double value, int expireTime) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();
            Double mount = writer.incrByFloat(key, value);
            writer.expire(key,expireTime);
            return mount;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }

    public T getSerialize(String key) {
        Jedis reader = null;
        try {
            key = tagName + key;
            reader = jedisReader.getResource();
            String result = reader.get(key);
            return (T) JSON.parse(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisReader.returnResourceObject(reader);
        }
    }

    public Long incrLong(String key) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();

            long result = writer.incr(key);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }
    public Long incrLong(String key, int expireTime) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();
            long result = writer.incr(key);
            writer.expire(key,expireTime);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }
    public Long decrLong(String key) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();
            long result = writer.decr(key);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }

    public Map<String, String> hGetAll(String key) {
        Jedis reader = null;
        try {
            key = tagName + key;
            reader = jedisReader.getResource();
            Map<String, String> map = reader.hgetAll(key);
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisReader.returnResourceObject(reader);
        }


    }

    /**
     * @param key
     * @param value
     * @param time  0则添加默认时间60天
     * @return
     */
    public String set(String key, String value, int time) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();
            String result = writer.set(key, value);
            if (time == 0) {
                time = outTime;
            }
            writer.expire(key, time);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }
    /** set 不设置失效时间-->永不失效   20151201
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();
            String result = writer.set(key, value);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }


    /**
     * @param key
     * @param entity
     * @param time
     * @return
     * @throws UnsupportedEncodingException
     */
    public String set(String key, T entity, int time) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();
            SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteClassName};
            String jsonResult = JSON.toJSONString(entity, features);
            String result = writer.set(key, jsonResult);
            if (time == 0) {
                time = outTime;
            }
            writer.expire(key, time);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }

    public String hmSet(String key, Map<String, String> hash, int time) {
        Jedis writer = null;
        try {
            key = tagName + key;
            writer = jedisWriter.getResource();
            String result = writer.hmset(key, hash);
            if (time == 0) {
                time = outTime;
            }
            writer.expire(key, time);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            jedisWriter.returnResourceObject(writer);
        }
    }
    public boolean remove(String key) {
        Jedis jedis = null;
        try {
            key = tagName + key;
            jedis = jedisWriter.getResource();
            jedis.del(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException("删缓存出错啦····");
        } finally {
            jedisWriter.returnResource(jedis);
        }
        return false;
    }
    public boolean setExpire(String key, int seconds) {
        Jedis jedis = null;
        try {
            key = tagName + key;
            jedis = jedisWriter.getResource();
            jedis.expire(key,seconds);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException("设置过期时间出错了····");
        } finally {
            jedisWriter.returnResource(jedis);
        }
        return false;
    }


    public Long publish(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisWriter.getResource();
            return jedis.publish(channel,message);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException("publish出错了····");
        } finally {
            jedisWriter.returnResource(jedis);
        }
        return null;
    }
    public Long time() {
        Jedis jedis = null;
        try {
            jedis = jedisWriter.getResource();
            List<String> time= jedis.time();
            return Long.parseLong(time.get(0)+time.get(1));
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException("得到时间出错了····");
        } finally {
            jedisWriter.returnResource(jedis);
        }
        return null;
    }



//    /**
//     * 新增 putBytes
//     *
//     * @param key
//     * @param value Obj转字节数组
//     * @return
//     */
//    public String putBytes(String key, byte[] value, int past) {
//        Jedis writer = jedisWriter.getResource();
//        byte[] bk = key.getBytes();
//        String re = writer.set(bk, value);
//        writer.expire(bk, past);
//        jedisWriter.returnResourceObject(writer);
//        return re;
//    }
//
//    public byte[] getBytes(String key) {
//        Jedis reader = jedisReader.getResource();
//        byte[] bytes = null;
//        bytes = reader.get(key.getBytes());
//        jedisReader.returnResourceObject(reader);
//        return bytes;
//    }
//
//    public Object getObject(String key) {
//        try {
//            Jedis reader = jedisReader.getResource();
//            byte[] bytes = reader.get(key.getBytes());
//            if (bytes == null) {
//                return null;
//            }
//            jedisReader.returnResourceObject(reader);
//            Object object = this.unSerialize(bytes);
//            return object;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    public String setObject(String key, Object object, int past) {
//        Jedis writer = jedisWriter.getResource();
//        byte[] kb = key.getBytes();
//        try {
//            byte[] byteIn = serialize(object);
//            String result = writer.set(kb, byteIn);
//            writer.expire(kb, past);
//            jedisWriter.returnResourceObject(writer);
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    public byte[] serialize(Object object) throws IOException {
//        byte[] bytes = new byte[0];
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(object);
//        oos.flush();
//        bytes = baos.toByteArray();
//        oos.close();
//        return bytes;
//    }
//
//    public Object unSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
//        Object object = null;
//        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
//        object = ois.readObject();
//        ois.close();
//        return object;
//    }
public static void main(String[] args) {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxTotal(500);
    jedisPoolConfig.setMaxIdle(5);
    jedisPoolConfig.setMaxWaitMillis(1000 * 100);
    jedisPoolConfig.setTestOnBorrow(true);
    JedisPool jedisPool=new JedisPool(jedisPoolConfig,"10.10.99.242",6379);
    Jedis writer = null;
    try {
        writer = jedisPool.getResource();
        String result = writer.set("cipher", GenUtil.generate(10)+DateTimeUtils.getDateToString()+GenUtil.generate(13));
        System.out.println(result);
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex);
    } finally {
        jedisPool.returnResourceObject(writer);
    }
    System.out.println(writer.get("cipher"));
    System.out.println(writer.get("syw111"));
}
}

