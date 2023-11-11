package G09TP1;


import io.grpc.ServerBuilder;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private final static int svcPort = 8500;

    public static void main(String[] args) {
        try {
            ServerPhoto serverPhoto = new ServerPhoto();

            ReentrantLock lock = new ReentrantLock();
            io.grpc.Server svc = ServerBuilder
                    .forPort(svcPort)
                    .addService(new ServiceContract(serverPhoto,lock))
                    .addService(new ClientContract(serverPhoto,lock))
                    .build();
            svc.start();
            System.out.println("Server started, listening on " + svcPort);

            Scanner scan = new Scanner(System.in);
            while(!scan.nextLine().isBlank())
                serverPhoto.printAllServer();

            svc.awaitTermination();
            svc.shutdown();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}