package fun.tiga.toolapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.tiga.toolapi.mapper.ParsingInfoMapper;
import fun.tiga.toolapi.model.entity.ParsingInfo;
import fun.tiga.toolapi.model.support.BaseResponse;
import fun.tiga.toolapi.service.VideoService;
import fun.tiga.toolapi.service.WxUserService;
import org.jsoup.internal.StringUtil;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/watermark/video")
public class VideoController {
    @Resource
    ParsingInfoMapper parsingInfoMapper;

    @Resource
    WxUserService wxUserService;


    @Resource
    VideoService videoService;

    /***
     * 视频无水印链接解析
     * @param url 分享地址
     * @param openId 用户openId
     * @return
     */
    @PostMapping(value = "getVideoInfo")
    public BaseResponse getVideoInfo(@RequestParam(value = "url") String url, @RequestParam(value = "openId") String openId) {
        return BaseResponse.ok(videoService.getVideoInfo(openId, url));
    }

    /***
     * 获取解析记录
     * @param openId
     * @return
     */
    @PostMapping(value = "getParsingInfo")
    public BaseResponse<List> getVideoInfo(@RequestParam(value = "openId") String openId) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
        QueryWrapper<ParsingInfo> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(ParsingInfo::getUserOpenId, openId).gt(ParsingInfo::getCreateTime, calendar.getTime());
        List<ParsingInfo> parsingInfoList = parsingInfoMapper.selectList(queryWrapper);
        return BaseResponse.ok(parsingInfoList);
    }

    /***
     * 获取解析记录
     * @param url
     * @return
     */
    @GetMapping(value = "down")
    public ResponseEntity<ByteArrayResource> down(@RequestParam(value = "openId") String openId, @RequestParam("url") String url) throws Exception {

        if (null == openId || null == url) {
            throw new Exception("请求参数异常");
        }
        //url="https://aweme.snssdk.com/aweme/v1/play/?video_id=v0d00fg10000cbht0sbc77ubken4753g&ratio=720p&line=0";
        var userInfo = wxUserService.getUserInfoByOpenId(openId);
        if (null == userInfo || userInfo.getVideoNumber() <= 0) {
            throw new Exception("请求异常");
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Mobile Safari/537.36");
        httpHeaders.set("Referer", url);
        HttpEntity<?> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<ByteArrayResource> data = new RestTemplate().exchange(url, HttpMethod.GET, httpEntity, ByteArrayResource.class);
        return data;
    }
}
