import java.net.DatagramPacket;
import java.net.InetAddress;

public class TCPSocketImpl extends TCPSocket {
    static final int senderPort = 9090;
    private EnhancedDatagramSocket socket;
    private int mySequenceNum = 100;
    private String destIp;
    private String destPort;

    public TCPSocketImpl(String ip, int port) throws Exception {
        // destination's ip and port
        super(ip, port);
        this.socket = new EnhancedDatagramSocket(senderPort);

        TCPHeaderGenerator synPacket = new TCPHeaderGenerator(ip, port);
        synPacket.setSynFlag();
        //TODO: set sequence number
        this.socket.send(synPacket.getPacket());

        System.out.println("Sent");
        // should first do a handshake!


        byte bufAck[] = new byte[2];
        DatagramPacket dpAck = new DatagramPacket(bufAck, 2);
        socket.receive(dpAck);

        System.out.println(dpAck.getData()[0]);
        
        TCPHeaderGenerator ackPacket = new TCPHeaderGenerator(ip, port);
        synPacket.setAckFlag();
        //TODO: set sequence number
        //TODO: set Ack number
        this.socket.send(ackPacket.getPacket());
        System.out.println("Sent");
        System.out.println("Est");



    }

    @Override
    public void send(String pathToFile) throws Exception {
        // throw new RuntimeException("Not implemented!");
    }

    @Override
    public void receive(String pathToFile) throws Exception {
        // throw new RuntimeException("Not implemented!");
    }

    @Override
    public void close() throws Exception {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public long getSSThreshold() {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public long getWindowSize() {
        throw new RuntimeException("Not implemented!");
    }
}
