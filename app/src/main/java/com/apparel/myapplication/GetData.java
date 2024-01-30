package com.apparel.myapplication;

import android.util.Log;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

public class GetData {
    // Declaring Server ip, username, database name and password


    ConnectionVariables con_variables=new ConnectionVariables();

   String ip= con_variables.getGlobal_ip(),
    db = con_variables.getDb(),
    u_name = con_variables.getU_name(),
    pass =con_variables.getPass();
    // Declaring Server ip, username, database name and password


    Connection con;

    ResultSet rs;

    Statement st;
    //Array List for spinner data
    ArrayList<String> location_list = new ArrayList();
    List<Map<String,String>> Loclist = null   ;
    final ArrayList<String> user_names_list = new ArrayList();
    ArrayList<String> l1,l2;



    DBConnection connect = new DBConnection();


    public ResultSet getLocation()
    {

        // Declaring Server ip, username, database name and password
        // Declaring Server ip, username, database name and password
        con= connect.connectionclass();
        String query = "select distinct LocationID ,Location from vw_EmployeeMaster em where em.Status = 'A'";
        try {

            st = con.createStatement();
            Statement st3 = con.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException se) {
            Log.e("SQL Error : ", se.getMessage());
        }
       return rs;
    }



    public ResultSet getEmployeeDetails(int empid)
    {
        // Declaring Server ip, username, database name and password
        // Declaring Server ip, username, database name and password
        con= connect.connectionclass();
        String query = "select * from vw_EmployeeMaster em  " +
                "where em.EmpID=" + empid;

        try {

            st = con.createStatement();
            Statement st3 = con.createStatement();
            rs = st.executeQuery(query);
        }
        catch (SQLException se)
        {
            Log.e("SQL Error : ", se.getMessage());
        }
        return rs;
    }


    public ResultSet getContractLocation(int empid)
    {
        // Declaring Server ip, username, database name and password
        // Declaring Server ip, username, database name and password
        con= connect.connectionclass();
        String query = "select distinct em.LocationID ," +
                "em.Location,CONVERT(varchar,em.LocationID) +'_' +em.Location LocationName " +
                "from UsersMaster_tbl u inner join vw_EmployeeMaster em on em.LocationID = u.LocationID " +
                "where u.Empid='" + empid + "' and em.Status = 'A'";

        try {

            st = con.createStatement();
            Statement st3 = con.createStatement();
            rs = st.executeQuery(query);
        }
        catch (SQLException se)
        {
            Log.e("SQL Error : ", se.getMessage());
        }
        return rs;
    }


    public ResultSet getLocationID(int id){
        // Declaring Server ip, username, database name and password
        // Declaring Server ip, username, database name and password
        con= connect.connectionclass();
        String query = "select u.Username,u.Id,e.Location,u.LocationID,e.EmpID,u.UserType, u.Username,e.FirstName " +
                "from UsersMaster_tbl u inner join vw_EmployeeMaster e on e.EmpID=u.Empid where u.LocationID = '"+id+"' " +
                "and e.Status = 'A' order by u.Username";
        try {

            st = con.createStatement();
            Statement st3 = con.createStatement();
            rs = st.executeQuery(query);
        }
        catch (SQLException se)
        {
            Log.e("SQL Error : ", se.getMessage());
        }

        return rs;
    }


    public ResultSet getLoginCredentials(int emp_id, String password, int LocationID)
    {
        con= connect.connectionclass();
        String query = "select u.Username,u.Id,e.Location,u.LocationID,e.EmpID,u.Username,e.FirstName ,u.UserType,Convert(varchar,u.PasswordUpdateDate,103) PasswordUpdateDate,u.Password \" +\n" +
                "\" from UsersMaster_tbl u inner join vw_EmployeeMaster e on e.EmpID = u.Empid where e.Status = 'A' and e.EmpID = '" + emp_id + "' and u.Password = '" + password + "' and u.LocationID =" + LocationID + "";

        try {
            st = con.createStatement();
            Statement st3 = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException se) {
            Log.e("error here 1 : ", se.getMessage());
        }
        return rs;
    }


