package services;

import com.clothingstore.bus.RoleBUS;
import com.clothingstore.bus.UserBUS;
import com.clothingstore.enums.UserStatus;
import com.clothingstore.models.RoleModel;
import com.clothingstore.models.UserModel;

public class Authentication {

  private static Authentication instance;
  private UserModel currentUser;

  private Authentication() {
    currentUser = null;
  }

  public static Authentication getInstance() {
    if (instance == null) {
      instance = new Authentication();
    }
    return instance;
  }

  public static UserModel getCurrentUser() {
    if (Authentication.getInstance().currentUser == null) {
      return null;
    }
    setCurrentUser(
        UserBUS
            .getInstance()
            .getModelById(Authentication.getInstance().currentUser.getId()));
    return Authentication.getInstance().currentUser;
  }

  public static void setCurrentUser(UserModel currentUser) {
    if (currentUser == null) {
      throw new RuntimeException("User is null");
    }

    if (currentUser.getUserStatus() != UserStatus.ACTIVE) {
      throw new RuntimeException("User is not active");
    }

    Authentication.getInstance().currentUser = currentUser;
  }

  public static void logout() {
    Authentication.getInstance().currentUser = null;
  }

  public static boolean isLoggedIn() {
    return Authentication.getInstance().currentUser != null;
  }

  public static boolean isRole(String role) {
    if (!Authentication.isLoggedIn()) {
      return false;
    }
    RoleModel roleModel = RoleBUS.getInstance().getModelById(Authentication.getInstance().currentUser.getRoleId());
    return roleModel.getName().equals(role);
  }
}
