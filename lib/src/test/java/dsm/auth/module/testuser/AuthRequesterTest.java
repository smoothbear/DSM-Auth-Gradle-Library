package dsm.auth.module.testuser;

import dsm.auth.module.AuthRequester;
import dsm.auth.module.DsmUserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthRequesterTest {
    @Test
    public void requestTest() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkZW50aXR5Ijoiaml3b28iLCJjbGllbnRfaWQiOiJlMjA5NjE0MDNlMGI0MzAwOWM1ZGMwNzBhODI0NWUyZSIsInR5cGUiOiJhY2Nlc3MiLCJpYXQiOjE2MTYyNDg3MzYsImV4cCI6MTYxNjI1NTkzNiwiaXNzIjoiZHNtX2F1dGgifQ.sQbdZ4_Lba1HWiiDepswuanpBQc07G_xm4nwnBGDlZs";

        AuthRequester authRequesterImpl = new AuthRequester();
        DsmUserInfo userInfo = authRequesterImpl.getUserinfo(token);

        assertEquals(userInfo.getGcn(), "201216jjw@dsm.hs.kr");
    }
}
