package cn.itcast.ssm.service;

import cn.itcast.ssm.dao.SysLog;

public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;
}
