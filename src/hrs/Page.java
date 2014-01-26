/*
 * 一个可视的独立页面
 */

package hrs;

import hrs.HrsMain;
import java.io.IOException;
import java.io.InputStream;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

/**
 *
 * @author Administrator
 */
public abstract class Page extends TreeItem<String>{
    protected static Initializable controller;
    protected Page(String name){
        super(name);
    }
    /**
     * 页面元素初始化以后可能需要一些获取默认数据等工作
     */
    public void afterInitiate(){
        
    }
    public void setName(String name){
        setValue(name);
    }
     public String getName() {
        return getValue();
    }
    @Override
    public boolean equals(Object page){
        if(page instanceof Page){
            String path = ((Page)page).getPath();
            if(path!=null && path.equals(getPath())){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return getPath().hashCode();
    }

    public Initializable getController() {
        return controller;
    }
    
    public Pane getFXMLPane(Class rootClass, String fxml) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        InputStream in = rootClass.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(rootClass.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
            controller = loader.getController();
        } finally {
            in.close();
        }
        return page;
    }
    //按树的层次结构“r1/r2/r3/.../rn”
    public String getPath() {
        if (getParent() == null) {
            return getName();
        } else {
            String parentsPath = ((Page)getParent()).getPath();
            if (parentsPath.equalsIgnoreCase("All")) {
                return getName();
            } else {
                return  parentsPath + "/" + getName();
            }
        }
    }
    //各个子类构建自己的视图组件
    public abstract Node createView();

    /**用 “r1/r2/r3/.../rn”这样的路径来获取一个子节点*/
    public Page getChild(String path){
        int firstIndex = path.indexOf('/');
        String childName = (firstIndex==-1?path:path.substring(0, firstIndex));
        String anchor = null;
        //每个子节点还有一层子元素用#分割
        if(childName.indexOf('#')!=-1){
            String[] parts = childName.split("#");
            childName = parts[0];
            anchor = (parts.length==2)?parts[1]:null;
        }
        for(TreeItem child : getChildren()){
            Page page= (Page)child;
            if(page.getName().equals(childName)){
                if(firstIndex==-1){
                  //  if(page instanceof DocPage){
                        //((DocPage)page).setAnchor(anchor);
                  //  }
                    return page;
                }else{
                    return page.getChild(path.substring(firstIndex+1));
                }
            }
        }
        return null;
    }
    @Override public String toString(){
        return toString("/");
    }
    private String toString(String indent){
        String out = indent + "["+getName()+"] "+getClass().getSimpleName();
        ObservableList<TreeItem<String>> childModel = getChildren();
        if (childModel!=null) {
            for (TreeItem child:childModel) {
                out += "\n"+((Page)child).toString("    "+indent);
            }
        }
        return out;
    }


    public static class GoToPageEventHandler implements EventHandler{
        private String pagePath;
        public GoToPageEventHandler(String pagePath){
            this.pagePath = pagePath;
        }
        public void handle(Event event){
           HrsMain.getHrsMain().goToPage(pagePath);
        }
    }
}
