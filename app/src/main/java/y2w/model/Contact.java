package y2w.model;

import y2w.manage.Contacts;
import y2w.entities.ContactEntity;
import y2w.entities.SessionEntity;
import y2w.manage.EnumManage;
import y2w.service.Back;

/**
 * Created by maa2 on 2016/1/16.
 */
public class Contact{
    private User user;
    private Contacts contacts;
    private ContactEntity entity;

    public Contact(User user,Contacts contacts,ContactEntity entity){
        this.user = user;
        this.contacts = contacts;
        this.entity = entity;
    }

    public void getUserConversation(){

    }

    public ContactEntity getEntity() {
        return entity;
    }


    public void getSession(Back.Result<Session> result){
        this.contacts.getUser().getSessions().getSessionByTargetId(entity.getUserId(), EnumManage.SessionType.p2p.toString(),result);
    }
}
