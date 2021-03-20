package dsm.auth.module;

import dsm.auth.module.testuser.NotImplementedTestUser;
import dsm.auth.module.testuser.TestUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DsmUserInfoTest {
    @Test
    @DisplayName("Convert to class test")
    public void covertToClassTest() {
        DsmUserInfo dsmUserInfo = new DsmUserInfo("test@test.com", "tester", "9999");
        TestUser testUser = new TestUser();

        try {
            testUser = (TestUser) dsmUserInfo.toClass(TestUser.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals("test@test.com", testUser.getEmail());
        assertEquals("tester", testUser.getUserName());
        assertEquals("9999", testUser.getNumber());
    }

    @Test
    @DisplayName("Convert to class not implemented throw test")
    public void convertToClassTestNotImplementedException() {
        DsmUserInfo dsmUserInfo = new DsmUserInfo("test@test.com", "tester", "9999");

        assertThrows(Exception.class, () -> dsmUserInfo.toClass(NotImplementedTestUser.class));
    }
}
