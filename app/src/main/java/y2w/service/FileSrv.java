package y2w.service;

import android.content.Context;
import y2w.base.AppData;
import y2w.common.AsyncMultiPartPost;

/**
 * Created by maa2 on 2016/2/29.
 */
public class FileSrv {

    private static FileSrv FileSrv = null;
    public static FileSrv getInstance(){
        if(FileSrv == null){
            FileSrv = new FileSrv();
        }
        return FileSrv;
    }

    public void uploadMessagesFile(final Context context,String token, String uploadFileURL, String filepath){
        try {
            AsyncMultiPartPost post = new AsyncMultiPartPost(context,token, uploadFileURL, filepath);

            post.execute();
            //将请求加入到全局保存
            AppData.getInstance().addPost(filepath, post);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
