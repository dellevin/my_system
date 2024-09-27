package io.dellevin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mark_visiter")
public class MarkVisiterEntity {
    @TableId
    private Long id;
    private String visitUrl;
    private String visitUrlChild;
    private String ip;
    private String ipAddr;
    private Date create_time;
}
