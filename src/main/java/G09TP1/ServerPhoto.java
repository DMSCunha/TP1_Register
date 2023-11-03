package G09TP1;


import java.util.ArrayList;

public class ServerPhoto {

    private static final ArrayList<ServerAddress> server = new ArrayList<>();
    private static final ArrayList<Integer> errormessages = new ArrayList<>();

    private static int index = 0;
    private static final int REMOVESERVER = 3;

    public ServerPhoto(){

    }

    public boolean errorOnServer(ServerAddress address){
        if(server.contains(address)){

            int errors = errormessages.get(server.indexOf(address));

            //se é o terceiro erro -> remover
            if(++errors == REMOVESERVER){
                return removeServer(address);
            }

            // se nao, substituir o numero de erros reportados
            errormessages.set(server.indexOf(address),errors);
            return true;
        }
        return false;
    }

    //remove o servidor e o numero de erros comunicados
    private boolean removeServer(ServerAddress address){
        if(server.contains(address)){
            errormessages.remove(server.indexOf(address));
            server.remove(address);
            return true;
        }
        return false;
    }

    //adiciona um novo servidor
    public boolean addNewServer(ServerAddress address){
        if(!server.contains(address)){
            server.add(address);
            errormessages.add(0);
            return true;
        }
        return false;
    }

    //devolve o próximo servidor a enviar
    public ServerAddress getNextServer(){
        if(server.isEmpty())
            return null;
        if(server.size()==index)
            index = 0;
        return server.get(index++);
    }

}
