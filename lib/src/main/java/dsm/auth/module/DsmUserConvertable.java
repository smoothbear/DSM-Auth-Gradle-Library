package dsm.auth.module;

public interface DsmUserConvertable {
    default void setName(String name) {}
    default void setGcn(String gcn) {}
    default void setEmail(String email) {}
}
