package com.loofi.asset.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetRuntimeException extends RuntimeException{
  HttpStatus httpStatus;
  String code;
}
