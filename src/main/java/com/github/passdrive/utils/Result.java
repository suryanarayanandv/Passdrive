package com.github.passdrive.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Result {
    private int code;
    private String message;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", code);
        jsonObject.addProperty("message", message);
        return jsonObject;
    }
}
