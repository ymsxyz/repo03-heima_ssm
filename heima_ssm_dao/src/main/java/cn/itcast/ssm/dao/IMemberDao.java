package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    //查询会员通过id
    @Select("select * from member where id=#{id}")
    Member findById(String id) throws Exception;
}
