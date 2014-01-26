/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrs;

import hrs.controls.WindowButtons;
import hrs.controls.WindowResizeButton;
import hrs.Pages;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import login.app.ProfilePage;
import login.entity.User;
import login.security.Authenticator;

/**
 *
 * @author Administrator
 */
public class HrsMain extends Application {

    private User loggedUser;
    public static HrsMain hrsMain;
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private ToolBar toolBar;
    private SplitPane splitPane;
    private Pane pageArea;
    private WindowResizeButton windowResizeButton;
    private StackPane modalDimmer;
    private double mouseDragOffsetX = 0;
    private double mouseDragOffsetY = 0;
    private ToolBar pageToolBar;
    private Pages pages;
    //当前页面对象
    private Page currentPage;
    //标识是否正在切换主页面
    private boolean changingPage = false;
    //当前主页面的视图
    private Node currentPageView;
    //当前页面层次结构路径
    private String currentPagePath;
    public static HrsMain getHrsMain() {
        return hrsMain;
    }
    public User getLoggedUser() {
        return loggedUser;
    }
    public  boolean userLogging(String userId, String password){
        System.out.println("got user id " + userId + " password " + password);
        if (Authenticator.validate(userId, password)) {
            System.out.println("OK");
            loggedUser = User.of(userId);
            goToPage(Pages.USERPROFILE);
            //((ProfilePage)Pages.getPage(Pages.USERPROFILE)).
            return true;
        } else {
            System.out.println("NOT OK");
            return false;
        }
    }
    public void userLogout(){
        loggedUser = null;
        goToPage(Pages.LOGIN);
    }
    @Override
    public void start(final Stage stage) {
        hrsMain = this;
        stage.setTitle("HRSSystem");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                @Override
                public void handle(WindowEvent e){
                    System.exit(0);
                    Platform.exit();
                }
            });
        
        StackPane layerPane = new StackPane();
        stage.initStyle(StageStyle.UNDECORATED);
        //creagte window resize button
        windowResizeButton = new WindowResizeButton(stage, 1020, 700);
        root = new BorderPane() {
            @Override
            protected void layoutChildren() {
                super.layoutChildren();
                windowResizeButton.autosize();
                windowResizeButton.setLayoutX(getWidth() - windowResizeButton.getLayoutBounds().getWidth());
                windowResizeButton.setLayoutY(getHeight() - windowResizeButton.getLayoutBounds().getHeight());
            }
        };
        root.getStyleClass().add("application");
        root.setId("root");
        layerPane.setDepthTest(DepthTest.DISABLE);
        layerPane.getChildren().add(root);
        boolean is3dSupperted = Platform.isSupported(ConditionalFeature.SCENE3D);
        scene = new Scene(layerPane, 1020, 700, is3dSupperted);
        if (is3dSupperted) {
            scene.setCamera(new PerspectiveCamera());
        }
        scene.getStylesheets().add(HrsMain.class.getResource("hrsmain.css").toExternalForm());
        modalDimmer = new StackPane();
        modalDimmer.setId("ModalDimmer");
        modalDimmer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                t.consume();
                hideModalMessage();
            }
        });
        modalDimmer.setVisible(false);
        layerPane.getChildren().add(modalDimmer);

        //create main toolbar
        toolBar = new ToolBar();
        toolBar.setId("mainToolBar");
        ImageView logo = new ImageView(new Image(HrsMain.class.getResourceAsStream("images/logo.png")));
        HBox.setMargin(logo, new Insets(0, 0, 0, 5));
        toolBar.getItems().add(logo);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        toolBar.getItems().add(spacer);
        Button highlightsButton = new Button();
        highlightsButton.setId("highlightsButton");
        highlightsButton.setMinSize(120, 66);
        highlightsButton.setPrefSize(120, 66);
        highlightsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("goto personinfo page");
                goToPage(Pages.PERSONINFO);
            }
        });
        toolBar.getItems().add(highlightsButton);
        Button newButton = new Button();
        newButton.setId("newButton");
        newButton.setMinSize(120, 66);
        newButton.setPrefSize(120, 66);
        newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goToPage(Pages.PERSONLIST);
            }
        });
        toolBar.getItems().add(newButton);
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        toolBar.getItems().add(spacer2);
        ImageView searchTest = new ImageView(new Image(HrsMain.class.getResourceAsStream("images/search-text.png")));
        toolBar.getItems().add(searchTest);
        //searchBox没有添加
        toolBar.setPrefHeight(66);
        toolBar.setMinHeight(66);
        toolBar.setMaxHeight(66);
        GridPane.setConstraints(toolBar, 0, 0);
        final WindowButtons windowButtons = new WindowButtons(stage);
        toolBar.getItems().add(windowButtons);
        //双击工具栏 最大化窗口
        toolBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    windowButtons.toogleMaximized();
                }
            }
        });
        toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseDragOffsetX = event.getSceneX();
                mouseDragOffsetY = event.getSceneY();
            }
        });
        toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!windowButtons.isMaximized()) {
                    stage.setX(event.getScreenX() - mouseDragOffsetX);
                    stage.setY(event.getScreenY() - mouseDragOffsetY);
                }
            }
        });
        //新建page tree toolBar
        //左上角第二行all，sample，document三个菜单按钮
        ToolBar pageTreeToolBar = new ToolBar() {
            @Override
            public void requestLayout() {
                {
                    super.requestLayout();
                    //让pageToolBar和pageTreeToolBar高度保持一致
                    if (pageToolBar != null && getHeight() != pageToolBar.prefHeight(-1)) {
                        pageToolBar.setPrefHeight(getHeight());
                    }
                }
            }
        };
        pageTreeToolBar.setId("page-tree-toolbar");
        pageTreeToolBar.setMinHeight(29);
        pageTreeToolBar.setMaxWidth(Double.MAX_VALUE);

        ToggleGroup pageButtonGroup = new ToggleGroup();
        final ToggleButton allButton = new ToggleButton("全部");
        allButton.setToggleGroup(pageButtonGroup);
        allButton.setSelected(true);
        final ToggleButton samplesButton = new ToggleButton("样例");
        samplesButton.setToggleGroup(pageButtonGroup);
        final ToggleButton docsButton = new ToggleButton("文档");
        docsButton.setToggleGroup(pageButtonGroup);
        
        InvalidationListener treeButtonNotifyListener = new InvalidationListener(){
            public void invalidated(Observable ov){
               /** if(allButton.isSelected()){
                    //pageTree.setRoot(pages.getRoot());
                }*/
            }
        };
        allButton.selectedProperty().addListener(treeButtonNotifyListener);
        samplesButton.selectedProperty().addListener(treeButtonNotifyListener);
        docsButton.selectedProperty().addListener(treeButtonNotifyListener);
        pageTreeToolBar.getItems().addAll(allButton,samplesButton,docsButton);
        //创建页面树
        pages = new Pages();
        //代理对话框未使用
        BorderPane leftSplitPane = new BorderPane();
        leftSplitPane.setTop(pageTreeToolBar);
        
        
        //右上角第二行页面前进、后退、刷新，以及浏览页面栈
        pageToolBar = new ToolBar();
        pageToolBar.setId("page-toolbar");
        pageToolBar.setMinHeight(29);
        pageToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);
        //后退按钮
        Button backButton = new Button();
        backButton.setGraphic(new ImageView(new Image(HrsMain.class.getResourceAsStream("images/back.png"))));
        backButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                //back();
            }
        });
        backButton.setMaxHeight(Double.MAX_VALUE);
        //前进按钮
        Button forwardButton = new Button();
        forwardButton.setGraphic(new ImageView(new Image(HrsMain.class.getResourceAsStream("images/forward.png"))));
        forwardButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                //forward();
            }
        });
        forwardButton.setMaxHeight(Double.MAX_VALUE);
        //刷新按钮
        Button reloadButton = new Button();
        reloadButton.setGraphic(new ImageView(new Image(HrsMain.class.getResourceAsStream("images/reload.png"))));
        reloadButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // reload();
            }
        });
        reloadButton.setMaxHeight(Double.MAX_VALUE);
        //将上面三个按钮添加到页面工具条
        pageToolBar.getItems().addAll(backButton,forwardButton,reloadButton);
        
        //页面层次结构条
        //页面层次结构条暂时未添加
        
        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        Button settingsButton = new Button();
        settingsButton.setId("SettingsButton");
        settingsButton.setGraphic(new ImageView(new Image(HrsMain.class.getResourceAsStream("images/settings.png"))));
        settingsButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                //showSettingDialog();
            }
        });
        settingsButton.setMaxHeight(Double.MAX_VALUE);
        pageToolBar.getItems().addAll(spacer3, settingsButton);
        //主页面面板
        pageArea = new Pane(){
            @Override protected void layoutChildren(){
                for(Node child:pageArea.getChildren()){
                    child.resizeRelocate(0, 0, pageArea.getWidth(), pageArea.getHeight());
                }
            }
        };
        pageArea.setId("page-area");
        //创建右半个面板
        BorderPane rightSplitPane = new BorderPane();
        rightSplitPane.setTop(pageToolBar);
        rightSplitPane.setCenter(pageArea);
        //下面主面板
        splitPane = new SplitPane();
        splitPane.setId("page-splitpane");
        splitPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setConstraints(splitPane, 0, 1);
        splitPane.getItems().addAll(leftSplitPane,rightSplitPane);
        splitPane.setDividerPosition(0, 0.1);
        
        
        this.root.setTop(toolBar);
        this.root.setCenter(splitPane);
        windowResizeButton.setManaged(false);
        this.root.getChildren().add(windowResizeButton);
        
        goToPage(Pages.LOGIN);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Hide any modal message that is shown
     */
    public void hideModalMessage() {
        modalDimmer.setCache(true);
        TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent t) {
                                modalDimmer.setCache(false);
                                modalDimmer.setVisible(false);
                                modalDimmer.getChildren().clear();
                            }
                        },
                        new KeyValue(modalDimmer.opacityProperty(), 0, Interpolator.EASE_BOTH)
                )).build().play();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 将左下角当前页面设置到此path，前一个path添加到历史记录中
     * @param pagePath 要显示的页面路径
     */
    public void goToPage(String pagePath) {
        goToPage(pages.getPage(pagePath));
    }

    /**
     * 将左下角当前页面设置到此path，前一个path添加到历史记录中
     * 
     * @param pagePath  要显示的页面路径
     * @param force     强制刷新页面，即使当前页面就是所需页面
     */
    public void goToPage(String pagePath, boolean force) {
        goToPage(pages.getPage(pagePath),true,force,true);
    }

    /**
     * 将左下角当前页面设置到此path，前一个path添加到历史记录中
     * 
     * @param page 要显示的页面
     */
    public void goToPage(Page page) {
        goToPage(page, true, false, true);
    }
    /**
     * 将左下角当前页面设置到此path，前一个path添加到历史记录中
     * @param page 要显示的页面
     * @param addHistory 是否要将当前页面添加到历史记录中
     * @param force 是否强制刷新当前页面
     * @param swapViews 页面是否要交换到新页面
     */
    public void goToPage(Page page, boolean addHistory, boolean force, boolean swapViews){
        if(page==null) return;
        if(!force && page == currentPage){
            return;
        }
        changingPage = true;
        if(swapViews){
            Node view = page.createView();
            if(view == null) view = new Region();
            //如果view是新的，则replace
            if(force || view != currentPageView){
                pageArea.getChildren().setAll(view);
                currentPageView = view;
            }
        }
        //页面历史记录
        if(addHistory && currentPage!=null){
            //history.push(currentPage);
            //forwardHistory.clear();
        }
        currentPage = page;
        currentPagePath = page.getPath();
       
        Page p = page;
        while(p!=null){
            p.setExpanded(true);
            p = (Page)p.getParent();
        }
        //
        //pageTree.getSelectionModel().select(page);
        //breadcrumBar.setPath(currentPagePath);
        //
        //页面元素初始化以后可能需要一些获取默认数据等工作
        page.afterInitiate();
        changingPage = false;
    }
}
