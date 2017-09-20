package myredis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

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
        jedis.set("gender","男");
        jedis.set("long","长生不老");

        /*测试key失效，注意，5代表5秒*/
        jedis.expire("age",5);
//        jedis.expireAt("age",unixTime);
        /*测试key失效，注意，5000代表毫秒*/
        jedis.pexpire("gender",10000l);
        System.out.println("before age: "+jedis.get("age"));
        System.out.println("before gender: "+jedis.get("gender"));
        try {
            Thread.sleep(6000);
            /*测试某个key值剩余的有效时间，毫秒级/秒级*/
            System.out.println("gender's live is: "+jedis.pttl("gender"));
            System.out.println("gender's live is: "+jedis.ttl("gender"));
            System.out.println("long's live is: "+jedis.ttl("long"));
            Thread.sleep(6000);
        } catch (InterruptedException e) {
        }
        System.out.println("after age: "+jedis.get("age"));
        System.out.println("after gender: "+jedis.get("gender"));
    }

    @Test
    public void testKey2(){
        jedis.set("abc1","1");
        jedis.set("abc2","2");
        jedis.set("abc3","3");
        jedis.set("abc4","4");

        /*取出所有key*/
        Set strSet=jedis.keys("*");
        System.out.println(strSet);
        /*移动key，选择redis库*/
        jedis.move("abc1",2);
        jedis.select(2);

        strSet=jedis.keys("*");
        System.out.println(strSet);

        /*随机返回一个key*/
        System.out.println(jedis.randomKey());

        /*修改key的名称,若新的名称存在，那么覆盖掉*/
        if (jedis.exists("newlong")){
            jedis.rename("newlong","long");
            System.out.println("newlong is exists?"+jedis.exists("newlong"));
        }
        /*修改key的名称,若新的名称存在，那么更改失败,返回0-失败 1-成功*/
        jedis.set("frute","banana");
        jedis.set("frute2","apple");
        System.out.println(jedis.renamenx("frute","frute2"));
        System.out.println(jedis.renamenx("frute","frute3"));
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.type("frute2"));
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
        System.out.println(jedis.keys("*"));
    }

}
