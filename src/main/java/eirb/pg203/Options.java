package eirb.pg203;

import java.util.List;

public abstract class Options<T extends CalElement> {
    public abstract List<T> filter(String type, List<T> elements);
}
