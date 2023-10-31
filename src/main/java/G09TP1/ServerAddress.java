package G09TP1;

public class ServerAddress{
    private final int port;
    private final String ip;

    public ServerAddress(String ip, int port){
        this.port = port;
        this.ip = ip;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ServerAddress){
            return port == ((ServerAddress) obj).getPort() && ip.equals(((ServerAddress) obj).getIp());
        }
        return false;
    }


    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
