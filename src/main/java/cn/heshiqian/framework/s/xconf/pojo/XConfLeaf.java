package cn.heshiqian.framework.s.xconf.pojo;

public class XConfLeaf {

    private String originString;
    private String key;
    private String value;


    public XConfLeaf(String originString, String key, String value) {
        this.originString = originString;
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "XConfLeaf{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
