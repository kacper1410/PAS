package pl.pas.rest.jsonb.adapters;

import javax.json.Json;
import javax.json.JsonValue;
import javax.json.bind.adapter.JsonbAdapter;

public class StringToEmptyAdapter implements JsonbAdapter<String, JsonValue> {

    @Override
    public JsonValue adaptToJson(String s) throws Exception {
        return Json.createValue("");
    }

    @Override
    public String adaptFromJson(JsonValue jsonValue) throws Exception {
        return jsonValue.toString();
    }
}
