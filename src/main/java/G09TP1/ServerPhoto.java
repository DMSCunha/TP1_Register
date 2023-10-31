package G09TP1;


import java.util.ArrayList;

public class ServerPhoto {

    private static final ArrayList<ServerAddress> server = new ArrayList<>();

    private static int index = 0;

    public ServerPhoto(){

    }

    public boolean addNewServer(ServerAddress address){
        if(!server.contains(address)){
            server.add(address);
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
