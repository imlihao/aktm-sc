package com.lh.dao;
/**
 * @author lihao
 */
import java.util.List;
import com.lh.vo.user;

public interface userDao {
   public List<user> serach();
   public List<user> serach(user od);
   public boolean insert(user user);
   public boolean delete(user user);
}
