package myredis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 关于redis的一些常用的基础练习
 * Created by Administrator on 2017/9/19.
 */
public class prectisOne {
    private Jedis jedis;

    @Before
    public void beforeTest(){
        jedis = RedisUtil.getJedis();
    }

    @Test
    public void testKeys(){
        jedis.set("age","22");

        /*测试key失效，注意，5代表5秒*/
        jedis.expire("age",5);
//        jedis.expireAt("age",unixTime);
//        jedis.pexpire()
        System.out.println("before: "+jedis.get("age"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        System.out.println("after: "+jedis.get("age"));
    }

    /**
     * 测试redis的String操作
     */
    @Test
    public void testString(){
        jedis.set("name","apple");
        System.out.println(jedis.get("name"));
        jedis.append("name"," is my apple");
        System.out.println(jedis.get("name"));
        jedis.del("name");
        System.out.println(jedis.get("name"));
        System.out.println(jedis.exists("name"));
    }

}