    public int insertLogData(String v_number, String status, int gateID, int locationID, int d_id, String remarks, int odometer_value){

        con= connect.connectionclass();

        String query = "insert into VehicleLog_tbl(VehicleNum,InOutStatus,GateID,LocationID,DriverID,Remarks,Odometer) values(?,?,?,?,?,?,?)";
         int action = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, v_number);
            stmt.setString(2, status);
            stmt.setInt(3, gateID);
            stmt.setInt(4,  locationID);
            stmt.setInt(5, d_id);
            stmt.setString(6, remarks);
            stmt.setInt(7, odometer_value);

//
            action = stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return action;
    }



    public int insertDeviceData(int emp_id,int loc_id,int dep_id, String status, int dev_ID, int userID, String remarks, int designID){

        con= connect.connectionclass();

        String query = "insert into AssetInventory(LocationID,DeptPK,EmpID,DesgPK,ItemID,UserPK,AssetStatus,Remarks) values(?,?,?,?,?,?,?,?)";

        int action = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, loc_id);
            stmt.setInt(2, dep_id);
            stmt.setInt(3, emp_id);
            stmt.setInt(4,  designID);
            stmt.setInt(5, dev_ID);
            stmt.setInt(6, userID);
            stmt.setString(7, status);
            stmt.setString(8, remarks);

//
            action = stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return action;
    }

    public int insertGatepassData(int designationPK, int userPK, int empID, int locationID, Date date, String reasons){

        con= connect.connectionclass();

        String query = "insert into GatePassMaster_tbl(DesgnationPK,userPK,empid,BranchLctnPK,GatepassDate,fromtime,GatePassDescription,GatePassnum) values(?,?,?,?,?,?,?,?)";
        int action = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, designationPK);
            stmt.setInt(2, userPK);
            stmt.setInt(3, empID);
            stmt.setInt(4,  locationID);
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stmt.setString(7, reasons);
           // stmt.setString(8, "GP-"+"GatePassId");

