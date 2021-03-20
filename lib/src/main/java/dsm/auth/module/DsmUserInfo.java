package dsm.auth.module;

import java.lang.reflect.Method;

public class DsmUserInfo {

    public DsmUserInfo(String email, String name, String gcn) {
        this.email = email;
        this.name = name;
        this.gcn = gcn;
    }

    private String email;
    private String name;
    private String gcn;

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getGcn() {
        return this.gcn;
    }

    public Object toClass(Class<?> type) throws Exception {
        Object obj = type.getDeclaredConstructor().newInstance();

        if (!(obj instanceof DsmUserConvertable)) {
            throw new Exception("DsmUserConvertable is not implemented!!");
        }

        Method setEmail = type.getMethod("setEmail", String.class);
        Method setGcn = type.getMethod("setGcn", String.class);
        Method setName = type.getMethod("setName", String.class);

        setEmail.invoke(obj, email);
        setGcn.invoke(obj, gcn);
        setName.invoke(obj, name);

        return obj;
    }
}
