package fun.tiga.toolapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.tiga.toolapi.config.WxConfig;
import fun.tiga.toolapi.exception.Asserts;
import fun.tiga.toolapi.mapper.WxUserMapper;
import fun.tiga.toolapi.model.entity.WxUser;
import fun.tiga.toolapi.service.WxUserService;
import fun.tiga.toolapi.utils.DateUtil;
import fun.tiga.toolapi.utils.RestTemplateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {

    @Resource
    private WxUserMapper wxUserMapper;

    @Resource
    private RestTemplateUtil restTemplateUtil;

    @Override
    public WxUser getUserInfoByOpenId(String openId) {
        QueryWrapper<WxUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", openId);
        List<WxUser> userList = wxUserMapper.selectList(queryWrapper);
        if (!userList.isEmpty())
            return userList.get(0);
        return null;
    }

    @Override
    public int insert(WxUser wxUser) {
        return wxUserMapper.insert(wxUser);
    }

    @Override
    public int updateById(WxUser wxUser) {
        return wxUserMapper.updateById(wxUser);
    }

    @Override
    public WxUser singIn(String openId) {
        WxUser wxUser = getUserInfoByOpenId(openId);
        if (wxUser == null)
            Asserts.wxInfoFail("获取用户信息失败");
        Date signTime = wxUser.getEndSignInTime();//最后签到时间
        Date nowDate = new Date();
        Date startDate = DateUtil.getStartTime(nowDate);//今日开始时间
        Date endDate = DateUtil.getEndTime(nowDate);//今日结束时间
        if (signTime == null || signTime.before(startDate) || signTime.after(endDate)) {
            wxUser.setSignInSum(wxUser.getSignInSum() + 1);
            wxUser.setVideoNumber(wxUser.getVideoNumber() + 20);
            wxUser.setEndSignInTime(nowDate);
            wxUserMapper.updateById(wxUser);
        } else {
            Asserts.wxInfoFail("重复签到");
        }
        return wxUser;
    }

    @Override
    public String getOpenId(String code) {
        String openId = null;
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=" + WxConfig.appId);
        url.append("&secret=" + WxConfig.secret);
        url.append("&js_code=" + code);
        url.append("&grant_type=authorization_code");
        String data = restTemplateUtil.getForObject(url.toString(), null, String.class);
        Asserts.urlInfoNotNull(data, "openid解析异常");
        JSONObject jsonObject = JSON.parseObject(data);
        if (jsonObject.get("openid") == null) {
            Asserts.wxInfoFail(jsonObject.get("errmsg").toString());
        } else {
            openId = jsonObject.get("openid").toString();
        }
        return openId;
    }
}
