package G09TP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerPhotoTest {

    @Test
    void addServerTest(){
        //novos servers
        var server = new ServerPhoto();

        //está vazio
        assertNull(server.getNextServer());

        //adicionou um novo
        server.addNewServer(new ServerAddress("192.168.1.1",5000));

        //ver se percebe que este servidor já foi adicionado
        assertFalse(server.addNewServer(new ServerAddress("192.168.1.1", 5000)));
    }

    @Test
    void getNewServerTest(){
        var server = new ServerPhoto();
        server.addNewServer(new ServerAddress("1",1));
        server.addNewServer(new ServerAddress("2",1));
        server.addNewServer(new ServerAddress("3",1));
        server.addNewServer(new ServerAddress("4",1));
        server.addNewServer(new ServerAddress("5",1));

        //se devolve os servidores por ordem
        assertEquals(new ServerAddress("1",1), server.getNextServer());
        assertEquals(new ServerAddress("2",1), server.getNextServer());
            //aqui um diferente só para ser do contra
            assertNotEquals(new ServerAddress("2",1), server.getNextServer());
        assertEquals(new ServerAddress("4",1), server.getNextServer());
        assertEquals(new ServerAddress("5",1), server.getNextServer());

        //se recomeça do inicio
        assertEquals(new ServerAddress("1",1), server.getNextServer());
    }

}