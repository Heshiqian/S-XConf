import cn.heshiqian.framework.s.xconf.XConf;
import cn.heshiqian.framework.s.xconf.pojo.XConfTree;

public class Test {

    @org.junit.Test
    public void t1() {
//        XConfTree read = XConf.read("configuration.conf",XConf.CONF);
        XConfTree read = XConf.read("configuration.conf", XConf.CONF);
        String value = read.getRootByName("server").getLeafByName("staticFilePath").getValue();
        System.out.println(value);
    }
}
