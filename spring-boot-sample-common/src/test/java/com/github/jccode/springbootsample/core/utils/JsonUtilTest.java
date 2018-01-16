package com.github.jccode.springbootsample.core.utils;

import org.junit.Test;

import java.io.Serializable;
import java.util.Objects;

import static org.junit.Assert.*;

public class JsonUtilTest {

    static class User implements Serializable {
        public String name;
        public int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return age == user.age &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }



    private User tom = new User("tom", 18);
    private String tomJsonStr = "{\"name\":\"tom\",\"age\":18}";

    @Test
    public void toJson() {
        String s = JsonUtil.toJson(tom);
        assertEquals("serialized string should equals.", tomJsonStr, s);
    }

    @Test
    public void fromJson() {
        User user = JsonUtil.fromJson(tomJsonStr, User.class);
        assertEquals("should equals after deserilized.", tom, user);
    }
}