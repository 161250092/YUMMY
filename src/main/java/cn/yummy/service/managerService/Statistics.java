package cn.yummy.service.managerService;

import cn.yummy.entity.manager.PlatformCondition;

import java.time.LocalDate;

public interface Statistics {


        public PlatformCondition getPlatformCondition(LocalDate startTime, LocalDate endTime, String interval);


}
