package com.loofi.asset.exceptions;

import java.util.HashMap;
import java.util.Map;

public class AssetErrors {
  private AssetErrors() {

  }

  // component code
  public static final String LOOFI_ASSET_SERVICE = "12";

  // feature code
  public static final String LOOFI_ASSET = "001";

  // error code
  public static final String LOOFI_ASSET_NOT_FOUND = "4001";


  // Error mapping
  public static final Map<String, String> ERROR_MAP = new HashMap<String, String>();

  static {
    ERROR_MAP.put(LOOFI_ASSET_NOT_FOUND, "Asset Not Found");


  }

  public static String getErrorCode(String featureCode, String errorCode) {
    return AssetErrors.LOOFI_ASSET_SERVICE + featureCode + errorCode;
  }
}
