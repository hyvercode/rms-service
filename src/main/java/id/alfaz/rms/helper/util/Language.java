package id.alfaz.rms.helper.util;

public enum Language {
    ID_ID("id-ID"),
    EN_ID("en-ID");

    private String code;

    private Language(String code) {
        this.code = code;
    }

    public static Language getValueOf(String type) {
        try {
            return valueOf(type.trim().toUpperCase().replace("-", "_"));
        } catch (Exception var2) {
            return ID_ID;
        }
    }

    public String getCode() {
        return this.code;
    }
}
