/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import person.entity.Person;

/**
 *
 * @author Administrator
 */
public class PersonDAOTest {
    
    public PersonDAOTest() {
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
     * Test of getAllPerson method, of class PersonDAO.
     */
    @Test
    public void testGetAllPerson() {
        System.out.println("getAllPerson");
        PersonDAO instance = new PersonDAOImpl();
        List<Person> expResult = null;
        List<Person> result = instance.getAllPerson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPerson method, of class PersonDAO.
     */
    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        Person p = null;
        PersonDAO instance = new PersonDAOImpl();
        instance.addPerson(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPerson method, of class PersonDAO.
     */
    @Test
    public void testGetPerson() {
        System.out.println("getPerson");
        int personid = 0;
        PersonDAO instance = new PersonDAOImpl();
        Person expResult = null;
        Person result = instance.getPerson(personid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePerson method, of class PersonDAO.
     */
    @Test
    public void testRemovePerson() {
        System.out.println("removePerson");
        int personid = 0;
        PersonDAO instance = new PersonDAOImpl();
        instance.removePerson(personid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePerson method, of class PersonDAO.
     */
    @Test
    public void testUpdatePerson() {
        System.out.println("updatePerson");
        Person person = null;
        PersonDAO instance = new PersonDAOImpl();
        instance.updatePerson(person);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonNum method, of class PersonDAO.
     */
    @Test
    public void testGetPersonNum() {
        System.out.println("getPersonNum");
        PersonDAO instance = new PersonDAOImpl();
        int expResult = 0;
        int result = instance.getPersonNum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPagedPerson method, of class PersonDAO.
     */
    @Test
    public void testGetPagedPerson() {
        System.out.println("getPagedPerson");
        int pageId = 0;
        int onePageNum = 0;
        PersonDAO instance = new PersonDAOImpl();
        List<Person> expResult = null;
        List<Person> result = instance.getPagedPerson(pageId, onePageNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PersonDAOImpl implements PersonDAO {

        public List<Person> getAllPerson() {
            return null;
        }

        public void addPerson(Person p) {
        }

        public Person getPerson(int personid) {
            return null;
        }

        public void removePerson(int personid) {
        }

        public void updatePerson(Person person) {
        }

        public int getPersonNum() {
            return 0;
        }

        public List<Person> getPagedPerson(int pageId, int onePageNum) {
            return null;
        }
    }
    
}
