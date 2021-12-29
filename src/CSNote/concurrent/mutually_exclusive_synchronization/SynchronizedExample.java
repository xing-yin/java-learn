package CSNote.concurrent.mutually_exclusive_synchronization;

/**
 * synchronized
 *
 * @author yinxing
 * @date 2019/2/20
 */

public class SynchronizedExample {

    // 1. 同步一个代码块
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    // 2. 同步一个方法
    public synchronized void func2() {
        // ...
    }

    // 3.同步一个类
    // 作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
    public void func3() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    // 4. 同步一个静态方法
    // 作用于整个类
    public synchronized static void func4(){
        // ...
    }

//    public static void main(String[] args) {
//        /**
//         * 它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
//         *
//         对于以下代码，使用 ExecutorService 执行了两个线程，由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，当一个线程进入同步语句块时，另一个线程就必须等待。
//         */
////        ExecutorService executorService = Executors.newCachedThreadPool();
////        SynchronizedExample se1 = new SynchronizedExample();
////        executorService.execute(() -> se1.func1());
////        executorService.execute(() -> se1.func1());
//
//        /**
//         * 对于以下代码，两个线程调用了不同对象的同步代码块，因此这两个线程就不需要同步。从输出结果可以看出，两个线程可能交叉执行。
//         */
////        SynchronizedExample se2 = new SynchronizedExample();
////        SynchronizedExample se3 = new SynchronizedExample();
////        ExecutorService executorService2 = Executors.newCachedThreadPool();
////        executorService2.execute(() -> se2.func1());
////        executorService2.execute(() -> se3.func1());
//
//        SynchronizedExample e1 = new SynchronizedExample();
//        SynchronizedExample e2 = new SynchronizedExample();
//        ExecutorService executorService3 = Executors.newCachedThreadPool();
//        executorService3.execute(()->e1.func3());
//        executorService3.execute(()->e2.func3());
//    }


