首先说一下：本次小作业，可谓是一个综合性稍高的系统（就目前我们的水平哈，大佬勿喷），我们需要掌握的知识有
- SSH中的strus,spring,hibernate等基本配置的的使用
- 这期间还涉及到mysql数据库的使用
- idea的使用，如何配置基于maven的环境，配置tomcat
- 并且必须有一台或者多个搭建好hadoop环境的服务器，必须还得可以与外界进行通信，

我想对于我这样一个基础不牢，地动山摇的人来说，还是有一定的困难的。（当然，每个人的情况都不一样，光搭建这个环境就得话费好久的时间 哈哈哈哈）

## 准备1：启动虚拟机 开启hdfs文件系统
1.进入hadoop的安装目录

```
[xxx@hadoop sbin]#./start-all.sh
```
出现下图，则证明文件系统启动成功：
![这里写图片描述](http://img.blog.csdn.net/20171104155204069?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbTBfMzc2Mzk1NDI=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
## 准备2：关闭文件系统读写保护
不关闭或者文件系统未正常启动时，都会报出读写错误信息。
在Hadoop的安装目录下，修改/etc/hadoophdfs-site.xml文件
```
<property>
	<name>dfs.permissions</name>
	<value>false</value>
</property>
```
记得重启hdfs系统。

## 准备3.学会Hadoop的命令
如果你很熟悉这个，可以跳过！！一会会用的到

1.命令行输入hadoop没有反应提示：没有相关名令
解决：
解决方法 
1. cd /etc/profile 
2. 把这三条加到proflie文件的最后

```
export JAVA_HOME=XXXX(在安装了jdk的前提下，echo $JAVA_HOME可以查看得到)
export HADOOP_HOME=XXX（hadoop的安装路径）
export PATH=.:$HADOOP_HOME/bin:$JAVA_HOME/bin:$PATH
```
3.记得执行重启环境变量命令：

```
source /etc/profile
```
4.再次输入输入hadoop ，出现下图则成功。
![这里写图片描述](http://img.blog.csdn.net/20171104153930760?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbTBfMzc2Mzk1NDI=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

5.来测试几个命令
[所有的 名令 官方详细文档](http://hadoop.apache.org/docs/r1.0.4/cn/hdfs_shell.html)

方式一：命令行方式

Hadoop文件操作命令形式为

```
hadoop fs -cmd <参数>
```

说明：cmd是具体的文件操作命令，<参数>是一组数目可变的参数。

Hadoop最常用的文件操作命令，包括添加文件和目录、获取文件、删除文件等。

### 1.添加文件和目录

HDFS有一个默认工作目录/usr/$USER，其中$USER是你的登录用户名，作者的用户名是root。该目录不能自动创建，需要执行mkdir命令创建。

```
hadoop fs -mkdir  /usr/root
```

使用Hadoop的命令put将本地文件README.txt送到HDFS。

```
hadoop fs -put ceshi.txt  .

```

注意上面这个命令最后一个参数是句点（.），这意味着把本地文件放入到默认的工作目录，该命令等价于：

```
hadoop fs -put ceshi.txt     /user/xxx/xxx/xxx
```

使用Hadoop的ls命令，即

```
hadoop fs -ls
```
显示结果如图1所示,只是我的文件比较少。
![这里写图片描述](http://img.blog.csdn.net/20171104154344935?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbTBfMzc2Mzk1NDI=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 2 .获取文件

获取文件包含两层意思，一是HDFS从本地文件中获取文件，即前面介绍的添加文件；二是本地文件从HDFS中获取文件，可以使用Hadoop的get命令。例如若本地文件没有README.txt文件，需要从HDFS中取回，可以执行如下命令。

```
hadoop fs -get  ceshi.txt  .
```

或者

```
hadoop fs -get ceshi.txt  /usr/xxx/xxxx/ceshi.txt
```

### 3. 删除文件

Hadoop删除文件命令为rm。例如要删除从本地文件上传的README.txt，可以执行如下命令。

```
hadoop  fs -rm  ceshi.txt
```

### 4.检索文件

检索文件即查阅HDFS中的文件内容，可以使用hadoop中的cat命令。例如要查阅README.txt的内容，可以执行如下命令。

```
hadoop fs -cat ceshi.txt
```



另外，hadoop的cat命令的输出也可以使用管道传递给Unix 命令的head：

```
hadoop fs -cat ceshi.txt | head
```

Hadoop也支持tail命令查看最后一千字节。例如要查阅README.txt最后一千个字节，可以执行如下命令。

```

hadoop fs -tail ceshi.txt
```

### 5.查阅帮助

查阅Hadoop命令帮助，可以让我们很好地掌握和使用Hadoop的 命令。我们可以执行hadoop fs 获取所用版本Hadoop的一个完整命令列别，也可以使用help来显示某个具体命令的用法及简短描述。

例如，要了解ls命令，可执行如下命令。

```
hadoop  fs -help ls
```

这是经常使用到的。

-----
#开始实验
##实验一：读取文件


```
package hadoop.panpan.com;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import java.io.IOException;
public class HDFSAPI {
    //列出文件
    public void read(){
        //1.连接HDFS系统
        /*
        * 创建一个Configuration对象时，其构造方法会默认加载SRC下两个配置文件，分别是hdfs-site.xml以及core-site.xml，
        * 这两个文件中会有访问hdfs所需的参数值，主要是fs.default.name，指定了hdfs的地址，有了这个地址客户端就可以通过这个地址访问hdfs了。
        * */
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.142.130:9000");
        //2.创建文件系统对象

        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.设置读取文件的路径

        String fileAddr = "/user/TestPanpan/mapreduce/wordcount/input/my.input";
        Path filepath = new Path(fileAddr);

        //4.生成InputStream
        FSDataInputStream inputStream = null;
        try {
            inputStream = fileSystem.open(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读
        try {
            IOUtils.copyBytes(
                    inputStream, System.out, 4096, false
            );
        } catch (Exception e) {

        } finally {
            //手动关闭流
            IOUtils.closeStream(inputStream);
        }
    }
    public void copy(){
    }
}
```
可以看到的下面的文字就说明测试成功！！
![这里写图片描述](http://img.blog.csdn.net/20171104154857795?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbTBfMzc2Mzk1NDI=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
##实验二：上传文件

```
 public static void uploadFileToHDFS(String filePath, String dst) throws Exception {
        //创建一个文件系统
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path(filePath);
        Path dstPath = new Path(dst);
        Long start = System.currentTimeMillis();
        fs.copyFromLocalFile(false, srcPath, dstPath);
        System.out.println("Time:" + (System.currentTimeMillis() - start));

        System.out.println("________________________Upload to " + conf.get("fs.default.name") + "________________________");
        fs.close();
        getDirectoryFromHdfs(dst);
    }

```
结果如下图：
使用

```
#hadoop fs -ls user/TestPanpan/mapreduce/wordcount/input//也就是你自己定义的上传的路径
```
![这里写图片描述](http://img.blog.csdn.net/20171104160056119?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbTBfMzc2Mzk1NDI=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20171104160106375?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbTBfMzc2Mzk1NDI=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

##实验三：列出文件目录
遍历指定目录(direPath)下的所有文件
```
public static void getDirectoryFromHdfs(String direPath) throws Exception {

        FileSystem fs = FileSystem.get(URI.create(direPath), conf);
        FileStatus[] filelist = fs.listStatus(new Path(direPath));
        for (int i = 0; i < filelist.length; i++) {
            System.out.println("_________________***********************____________________");
            FileStatus fileStatus = filelist[i];
            System.out.println("Name:" + fileStatus.getPath().getName());
            System.out.println("size:" + fileStatus.getLen());
            System.out.println("_________________***********************____________________");
        }
        fs.close();
    }
```
执行结果：

![这里写图片描述](http://img.blog.csdn.net/20171104160848532?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbTBfMzc2Mzk1NDI=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)






欢迎交流！panpan668706




