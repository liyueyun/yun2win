package y2w.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.yun2win.utils.Json;

import java.io.Serializable;

import com.y2w.uikit.utils.StringUtil;

/**
 * Created by maa2 on 2016/1/18.
 */
@DatabaseTable (tableName = "MessageEntity")
public class MessageEntity implements Serializable{
    @DatabaseField
    private String id;
    @DatabaseField
    private String sender;
    @DatabaseField
    private String content;
    @DatabaseField
    private String createdAt;
    @DatabaseField
    private String updatedAt;
    @DatabaseField
    private String type;
    @DatabaseField
    private String status;
    @DatabaseField
    private String sessionId;
    @DatabaseField
    private String myId;

    private boolean isDelete;

    public static MessageEntity parse(Json json){
        MessageEntity entity = new MessageEntity();
        entity.setId(json.getStr("id"));
        entity.setSender(json.getStr("sender"));
        entity.setContent(json.getStr("content"));
        entity.setCreatedAt(json.getStr("createdAt"));
        entity.setUpdatedAt(json.getStr("updatedAt"));
        entity.setType(json.getStr("type"));
        entity.setStatus("");
        entity.setSessionId(json.getStr("sessionId"));
        return entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = StringUtil.getOPerTime(createdAt);
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = StringUtil.getOPerTime(updatedAt);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public static enum  MessageState{
        storing,stored,storeFailed
    }
}