    public static void main(String[] args) {
        String text ="I1209 10:19:45.443269 48943 fetcher.cpp:533] Fetcher Info: {\"cache_directory\":\"\\/tmp\\/mesos\\/fetch\\/slaves\\/02a35bb2-bb2f-467d-9b78-f6f577d74544-S1\\/etl\",\"items\":[{\"action\":\"BYPASS_CACHE\",\"uri\":{\"cache\":false,\"executable\":true,\"extract\":false,\"value\":\"http:\\/\\/odep-merlion-api.k8s.uc.host.dxy\\/api\\/merlion\\/job\\/3771.sh\"}}],\"sandbox_directory\":\"\\/var\\/lib\\/mesos\\/slaves\\/02a35bb2-bb2f-467d-9b78-f6f577d74544-S1\\/frameworks\\/3e2ea5fc-4989-4256-87bf-271aa8f09688-0000\\/executors\\/1639016340000_system_1_1_odep_3771_job\\/runs\\/6a18023c-f92a-4734-939e-b2d9614b6dac\",\"user\":\"etl\"}\n" +
                "I1209 10:19:45.452972 48943 fetcher.cpp:444] Fetching URI 'http://odep-merlion-api.k8s.uc.host.dxy/api/merlion/job/3771.sh'\n" +
                "I1209 10:19:45.453002 48943 fetcher.cpp:285] Fetching directly into the sandbox directory\n" +
                "I1209 10:19:45.453040 48943 fetcher.cpp:222] Fetching URI 'http://odep-merlion-api.k8s.uc.host.dxy/api/merlion/job/3771.sh'\n" +
                "I1209 10:19:45.453066 48943 fetcher.cpp:165] Downloading resource from 'http://odep-merlion-api.k8s.uc.host.dxy/api/merlion/job/3771.sh' to '/var/lib/mesos/slaves/02a35bb2-bb2f-467d-9b78-f6f577d74544-S1/frameworks/3e2ea5fc-4989-4256-87bf-271aa8f09688-0000/executors/1639016340000_system_1_1_odep_3771_job/runs/6a18023c-f92a-4734-939e-b2d9614b6dac/3771.sh'\n" +
                "I1209 10:19:45.467365 48943 fetcher.cpp:582] Fetched 'http://odep-merlion-api.k8s.uc.host.dxy/api/merlion/job/3771.sh' to '/var/lib/mesos/slaves/02a35bb2-bb2f-467d-9b78-f6f577d74544-S1/frameworks/3e2ea5fc-4989-4256-87bf-271aa8f09688-0000/executors/1639016340000_system_1_1_odep_3771_job/runs/6a18023c-f92a-4734-939e-b2d9614b6dac/3771.sh'\n" +
                "I1209 10:19:45.576121 48961 exec.cpp:162] Version: 1.3.0\n" +
                "I1209 10:19:45.600986 48957 exec.cpp:237] Executor registered on agent 02a35bb2-bb2f-467d-9b78-f6f577d74544-S1\n" +
                "/usr/local/lib/python2.7/dist-packages/cryptography/hazmat/primitives/constant_time.py:26: CryptographyDeprecationWarning: Support for your Python version is deprecated. The next version of cryptography will remove support. Please upgrade to a 2.7.x release that supports hmac.compare_digest as soon as possible.\n" +
                "  utils.DeprecatedIn23,\n" +
                "/usr/local/lib/python2.7/dist-packages/urllib3/util/ssl_.py:339: SNIMissingWarning: An HTTPS request has been made, but the SNI (Subject Name Indication) extension to TLS is not available on this platform. This may cause the server to present an incorrect TLS certificate, which can cause validation failures. You can upgrade to a newer version of Python to solve this. For more information, see https://urllib3.readthedocs.io/en/latest/advanced-usage.html#ssl-warnings\n" +
                "  SNIMissingWarning\n" +
                "/usr/local/lib/python2.7/dist-packages/urllib3/util/ssl_.py:137: InsecurePlatformWarning: A true SSLContext object is not available. This prevents urllib3 from configuring SSL appropriately and may cause certain SSL connections to fail. You can upgrade to a newer version of Python to solve this. For more information, see https://urllib3.readthedocs.io/en/latest/advanced-usage.html#ssl-warnings\n" +
                "  InsecurePlatformWarning\n" +
                "/usr/local/lib/python2.7/dist-packages/urllib3/connectionpool.py:858: InsecureRequestWarning: Unverified HTTPS request is being made. Adding certificate verification is strongly advised. See: https://urllib3.readthedocs.io/en/latest/advanced-usage.html#ssl-warnings\n" +
                "  InsecureRequestWarning)\n" +
                "/usr/local/lib/python2.7/dist-packages/urllib3/util/ssl_.py:137: InsecurePlatformWarning: A true SSLContext object is not available. This prevents urllib3 from configuring SSL appropriately and may cause certain SSL connections to fail. You can upgrade to a newer version of Python to solve this. For more information, see https://urllib3.readthedocs.io/en/latest/advanced-usage.html#ssl-warnings\n" +
                "  InsecurePlatformWarning\n" +
                "/usr/local/lib/python2.7/dist-packages/urllib3/connectionpool.py:858: InsecureRequestWarning: Unverified HTTPS request is being made. Adding certificate verification is strongly advised. See: https://urllib3.readthedocs.io/en/latest/advanced-usage.html#ssl-warnings\n" +
                "  InsecureRequestWarning)\n" +
                "/opt/etl-jobs-new-etl/bin/sync_data.py:186: YAMLLoadWarning: calling yaml.load() without Loader=... is deprecated, as the default Loader is unsafe. Please read https://msg.pyyaml.org/load for full details.\n" +
                "  yml = yaml.load(read_file_from_hdfs(options.conf))\n" +
                "21/12/09 10:19:51 INFO sqoop.Sqoop: Running Sqoop version: 1.4.6-cdh5.13.1\n" +
                "21/12/09 10:19:53 INFO tool.BaseSqoopTool: Using Hive-specific delimiters for output. You can override\n" +
                "21/12/09 10:19:53 INFO tool.BaseSqoopTool: delimiters with --fields-terminated-by, etc.\n" +
                "21/12/09 10:19:53 INFO manager.MySQLManager: Preparing to use a MySQL streaming resultset.\n" +
                "21/12/09 10:19:53 INFO tool.CodeGenTool: Beginning code generation\n" +
                "21/12/09 10:19:53 INFO manager.SqlManager: Executing SQL statement: SELECT t.* FROM `jute_post_tag_v2` AS t LIMIT 1\n" +
                "21/12/09 10:19:53 ERROR manager.SqlManager: Error executing statement: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table 'jute.jute_post_tag_v2' doesn't exist\n" +
                "com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table 'jute.jute_post_tag_v2' doesn't exist\n" +
                "\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n" +
                "\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n" +
                "\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n" +
                "\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n" +
                "\tat com.mysql.jdbc.Util.handleNewInstance(Util.java:377)\n" +
                "\tat com.mysql.jdbc.Util.getInstance(Util.java:360)\n" +
                "\tat com.mysql.jdbc.SQLError.createSQLException(SQLError.java:978)\n" +
                "\tat com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3887)\n" +
                "\tat com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3823)\n" +
                "\tat com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:2435)\n" +
                "\tat com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2582)\n" +
                "\tat com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2530)\n" +
                "\tat com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1907)\n" +
                "\tat com.mysql.jdbc.PreparedStatement.executeQuery(PreparedStatement.java:2030)\n" +
                "\tat org.apache.sqoop.manager.SqlManager.execute(SqlManager.java:777)\n" +
                "\tat org.apache.sqoop.manager.SqlManager.execute(SqlManager.java:786)\n" +
                "\tat org.apache.sqoop.manager.SqlManager.getColumnInfoForRawQuery(SqlManager.java:289)\n" +
                "\tat org.apache.sqoop.manager.SqlManager.getColumnTypesForRawQuery(SqlManager.java:260)\n" +
                "\tat org.apache.sqoop.manager.SqlManager.getColumnTypes(SqlManager.java:246)\n" +
                "\tat org.apache.sqoop.manager.ConnManager.getColumnTypes(ConnManager.java:327)\n" +
                "\tat org.apache.sqoop.orm.ClassWriter.getColumnTypes(ClassWriter.java:1858)\n" +
                "\tat org.apache.sqoop.orm.ClassWriter.generate(ClassWriter.java:1657)\n" +
                "\tat org.apache.sqoop.tool.CodeGenTool.generateORM(CodeGenTool.java:106)\n" +
                "\tat org.apache.sqoop.tool.ImportTool.importTable(ImportTool.java:494)\n" +
                "\tat org.apache.sqoop.tool.ImportTool.run(ImportTool.java:621)\n" +
                "\tat org.apache.sqoop.Sqoop.run(Sqoop.java:147)\n" +
                "\tat org.apache.hadoop.util.ToolRunner.run(ToolRunner.java:70)\n" +
                "\tat org.apache.sqoop.Sqoop.runSqoop(Sqoop.java:183)\n" +
                "\tat org.apache.sqoop.Sqoop.runTool(Sqoop.java:234)\n" +
                "\tat org.apache.sqoop.Sqoop.runTool(Sqoop.java:243)\n" +
                "\tat org.apache.sqoop.Sqoop.main(Sqoop.java:252)\n" +
                "21/12/09 10:19:53 ERROR tool.ImportTool: Import failed: java.io.IOException: No columns to generate for ClassWriter\n" +
                "\tat org.apache.sqoop.orm.ClassWriter.generate(ClassWriter.java:1663)\n" +
                "\tat org.apache.sqoop.tool.CodeGenTool.generateORM(CodeGenTool.java:106)\n" +
                "\tat org.apache.sqoop.tool.ImportTool.importTable(ImportTool.java:494)\n" +
                "\tat org.apache.sqoop.tool.ImportTool.run(ImportTool.java:621)\n" +
                "\tat org.apache.sqoop.Sqoop.run(Sqoop.java:147)\n" +
                "\tat org.apache.hadoop.util.ToolRunner.run(ToolRunner.java:70)\n" +
                "\tat org.apache.sqoop.Sqoop.runSqoop(Sqoop.java:183)\n" +
                "\tat org.apache.sqoop.Sqoop.runTool(Sqoop.java:234)\n" +
                "\tat org.apache.sqoop.Sqoop.runTool(Sqoop.java:243)\n" +
                "\tat org.apache.sqoop.Sqoop.main(Sqoop.java:252)\n" +
                "\n" +
                "E1209 10:19:55.450552 48994 process.cpp:951] Failed to accept socket: future discarded";
        text = "ERROR manager.Sql";
        int index = text.indexOf("ERROR");
        System.out.println(text.substring(index));
    }

}
