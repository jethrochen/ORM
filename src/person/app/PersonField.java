/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youli
 */
public enum PersonField {
    ANY("任意匹配"),
    ID("工号"),
    NAME("姓名"),
    BIRTHDAY("出生年月"),
    DEPARTMENT("部门"),
    JOBTITLE("职称"),
    EDUBACKGROUND("学历");
    
    private String fieldName;
    PersonField(String fn){
        this.fieldName = fn;
    }

    public String getFieldName() {
        return fieldName;
    }
    static Map<Integer,String> fields;
    static String getFieldName(int index){
        switch(index){
            case 1:
                return "personId";
            case 2:
                return "name";
            case 3:
                return "birthday";
            case 4:
                return "depart";
            case 5:
                return "jobTitle";
            case 6:
                return "eduBackground";
        }
        return "";
    }
    final static Collection<String> getFieldNames(){
        if(fields==null){
            fields = new HashMap<Integer,String>();
            fields.put(0, ANY.getFieldName());
            fields.put(1,ID.getFieldName());
            fields.put(2,NAME.getFieldName());
            fields.put(3,BIRTHDAY.getFieldName());
            fields.put(4,DEPARTMENT.getFieldName());
            fields.put(5,JOBTITLE.getFieldName());
            fields.put(6,EDUBACKGROUND.getFieldName());
        }
        return fields.values();
    }
}
