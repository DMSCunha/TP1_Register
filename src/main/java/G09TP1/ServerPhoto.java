package G09TP1;


import java.util.ArrayList;

public class ServerPhoto {

    private final ArrayList<ServerAddress> server = new ArrayList<>();
    private final ArrayList<Integer> errormessages = new ArrayList<>();
    //private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int index = 0;
    private static final int REMOVESERVER = 3;

    public ServerPhoto(){
    }

    public void errorOnServer(ServerAddress address){
        if(server.contains(address)){

            int errors = errormessages.get(server.indexOf(address));

            //se é o terceiro erro -> remover
            if(++errors == REMOVESERVER){
                removeServer(address);
                return ;
            }

            // se nao, substituir o numero de erros reportados
            errormessages.set(server.indexOf(address),errors);
        }
    }

    //remove o servidor e o numero de erros comunicados
    private void removeServer(ServerAddress address){
        errormessages.remove(server.indexOf(address));
        server.remove(address);
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

    public void printAllServer(){
        int i=0;
        for (ServerAddress aux: server){
            System.out.println("["+ i +"] - IP: "+aux.getIp()+" Port: "+aux.getPort());
            System.out.println("["+ i +"] - Erros: "+ errormessages.get(i++));
        }
    }

}
