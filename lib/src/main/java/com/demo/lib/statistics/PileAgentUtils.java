package com.demo.lib.statistics;

import com.demo.lib.Utils.AppUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class PileAgentUtils {

    public static void agentWithParameter(String eventId, Map map) {
        try {
            MobclickAgent.onEvent(AppUtils.getApp(), eventId, map);
        } catch (Exception e) {
        }
    }

    public static Map buildParameters(String key, String value) {
        Map map = new HashMap();
        map.put(key, value);
        return map;
    }

    public static Map buildParameters(String[] keys, String... values) {
        Map map = new HashMap();
        if (null == keys || values == null) {
            return map;
        }
        if (keys.length <= 0 && values.length <= 0) {
            return map;
        }
        if (keys.length != values.length) {
            return map;
        }
        for (int position = 0; position < keys.length; position++) {
            map.putAll(buildParameters(keys[position], values[position]));
        }
        return map;
    }

    public static void agentWithoutParameter(String eventId) {
        MobclickAgent.onEvent(AppUtils.getApp(), eventId);
    }

    public static void agentWithParameter(String eventId, String key, String value) {
        agentWithParameter(eventId, buildParameters(key, value));
    }
}