//
            action = stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return action;
    }

    public ResultSet getVehiclenumbers(){

        con= connect.connectionclass();
        String query = "select RegistrationNo " +
                     "From VehicleMaster_tbl\n" +
                     "where VehicleOwner = 'H'\n" +
                     "and IsActive = 1";

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException se) {
            Log.e("Error occured : ", se.getMessage());
        }

        return rs;
    }



    public ResultSet getGateData(int locationID){

        con= connect.connectionclass();

        String query = "select * from GateMaster_tbl where LocationID="+locationID;

        try {

            st = con.createStatement();
            Statement st3 = con.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException se) {
            Log.e("Error occured : ", se.getMessage());
        }

        return rs;
    }

    public ResultSet getEmpID(int depPk)
    {
    String query = "select distinct e.EmpID, e.Name, e.DesignationPk, e.LocationID from vw_EmployeeMaster e where e.DepartmeentPk ="+depPk;

        Connection con = connect.connectionclass();

        try {

            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch (Exception e)
        {
            Log.e("Error caught:", e.getMessage());
        }
        return rs;
    }
    public ResultSet getDepartments()
    {
        String query = "select distinct e.departmentName, e.DepartmeentPk from vw_EmployeeMaster e";

        Connection con = connect.connectionclass();

        try {

            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch (Exception e)
        {
            Log.e("Error caught:", e.getMessage());
        }
        return rs;

    }


//User authentication starts here
    public LinkedHashMap<String, List<String>> GetMenus(int empID) {

        ArrayList<MenusInfo> menus = new ArrayList<MenusInfo>();
        List<String> sub_menu_list = new ArrayList<String>();
        List<String> MenuData = new ArrayList<String>();
        LinkedHashMap<String, List<String>> listLinkedHashMap
                = new LinkedHashMap<String, List<String>>();


        Connection con = connect.connectionclass();
        String query = "select distinct " +
                "MM.MnuID," +
                "MM.MenuText," +
                "Md.MnuDetName," +
                "MSD.MnuSubDetName " +
                "from UserPermission_tbl UP\n" +
                "inner Join MenuMaster MM " +
                "on UP.menuid = MM.MnuID\n" +
                "left Join MenuDetails MD " +
                "on UP.menusubid = MD.MnuDetID " +
                "and md.IsAndroid = 1\n" +
                "left Join MenuSubDetails MSD " +
                "on UP.submenudetailid = MSD.MnuSubDetID and msd.IsAndroid = 1\n" +
                "where UP.empid= "+empID+
                "and UP.status='Y' and mm.IsAndroid = 1";

        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                MenusInfo info = new MenusInfo();
                info.MenuName = rs.getString("MnuDetName");
                info.Submenu = rs.getString("MenuText");
                menus.add(info);

                if (!sub_menu_list.contains(rs.getString("MenuText")))
                    sub_menu_list.add(rs.getString("MenuText"));
            }


            for (int i = 0; i < sub_menu_list.size(); i++) {
                MenuData = new ArrayList<String>();

                for (MenusInfo m : menus) {

                    if (m.Submenu.equals(sub_menu_list.get(i)))
                        if(!MenuData.contains(m.MenuName))
                           MenuData.add(m.MenuName);
                }
                listLinkedHashMap.put(sub_menu_list.get(i).toString(), MenuData);
            }

            return listLinkedHashMap;
        } catch (Exception ex)
        {
            return null;
        }
    }


    //e method yino ebhukula chisubmenus

    public void upDate(int empid)
    {




        Connection con = connect.connectionclass();
        String query = "Update loginTempIPDetail " +
                "set Status = 'LOGOUT', " +
                "logouttime = getdate()" +
                "where empid = "+empid+
                "and status = 'LOGIN'";


        try {

            Statement stmt = con.createStatement();
            stmt.executeQuery(query);



        } catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
        }
    }
    public ResultSet getSub_Submenus(String parent, String child, int empid)
    {

        ResultSet rss;

        Connection con = connect.connectionclass();


        String query = "select distinct \n" +
                "                MM.MnuID,\n" +
                "                MM.MenuText,\n" +
                "                Md.MnuDetName,\n" +
                "                MSD.MnuSubDetName, \n" +
                "                MSD.MnuSubDetID \n"+
                "                from UserPermission_tbl UP\n" +
                "                inner Join MenuMaster MM \n" +
                "                on UP.menuid = MM.MnuID\n" +
                "                left Join MenuDetails MD \n" +
                "                on UP.menusubid = MD.MnuDetID \n" +
                "                and md.IsAndroid = 1\n" +
                "                left Join MenuSubDetails MSD \n" +
                "                on UP.submenudetailid = MSD.MnuSubDetID " +
                "                and msd.IsAndroid = 1\n" +
                "                where UP.empid= "+empid+
                "                and MM.MenuText = '" +parent+
                "               ' and Md.MnuDetName = '"+child+"'"+
                "                and UP.status='Y' " +
                "               and mm.IsAndroid = 1 "+
                "               ORDER BY MSD.MnuSubDetID asc";



        try {

            Statement stmt = con.createStatement();
           rss = stmt.executeQuery(query);



        } catch (Exception ex)
        {
            return null;
        }
        return rss;
    }



    //Bharumikhi bhali mu userlock
    public ResultSet getUsers()
    {
        ResultSet rset;
        Connection con = connect.connectionclass();


        String query = "select distinct a.empid, name\n" +
                "FROM loginTempIPDetail a, vw_EmployeeMaster b\n" +
                "where a.empid = b.empid\n" +
                "and a.status = 'LOGIN'" +
                "order by name asc";


        try {

            Statement stmt = con.createStatement();
            rset = stmt.executeQuery(query);



        } catch (Exception ex)
        {
            return null;
        }
        return rset;

    }


    //GM APPROVALS
    public ResultSet hrApprovals(String usertype, int locid)
    {

        ResultSet rsss;
        Connection con = connect.connectionclass();
        String queryy;

                if (usertype.equals("A") || locid == 18)
                {
                    queryy = "Select RecruitmentAppPk AppPk,Applicationnum AppNo,BranchLocationName," +
                            "DesignationName,Contracttype Type, Vaccancynum Vacancies,CONVERT(varchar,JoiningDate,103) ExpectedJoiningDate " +
                            "from RecruitmentApplicationMaster_tbl R " +
                            "inner join BranchLocartionMaster_tbl B " +
                            "on b.BranchlLocationPk=R.BranchLocationPK " +
                            "Inner join DesignationMaster_tbl D " +
                            "on D.DesgnationPK= R.DesignationPk " +
                            "where iscompleted='W' ";
                }
                else
                {
                    queryy = "Select RecruitmentAppPk AppPk,Applicationnum AppNo,BranchLocationName," +
                            "DesignationName,Contracttype Type,Vaccancynum Vacancies,CONVERT(varchar,JoiningDate,103) " +
                            "ExpectedJoiningDate " +
                            "from RecruitmentApplicationMaster_tbl R " +
                            "inner join BranchLocartionMaster_tbl B " +
                            "on b.BranchlLocationPk=R.BranchLocationPK " +
                            "Inner join DesignationMaster_tbl D on D.DesgnationPK= R.DesignationPk " +
                            "where iscompleted='W' and BranchlLocationPk=" + locid;
                }


        try {

            Statement stmt = con.createStatement();
            rsss = stmt.executeQuery(queryy);



        } catch (Exception ex)
        {
            return null;
        }
        return rsss;

    }





    public ResultSet gmApprovals(String usertype, int locid)
    {

        ResultSet rsss;
        Connection con = connect.connectionclass();
        String queryy;



        if (usertype == "A" || locid == 18)
        {
            queryy = "Select RecruitmentAppPk AppPk,Applicationnum AppNo,BranchLocationName," +
                    "DesignationName,Contracttype Type, Vaccancynum Vacancies," +
                    "CONVERT(varchar,JoiningDate,103) ExpectedJoiningDate " +
                    "from RecruitmentApplicationMaster_tbl R " +
                    "inner join BranchLocartionMaster_tbl B on b.BranchlLocationPk=R.BranchLocationPK " +
                    "Inner join DesignationMaster_tbl D on D.DesgnationPK= R.DesignationPk " +
                    "Inner join RecruitmentApprovalMaster_tbl A on A.RecruitmentPK=R.RecruitmentAppPk " +
                    "where islevel1='A' and Islevel2='N' and Islevel3='N' ";
        }
        else
        {
            queryy = "Select RecruitmentAppPk AppPk,Applicationnum AppNo," +
                    "BranchLocationName,DesignationName,Contracttype Type, " +
                    "Vaccancynum Vacancies,CONVERT(varchar,JoiningDate,103) ExpectedJoiningDate from RecruitmentApplicationMaster_tbl R " +
                    "inner join BranchLocartionMaster_tbl B on b.BranchlLocationPk=R.BranchLocationPK " +
                    "Inner join DesignationMaster_tbl D on D.DesgnationPK= R.DesignationPk " +
                    "Inner join RecruitmentApprovalMaster_tbl A on A.RecruitmentPK=R.RecruitmentAppPk " +
                    "where islevel1='A' and Islevel2='N' and Islevel3='N' and BranchlLocationPk=" + locid;
        }


        try {

            Statement stmt = con.createStatement();
            rsss = stmt.executeQuery(queryy);



        } catch (Exception ex)
        {
            return null;
        }
        return rsss;

    }



    public ResultSet FCApprovals(String usertype, int locid)
    {

        ResultSet rsss;
        Connection con = connect.connectionclass();
        String queryy;



        if (usertype == "A" || locid == 18)
        {
            queryy = "Select RecruitmentAppPk AppPk,Applicationnum AppNo,BranchLocationName," +
                    "DesignationName,Contracttype Type, " +
                    "Vaccancynum Vacancies,CONVERT(varchar,JoiningDate,103) ExpectedJoiningDate " +
                    "from RecruitmentApplicationMaster_tbl R " +
                    "inner join BranchLocartionMaster_tbl B on b.BranchlLocationPk=R.BranchLocationPK " +
                    "Inner join DesignationMaster_tbl D on D.DesgnationPK= R.DesignationPk " +
                    "Inner join RecruitmentApprovalMaster_tbl A on A.RecruitmentPK=R.RecruitmentAppPk " +
                    "where islevel1='A' and Islevel2='A' and Islevel3='N' ";
        }
        else
        {
            queryy = "Select RecruitmentAppPk AppPk,Applicationnum AppNo," +
                "BranchLocationName,DesignationName,Contracttype Type, " +
                "Vaccancynum Vacancies,CONVERT(varchar,JoiningDate,103) ExpectedJoiningDate " +
                "from RecruitmentApplicationMaster_tbl R " +
                "inner join BranchLocartionMaster_tbl B on b.BranchlLocationPk=R.BranchLocationPK " +
                "Inner join DesignationMaster_tbl D on D.DesgnationPK= R.DesignationPk " +
                "Inner join RecruitmentApprovalMaster_tbl A on A.RecruitmentPK=R.RecruitmentAppPk " +
                "where islevel1='A' and Islevel2='A' and Islevel3='N' and BranchlLocationPk=" + locid;
        }


        try {

            Statement stmt = con.createStatement();
            rsss = stmt.executeQuery(queryy);



        } catch (Exception ex)
        {
            return null;
        }
        return rsss;

    }







    //eButton ya load erumikhila kuclass kuno
    public ResultSet Load(int empid)
    {

        ResultSet rst;
        Connection con = connect.connectionclass();

        String q = "SELECT empid, IPAddress, IPHostName\n" +
                "FROM loginTempIPDetail where empid = "+empid+" and status = 'LOGIN'";

        try {

            Statement stmt = con.createStatement();
            rst = stmt.executeQuery(q);



        } catch (Exception ex)
        {
            return null;
        }
        return rst;



    }





    //Eapprova
    public int Approve(int empid,int apppk)

    {

       int rrs;
        Connection con = connect.connectionclass();
       try
       {
           PreparedStatement statement = con.prepareStatement("EXEC sp_RecruitmentApprovallevel1 '" + apppk+ "','"+empid+"'");
           rrs = statement.executeUpdate();
       }
       catch (Exception ex)
       {
            Log.e("Error:",ex.getMessage());
            return 0;

       }
       return rrs;

    }



    //Erejecta
    public int rejecta(int empid,int apppk)

    {

        int rrs;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_RecruitmentRejectedLevel1 '" + apppk+ "','"+empid+"'");
            rrs = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return rrs;

    }



    ////////////////////////////////////////
    //Eapprova
    public int gmApprove(int empid,int apppk)

    {

        int rrs;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC Sp_RecruitmentApprovallevel2 '" + apppk+ "','"+empid+"'");
           rrs =  statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }
