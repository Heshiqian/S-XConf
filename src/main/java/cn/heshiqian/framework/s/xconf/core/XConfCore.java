package cn.heshiqian.framework.s.xconf.core;

import cn.heshiqian.framework.s.xconf.exception.XConfException;
import cn.heshiqian.framework.s.xconf.pojo.XConfLeaf;
import cn.heshiqian.framework.s.xconf.pojo.XConfRoot;
import cn.heshiqian.framework.s.xconf.pojo.XConfTree;
import cn.heshiqian.framework.s.xconf.tool.Tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

public final class XConfCore{

    private static XConfCore xConfCore;
    private ArrayList<String> treeRoot=new ArrayList<>();

    private XConfCore(){
    }

    /**
     * 单例
     * @return XConfTree
     */
    public static XConfCore getInstance(){
        if(xConfCore==null){
            xConfCore=new XConfCore();
        }
        return xConfCore;
    }

    /**
     * 解析核心
     * @param confPath conf文件位置，通过CLassLoader的GetResource获得的
     * @return XConfTree
     */
    private XConfTree read(String confPath){
        String origin = Tool.readConfFile(confPath);
//        System.out.println(origin);
        XConfTree xConfTree = new XConfTree();
        //先分割出每个根标签的行号
        ArrayList<Integer> lineCount=new ArrayList<>();
        String[] originLine = origin.split("\r\n");
        for(int ln=0;ln<originLine.length;ln++){
            if(originLine[ln].indexOf("[")==0&&originLine[ln].lastIndexOf("]")==originLine[ln].length()-1){
                //根标签
                lineCount.add(ln+1);
            }
        }
        //对一个根进行提取
        for(int i=0;i<lineCount.size();i++){
            Integer f = lineCount.get(i);
            if(i+1>=lineCount.size()){
                //证明这是最后一个或者单个
                saveRootOrigin(Tool.mergeText(f-1,originLine.length,originLine));
                continue;
            }
            Integer s = lineCount.get(i + 1);
            saveRootOrigin(Tool.mergeText(f-1,s-1,originLine));
        }

        ArrayList<String[]> rootOver=new ArrayList<>();
        for(String s:treeRoot){
            String[] split = s.split("\r\n");
            for(String t:split){
                if(t.indexOf("#")==0||t.equals("")){
                    split=Tool.Sremove(split,t);
                }
            }
            rootOver.add(split);
        }

        ArrayList<XConfRoot> roots=new ArrayList<>();
        for(String[] s:rootOver){
            XConfRoot root = new XConfRoot(Arrays.toString(s));
            root.setRootName(s[0].substring(1,s[0].length()-1));
            XConfLeaf[] confLeaves=new XConfLeaf[]{};
            ArrayList<XConfLeaf> leaves=new ArrayList<>();
            for(int i=1;i<s.length;i++){
                String s1 = s[i].replace(";","");
                String[] split = s1.split("=");
                String key=split[0];
                String value=split[1];
                XConfLeaf xConfLeaf = new XConfLeaf(s[i], key, value);
                leaves.add(xConfLeaf);
            }
            root.setLeafs(leaves.toArray(confLeaves));
            roots.add(root);
        }
        XConfRoot[] xConfRoots=new XConfRoot[]{};
        xConfTree.setRoots(roots.toArray(xConfRoots));
        return xConfTree;
    }

    /**
     * 解析核心
     * @param pptiesPath Properties文件位置，通过CLassLoader的GetResource获得的
     * @return XConfTree
     */
    private XConfTree read_(String pptiesPath){
        Properties properties = new Properties();
        XConfTree xConfTree=new XConfTree();
        try {
            FileInputStream fis = new FileInputStream(new File(pptiesPath));
            properties.load(fis);
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()){
                String key = (String) keys.nextElement();
                String val = properties.getProperty(key);
                XConfLeaf xConfLeaf = new XConfLeaf(key + "=" + val, key, val);

            }




        } catch (Exception e) {
            throw new XConfException("Properties 解析失败！XConfTree将返回空值！");
        }
        return xConfTree;
    }

    public XConfTree readByConf(String path){
        return read(path);
    }

    public XConfTree readByProperties(String path){
        return read_(path);
    }

    private void saveRootOrigin(String root){
        treeRoot.add(root);
    }


}