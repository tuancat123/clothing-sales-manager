package com.clothingstore.bus;

import java.util.List;

import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.UserModel;

public class UserBUS implements IBUS<UserModel> {

    @Override
    public List<UserModel> getAllModels() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllModels'");
    }

    @Override
    public void refreshData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshData'");
    }

    @Override
    public UserModel getModelById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModelById'");
    }

    @Override
    public int addModel(UserModel model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addModel'");
    }

    @Override
    public int updateModel(UserModel model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateModel'");
    }

    @Override
    public int deleteModel(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteModel'");
    }

    @Override
    public List<UserModel> searchModel(String value, String[] columns) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchModel'");
    }

}
