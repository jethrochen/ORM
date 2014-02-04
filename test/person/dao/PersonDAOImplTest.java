/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.dao;

import hibernate.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.contact.entity.Department;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;
import person.entity.Person;

/**
 *
 * @author Administrator
 */
public class PersonDAOImplTest {
    
    public PersonDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllPerson method, of class PersonDAOImpl.
     */
    @Test
    @Ignore
    public void testGetAllPerson() {
        System.out.println("getAllPerson");
        PersonDAOImpl instance = new PersonDAOImpl();
        List<Person> expResult = null;
        List<Person> result = instance.getAllPerson();
       // assertEquals(expResult, result);
    }

    /**
     * Test of addPerson method, of class PersonDAOImpl.
     */
    @Test
    @Ignore
    public void testAddPerson() {
        System.out.println("addPerson");
        
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        Department dp = (Department) s.byId(Department.class).getReference(1);
        
        s.getTransaction().commit();
        s.close();
       // System.out.println(dp.getName());
        Person p = new Person(201411,"测试",new Date(), dp,"攻城狮","硕士");
        PersonDAOImpl instance = new PersonDAOImpl();
        instance.addPerson(p);
    }

    /**
     * Test of getPerson method, of class PersonDAOImpl.
     */
    @Test
    public void testGetPerson() {
        PersonDAOImpl instance = new PersonDAOImpl();
        Person p = instance.getPerson(201410);
        System.out.println(p);
        assertEquals((int)p.getPersonId(), 201410);
    }

    /**
     * Test of removePerson method, of class PersonDAOImpl.
     */
    @Test
    public void testRemovePerson() {
    }

    /**
     * Test of updatePerson method, of class PersonDAOImpl.
     */
    @Test
    @Ignore
    public void testUpdatePerson() {
        System.out.println("updatePerson");
        Person person = null;
        PersonDAOImpl instance = new PersonDAOImpl();
        instance.updatePerson(person);
    }

    /**
     * Test of getPersonNum method, of class PersonDAOImpl.
     */
    @Test
    @Ignore
    public void testGetPersonNum() {
        System.out.println("getPersonNum");
        PersonDAOImpl instance = new PersonDAOImpl();
        int expResult = 0;
        int result = instance.getPersonNum();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPagedPerson method, of class PersonDAOImpl.
     */
    @Test
    public void testGetPagedPerson() {
        System.out.println("getPagedPerson");
        int pageId = 0;
        int onePageNum = 0;
        PersonDAOImpl instance = new PersonDAOImpl();
        List<Person> expResult = null;
        List<Person> result = instance.getPagedPerson(pageId, onePageNum);
       // assertEquals(expResult, result);
    }
    
}
