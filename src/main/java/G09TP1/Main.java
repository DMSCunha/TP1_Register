package G09TP1;


import io.grpc.ServerBuilder;

public class Main {

    protected static int svcPort = 8500;

    public static void main(String[] args) {
        try {
            ServerPhoto serverPhoto = new ServerPhoto();
            io.grpc.Server svc = ServerBuilder
                    .forPort(svcPort)
                    .addService(new ServiceContract())
                    .addService(new ClientContract(serverPhoto))
                    .build();
            svc.start();
            System.out.println("Server started, listening on " + svcPort);
            //Scanner scan = new Scanner(System.in);
            //scan.nextLine();
            svc.awaitTermination();
            svc.shutdown();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}