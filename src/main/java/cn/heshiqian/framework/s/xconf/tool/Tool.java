package cn.heshiqian.framework.s.xconf.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Tool {

    public static String readConfFile(String path){
        StringBuilder stringBuilder=new StringBuilder();
        File file = new File(path);
        if(!file.exists())
            throw new RuntimeException(new FileNotFoundException("文件"+path+"未找到"));
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String temp="";
            while ((temp=bufferedReader.readLine())!=null){
                stringBuilder.append(temp+"\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String mergeText(int beginLine,int endLine,String[] texts){
        String end="";
        for(int tempLine=beginLine;;){
            if(tempLine==endLine)
                break;
            end+=texts[tempLine++]+"\r\n";
        }
        return end;
    }

    public static String[] Sremove(String[] collection,String key){
        ArrayList<String> strings = new ArrayList<>();
        strings.addAll(Arrays.asList(collection));
        for(int i=0;i<strings.size();i++){
            if(strings.get(i).equals(key)){
                strings.remove(i);
            }
        }
        String[] strings1=new String[]{};
        return strings.toArray(strings1);
    }

}
