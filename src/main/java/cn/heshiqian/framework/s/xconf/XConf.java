package cn.heshiqian.framework.s.xconf;

import cn.heshiqian.framework.s.xconf.core.XConfCore;
import cn.heshiqian.framework.s.xconf.exception.XConfException;
import cn.heshiqian.framework.s.xconf.pojo.XConfTree;

import java.net.URL;

public final class XConf {

    public static final short CONF = -10;
    public static final short PROPERTIES = -20;

    private static XConfCore xConfCore;

    /**
     * 读取一个配置文件
     *
     * @param path 文件名称或路径
     * @return 解析完成的XConfTree
     */
    public static XConfTree read(String path, short type) {
        if (path == null)
            throw new XConfException("Path is Null!");
        if (path.equals(""))
            throw new XConfException("Path equals ' ', it's empty!");
        if(type==0)
            throw new XConfException("Type undefined!");
        if(!(type == CONF||type == PROPERTIES))
            throw new XConfException("Type unsupported, only input XConf.CONF or XConf.PROPERTIES!");
        return $read(path,type);
    }

    /**
     * 读取配置文件，自动类型
     * @param path 文件地址
     * @return XConfTree
     */
    public static XConfTree read(String path){
        if (path == null)
            throw new XConfException("Path is Null!");
        if (path.equals(""))
            throw new XConfException("Path equals ' ', it's empty!");

        return $read(path,CONF);

    }

    private static XConfTree $read(String path,short type){
        URL resource = XConf.class.getClassLoader().getResource(path);
        if (resource == null)
            throw new XConfException("Resource not find!");
        String filePath = resource.getPath().substring(1);
        if (xConfCore == null) {
            xConfCore = XConfCore.getInstance();
        }
        switch (type) {
            case CONF:

                break;
            case PROPERTIES:

                break;
            default:

                break;
        }
        return xConfCore.readByConf(filePath);
    }

}
