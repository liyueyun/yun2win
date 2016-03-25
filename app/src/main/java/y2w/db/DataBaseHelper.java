package y2w.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import y2w.entities.ContactEntity;
import y2w.entities.SessionEntity;
import y2w.entities.SessionMemberEntity;
import y2w.entities.MessageEntity;
import y2w.entities.SyncQueueEntity;
import y2w.entities.TimeStampEntity;
import y2w.entities.UserConversationEntity;
import y2w.entities.UserEntity;
import y2w.entities.UserSessionEntity;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {


	private static final String TAG = "DataBaseHelper";
	private static final String DATABASE_NAME = "y2w.db";
	private static final int DATABASE_VERSION = 2;

	private Dao<ContactEntity, Integer> dao_contact = null;
	private Dao<UserConversationEntity, Integer> dao_userConversation = null;
	private Dao<SessionEntity, Integer> dao_session = null;
	private Dao<SessionMemberEntity, Integer> dao_sessionMember = null;
	private Dao<MessageEntity, Integer> dao_message = null;
	private Dao<UserSessionEntity, Integer> dao_userSession = null;
	private Dao<TimeStampEntity, Integer> dao_timeStamp = null;
	private Dao<UserEntity, Integer> dao_user = null;
	private Dao<SyncQueueEntity, Integer> dao_syncQueue = null;
	private Context context;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTableIfNotExists(arg1, ContactEntity.class);
		} catch (Exception ex) {
		}

		try {
			TableUtils.createTableIfNotExists(arg1, UserConversationEntity.class);
		} catch (Exception ex) {
		}
		try {
			TableUtils.createTableIfNotExists(arg1, SessionEntity.class);
		} catch (Exception ex) {
		}

		try {
			TableUtils.createTableIfNotExists(arg1, TimeStampEntity.class);
		} catch (Exception ex) {
		}

		try {
			TableUtils.createTableIfNotExists(arg1, SessionMemberEntity.class);
		} catch (Exception ex) {
		}

		try {
			TableUtils.createTableIfNotExists(arg1, MessageEntity.class);
		} catch (Exception ex) {
		}

		try {
			TableUtils.createTableIfNotExists(arg1, UserEntity.class);
		} catch (Exception ex) {
		}

		try {
			TableUtils.createTableIfNotExists(arg1, SyncQueueEntity.class);
		} catch (Exception ex) {
		}


	}


	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {

		try {
			onCreate(arg0, arg1);
		} catch (Exception e) {
		}
	}

	public Dao<ContactEntity, Integer> getContactsDao() throws SQLException {
		if (dao_contact == null) {
			dao_contact = getDao(ContactEntity.class);
		}
		return dao_contact;
	}

	public Dao<SessionEntity, Integer> getSessionDao() throws SQLException {
		if (dao_session == null) {
			dao_session = getDao(SessionEntity.class);
		}
		return dao_session;
	}

	public Dao<SessionMemberEntity, Integer> getSessionMemberDao() throws SQLException {
		if (dao_sessionMember == null) {
			dao_sessionMember = getDao(SessionMemberEntity.class);
		}
		return dao_sessionMember;
	}

	public Dao<UserConversationEntity, Integer> getUserConversationDao() throws SQLException {
		if (dao_userConversation == null) {
			dao_userConversation = getDao(UserConversationEntity.class);
		}
		return dao_userConversation;
	}

	public Dao<MessageEntity, Integer> getMessageDao() throws SQLException {
		if (dao_message == null) {
			dao_message = getDao(MessageEntity.class);
		}
		return dao_message;
	}

	public Dao<UserSessionEntity, Integer> getUserSessionDao() throws SQLException {
		if (dao_userSession == null) {
			dao_userSession = getDao(UserSessionEntity.class);
		}
		return dao_userSession;
	}

	public Dao<TimeStampEntity, Integer> getTimeStampDao() throws SQLException {
		if (dao_timeStamp == null) {
			dao_timeStamp = getDao(TimeStampEntity.class);
		}
		return dao_timeStamp;
	}

	public Dao<UserEntity, Integer> getUserDao() throws SQLException {
		if (dao_user == null) {
			dao_user = getDao(UserEntity.class);
		}
		return dao_user;
	}

	public Dao<SyncQueueEntity, Integer> getSyncQueueDao() throws SQLException {
		if (dao_syncQueue == null) {
			dao_syncQueue = getDao(SyncQueueEntity.class);
		}
		return dao_syncQueue;
	}


	@Override
	public void close() {
		super.close();
		dao_contact = null;
		dao_session = null;
		dao_sessionMember = null;

	}
}
