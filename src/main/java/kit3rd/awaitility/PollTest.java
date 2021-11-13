package kit3rd.awaitility;

import org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

public class PollTest {

    public static void main(String[] args) {
        Awaitility.await().pollInSameThread().atMost(30, TimeUnit.SECONDS)
                .pollInterval(10, TimeUnit.SECONDS)
                .pollDelay(0, TimeUnit.SECONDS)
                .until(() -> {
                    return false; // true will break the poll
                });
    }

}

