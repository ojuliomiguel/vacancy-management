package br.com.bluelobster.vacancy_management.modules.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException() {
    super("User already exists");
  }
}
