package net.petrikainulainen.spring.testmvc.common.controller;

/**
 * Created by Denis_Maliarevich on 2017-03-21
 */
public class UserBuilderBad {

  private User user;

  public UserBuilderBad() {
    user = new User();
  }

  public UserBuilderBad id(long id) {
    user.setId(id);
    return this;
  }

  public UserBuilderBad name(String name) {
    user.setName(name);
    return this;
  }

  public User build() {
    return user;
  }
}
