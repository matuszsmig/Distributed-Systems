import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class ZookeeperApp {
    private ZooKeeper zooKeeper;
    private String znodePath = "/a";
    private Process externalAppProcess;
    private String externalAppCommand;

    public ZookeeperApp(String connectString, String externalAppCommand) throws IOException, KeeperException, InterruptedException {
        this.externalAppCommand = externalAppCommand;
        zooKeeper = new ZooKeeper(connectString, 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });
        checkNodeA();
    }

    private void checkNodeA() throws KeeperException, InterruptedException {
        if (zooKeeper.exists(znodePath, nodeAWatcher) != null) {
            startExternalApp();
            setChildWatcher();
        }
    }

    private Watcher nodeAWatcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeCreated) {
                startExternalApp();
                setChildWatcher();
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                stopExternalApp();
            }
            try {
                checkNodeA();
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private boolean isProcessRunning(String processName) {
        try {
            Process process = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void startExternalApp() {
        try {
            if (!isProcessRunning(externalAppCommand)) {
                externalAppProcess = new ProcessBuilder(externalAppCommand).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopExternalApp() {
        if (externalAppProcess != null) {
            externalAppProcess.destroy();
            try {
                externalAppProcess.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setChildWatcher() {
        try {
            zooKeeper.getChildren(znodePath, childrenWatcher);
            displayChildrenCount();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Watcher childrenWatcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeChildrenChanged) {
                displayChildrenCount();
                setChildWatcher();
            }
        }
    };

    private void displayChildrenCount() {
        try {
            int childrenCount = zooKeeper.getChildren(znodePath, false).size();
            System.out.println("Liczba potomków: " + childrenCount);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displayNode(String path, int level) {
        try {
            List<String> children = zooKeeper.getChildren(path, false);
            for (String child : children) {
                for (int i = 0; i < level; i++) {
                    System.out.print("--");
                }
                System.out.println(child);
                displayNode(path + "/" + child, level + 1);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        if (args.length < 1) {
            System.err.println("Please give external graphic app");
            System.exit(1);
        }

        String ip = "127.0.0.1";
        String port = "2183";
        String connectString = ip + ":" + port;
        String externalAppCommand = args[0];

        ZookeeperApp app = new ZookeeperApp(connectString, externalAppCommand);

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("ls")) {
                    app.displayNode(app.znodePath, 0);
                }
            }
        }).start();

        while (true) {
            Thread.sleep(1000);
        }
    }
}
