package fun.tiga.toolapi.service;

import fun.tiga.toolapi.model.entity.WxUser;


public interface WxUserService {
    WxUser getUserInfoByOpenId(String openId);

    int insert(WxUser wxUser);

    int updateById(WxUser wxUser);

    /**
     * 签到
     *
     * @param openId
     * @return 累计签到次数
     */
    WxUser singIn(String openId);

    /***
     * 获取微信用户openId
     * @param code
     * @return
     */
    String getOpenId(String code);
}
