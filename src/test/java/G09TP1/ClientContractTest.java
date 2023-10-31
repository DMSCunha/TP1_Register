package G09TP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientContractTest {
    @Test
    void test(){
        var serverphoto = new ServerPhoto();
        var cc = new ClientContract(serverphoto);
        System.out.println(serverphoto.toString()+ " - " + cc.server.toString());
        assertEquals(serverphoto, cc.server);
    }
}