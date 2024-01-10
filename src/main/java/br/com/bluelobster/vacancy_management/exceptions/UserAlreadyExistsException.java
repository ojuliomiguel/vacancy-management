package br.com.bluelobster.vacancy_management.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException() {
    super("User already exists");
  }
}
