package io.sanye.open.financial.seller;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/9/1 下午10:16
 */
@Component
public class HazelcastTest {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    //@PostConstruct
    public void init() {
        Map map = hazelcastInstance.getMap("imooc");
        map.put("name", "imooc");
    }
}
