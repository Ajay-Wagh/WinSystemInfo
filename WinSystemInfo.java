import java.io.*;

public class WinSystemInfo {
    private final static String cpucmd = "typeperf -sc 4 \"\\Processor(_Total)\\% Processor Time\"";
    private final static String sysinfo = "systeminfo";
    private final static String diskuse = "typeperf -sc 1 \"\\LogicalDisk(*)\\Disk Transfers/sec\"";
    private final static String netuse = "typeperf -sc 1 \"\\Network Interface(*)\\Bytes Total/sec\"";

    double getCPU() {
        Process p;
        BufferedReader cmdoutputreader;
        String s="";
        try {
            p = Runtime.getRuntime().exec(cpucmd);
            cmdoutputreader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String temp;
            while ((temp = cmdoutputreader.readLine()) != null) {
                cmdoutputreader.readLine();
                s = s + temp;
            }
            s=s.substring(0,s.length()-49);
            String[] str=s.split("\"");
            s=str[str.length-1];
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(s);
        return Double.parseDouble(s);
    }

    String[] getConstantData()
    {
        Process p;
        BufferedReader cmdoutputreader;
        String[] str=new String[5];
        try {
            p = Runtime.getRuntime().exec(sysinfo);
            cmdoutputreader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            cmdoutputreader.readLine();
            String temp=cmdoutputreader.readLine();
            temp=temp.substring(12);
            temp=temp.trim();
            str[0]=temp;
            temp=cmdoutputreader.readLine();
            temp=temp.substring(12);
            temp=temp.trim();
            str[1]=temp;
            temp=cmdoutputreader.readLine();
            temp=temp.substring(13);
            temp=temp.trim();
            str[2]=temp;
            for(int i=0;i<9;i++)
                temp=cmdoutputreader.readLine();
            temp=temp.substring(22);
            temp=temp.trim();
            str[3]=temp;
            temp=cmdoutputreader.readLine();
            temp=temp.substring(14);
            temp=temp.trim();
            str[3]=str[3]+" "+temp;
            for (int i=0;i<11;i++)
                temp=cmdoutputreader.readLine();
            temp=temp.substring(24);
            temp=temp.trim();
            temp=temp.replaceAll(",","");
            temp=temp.substring(0,4);
            str[4]=temp;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(str);
        return str;
    }

    int getAvailableMemory()
    {
        Process p;
        BufferedReader cmdoutputreader;
        String temp="";
        try {
            p = Runtime.getRuntime().exec(sysinfo);
            cmdoutputreader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            for(int i=0;i<26;i++)
                temp=cmdoutputreader.readLine();
            temp=temp.substring(27);
            temp=temp.replaceAll(",","");
            temp=temp.substring(0,4);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(temp);
        return Integer.parseInt(temp);
    }

    double getDisk()
    {
        Process p;
        BufferedReader cmdoutputreader;
        String temp="";
        try {
            p = Runtime.getRuntime().exec(diskuse);
            cmdoutputreader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            for (int i=0;i<3;i++)
                temp=cmdoutputreader.readLine();
            temp=temp.substring(27);
            String[] str=temp.split("\",\"");
            temp=str[4];
            temp=temp.replaceAll("\"","");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(temp);
        return Double.parseDouble(temp);
    }

    double getNetwork()
    {
        Process p;
        BufferedReader cmdoutputreader;
        String temp="";
        try {
            p = Runtime.getRuntime().exec(netuse);
            cmdoutputreader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            for (int i=0;i<3;i++)
                temp=cmdoutputreader.readLine();
            temp=temp.substring(27);
            String[] str=temp.split("\",\"");
            temp=str[4];
            temp=temp.replaceAll("\"","");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(temp);
        return Double.parseDouble(temp);
    }


}