return rrs;

    }



    //Erejecta
    public int gmrejecta(int empid,int apppk)

    {

        int rst;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_RecruitmentRejectLevel2 '" + apppk+ "','"+empid+"'");
            rst = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return rst;


    }

    /////////////////////////////////////////////


    ////////////////////////////////////////
    //Eapprova
    public int fcApprove(int empid,int apppk, int vac)

    {

        int result;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC Sp_RecruitmentApprovalLevel3 '" + apppk+ "','"+empid+"','"+vac+"'");
            result = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return result;

    }




    //Erejecta
    public int fcrejecta(int empid,int apppk)

    {

        int rrs;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_RecruitmentRejection '" + apppk+ "','"+empid+"'");
            rrs = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return rrs;

    }

    /////////////////////////////////////////////
    public ResultSet loadContractpprovals(int locid)
    {

        ResultSet rsss;
        Connection con = connect.connectionclass();
        String queryy;




            queryy = "SELECT EmpID,OldEmpID,Name,NationalId,EmpGender Gender,Category,Designation,DepartmentName," +
                    "convert(varchar,ContractStartDate,103) " +
                    "ContractStartDate,convert(varchar,ContractEndDate,103) " +
                    "ContractEndDate,NSSFnum NSSF,NHIFnum NHIF,Incometaxnum KRAPIN,BankName," +
                    "AccountNum,Basic,HRA,Special,BAsic+HRA+Special Gross from vw_EmployeeMaster where Status='W' " +
                    "and cast(ContractEndDate as date) > cast(GETDATE() as date) and LocationID="+locid+ "and empnation <> 'KENYA'\n" +
                    "UNION\n" +
                    "Select EmpID, OldEmpID, Name, NationalId, EmpGender Gender, Category, Designation, DepartmentName," +
                    "convert(varchar,ContractStartDate,103) ContractStartDate,convert(varchar,ContractEndDate,103) " +
                    "ContractEndDate, NSSFnum NSSF, NHIFnum NHIF, Incometaxnum KRAPIN, BankName, AccountNum, Basic, HRA, Special," +
                    " BAsic + HRA + Special Gross from vw_EmployeeMaster where Status = 'W' " +
                    "and cast(ContractEndDate as date) > cast(GETDATE() " +
                    "as date) and LocationID =" +locid+
                    "and Basic+HRA + Special > 100";


        try {

            Statement stmt = con.createStatement();
            rsss = stmt.executeQuery(queryy);



        } catch (Exception ex)
        {
            return null;
        }
        return rsss;

    }



    public ResultSet loadDeactivationhrapprovals(int locid)
    {

        ResultSet rsss;
        Connection con = connect.connectionclass();
        String queryy;




        queryy = "SELECT DeactivationPK, D.empid,Name,Designation,DepartmentName," +
                "Dateadded,Remark,IsLevel1,Islevel2,Islevel3,D.IsBlackListed " +
                "FROM EmployeDeactivationMaster_tbl D, vw_EmployeeMaster E\n" +
                "WHERE E.EmpID = D.empid AND IsLevel1 = 'N' " +
                "AND Islevel2 = 'N' " +
                "AND Islevel3 = 'N' " +
                "and LocationID in (" + locid + ")";


        try {

            Statement stmt = con.createStatement();
            rsss = stmt.executeQuery(queryy);



        } catch (Exception ex)
        {
            return null;
        }
        return rsss;

    }






    public ResultSet loadDeactivationfcapprovals(int locid)
    {

        ResultSet rsss;
        Connection con = connect.connectionclass();
        String queryy;



        queryy = "SELECT DeactivationPK, D.empid,Name,Designation,DepartmentName," +
                "Dateadded,Remark,IsLevel1,Islevel2,Islevel3,D.IsBlackListed " +
                "FROM EmployeDeactivationMaster_tbl D, vw_EmployeeMaster E\n" +
                "WHERE E.EmpID = D.empid " +
                "AND IsLevel1='A' " +
                "AND Islevel2='A' " +
                "AND Islevel3='N' " +
                "and LocationID in (" + locid + ")";


        try {
            Statement stmt = con.createStatement();
            rsss = stmt.executeQuery(queryy);

        } catch (Exception ex)
        {
            return null;
        }
        return rsss;

    }



    public ResultSet loadDeactivationgmapprovals(int locid)
    {

        ResultSet rsss;
        Connection con = connect.connectionclass();
        String queryy;

        queryy = "SELECT DeactivationPK, D.empid,Name,Designation,DepartmentName,Dateadded,Remark," +
                "IsLevel1,Islevel2,Islevel3,D.IsBlackListed " +
                "FROM EmployeDeactivationMaster_tbl D, vw_EmployeeMaster E\n" +
                "WHERE E.EmpID = D.empid AND IsLevel1='A' AND Islevel2='N' AND Islevel3='N' " +
                "and LocationID in (" + locid + ")";


        try {
            Statement stmt = con.createStatement();
            rsss = stmt.executeQuery(queryy);

        } catch (Exception ex)
        {
            return null;
        }
        return rsss;

    }








    //Eapprova
    public int contractApprove(int empid)

    {
        int result;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_ContractApproval '" +empid+"'");
            result = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return result;

    }



    //Erejecta
    public int contractrejecta(int empid)

    {

        int rrs;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_Contractrejection '"+empid+"'");
            rrs = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return rrs;

    }


