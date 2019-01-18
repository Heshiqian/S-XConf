package cn.heshiqian.framework.s.xconf.pojo;

import java.util.Arrays;

public class XConfTree {


    private XConfRoot[] roots;

    public XConfTree() {
    }

    public XConfRoot[] getRoots() {
        return roots;
    }

    public void setRoots(XConfRoot[] roots) {
        this.roots = roots;
    }

    @Override
    public String toString() {
        return "XConfTree{" +
                "roots=" + Arrays.toString(roots) +
                '}';
    }

    public XConfRoot getRootByName(String rootName){
        for(XConfRoot root:roots){
            if(root.getRootName().equals(rootName))
                return root;
        }
        throw new RuntimeException("没有:"+rootName+"这个根");
    }

}
