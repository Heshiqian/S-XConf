package cn.heshiqian.framework.s.xconf.pojo;

import java.util.Arrays;

public class XConfRoot {

    private String rootOriginString;

    private String rootName;

    private XConfLeaf[] leafs;


    public XConfRoot(String rootOriginString) {
        this.rootOriginString = rootOriginString;
    }

    @Override
    public String toString() {
        return "XConfRoot{" +
                "rootOriginString='" + rootOriginString + '\'' +
                ", rootName='" + rootName + '\'' +
                ", leafs=" + Arrays.toString(leafs) +
                '}';
    }

    public XConfLeaf getLeafByName(String leafKey){

        for(XConfLeaf xConfLeaf:leafs){
            if(xConfLeaf.getKey().equals(leafKey))
                return xConfLeaf;
        }
        throw new RuntimeException("In root : '"+rootName+"', NOT exist this leaf by key : "+leafKey);
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public void setLeafs(XConfLeaf[] leafs) {
        this.leafs = leafs;
    }

    public String getRootName() {
        return rootName;
    }
}
