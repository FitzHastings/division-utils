package conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import division.util.Utility;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class P {
  public static Map<String,Object> map;
  
  static {
    try {
      map = new ObjectMapper().readValue(Utility.getStringFromFile("conf"+File.separator+"conf.json"), Map.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static boolean isNull(String name) {
    return get(name) == null;
  }
  
  public static boolean contains(String name) {
    return get(name) != null;
  }

  public static Object get(String name) {
    Object val = null;
    Map<String,Object> m = map;
    for(String key : name.split("\\.")) {
      val = m.get(key);
      if(val instanceof Map)
        m = (Map<String, Object>) val;
    }
    return val;
  }

  public static <T> T get(String name, Class<? extends T> type) {
    return (T) get(name);
  }
  
  public static <T> T get(String name, T defaultValue) {
    T v = (T) get(name, defaultValue.getClass());
    return v == null ? defaultValue : v;
  }

  public static List List(String name) {
    return get(name, List.class);
  }

  public static Map<String, Object> Map(String name) {
    return get(name, Map.class);
  }
  
  public static String String(String name) {
    return get(name, String.class);
  }
  
  public static Integer Integer(String name) {
    return get(name, Integer.TYPE);
  }
  
  public static Double Double(String name) {
    return get(name, Double.TYPE);
  }
  
  public static Float Float(String name) {
    return get(name, Float.TYPE);
  }
  
  public static BigDecimal BigDecimal(String name) {
    return get(name, BigDecimal.class);
  }
}
