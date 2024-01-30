package com.apparel.myapplication;

public class ConnectionVariables {
    public static String global_ip ="41.139.141.246";
    public static String local_ip ="192.168.1.250";
    public static String test_ip ="192.168.1.22";

    public static String portNo =":4444";

    public static String db = "ATCHRM";
    public static String u_name = "sa";
    public static String pass = "admin_123";
    public static String test_pass="admin@123";

    public String getGlobal_ip()
    {
        return global_ip;
    }
    public String getLocal_ip()
    {
        return local_ip;
    }
    public String getTest_ip()
    {
        return test_ip;
    }
    public String getDb()
    {
        return db;
    }
    public String getU_name()
    {
        return u_name;
    }
    public  String getPass()
    {
        return pass;
    }
    public String getPortNo()
    {
        return portNo;
    }

    public String getTest_pass()
    {
        return test_pass;
    }
}
