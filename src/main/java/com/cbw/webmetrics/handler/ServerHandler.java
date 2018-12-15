package com.cbw.webmetrics.handler;

import com.cbw.webmetrics.beans.db.ServerPerformanceBean;
import com.cbw.webmetrics.beans.db.UserProjectBean;
import com.cbw.webmetrics.utils.DBUtil;
import com.cbw.webmetrics.utils.DateUtil;
import com.sun.management.OperatingSystemMXBean;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * use TimerTask to monitor user server performance
 * every 1 minutes
 */
public class ServerHandler {

    private static String osName = System.getProperty("os.name");
    private static final int CPUTIME = 500;
    private static final int PERCENT = 100;
    private static final int FAULTLENGTH = 10;

    /**
     * use thread pool to schedule method every 1 minute-
     *
     * @param projectId
     */
    public static synchronized void handle(String projectId) {
        try {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            TimerTask coreTask = new TimerTask() {
                @Override
                public void run() {
                    checkServerPerformanceAndWarn(Integer.parseInt(projectId));
                }
            };
            scheduler.scheduleAtFixedRate(coreTask, 1, 1, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("ServerHandler").warning("handle server performance error, projectId is: " + projectId + ", time is: " + DateUtil.getUserTimeMin() + e);
        }
    }

    /**
     * get ServerPerformanceBean now
     *
     * @param projectId
     * @return
     */
    private static void checkServerPerformanceAndWarn(int projectId) {
        String timeMin = DateUtil.getUserTimeMin();
        double cpuUsage = getCpuUsage();
        double memUsage = getMemUsage();
        double diskUsage = getDiskUsage();

        UserProjectBean userProjectBean = DBUtil.getUserProjectInfo(projectId);
        ServerPerformanceBean serverPerformanceBean = new ServerPerformanceBean(projectId, timeMin, userProjectBean.getIfCpuNeedWarn(),
                userProjectBean.getIfMemNeedWarn(), userProjectBean.getIfDiskNeedWarn(), userProjectBean.getCpuWarnNum(),
                userProjectBean.getMemWarnNum(), userProjectBean.getDiskWarnNum(), cpuUsage, memUsage, diskUsage);

        if ("no".equals(serverPerformanceBean.getIfCpuNeedWarn())) {
            serverPerformanceBean.setIfCpuWarned("no");
        } else {
            serverPerformanceBean.setIfCpuWarned(cpuUsage > serverPerformanceBean.getCpuWarnNum() ? "yes" : "no");
        }
        if ("no".equals(serverPerformanceBean.getIfMemNeedWarn())) {
            serverPerformanceBean.setIfMemWarned("no");
        } else {
            serverPerformanceBean.setIfMemWarned(memUsage > serverPerformanceBean.getMemWarnNum() ? "yes" : "no");
        }
        if ("no".equals(serverPerformanceBean.getIfDiskNeedWarn())) {
            serverPerformanceBean.setIfDiskWarned("no");
        } else {
            serverPerformanceBean.setIfDiskWarned(diskUsage > serverPerformanceBean.getDiskWarnNum() ? "yes" : "no");
        }

        checkServerWarn(userProjectBean, serverPerformanceBean);
        DBUtil.saveSysPerformanceToDB(serverPerformanceBean, userProjectBean);
    }

    /**
     * check if need warn and warn work
     *
     * @param userProjectBean
     * @param serverPerformanceBean
     */
    private static void checkServerWarn(UserProjectBean userProjectBean, ServerPerformanceBean serverPerformanceBean) {
        if ("yes".equals(serverPerformanceBean.getIfCpuWarned()) || "yes".equals(serverPerformanceBean.getIfMemWarned()) || "yes".equals(serverPerformanceBean.getIfDiskWarned())) {
            String phone = userProjectBean.getPhone();
            String email = DBUtil.getUserInfoByPhone(phone);
            String message = "<<<<" + serverPerformanceBean.toString() + " >>>>";
            MessageHandler.sendMessage(phone, message);
            MailHandler.sendEmail(email, message);
        }
    }

    /**
     * get disk usage of Windows/Linux
     */
    public static double getDiskUsage() {
        try {
            double totalHD = 0;
            double usedHD = 0;
            if (osName.toLowerCase().contains("windows") || osName.toLowerCase().contains("win")) {
                long allTotal = 0;
                long allFree = 0;
                for (char c = 'A'; c <= 'Z'; c++) {
                    String dirName = c + ":/";
                    File win = new File(dirName);
                    if (win.exists()) {
                        long total = (long) win.getTotalSpace();
                        long free = (long) win.getFreeSpace();
                        allTotal = allTotal + total;
                        allFree = allFree + free;
                    }
                }
                double precent = (1 - allFree * 1.0 / allTotal) * 100;
                BigDecimal b1 = new BigDecimal(precent);
                precent = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                return precent;
            } else {
                Runtime rt = Runtime.getRuntime();
                // df -hl View hard disk space
                Process p = null;
                try {
                    p = rt.exec("df -hl");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String str = null;
                    String[] strArray = null;
                    while ((str = in.readLine()) != null) {
                        int m = 0;
                        strArray = str.split(" ");
                        for (String tmp : strArray) {
                            if (tmp.trim().length() == 0)
                                continue;
                            ++m;
                            if (tmp.contains("G")) {
                                if (m == 2) {
                                    if (!tmp.equals("") && !tmp.equals("0"))
                                        totalHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1)) * 1024;
                                }
                                if (m == 3) {
                                    if (!tmp.equals("none") && !tmp.equals("0"))
                                        usedHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1)) * 1024;
                                }
                            }
                            if (tmp.contains("M")) {
                                if (m == 2) {
                                    if (!tmp.equals("") && !tmp.equals("0"))
                                        totalHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1));
                                }
                                if (m == 3) {
                                    if (!tmp.equals("none") && !tmp.equals("0"))
                                        usedHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1));
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // keep 2 decimal places.
                double precent = (usedHD / totalHD) * 100;
                BigDecimal b1 = new BigDecimal(precent);
                precent = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                return precent;
            }
        } catch (Exception e) {
            Logger.getLogger("ServerHandler").warning("get server disk error, time is: " + DateUtil.getUserTimeMin() + e);
            return 0.0;
        }
    }

    /**
     * get memory usage of Windows/Linux
     */
    public static double getMemUsage() {
        try {
            if (osName.toLowerCase().contains("windows") || osName.toLowerCase().contains("win")) {
                try {
                    OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                    // Total physical memory + virtual memory
                    long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
                    // Remaining physical memory
                    long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
                    Double usage = (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
                    BigDecimal b1 = new BigDecimal(usage);
                    return b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                InputStreamReader inputs = null;
                BufferedReader buffer = null;
                try {
                    inputs = new InputStreamReader(new FileInputStream("/proc/meminfo"));
                    buffer = new BufferedReader(inputs);
                    String line = "";
                    while (true) {
                        line = buffer.readLine();
                        if (line == null)
                            break;
                        int beginIndex = 0;
                        int endIndex = line.indexOf(":");
                        if (endIndex != -1) {
                            String key = line.substring(beginIndex, endIndex);
                            beginIndex = endIndex + 1;
                            endIndex = line.length();
                            String memory = line.substring(beginIndex, endIndex);
                            String value = memory.replace("kB", "").trim();
                            map.put(key, value);
                        }
                    }

                    long memTotal = Long.parseLong(map.get("MemTotal").toString());
                    long memFree = Long.parseLong(map.get("MemFree").toString());
                    long memused = memTotal - memFree;
                    long buffers = Long.parseLong(map.get("Buffers").toString());
                    long cached = Long.parseLong(map.get("Cached").toString());

                    double usage = (double) (memused - buffers - cached) / memTotal * 100;
                    BigDecimal b1 = new BigDecimal(usage);
                    return b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (buffer != null) {
                            buffer.close();
                        }
                        if (inputs != null) {
                            inputs.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

            }
        } catch (Exception e) {
            Logger.getLogger("ServerHandler").warning("get server memory error, time is: " + DateUtil.getUserTimeMin() + e);
            return 0.0;
        }
        return 0.0;
    }

    /**
     * get cpu usage of Windows/Linux
     */
    public static double getCpuUsage() {
        try {
            // if Windows
            if (osName.toLowerCase().contains("windows")
                    || osName.toLowerCase().contains("win")) {
                try {
                    String procCmd = System.getenv("windir") + "//system32//wbem//wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
                    // get process info, first time to read cpu
                    long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
                    //sleep 500ms
                    Thread.sleep(CPUTIME);
                    //second time to read cpu
                    long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
                    if (c0 != null && c1 != null) {
                        long idletime = c1[0] - c0[0];  //idle time
                        long busytime = c1[1] - c0[1];  //sleep time
                        Double cpusage = PERCENT * (busytime) * 1.0 / (busytime + idletime);
                        BigDecimal b1 = new BigDecimal(cpusage);
                        return b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    } else {
                        return 0.0;
                    }
                } catch (Exception ex) {
                    return 0.0;
                }
            } else {
                try {
                    Map<?, ?> map1 = cpuinfo();
                    Thread.sleep(CPUTIME);
                    Map<?, ?> map2 = cpuinfo();

                    long user1 = Long.parseLong(map1.get("user").toString());
                    long nice1 = Long.parseLong(map1.get("nice").toString());
                    long system1 = Long.parseLong(map1.get("system").toString());
                    long idle1 = Long.parseLong(map1.get("idle").toString());

                    long user2 = Long.parseLong(map2.get("user").toString());
                    long nice2 = Long.parseLong(map2.get("nice").toString());
                    long system2 = Long.parseLong(map2.get("system").toString());
                    long idle2 = Long.parseLong(map2.get("idle").toString());

                    long total1 = user1 + system1 + nice1;
                    long total2 = user2 + system2 + nice2;
                    float total = total2 - total1;

                    long totalIdle1 = user1 + nice1 + system1 + idle1;
                    long totalIdle2 = user2 + nice2 + system2 + idle2;
                    float totalidle = totalIdle2 - totalIdle1;

                    float cpusage = (total / totalidle) * 100;

                    BigDecimal b1 = new BigDecimal(cpusage);
                    return b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Logger.getLogger("ServerHandler").warning("get server cpu error, time is: " + DateUtil.getUserTimeMin() + e);
            return 0.0;
        }
        return 0.0;
    }

    /**
     * get cpu info of Windows System
     *
     * @param proc
     * @return
     */
    private static long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < FAULTLENGTH) {
                return null;
            }
            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0;
            long kneltime = 0;  //time to read physical equipment
            long usertime = 0;  //time to execute code
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {
                    continue;
                }
                // Field occurrence order：Caption,CommandLine,KernelModeTime,ReadOperationCount
                String caption = substring(line, capidx, cmdidx - 1).trim();
                String cmd = substring(line, cmdidx, kmtidx - 1).trim();
                if (cmd.contains("wmic.exe")) {
                    continue;
                }
                String s1 = substring(line, kmtidx, rocidx - 1).trim();
                String s2 = substring(line, umtidx, wocidx - 1).trim();
                List<String> digitS1 = getDigit(s1);
                List<String> digitS2 = getDigit(s2);

                if (caption.equals("System Idle Process") || caption.equals("System")) {
                    if (s1.length() > 0) {
                        if (!digitS1.get(0).equals("") && digitS1.get(0) != null) {
                            idletime += Long.valueOf(digitS1.get(0));
                        }
                    }
                    if (s2.length() > 0) {
                        if (!digitS2.get(0).equals("") && digitS2.get(0) != null) {
                            idletime += Long.valueOf(digitS2.get(0));
                        }
                    }
                    continue;
                }
                if (s1.length() > 0) {
                    if (!digitS1.get(0).equals("") && digitS1.get(0) != null) {
                        kneltime += Long.valueOf(digitS1.get(0));
                    }
                }

                if (s2.length() > 0) {
                    if (!digitS2.get(0).equals("") && digitS2.get(0) != null) {
                        kneltime += Long.valueOf(digitS2.get(0));
                    }
                }
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;

            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * get cpu info of Linux System
     */
    private static Map<?, ?> cpuinfo() {
        InputStreamReader inputs = null;
        BufferedReader buffer = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            inputs = new InputStreamReader(new FileInputStream("/proc/stat"));
            buffer = new BufferedReader(inputs);
            String line = "";
            while (true) {
                line = buffer.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("cpu")) {
                    StringTokenizer tokenizer = new StringTokenizer(line);
                    List<String> temp = new ArrayList<String>();
                    while (tokenizer.hasMoreElements()) {
                        String value = tokenizer.nextToken();
                        temp.add(value);
                    }
                    map.put("user", temp.get(1));
                    map.put("nice", temp.get(2));
                    map.put("system", temp.get(3));
                    map.put("idle", temp.get(4));
                    map.put("iowait", temp.get(5));
                    map.put("irq", temp.get(6));
                    map.put("softirq", temp.get(7));
                    map.put("stealstolen", temp.get(8));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert buffer != null;
                buffer.close();
                inputs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return map;
    }

    /**
     * get number from text
     */
    private static List<String> getDigit(String text) {
        List<String> digitList = new ArrayList<String>();
        digitList.add(text.replaceAll("\\D", ""));
        return digitList;
    }

    /**
     * As String.subString() has problems in the processing of Chinese characters(treating a Chinese character as a
     * byte), there are hidden dangers in the string containing Chinese characters. Now we adjust it as follows:
     *
     * @param src       string to intercept
     * @param start_idx start coordinates (including those coordinates)
     * @param end_idx   cut-off coordinates (including those coordinates)
     * @return
     */
    private static String substring(String src, int start_idx, int end_idx) {
        byte[] b = src.getBytes();
        String tgt = "";
        for (int i = start_idx; i <= end_idx; i++) {
            tgt += (char) b[i];
        }
        return tgt;
    }
}