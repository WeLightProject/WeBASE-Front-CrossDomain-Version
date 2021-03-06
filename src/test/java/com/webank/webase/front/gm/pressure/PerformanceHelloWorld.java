package com.webank.webase.front.gm.pressure;

import com.google.common.util.concurrent.RateLimiter;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class PerformanceHelloWorld {
  public static Logger logger = LoggerFactory.getLogger(PerformanceHelloWorld.class);
  private static AtomicInteger sended = new AtomicInteger(0);

  public static void main(String[] args) throws Exception {
    try {
      String groupId = args[3];
      ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//      Service service = context.getBean(Service.class);
//      service.setGroupId(Integer.parseInt(groupId));
//      service.run();
//
//      System.out.println("Start test...");
//      System.out.println("===================================================================");
//
//      ChannelEthereumService channelEthereumService = new ChannelEthereumService();
//      channelEthereumService.setChannelService(service);
//
//      Web3AsyncThreadPoolSize.web3AsyncCorePoolSize = 3000;
//      Web3AsyncThreadPoolSize.web3AsyncPoolSize = 2000;
//
//      ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(500);
//      Web3j web3 = Web3j.build(channelEthereumService, 15 * 100, scheduledExecutorService, Integer.parseInt(groupId));
//
//      Credentials credentials = Credentials.create("b83261efa42895c38c6c2364ca878f43e77f3cddbc922bf57d0d48070f79feb6");

      BigInteger gasPrice = new BigInteger("30000000");
      BigInteger gasLimit = new BigInteger("30000000");

      String command = args[0];
      Integer count = 0;
      Integer qps = 0;

      switch (command) {
      case "trans":
        count = Integer.parseInt(args[1]);
        qps = Integer.parseInt(args[2]);
       // break;
      default:
        System.out.println("Args: <trans> <Total> <QPS>");
      }

      ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
      threadPool.setCorePoolSize(200);
      threadPool.setMaxPoolSize(500);
      threadPool.setQueueCapacity(count);

      threadPool.initialize();

      System.out.println("Deploying contract...");
//      HelloWorldGM helloWorldGM = HelloWorldGM.deploy(web3, credentials, gasPrice, gasLimit).send();

      PerformanceCollector collector = new PerformanceCollector();
      collector.setTotal(count);

      RateLimiter limiter = RateLimiter.create(qps);
      Integer area = count / 10;
      final Integer total = count;

      System.out.println("Start test???total???" + count);
      for (Integer i = 0; i < count; ++i) {
//        threadPool.execute(new Runnable() {
//          @Override
//          public void run() {
//            limiter.acquire();
//            PerformanceHelloWorldCallback callback = new PerformanceHelloWorldCallback();
//            callback.setCollector(collector);
//            try {
//              helloWorldGM.set("4", callback);
//            } catch (Exception e) {
//              TransactionReceipt receipt = new TransactionReceipt();
//              receipt.setStatus("-1");
//
//              callback.onResponse(receipt);
//              logger.info(e.getMessage());
//            }
//
//            int current = sended.incrementAndGet();
//
//            if (current >= area && ((current % area) == 0)) {
//              System.out.println("Already sended: " + current + "/" + total + " transactions");
//            }
//          }
//        });
      }
    } catch (Exception e) {
      e.printStackTrace();
//      System.exit(-1);
    }
  }


//  @Test
//  public  void main() throws InterruptedException {
//    System.out.println("&&&&&&&&&&&&&&&&&&");
//    try {
//      String groupId = "1";
//      ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//      Service service = context.getBean(Service.class);
//      service.setGroupId(Integer.parseInt(groupId));
//      service.run();
//
//      System.out.println("Start test...");
//      System.out.println("===================================================================");
//
//      ChannelEthereumService channelEthereumService = new ChannelEthereumService();
//      channelEthereumService.setChannelService(service);
//
//      Web3AsyncThreadPoolSize.web3AsyncCorePoolSize = 3000;
//      Web3AsyncThreadPoolSize.web3AsyncPoolSize = 2000;
//
//      ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(500);
//      Web3j web3 = Web3j.build(channelEthereumService, 15 * 100, scheduledExecutorService, Integer.parseInt(groupId));
//
//      Credentials credentials = GenCredential.create("b83261efa42895c38c6c2364ca878f43e77f3cddbc922bf57d0d48070f79feb6");
//
//      BigInteger gasPrice = new BigInteger("3");
//      BigInteger gasLimit = new BigInteger("30000000");
//
//      Integer count = 0;
//      Integer qps = 0;
//
//        count = Integer.parseInt("10000");
//        qps = Integer.parseInt("1000");
//
//
//      ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
//      threadPool.setCorePoolSize(2000);
//      threadPool.setMaxPoolSize(2000);
//      threadPool.setQueueCapacity(count);
//
//      threadPool.initialize();
//
//      System.out.println("Deploying contract...");
//      HelloWorldGM helloWorldGM = HelloWorldGM.deploy(web3, credentials, gasPrice, gasLimit).send();
//
//      PerformanceCollector collector = new PerformanceCollector();
//      collector.setTotal(count);
//
//      RateLimiter limiter = RateLimiter.create(qps);
//      Integer area = count / 10;
//      final Integer total = count;
//
//      System.out.println("Start test???total???" + count);
//      for (Integer i = 0; i < count; ++i) {
//        threadPool.execute(new Runnable() {
//          @Override
//          public void run() {
//            limiter.acquire();
//            PerformanceHelloWorldCallback callback = new PerformanceHelloWorldCallback();
//            callback.setCollector(collector);
//            try {
//                int i = new Random().nextInt(1000000);
//              helloWorldGM.set("4"+ i , callback);
//            } catch (Exception e) {
//              TransactionReceipt receipt = new TransactionReceipt();
//              receipt.setStatus("-1");
//
//              callback.onResponse(receipt);
//              logger.info(e.getMessage());
//            }
//
//            int current = sended.incrementAndGet();
//
//            if (current >= area && ((current % area) == 0)) {
//              System.out.println("Already sended: " + current + "/" + total + " transactions");
//            }
//          }
//        });
//      }
//      assertTrue(count>0);
//    } catch (Exception e) {
//      e.printStackTrace();
//    //  System.exit(-1);
//    }
//
//    Thread.sleep(100000);
//
//  }




}
