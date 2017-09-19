package myredis;

import redis.clients.jedis.Jedis;

/**
 * redis初探秘
 * Created by Administrator on 2017/9/17.
 */
public class initOne {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.79.129", 6379);
//        jedis.auth("233503");
        jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));//执行结果：xinxin

        //测试中文字符
        jedis.set("gender","男");
        System.out.println(jedis.get("gender"));

        Jedis jedis1 = RedisUtil.getJedis();
        jedis1.del("tel");
        RedisUtil.returnResource(jedis1);

    }

}
