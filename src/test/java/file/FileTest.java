package file;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhoum on 2018/1/5.
 */
public class FileTest {
    @Test
    public void deleteOnExit() throws IOException {
        File file = File.createTempFile("HelloFile", ".txt");
        System.out.println(file.getName());
        if (!file.exists()) {
//            file.deleteOnExit();
        } else {
            System.out.println("不存在该文件");
        }
    }


    @Test
    public void delete() throws IOException {
        File file = new File("HelloFile.txt");
        if (!file.exists()) {
            file.delete();
            file.createNewFile();
        } else {


            System.out.println("不存在该文件");
        }
    }

    @Test
    public void pathsTest() {
        Path path = Paths.get(".");
        System.out.println(path.getNameCount());
        System.out.println(path.getRoot());
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println(absolutePath.getRoot());
        System.out.println(absolutePath.getNameCount());
        System.out.println(absolutePath.getName(3));
    }

    @Test
    public void filesTest() throws IOException {
        Files.copy(Paths.get("zjw.txt"), new FileOutputStream("zjw.java"));
        List<String> lines = Files.readAllLines(Paths.get("zjw.txt"));
        System.out.println(lines.size());
        byte[] bytes = Files.readAllBytes(Paths.get("zjw.txt"));
        String s = new String(bytes);
        System.out.println(s);
        System.out.println(Files.size(Paths.get("zjw.txt")));
        List<String> poem = new ArrayList<>();
        poem.add("伟哥");
        poem.add("伟哥2");
        Files.write(Paths.get("poem.txt"), poem, Charset.forName("utf-8"));
        Files.list(Paths.get(".")).forEach(System.out::println);
        Files.lines(Paths.get("poem.txt")).forEach(System.out::println);
        FileStore fileStore = Files.getFileStore(Paths.get("c:"));
        System.out.println(fileStore.getTotalSpace());
        System.out.println(fileStore.getUsableSpace());
    }

    @Test
    public void fileVisitorTest() throws IOException {
        Files.walkFileTree(Paths.get("D:", "work", "project"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问" + file + "文件");
                if (file.endsWith("zjw.java")) {
                    System.out.println("已经找到目标文件");
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问" + dir + "路劲");
                return FileVisitResult.CONTINUE;
            }
        });
    }


    @Test
    public void watchServiceTest() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Paths.get("d:/").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        while (true) {
            WatchKey take = watchService.take();
            for (WatchEvent<?> item : take.pollEvents()) {
                System.out.println(item.context() + "文件发生了" + item.kind() + "事件");
            }
            if (!take.reset()) {
                break;
            }
        }
    }

    @Test
    public void attributeViewTest() throws IOException {
        Path testPath = Paths.get("zjw.txt");
        BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        BasicFileAttributes basicAttributes = basicView.readAttributes();
        System.out.println("创建时间:" + new Date(basicAttributes.creationTime().toMillis()));
        System.out.println("最后访问时间:" + new Date(basicAttributes.lastAccessTime().toMillis()));
        System.out.println("最后修改时间:" + new Date(basicAttributes.lastModifiedTime().toMillis()));
        System.out.println("文件大小:" + basicAttributes.size());

        FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);
        //获取改文件所属用户
        System.out.println("获取改文件所属用户:" + ownerView.getOwner());
//        UserPrincipal guest = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
//        ownerView.setOwner(guest);
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath, UserDefinedFileAttributeView.class);
        List<String> attrNames = userView.list();
        for (String name : attrNames) {
            ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
            userView.read(name, buf);
            buf.flip();
            System.out.println(name + "--->" + Charset.defaultCharset().decode(buf).toString());
        }
    }

}
