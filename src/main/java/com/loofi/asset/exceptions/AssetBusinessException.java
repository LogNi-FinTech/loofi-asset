package com.loofi.asset.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetBusinessException extends RuntimeException {

  HttpStatus httpStatus;
  String code;

  public AssetBusinessException(HttpStatus httpStatus, String code, String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.code = code;
  }
}
