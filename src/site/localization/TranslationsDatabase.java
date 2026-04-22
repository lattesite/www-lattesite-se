package site.localization;

import site.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class TranslationsDatabase {

    private final List<Translation> list;

    public TranslationsDatabase() {
        this.list = new ArrayList<>();
    }

    public void add(String key, String value, String updated) {
        add(new Translation(key, value, updated));
    }

    public void add(String key, String value) {
        add(new Translation(key, value, DateUtil.toYYYYMMDDHHMMSS(DateUtil.now())));
    }

    public void add(Translation translation) {
        for (Translation existing : this.list) {
            if (existing.getKey().equals(translation.getKey())) {
                existing.setValue(translation.getValue());
                existing.setUpdated(translation.getUpdated());
                return;
            }
        }
        list.add(translation);
    }

    public List<Translation> getAll() {
        return this.list;
    }

}
