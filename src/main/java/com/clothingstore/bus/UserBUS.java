package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import com.clothingstore.dao.UserDAO;
import com.clothingstore.enums.UserStatus;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.UserModel;

import services.PasswordUtils;
import services.Validation;

public class UserBUS implements IBUS<UserModel> {

  private final List<UserModel> userList = new ArrayList<>();
  private static UserBUS instance;

  public static UserBUS getInstance() {
    if (instance == null) {
      instance = new UserBUS();
    }
    return instance;
  }

  private UserBUS() {
    this.userList.addAll(UserDAO.getInstance().readDatabase());
  }

  @Override
  public List<UserModel> getAllModels() {
    return Collections.unmodifiableList(userList);
  }

  @Override
  public void refreshData() {
    userList.clear();
    userList.addAll(UserDAO.getInstance().readDatabase());
  }

  @Override
  public UserModel getModelById(int id) {
    for (UserModel userModel : userList) {
      if (userModel.getId() == id) {
        return userModel;
      }
    }

    UserModel userModel = UserDAO.getInstance().getUserById(id);
    if (userModel != null) {
      userList.add(userModel);
    }
    return userModel;
  }

  @Override
  public int addModel(UserModel userModel) {
    if (userModel.getUsername() == null ||
        userModel.getUsername().isEmpty() ||
        userModel.getName() == null ||
        userModel.getName().isEmpty() ||
        userModel.getPassword() == null ||
        userModel.getPassword().isEmpty()) {
      throw new IllegalArgumentException(
          "Username, name and password cannot be empty. Please check the input and try again.");
    }

    boolean hasPhone = userModel.getPhone() != null && !userModel.getPhone().isEmpty();
    boolean hasEmail = userModel.getEmail() != null && !userModel.getEmail().isEmpty();

    if (hasEmail && !Validation.isValidEmail(userModel.getEmail())) {
      throw new IllegalArgumentException("Invalid email address.");
    }

    if (hasPhone && !Validation.isValidPhoneNumber(userModel.getPhone())) {
      throw new IllegalArgumentException("Invalid number format.");
    }
    // 0 is admin, 1 is manager, 2 is employee
    userModel.setRoleId(2);

    userModel.setUserStatus(
        userModel.getUserStatus() != null ? userModel.getUserStatus() : UserStatus.ACTIVE);
    int id = UserDAO.getInstance().insert(mapToEntity(userModel));
    userModel.setId(id);
    userList.add(userModel);
    return id;
  }

  @Override
  public int updateModel(UserModel userModel) {
    int updatedRows = UserDAO.getInstance().update(userModel);
    if (updatedRows > 0) {
      for (int i = 0; i < userList.size(); i++) {
        if (userList.get(i).getId() == userModel.getId()) {
          userList.set(i, userModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    UserModel userModel = getModelById(id);
    if (userModel == null) {
      throw new IllegalArgumentException(
          "User with ID " + id + " does not exist.");
    }
    int deletedRows = UserDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      userList.remove(userModel);
    }
    return deletedRows;
  }

  @Override
  public List<UserModel> searchModel(String value, String[] columns) {
    value.toLowerCase();
    List<UserModel> results = new ArrayList<>();
    List<UserModel> entities = UserDAO.getInstance().search(value, columns);
    for (UserModel entity : entities) {
      UserModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  private UserModel mapToEntity(UserModel from) {
    UserModel to = new UserModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(UserModel from, UserModel to) {
    to.setId(from.getId());
    to.setUsername(from.getUsername());
    to.setPassword(from.getPassword());
    to.setUserStatus(from.getUserStatus());
    to.setName(from.getName());
    to.setEmail(from.getEmail());
    to.setPhone(from.getPhone());
    to.setRoleId(from.getRoleId());
  }

  private boolean checkFilter(
      UserModel userModel,
      String value,
      String[] columns) {
    value.toLowerCase();
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (userModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "username" -> {
          if (userModel.getUsername().toLowerCase().contains(value)) {
            return true;
          }
        }
        case "status" -> {
          if (userModel.getUserStatus().toString().equals(value)) {
            return true;
          }
        }
        case "name" -> {
          if (userModel.getName().contains(value)) {
            return true;
          }
        }
        case "email" -> {
          if (userModel.getEmail().contains(value)) {
            return true;
          }
        }
        case "phone" -> {
          if (userModel.getPhone().equals(value)) {
            return true;
          }
        }
        case "role" -> {
          if (String.valueOf(userModel.getRoleId()).contains(value)) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(userModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(UserModel userModel, String value) {
    return (userModel.getId() == Integer.parseInt(value) ||
        userModel.getUsername().equalsIgnoreCase(value) ||
        userModel.getUserStatus().toString().equalsIgnoreCase(value) ||
        userModel.getName().equalsIgnoreCase(value) ||
        userModel.getEmail().equalsIgnoreCase(value) ||
        userModel.getPhone().equals(value) ||
        String.valueOf(userModel.getRoleId()).equalsIgnoreCase(value));
  }

  public boolean checkForDuplicate(List<String> values, String[] columns) {
    Optional<UserModel> optionalUser = UserBUS.getInstance().getAllModels().stream().filter(user -> {
      for (String value : values) {
        if (Arrays.asList(columns).contains("username") &&
            !value.isEmpty() &&
            user.getUsername().equals(value)) {
          return true;
        }
        if (Arrays.asList(columns).contains("email") &&
            user.getEmail().equals(value)) {
          return true;
        }
        if (Arrays.asList(columns).contains("phone") &&
            user.getPhone().equals(value)) {
          return true;
        }
      }
      return false;
    })
        .findFirst();
    return optionalUser.isPresent();
  }

  public UserModel login(String username, String password) throws LoginException {
    if (!Validation.isValidUsername(username)) {
      throw new LoginException("The account must have at least 8 characters, both letters and numbers");
    }
    if (!Validation.isValidPassword(password)) {
      throw new LoginException(
          "Password must have at least 8 characters, contain both letters and numbers, and capitalize the first letter");
    }
    UserModel userModel = UserDAO.getInstance().getUserByUsername(username);
    if (userModel == null) {
      throw new LoginException("User not found");
    }
    if (!PasswordUtils.checkPassword(password, userModel.getPassword())) {
      throw new LoginException("Incorrect password");
    }
    if (userModel.getUserStatus() == UserStatus.INACTIVE) {
      throw new LoginException("User is inactive");
    }
    if (userModel.getUserStatus() == UserStatus.BANNED) {
      throw new LoginException("User is banned");
    }
    return userModel;
  }
}
