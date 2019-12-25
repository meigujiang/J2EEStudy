package dao;

import entity.Staff;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

import utils.DBConn;

/**
 * @description: 1.注册驱动(静态方法)(包名 + 类名 ）
 *2.获取连接对象 ( 导包都导sql里面的 ， 不导jdbc里的 ； 多态 ！ 报异常是因为用户输入的串可能写错 ） 后面设置下数据格式
 *3.获取语句执行平台 ：
 *4.执行SQL语句 ： 增一套语句执行一句sql语句 返回的是一个Int值 ， 是指执行了几行
 *6.释放资源 ( 先开后关)
 * @author: Tong
 * @date: 2019-12-16 20:02
 */
public class StaffOperationDao {
    Logger logger = Logger.getLogger(StaffOperationDao.class);

    //新增干员信息
    public void addStaffInfo(Staff staff){
        try {
            Connection conn = DBConn.getConn();
            String sql = "INSERT INTO staff ( sta_name, sta_sex, sta_health, sta_attack_Power, sta_cost, sta_defence, sta_avoid_Num, sta_spell_Resistance, sta_rarity, sta_Redeploy_Speed, sta_attack_Speed, sta_career, sta_faction )" +
                    "VALUES" +
                    "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, staff.getStaName());
            ps.setString(2, staff.getStaSex());
            ps.setString(3, staff.getStaHealth());
            ps.setString(4, staff.getStaAttackPower());
            ps.setString(5, staff.getStaCost());
            ps.setString(6, staff.getStaDefence());
            ps.setString(7, staff.getStaAvoidNum());
            ps.setString(8, staff.getStaSpellResistance());
            ps.setString(9, staff.getStaRarity());
            ps.setString(10, staff.getStaRedeploySpeed());
            ps.setString(11, staff.getStaAttackSpeed());
            ps.setString(12, staff.getStaCareer());
            ps.setString(13, staff.getStaFaction());

            ps.execute();
            DBConn.closeConn(ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除干员信息
    public void removeStaffInfo(int id){
        try {
            Connection conn = DBConn.getConn();
            String sql = "delete from staff t where t.sta_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.execute();
            DBConn.closeConn(ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //修改干员信息
    public void updateStaffInfo(Staff staff){
        try {
            Connection conn = DBConn.getConn();
            String sql = "";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "");

            int row = ps.executeUpdate(sql);
            DBConn.closeConn(ps,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询单个干员信息
    public ArrayList<Staff> queryStaffInfo(int id){
        ArrayList<Staff> staffList = null;
        try {
            Connection conn = DBConn.getConn();
            String sql = "select sta_id,sta_name,sta_sex,sta_health,sta_attack_Power,sta_cost,sta_defence,sta_avoid_Num,sta_spell_Resistance,sta_rarity,sta_Redeploy_Speed,sta_attack_Speed,sta_career,sta_faction from staff where sta_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            staffList = initialStaffList(rs);

            DBConn.closeConn(ps, conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    //分页查询干员列表
    public ArrayList<Staff> queryStaffList(int startIndex, int queryCount) {
        ArrayList<Staff> staffList = null;
        try {
            Connection conn = DBConn.getConn();
            String sql = "select sta_id,sta_name,sta_sex,sta_health,sta_attack_Power,sta_cost,sta_defence,sta_avoid_Num,sta_spell_Resistance,sta_rarity,sta_Redeploy_Speed,sta_attack_Speed,sta_career,sta_faction from staff order by sta_id limit ?,?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, startIndex);//查询起始位
            ps.setInt(2, queryCount);//每次查询的数量

            ResultSet rs = ps.executeQuery();
            staffList = initialStaffList(rs);

            DBConn.closeConn(ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    //查询干员条数
    public int staffCount(){
        int staffTotal = 0;
        try {
            Connection conn = DBConn.getConn();
            String sql = "select count(1) staffCount from staff";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);

            staffTotal = 0;
            while (rs.next()) {
                staffTotal = rs.getInt("staffCount");
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffTotal;
    }

    public ArrayList<Staff> queryStaffListByCon(int startIndex, int queryCount){
        ArrayList<Staff> staffList = null;
        try {
            Connection conn = DBConn.getConn();
            String sql = "select sta_id,sta_name,sta_sex,sta_health,sta_attack_Power,sta_cost,sta_defence,sta_avoid_Num,sta_spell_Resistance,sta_rarity,sta_Redeploy_Speed,sta_attack_Speed,sta_career,sta_faction from staff order by sta_id limit ?,?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, startIndex);
            ps.setInt(2, queryCount);

            ResultSet rs = ps.executeQuery();
            staffList = initialStaffList(rs);

            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }


    //初始化干员列表
    public ArrayList<Staff> initialStaffList(ResultSet rs) {
        ArrayList<Staff> staffList = new ArrayList<>();
        try {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaId((long) rs.getInt("sta_id"));
                staff.setStaName(rs.getString("sta_name"));
                staff.setStaSex(rs.getString("sta_sex"));
                staff.setStaHealth(rs.getString("sta_health"));
                staff.setStaAttackPower(rs.getString("sta_attack_Power"));
                staff.setStaCost(rs.getString("sta_cost"));
                staff.setStaDefence(rs.getString("sta_defence"));
                staff.setStaAvoidNum(rs.getString("sta_avoid_Num"));
                staff.setStaSpellResistance(rs.getString("sta_spell_Resistance"));
                staff.setStaRarity(rs.getString("sta_rarity"));
                staff.setStaRedeploySpeed(rs.getString("sta_Redeploy_Speed"));
                staff.setStaAttackSpeed(rs.getString("sta_attack_Speed"));
                staff.setStaCareer(rs.getString("sta_career"));
                staff.setStaFaction(rs.getString("sta_faction"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

}
