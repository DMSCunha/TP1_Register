package G09TP1;

import ResgisterServiceStub.*;
import ResgisterServiceStub.Void;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.locks.ReentrantLock;

public class ServiceContract extends RegisterServiceGrpc.RegisterServiceImplBase {

    private final ServerPhoto server;
    private final ReentrantLock lock;

    public ServiceContract(ServerPhoto server, ReentrantLock lock){
        this.server = server;
        this.lock = lock;
    }


    //case 1
    //Servidor serviço N quer-se conectar ao register para registar-se
    //Register guarda o IP e porto para serem dados ao cliente
    //Recebe: address -> IP e porto
    //Devolve: Ack -> bool
    @Override
    public void connect(Address request, StreamObserver<Ack> responseObserver) {

        //obter server enviado
        ServerAddress address = new ServerAddress(request.getIp(), request.getPort());

        //sincronizar
        lock.lock();

        //adicionar servidor
        boolean done = server.addNewServer(address);

        //libertar
        lock.unlock();

        if(!done){
            responseObserver.onError(new Throwable("This server is already added"));
            return ;
        }

        //construir a resposta
        Ack ack = Ack.newBuilder()
                .setAck(true)
                .build();

        //enviar a resposta
        responseObserver.onNext(ack);
        responseObserver.onCompleted();
    }

    //case 1
    //Recebe void de um servidor de serviço
    //Retorna um bool
    @Override
    public void isAlive(Void request, StreamObserver<Status> responseObserver) {
        Status status = Status.newBuilder()
                .setStatus(true)
                .build();
        responseObserver.onNext(status);
        responseObserver.onCompleted();
    }

}
