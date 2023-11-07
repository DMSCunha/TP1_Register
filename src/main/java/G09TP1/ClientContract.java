package G09TP1;


import io.grpc.stub.StreamObserver;
import registerclientstub.Address;
import registerclientstub.RegisterClientGrpc;
import registerclientstub.Void;

import java.util.concurrent.locks.ReentrantLock;

public class ClientContract extends RegisterClientGrpc.RegisterClientImplBase {

    private final ServerPhoto server;
    private final ReentrantLock lock;

    public ClientContract(ServerPhoto server, ReentrantLock lock){
        this.server = server;
        this.lock = lock;
    }

    //case 1
    //Cliente pede o IP e porto de um servidor de serviço
    //Register envia um dos servidores disponiveis
    //Recebe:
    //Devolve: Address -> IP e porto
    @Override
    public void getIP(Void request, StreamObserver<Address> responseObserver) {

        //sincronizar aqui
        lock.lock();

        //obter o endereço do próximo servidor a enviar
        ServerAddress serverAddress = server.getNextServer();

        //libertar
        lock.unlock();

        if(serverAddress == null){
            responseObserver.onError(new Throwable("Sorry, no service servers ON, try again later..."));
            return ;
        }

        //enviar a resposta
        sendIpPort(responseObserver, serverAddress.getIp(), serverAddress.getPort());
    }

    //case 1
    //Cliente avisa o Resgister que nao se consegue conectar à aquele IP e porto
    //Resgister devolve um novo IP e porto
    //Recebe: Address -> IP e porto
    //Devolve: Address -> IP e porto
    @Override
    public void errorGetAnotherIP(Address request, StreamObserver<Address> responseObserver) {

        //sincronizar aqui
        lock.lock();

        //guardar ip e porto recebido
        ///tratar do server com erro
        server.errorOnServer(new ServerAddress(request.getIp(), request.getPort()));

        //obter outro endereço de um servidor
        ServerAddress serverAddress = server.getNextServer();

        //libertar
        lock.unlock();

        if(serverAddress == null){
            responseObserver.onError(new Throwable("Sorry, no service servers ON, try again later..."));
            return ;
        }

        //enviar a resposta
        sendIpPort(responseObserver, serverAddress.getIp(), serverAddress.getPort());
    }

    private void sendIpPort(StreamObserver<Address> responseObserver, String ip, int port){
        //construir a resposta
        Address address = Address.newBuilder()
                .setIp(ip)
                .setPort(port)
                .build();

        //enviar a resposta
        responseObserver.onNext(address);
        responseObserver.onCompleted();
    }
}
