/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.controller;

import hrs.HrsMain;
import hrs.controls.WarnDialog;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import person.app.ImportFileChooser;
import person.entity.Person;
import person.service.PersonService;
import person.service.PersonServiceImpl;

/**
 *
 * @author youli
 */
public class PersonInfoController extends AnchorPane implements Initializable {
    @FXML
    TextField personId;
    
    @FXML
    TextField birthday;
    
    @FXML
    TextField department;
    
    @FXML
    TextField personName;
    
    @FXML
    TextField eduBackground;
    
    @FXML
    TextField jobTitle;
    
    @FXML 
    private Label success;
    
    @FXML 
    private Button update;
    
    @FXML
    private Hyperlink backlink;
    
    private static Person currentPerson;
    private PersonService service = new PersonServiceImpl();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void setPerson(Person p){
        if(p!=null)
            currentPerson = p;
        if(currentPerson!=null){
            personId.setText(currentPerson.getPersonId().toString());
            department.setText(currentPerson.getDepart().toString());
            birthday.setText(new SimpleDateFormat("yyyy-MM-dd").format(currentPerson.getBirthday()));
            eduBackground.setText(currentPerson.getEduBackground());
            jobTitle.setText(currentPerson.getJobTitle());
            personName.setText(currentPerson.getName());
        }

    }
    private boolean isEmpty(TextField field){
        return field.getText().trim().length()==0;
    }
    private boolean updateToChanedPerson(){
        String hint = "";
        boolean hasProblem = false;
        String tname ="";
        if(isEmpty(personName)){
            hint+="姓名不能为空\t";
            hasProblem = true;
        }else{
            tname = personName.getText().trim();
        }
        int tid = 0;
        if(isEmpty(personId)){
            hint+="编号不能为空\t";
            hasProblem = true;
        }else{
            try{
                tid = Integer.parseInt(personId.getText().trim());
                if(tid<190000||tid>999999){
                   hint+="编号应为四位年份+两位数\t";
                   hasProblem = true; 
                }
            }catch(NumberFormatException e){
                hint+="编号格式错误\t";
                hasProblem = true;
            }
        }
        Date tbirthday = null;
        if(isEmpty(birthday)){
            hint+="出生年月不能为空\t";
            hasProblem = true;
        }else{
            try {
               tbirthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday.getText().trim());
            } catch (ParseException ex) {
                hint +="日期格式应为yyyy-mm-dd";
                hasProblem = true;
            }
        }
        String tdepart ="";
        if(isEmpty(personName)){
            hint+="部门不能为空\t";
            hasProblem = true;
        }else{
            tdepart = personName.getText().trim();
        }
        String tjobtitle ="";
        if(isEmpty(jobTitle)){
            hint+="职称不能为空\t";
            hasProblem = true;
        }else{
            tjobtitle = jobTitle.getText().trim();
        }
        String teduback ="";
        if(isEmpty(eduBackground)){
            hint+="学历不能为空\t";
            hasProblem = true;
        }else{
            teduback = eduBackground.getText().trim();
        }
        if(hasProblem){
            success.setText(hint);
            //success.sett
        }else{
            currentPerson.setBirthday(tbirthday);
            currentPerson.setDepart(service.getDepartment(tdepart));
            currentPerson.setEduBackground(teduback);
            currentPerson.setJobTitle(tjobtitle);
            currentPerson.setName(tname);
            currentPerson.setPersonId(tid);
        }
        System.out.println(currentPerson
        );
        return hasProblem;
    }
    public void processUpdate(ActionEvent event){
        if(!updateToChanedPerson()){
            if(service.updatePerson(currentPerson)==0)
                success.setText("更新成功！");
            else 
                success.setText("更新失败！！！");
        }
    }
    public void backToPersonList(ActionEvent event){
        
    }
    public void deletePerson(ActionEvent event){
        service.removePerson(currentPerson.getPersonId());
    }
    
    public void processImport(ActionEvent event){
        ImportFileChooser dialog = new ImportFileChooser(HrsMain.getHrsMain().getStage(),"导入Excel文件", "选择输入元数据文件",null,null);
        HrsMain.getHrsMain().showModalMessage(dialog);
    }
}
