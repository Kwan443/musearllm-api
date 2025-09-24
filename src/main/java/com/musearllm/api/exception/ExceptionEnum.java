package com.musearllm.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements BaseErrorInfoInterface {

    SUCCESS(200, "success"),
    USER_EXISTED(300, "User(UID) existed"),
    USER_NOT_FOUND(301, "User(UID) Not Found"),
    INVALID_OTAFILETYPE(302, "Invalid OTA File Type"),
    BODY_NOT_MATCH(400, "Body not match"),
    NULL_POINTER(401, "null pointer exception"),
    IO_EXCEPTION(504, "IO exception error"),
    NOT_FOUND(404, "no data found"),
    OTA_FILE_NOT_FOUND(405, "OTA File Not Found"),
    DATA_TRUNCATION(505, "Data truncation"),
    BINDING_EXCEPTION(506, "invalid bound statement"),
    UNSUPPORTED_OPERATION_EXCEPTION(507, "unsupported Operation Exception"),
    DB_EXCEPTION(508, "DB Exception"),
    ARRAY_INDEX_OUT_OF_BOUND_EXCEPTION(509, "index out of bound Exception"),
    FILE_NOT_FOUND_EXCEPTION(600, "file not existed"),
    INTERNAL_SERVER_ERROR(500, "internal server error"),
    INVALID_DATE_FORMAT(400, "Invalid date format provided"),
    VERSION_MISMATCH(601, "Version mismatch between HEX and DAT files"),
    INVALID_OTA_CONFIG(602, "Invalid OTA configuration"),
    ACCOUNT_LOCKED(403, "Account is locked"),
    INVALID_CREDENTIALS(402, "Invalid username or password"),
    TOKEN_EXPIRED(405, "Authentication token has expired"),
    UNAUTHORIZED(406, "Unauthorized access");

    /**
     * 错误码
     */
    private final int resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    @Override
    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}