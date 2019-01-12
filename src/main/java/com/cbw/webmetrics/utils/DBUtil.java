package com.cbw.webmetrics.utils;

import com.cbw.webmetrics.beans.db.*;
import com.cbw.webmetrics.beans.other.MethodSqlBean;
import com.cbw.webmetrics.config.Config;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * the DBUtil class is design to help crud with db
 */
public class DBUtil {

    static Gson gson = new Gson();

    /**
     * save this minute's server performance to db
     *
     * @param serverPerformanceBean
     * @param userProjectBean
     */
    public static void saveSysPerformanceToDB(ServerPerformanceBean serverPerformanceBean, UserProjectBean userProjectBean) {
        String userHostName = userProjectBean.getHostname();
        String userPost = userProjectBean.getPort();
        String username = userProjectBean.getUsername();
        String password = userProjectBean.getPassword();
        String userDbName = userProjectBean.getDbName();
        String userDbUrl = "jdbc:mysql://" + userHostName + ":" + userPost + "/" + userDbName;
        String THIS_TABLE_NAME = "server_performance";

        for (int i = 0; i < 3; i++) {
            Connection conn = null;
            PreparedStatement psql = null;
            String sql = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(userDbUrl, username, password);
                sql = "insert into " + THIS_TABLE_NAME + "(`project_id`, `time_min`, `if_cpu_need_warn`, `if_mem_need_warn`, `if_disk_need_warn`, `cpu_warn_num`, `mem_warn_num`, `disk_warn_num`, `cpu_usage`, `mem_usage`, `disk_usage`, `if_cpu_warned`, `if_mem_warned`, `if_disk_warned`)" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                psql = conn.prepareStatement(sql);

                psql.setInt(1, serverPerformanceBean.getProjectId());
                psql.setString(2, serverPerformanceBean.getTimeMin());

                psql.setString(3, serverPerformanceBean.getIfCpuNeedWarn());
                psql.setString(4, serverPerformanceBean.getIfMemNeedWarn());
                psql.setString(5, serverPerformanceBean.getIfDiskNeedWarn());

                psql.setDouble(6, serverPerformanceBean.getCpuWarnNum());
                psql.setDouble(7, serverPerformanceBean.getMemWarnNum());
                psql.setDouble(8, serverPerformanceBean.getDiskWarnNum());

                psql.setDouble(9, serverPerformanceBean.getCpuUsage());
                psql.setDouble(10, serverPerformanceBean.getMemUsage());
                psql.setDouble(11, serverPerformanceBean.getDiskUsage());

                psql.setString(12, serverPerformanceBean.getIfCpuWarned());
                psql.setString(13, serverPerformanceBean.getIfMemWarned());
                psql.setString(14, serverPerformanceBean.getIfDiskWarned());

                psql.executeUpdate();  //参数准备后执行语句

                psql.close();
                conn.close();
                break;
            } catch (Exception e) {
                Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "fetch from mysql Exception:" + e + ",sql args:" + sql);
            } finally {
                try {
                    if (psql != null) psql.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close PreparedStatement Exception:" + e + ",sql args:" + sql);
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close mysql conn Exception:" + e + ",sql args:" + sql);
                }
            }
        }
    }

    /**
     * save this time method cost to db
     *
     * @param costBean
     * @param userProjectBean
     */
    public static void saveTimeCostToDB(TimeCostBean costBean, UserProjectBean userProjectBean) {
        String userHostName = userProjectBean.getHostname();
        String userPost = userProjectBean.getPort();
        String username = userProjectBean.getUsername();
        String password = userProjectBean.getPassword();
        String userDbName = userProjectBean.getDbName();
        String userDbUrl = "jdbc:mysql://" + userHostName + ":" + userPost + "/" + userDbName;
        String THIS_TABLE_NAME = "time_cost";

        for (int i = 0; i < 3; i++) {
            Connection conn = null;
            PreparedStatement psql = null;
            String sql = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(userDbUrl, username, password);
                sql = "insert into " + THIS_TABLE_NAME + "(`project_id`, `method_id`, `class_name`, `method_name`, `start_nano_time`, `start_user_time`, `end_nano_time`, `if_cost_need_warn`, `cost_warn_num`, `cost`, `if_warned`)" + " values(?,?,?,?,?,?,?,?,?,?,?)";
                psql = conn.prepareStatement(sql);

                psql.setInt(1, costBean.getProjectId());
                psql.setInt(2, costBean.getMethodId());
                psql.setString(3, costBean.getClassName());
                psql.setString(4, costBean.getMethodName());
                psql.setLong(5, costBean.getStartNanoTime());
                psql.setString(6, costBean.getStartUserTime());
                psql.setLong(7, costBean.getEndNanoTime());

                psql.setString(8, costBean.getIfCostNeedWarn());
                psql.setInt(9, costBean.getCostWarnNum());
                psql.setInt(10, costBean.getCost());
                psql.setString(11, costBean.getIfWarned());

                psql.executeUpdate();  //参数准备后执行语句

                psql.close();
                conn.close();
                break;
            } catch (Exception e) {
                Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "fetch from mysql Exception:" + e + ",sql args:" + sql);
            } finally {
                try {
                    if (psql != null) psql.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close PreparedStatement Exception:" + e + ",sql args:" + sql);
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close mysql conn Exception:" + e + ",sql args:" + sql);
                }
            }
        }
    }

    /**
     * save the params that user upload to db.
     *
     * @param paramsBean
     * @param userProjectInfo
     */
    public static void saveParamsToDB(ParamsBean paramsBean, UserProjectBean userProjectInfo) {
        String userHostName = userProjectInfo.getHostname();
        String userPost = userProjectInfo.getPort();
        String username = userProjectInfo.getUsername();
        String password = userProjectInfo.getPassword();
        String userDbName = userProjectInfo.getDbName();
        String userDbUrl = "jdbc:mysql://" + userHostName + ":" + userPost + "/" + userDbName;
        String THIS_TABLE_NAME = "params";

        for (int i = 0; i < 3; i++) {
            Connection conn = null;
            PreparedStatement psql = null;
            String sql = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(userDbUrl, username, password);
                sql = "insert into " + THIS_TABLE_NAME + "(`project_id`, `method_id`, `method_class`, `method_name`, `time_s`, `content`)" + " values(?,?,?,?,?,?)";
                psql = conn.prepareStatement(sql);

                psql.setInt(1, paramsBean.getProjectId());
                psql.setInt(2, paramsBean.getMethodId());
                psql.setString(3, paramsBean.getMethodClass());
                psql.setString(4, paramsBean.getMethodName());
                psql.setString(5, paramsBean.getTime_s());
                psql.setString(6, paramsBean.getContent());
                psql.executeUpdate();  //参数准备后执行语句

                psql.close();
                conn.close();
                break;
            } catch (Exception e) {
                Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "fetch from mysql Exception:" + e + ",sql args:" + sql);
            } finally {
                try {
                    if (psql != null) psql.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close PreparedStatement Exception:" + e + ",sql args:" + sql);
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close mysql conn Exception:" + e + ",sql args:" + sql);
                }
            }
        }
    }

    /**
     * get methods json from db
     *
     * @param projectId
     * @return
     */
    public static String getMethodsJson(int projectId) {
        String THIS_TABLE_NAME = "cost_method_info";
        for (int i = 0; i < 3; i++) {
            Statement statement = null;
            Connection conn = null;
            String sql = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(Config.MY_DB_URL, Config.MY_USERNAME, Config.MY_PASSWORD);
                statement = conn.createStatement();
                sql = "select * from " + THIS_TABLE_NAME + " where project_id = " + projectId + " and flag!=2";
                ResultSet rs = statement.executeQuery(sql);
                List<MethodSqlBean> list = new ArrayList<>();
                while (rs.next()) {
                    MethodSqlBean methodSqlBean = new MethodSqlBean(String.valueOf(rs.getInt("method_id")), rs.getString("method_class"), rs.getString("method_name"));
                    list.add(methodSqlBean);
                }
                statement.close();
                conn.close();

                Map<String, List<MethodSqlBean>> resMap = new HashMap<>();
                resMap.put("methods", list);
                return gson.toJson(resMap);
            } catch (Exception e) {
                Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "fetch from mysql Exception:" + e + ",sql args:" + sql);
            } finally {
                try {
                    if (statement != null) statement.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close PreparedStatement Exception:" + e + ",sql args:" + sql);
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close mysql conn Exception:" + e + ",sql args:" + sql);
                }
            }
        }
        return null;
    }

    /**
     * get user project info such as db info from my db
     *
     * @param projectId
     * @return
     */
    public static UserProjectBean getUserProjectInfo(int projectId) {
        String THIS_TABLE_NAME = "user_project_info";
        for (int i = 0; i < 3; i++) {
            Statement statement = null;
            Connection conn = null;
            String sql = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(Config.MY_DB_URL, Config.MY_USERNAME, Config.MY_PASSWORD);
                statement = conn.createStatement();
                sql = "select * from " + THIS_TABLE_NAME + " where project_id = " + projectId;
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    return new UserProjectBean(rs.getString("phone"), rs.getInt("project_id"), rs.getString("project_name"),
                            rs.getString("hostname"), rs.getString("port"), rs.getString("username"), rs.getString("password"),
                            rs.getString("db_name"), rs.getString("cpu_need_warn"), rs.getString("mem_need_warn"), rs.getString("disk_need_warn"),
                            rs.getDouble("cpu_warn_num"), rs.getDouble("mem_warn_num"), rs.getDouble("disk_warn_num"));
                }
                statement.close();
                conn.close();

            } catch (Exception e) {
                Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "fetch from mysql Exception:" + e + ",sql args:" + sql);
            } finally {
                try {
                    if (statement != null) statement.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close PreparedStatement Exception:" + e + ",sql args:" + sql);
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close mysql conn Exception:" + e + ",sql args:" + sql);
                }
            }
        }
        return null;
    }

    /**
     * get user info by phone
     *
     * @param phone
     * @return
     */
    public static String getUserInfoByPhone(String phone) {
        String THIS_TABLE_NAME = "user_info";
        for (int i = 0; i < 3; i++) {
            Statement statement = null;
            Connection conn = null;
            String sql = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(Config.MY_DB_URL, Config.MY_USERNAME, Config.MY_PASSWORD);
                statement = conn.createStatement();
                sql = "select email from " + THIS_TABLE_NAME + " where phone = " + phone;
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    return rs.getString("email");
                }
                statement.close();
                conn.close();

            } catch (Exception e) {
                Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "fetch from mysql Exception:" + e + ",sql args:" + sql);
            } finally {
                try {
                    if (statement != null) statement.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close PreparedStatement Exception:" + e + ",sql args:" + sql);
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close mysql conn Exception:" + e + ",sql args:" + sql);
                }
            }
        }
        return null;
    }

    /**
     * get cost method info such as if_method_need_warn from my db.
     *
     * @param projectId
     * @param methodId
     * @return
     */
    public static CostMethodBean getCostMethodInfo(int projectId, int methodId) {
        String THIS_TABLE_NAME = "cost_method_info";
        for (int i = 0; i < 3; i++) {
            Statement statement = null;
            Connection conn = null;
            String sql = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(Config.MY_DB_URL, Config.MY_USERNAME, Config.MY_PASSWORD);
                statement = conn.createStatement();
                sql = "select * from " + THIS_TABLE_NAME + " where project_id = " + projectId + " and method_id = " + methodId + " and flag!=2";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    return new CostMethodBean(rs.getInt("project_id"), rs.getInt("method_id"),
                            rs.getString("method_class"), rs.getString("method_name"),
                            rs.getString("if_cost_need_warn"), rs.getInt("cost_warn_num"));
                }
                statement.close();
                conn.close();
            } catch (Exception e) {
                Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "fetch from mysql Exception:" + e + ",sql args:" + sql);
            } finally {
                try {
                    if (statement != null) statement.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close PreparedStatement Exception:" + e + ",sql args:" + sql);
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    Logger.getLogger("DBUtil").warning("the " + i + "attempt," + "close mysql conn Exception:" + e + ",sql args:" + sql);
                }
            }
        }
        return null;
    }
}