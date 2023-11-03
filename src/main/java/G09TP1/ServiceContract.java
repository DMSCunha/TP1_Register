package G09TP1;

import ResgisterServiceStub.*;
import ResgisterServiceStub.Void;
import io.grpc.stub.StreamObserver;

public class ServiceContract extends RegisterServiceGrpc.RegisterServiceImplBase {

    //case 1
    //Servidor serviço N quer-se conectar ao register para registar-se
    //Register guarda o IP e porto para serem dados ao cliente
    //Recebe: address -> IP e porto
    //Devolve: Ack -> bool
    @Override
    public void connect(Address request, StreamObserver<Ack> responseObserver) {

        //sincronizar aqui
        /************************************/


        /************************************/

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
