package eirb.pg203;

import java.util.Map;

public abstract class CalElement {

    protected final Map<String, String> fields;

    protected CalElement(Map<String,String> map){
        this.fields = map;
    }

    //public abstract String type();

    public String get(String key){
        return fields.getOrDefault(key, "");
    }

    public String getUid(){
        return fields.getOrDefault("UID", "");
    }

    public abstract ViewType viewType();


    @Override
    public abstract String toString();
}
