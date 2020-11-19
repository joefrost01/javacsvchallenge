package csvchallenge;

import java.util.concurrent.Callable;

public class TimeIt {

    public static <T> T printTime(Callable<T> task) {
        T call = null;
        try {
            long startTime = CPUUtils.getCpuTime();
            call = task.call();
            System.out.println((CPUUtils.getCpuTime() - startTime) / 1000000000d + "s");
        } catch (Exception e) {
            //...
        }
        return call;
    }
}
