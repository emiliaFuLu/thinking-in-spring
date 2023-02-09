package com.fulu.domain;

import com.fulu.annotation.Super;

/**
 * 超级用户
 *
 * @author fulu
 * {@code @date} 2023年01月30日 23:39
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
    