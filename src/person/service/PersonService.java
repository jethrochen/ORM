/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.service;

import java.util.List;
import org.contact.entity.Department;
import person.entity.Person;

/**
 *
 * @author Administrator
 */
public interface PersonService {
    /**
     * 通过id获取一个人，传入之前需验证id的有效性
     * @param personid 员工编号
     * @return 员工对象
     */
    public Person getPerson(int personid);
    public int addPerson(Person person);
    public int removePerson(int personid);
    public int updatePerson(Person person);
    public List<Person> searchPerson(String field, Object fieldValue);
    public Department getDepartment(String depname);
    /**
     * 用于分页显示时先获取总共员工个数，以便计算页数
     * @return 总员工个数
     */
    public int getPersonNum();
    /**
     * 获取所有员工集合
     * @return 所有员工集合
     */
    public List<Person> getAllPerson();
    /**
     * 用于分页显示时获取第pageId页的员工集合
     * @param pageId 第pageId页，从第0页开始计算
     * @param onePageNum 每一页要显示员工个数
     * @return 第pageId页的员工集合
     */
    public List<Person> getPagedPerson(int pageId, int onePageNum);
}
