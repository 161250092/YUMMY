package cn.tycoding.service.managerService;

import cn.tycoding.entity.member.Member;
import cn.tycoding.entity.merchant.Merchant;

import java.util.List;

public interface ManagerStatisticsService {
    public List<Merchant> getAllMerchant();

    public List<Member>  getAllMember();


}
