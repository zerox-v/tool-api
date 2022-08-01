package fun.tiga.toolapi;

import fun.tiga.toolapi.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest
class ToolApiApplicationTests {

    @Autowired
    VideoService videoService;




    @Test
    void contextLoads() {
        videoService.phpParsingVideoInfo("https://aweme.snssdk.com/aweme/v1/play/?video_id=v0d00fg10000cbht0sbc77ubken4753g&ratio=720p&line=0");
    }

    @Test
    void header() {
        String url="https://aweme.snssdk.com/aweme/v1/play/?video_id=v0d00fg10000cbht0sbc77ubken4753g&ratio=720p&line=0";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Mobile Safari/537.36");
        httpHeaders.set("Referer", url);
        HttpEntity<?> httpEntity = new HttpEntity<>(null, httpHeaders);
       // Object data= new RestTemplate().headForHeaders(url, HttpMethod.GET, httpEntity);
        Object data= new RestTemplate().exchange(url, HttpMethod.GET, httpEntity, FileSystemResource.class);

    }

}
