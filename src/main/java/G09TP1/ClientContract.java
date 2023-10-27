package G09TP1;


import io.grpc.stub.StreamObserver;
import registerclientstub.Address;
import registerclientstub.RegisterClientGrpc;
import registerclientstub.Void;

public class ClientContract extends RegisterClientGrpc.RegisterClientImplBase {

    public ClientContract(){

    }

    //case 1
    //Cliente pede o IP e porto de um servidor de serviço
    //Register envia um dos servidores disponiveis
    //Recebe:
    //Devolve: Address -> IP e porto
    @Override
    public void getIP(Void request, StreamObserver<Address> responseObserver) {
        super.getIP(request, responseObserver);
    }

    //case 1
    //Cliente avisa o Resgister que nao se consegue conectar à aquele IP e porto
    //Resgister devolve um novo IP e porto
    //Recebe: Address -> IP e porto
    //Devolve: Address -> IP e porto
    @Override
    public void errorGetAnotherIP(Address request, StreamObserver<Address> responseObserver) {
        super.errorGetAnotherIP(request, responseObserver);
    }
}
