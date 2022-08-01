package fun.tiga.toolapi.model.entity;

import lombok.Data;

import java.util.Date;


@Data
public class ParsingInfo {
    private Long id;
    private String userOpenId;
    private String downloadUrl;
    private String title;
    private Date createTime;
    private String author;//视频作者
    private String cover;//视频封面地址
}