/* --------------------------------TRANSACTION EMPLOYEE DEACTIVATIONS------------------------------------------------------*/

    //Employee Deactivation fro HR
    //Eapprova


    public ResultSet getDeactivationLocation(int empid){




        // Declaring Server ip, username, database name and password

        // Declaring Server ip, username, database name and password

        con= connect.connectionclass();


        String query = "select distinct em.LocationID ," +
                "em.Location,CONVERT(varchar,em.LocationID) +'_' +em.Location LocationName " +
                "from UsersMaster_tbl u inner join vw_EmployeeMaster em on em.LocationID = u.LocationID " +
                "where u.Empid='" + empid + "' and em.Status = 'A'";



        try {

            st = con.createStatement();
            Statement st3 = con.createStatement();
            rs = st.executeQuery(query);



        } catch (SQLException se) {
            Log.e("SQL Error : ", se.getMessage());
        }

        return rs;
    }




    public ResultSet getDevices()
    {

        con= connect.connectionclass();
        String query = "select * From AssetMaster";

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException se) {
            Log.e("SQL Error : ", se.getMessage());
        }

        return rs;
    }
    public int hrDeactivationApprove(int appk, int empid)

    {

        int result;
        Connection con = connect.connectionclass();


        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_DeactivationApprovalLevel1 '"+appk+"','" +empid+"'");
            result = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return result;

    }




    //Erejecta
    public int hrDeactivationrejecta(int apppk, int val)

    {

        int rrs;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_DeactivationRejection '"+apppk+"','"+val+"'");
            rrs = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return rrs;

    }

    /*`````````````````````````````````````````````````````````````````````````````````````````````````````*/
    public int gmDeactivationApprove(int dpk, int empid)

    {

        int result;
        Connection con = connect.connectionclass();

        // Toast.makeText(getApplicationContext(), a , Toast.LENGTH_SHORT).show();


        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_DeactivationApprovalLevel2 '"+dpk+"','" +empid+"'");
            result = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }


        return result;

    }




    //Erejecta
    public int gmDeactivationrejecta(int apppk, int val)

    {

        int rrs;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_DeactivationRejection '"+apppk+"','"+val+"'");
            rrs = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return rrs;

    }

     /*````````````````````````````````````````````````````````````````````````````````````````````````*/


    /*`````````````````````````````````````````````````````````````````````````````````````````````````````*/
    public int fcDeactivationApprove(int dpk, int empid)

    {

        int result;
        Connection con = connect.connectionclass();


        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_DeactivationApprovalLevel3 '"+dpk+"','" +empid+"'");
            result = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return result;

    }




    //Erejecta
    public int fcDeactivationrejecta(int dpk, int val)

    {

        int rrs;
        Connection con = connect.connectionclass();
        try
        {
            PreparedStatement statement = con.prepareStatement("EXEC sp_DeactivationRejection '"+dpk+"','"+val+"'");
            rrs = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
            return 0;

        }

        return rrs;

    }

    /*```````````````````````````````````````````````````````````````````````````````````````*/




    /*---------------------------------------------------------------TRANSACTION DEACTIVATIONS END------------------------------------------------------*/






    /*--------------------------------------HR Utility --------------------------------------------------------------*/

             /*``````````````````````````````````````````Reinstatement```````````````````````````````````*/

                    public  ResultSet loadEmployeeReinstate(int search_id, String type)
                    {  ResultSet rlst,rte;

                        String query="";

                        Connection conct = connect.connectionclass();

                        if(type.equals("NID"))
                        {
                            query = "SELECT M.DeactivationPK, M.empid,Name,DepartmentName,SubDept,Designation," +
                                    "Isreemployable, Remark, Dateadded, Level3Date, M.IsBlackListed,Cause " +
                                    "FROM EmployeDeactivationMaster_tbl M " +
                                    "INNER JOIN EmployeeDeactivationDetails_tbl D " +
                                    "ON D.DeactivationPK=M.DeactivationPK " +
                                    "INNER JOIN vw_EmployeeMaster V ON V.EmpID=M.empid " +
                                    "and islevel3='A' " +
                                    "WHERE UserpK=0 AND V.Status<>'A' " +
                                    "AND NationalId = '"+search_id+"'"+
                                    "and year(cast(M.Level3Date" +
                                    " as date)) >= Year(getdate()) - 1";
                        }
                        else if(type.equals("EMPID"))
                        {
                            query = "SELECT M.DeactivationPK, M.empid, Name, DepartmentName, " +
                                    "SubDept, Designation, Isreemployable, Remark, Dateadded, " +
                                    "Level3Date, M.IsBlackListed, Cause " +
                                    "FROM EmployeDeactivationMaster_tbl M " +
                                    "INNER JOIN EmployeeDeactivationDetails_tbl D " +
                                    "ON D.DeactivationPK = M.DeactivationPK " +
                                    "INNER JOIN vw_EmployeeMaster V " +
                                    "ON V.EmpID = M.empid and islevel3='A'" +
                                    "WHERE UserpK = 0 AND V.Status <> 'A' " +
                                    "AND M.empid ='"+search_id+"'"+
                                    "and year(cast(M.Level3Date " +
                                    "as date)) >= Year(getdate())-1";
                        }
                        else
                        {

                        }

                        try {

                            Statement st = conct.createStatement();
                            rlst = st.executeQuery(query);


                        } catch (SQLException se) {
                            Log.e("SQL Error : ", se.getMessage());
                            return null;
                        }

                        return rlst;
                    }
             /*```````````````````````````````````````Reinstatement end`````````````````````````````````*/




             /*``````````````````````````````````````Reinstatement validation```````````````````````````*/
                            public ResultSet validateReinstatement(int empid)
                            {
                                String query;
                                ResultSet rrst;

                                Connection conct = connect.connectionclass();

                                query = "select * from Deserteeletters_tbl where empid ='"+empid+"' and IsApproved not in ('R') order by dlid desc";

                                try {

                                    Statement st = conct.createStatement();
                                    rrst = st.executeQuery(query);


                                } catch (SQLException se) {
                                    Log.e("SQL Error : ", se.getMessage());
                                    return null;
                                }

                                return rrst;

                            }

            /*`````````````````````````````````````Reinstatement valication end`````````````````````````*/



            /*``````````````````````````````````Reactivate Employee``````````````````````````````````````*/
                public int reactivateEmployee(int Empid, int Deactivationpk, int loginid)
                {

                    Connection con = connect.connectionclass();

                    String query = "EXEC sp_ReactivateEmployee '"+Empid+"','"+ Deactivationpk+"','"+loginid+"'";

                    int ret_value;

                    try
                    {
                        PreparedStatement statement = con.prepareStatement(query);
                        ret_value = statement.executeUpdate();
                    }
                    catch (Exception ex)
                    {
                        Log.e("Error:",ex.getMessage());
                        return 0;

                    }
                    return ret_value;
                }

            /*```````````````````````````````Reactivate Employee End`````````````````````````````````````*/

    /*--------------------------------------HR Utility end-----------------------------------------------------------*/
    public class MenusInfo
    {

        public String MenuName;
        public String Submenu;

        public MenusInfo() {

        }
    }
    //User validation ends here


}
