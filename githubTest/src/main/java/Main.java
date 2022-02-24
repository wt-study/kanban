//import javafx.scene.chart.PieChart;
import org.kohsuke.github.*;
import org.w3c.dom.UserDataHandler;
import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        //账户名、密码、起始、结束时间设置
        String user = "fang-li-studying";
        String password = "19941023@lf";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date since = dateFormat.parse("2022-01-01");
        Date until = dateFormat.parse("2022-02-21");
        //创建github对象
        GitHub github = new GitHubBuilder().withPassword(user, password).build();

        System.out.println(user);
        //获取仓库对象
        GHRepository ghRepository = github.getRepository("lfstudy/kanban");
        System.out.println("获取repo");
        //设置分支
        GHCommit branch  = ghRepository.getCommit("master");
        System.out.println("master");
        //定义最后的修改行数
        int addCode = 0;
        int changeCode =0;
        //遍历查询条件，同时也支持查询作者等
        for (GHCommit ghCommit : ghRepository.queryCommits().since(since).until(until).list()) {
            //每次提交增加数量-删除数量即为本次增加的数量，具体操作逻辑再定义
            addCode += (ghCommit.getLinesAdded() - ghCommit.getLinesDeleted());
            changeCode +=(ghCommit.getLinesChanged());
        }
        System.out.println(addCode);
        System.out.println(changeCode);
//        GHCommit master = ghRepository.getCommit("master");
//        int linesAdded = master.getLinesAdded();
//        String sha1 = master.getSHA1();
//        System.out.println(sha1);
//        System.out.println(linesAdded);
//        System.out.println("登录成功");

    }
}
