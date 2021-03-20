package dsm.auth.module.testuser;

import dsm.auth.module.DsmUserConvertable;

public class TestUser implements DsmUserConvertable {
    private String userName;
    private String email;
    private String number;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public void setName(String name) {
        userName = name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setGcn(String gcn) {
        number = gcn;
    }
}
