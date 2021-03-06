package run.liuliuqiu.j2ee.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import run.liuliuqiu.j2ee.dao.StaffOperationDao;
import run.liuliuqiu.j2ee.entity.Rarity;
import run.liuliuqiu.j2ee.entity.Staff;


/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 19:58
 */
public class StaffOperationService {
    Logger logger = Logger.getLogger(StaffOperationService.class);
    StaffOperationDao sod = new StaffOperationDao();

    public void addStaffInfo(Staff staff) {
        sod.addStaffInfo(staff);
    }

    public void removeStaffInfo(int id) {
        sod.removeStaffInfo(id);
    }

    public void updateStaffInfo(Staff staff) {
        sod.updateStaffInfo(staff);
    }

    public ArrayList<Staff> queryStaffInfo(int id) {
        return sod.queryStaffInfo(id);
    }

    public ArrayList<Staff> queryStaffList(int startIndex, int queryCount) {
        return sod.queryStaffList(startIndex, queryCount);
    }

    public int staffCount() {
        return sod.staffCount();
    }

    public ArrayList<Staff> queryStaffListByCon(int startIndex, int queryCount, String name, String career, String faction, List<Rarity> rarityList) {
        return sod.queryStaffListByCon(startIndex, queryCount, name, career, faction, rarityList);
    }
}
