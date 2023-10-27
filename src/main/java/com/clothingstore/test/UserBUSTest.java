package com.clothingstore.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.clothingstore.bus.UserBUS;
import com.clothingstore.enums.UserStatus;
import com.clothingstore.models.UserModel;

public class UserBUSTest {

    @Test
    public void addModelTest() {
        UserModel userModel = new UserModel("bao212275", "Admin12345", "hoanggiabao212@gmail.com", "Hoàng Gia Bảo",
                "0972200871", "Đồng Nai", 1, "noimage", 2, UserStatus.ACTIVE);
        assertEquals(UserBUS.getInstance().addModel(userModel), 1);
    }
}
