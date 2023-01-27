package com.model;


@SuppressWarnings("serial")
class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String message){
        super(message);
    }
}
