package y2w.manage;

import com.y2w.uikit.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import y2w.db.SessionDb;
import y2w.db.SessionMemberDb;
import y2w.db.UserDb;
import y2w.entities.ContactEntity;
import y2w.entities.SessionEntity;
import y2w.entities.SessionMemberEntity;
import y2w.entities.UserEntity;
import y2w.model.Contact;
import y2w.model.Session;
import y2w.model.SessionMember;
import y2w.model.User;
import y2w.service.Back;
import y2w.service.ErrorCode;
import y2w.service.SessionSrv;
import y2w.service.UserSrv;

/**
 * 会话成员管理类
 * Created by maa2 on 2016/1/16.
 */
public class SessionMembers implements Serializable {
    private Session session;
    private Remote remote;
    public SessionMembers(Session session){
        this.session=session;
    }

    /**
     * 获取某个成员信息
     * @param userId
     * @return
     */
    public SessionMember getLocalMember(String userId){
        SessionMemberEntity entity = SessionMemberDb.queryByMemberId(session.getEntity().getMyId(), session.getEntity().getId(), userId);
        return new SessionMember(this,entity);
    }

    /**
     * 根据userId,获取成员信息
     * @param userId
     * @param result
     */
    public void getMember(String userId,Back.Result<SessionMember> result){
        SessionMember member = getLocalMember(userId);
        if(member.getEntity() != null){
            result.onSuccess(member);
        }else{
            getRemote().sessionMemberGet(userId, result);
        }
    }

    public void getMembers(Back.Result<List<SessionMember>> result){
        List<SessionMemberEntity> entities = SessionMemberDb.query(session.getEntity().getMyId(), session.getEntity().getId());
        List<SessionMember> memberList = new ArrayList<SessionMember>();
        if(entities.size() > 0){
            for(SessionMemberEntity entity:entities){
                memberList.add(new SessionMember(this,entity));
            }
            result.onSuccess(memberList);
        }else{
            getRemote().sync(result);
        }

    }
    /**
     * 将会话成员列表保存到数据集
     * @param sessionMemberList
     */
    public void add(List<SessionMember> sessionMemberList){
        for(SessionMember member:sessionMemberList){
            addSessionMember(member);
        }
    }
    /**
     * 将某个会员成员保存到数据库
     * @param member
     */
    public void addSessionMember(SessionMember member){
        member.getEntity().setSessionId(session.getEntity().getId());
        member.getEntity().setMyId(session.getEntity().getMyId());
        userInit(member);
        refreshSessionMTS(member);
        SessionMemberDb.addSessionMemberEntity(member.getEntity());
    }
    /**
     * 更新通讯录同步时间戳
     * @param member
     */
    private void refreshSessionMTS(SessionMember member){
        String createMTS = session.getEntity().getCreateMTS();
        if(StringUtil.isEmpty(createMTS)){
            session.getEntity().setCreateMTS(member.getEntity().getCreatedAt());
            SessionDb.addSessionEntity(session.getEntity());
        }else{
            if(StringUtil.timeCompare(createMTS,member.getEntity().getCreatedAt()) > 0){
                session.getEntity().setCreateMTS(member.getEntity().getCreatedAt());
                SessionDb.addSessionEntity(session.getEntity());
            }
        }
    }

    /**
     * user表数据初始化
     * @param member
     */
    private void userInit(SessionMember member){
        UserEntity entity = UserDb.queryById(session.getEntity().getMyId(), member.getEntity().getUserId());
        if(entity == null){
            User user = Users.getInstance().createUser(member.getEntity().getUserId(),member.getEntity().getName(),member.getEntity().getAvatarUrl());
            Users.getInstance().addUser(user);
        }
    }

    public Remote getRemote(){
        if(remote == null){
            remote = new Remote(this);
        }
        return  remote;
    }

    /*****************************remote*****************************/

    public class Remote implements Serializable{

        private SessionMembers sessionMembers;
        public Remote(SessionMembers sessionMembers){
            this.sessionMembers = sessionMembers;
        }

        /**
         * 添加会话成员
         * @param userId
         * @param name
         * @param role
         * @param avatarUrl
         * @param status
         * @param result
         */
        public void sessionMemberAdd(final String userId,final String name,final String role,final String avatarUrl,final String status, final Back.Result<SessionMember> result){
            SessionSrv.getInstance().sessionMemberAdd(session.getSessions().getUser().getToken(), session.getEntity().getId(), userId, name, role, avatarUrl, status, new Back.Result<SessionMemberEntity>() {
                @Override
                public void onSuccess(SessionMemberEntity Entity) {
                    SessionMember sessionMember = new SessionMember(sessionMembers, Entity);
                    result.onSuccess(sessionMember);
                }

                @Override
                public void onError(ErrorCode errorCode, String error) {
                    result.onError(errorCode, error);
                }
            });
        }

        /**
         * 删除会话成员
         * @param sessionMember
         * @param callback
         */

        public void sessionMemberDelete(final SessionMember sessionMember, final Back.Callback callback){
            SessionSrv.getInstance().sessionMembersDelete(session.getSessions().getUser().getToken(), sessionMember.getEntity().getSessionId(), sessionMember.getEntity().getId(), new Back.Callback() {
                @Override
                public void onSuccess() {
                    SessionMemberDb.delete(sessionMember.getEntity());
                    callback.onSuccess();
                }

                @Override
                public void onError(ErrorCode errorCode) {
                    callback.onError(errorCode);
                }
            });

        }

        public void sessionMemberGet(final String userId, Back.Result<SessionMember> result){

        }
        /**
         * 同步会话成员
         * @param result
         */
        public void sync(final Back.Result<List<SessionMember>> result){
            SessionSrv.getInstance().sessionMembersGet(session.getSessions().getUser().getToken(), session.getEntity().getUpdatedAt(), session.getEntity().getId(), new Back.Result<List<SessionMemberEntity>>() {
                @Override
                public void onSuccess(List<SessionMemberEntity> sessionMemberEntities) {
                    List<SessionMember> sessionMemberList = new ArrayList<SessionMember>();
                    for (SessionMemberEntity entity : sessionMemberEntities) {
                        sessionMemberList.add(new SessionMember(sessionMembers, entity));
                    }
                    add(sessionMemberList);
                    result.onSuccess(sessionMemberList);
                }

                @Override
                public void onError(ErrorCode errorCode, String error) {
                    result.onError(errorCode, error);
                }
            });
        }

    }


}
