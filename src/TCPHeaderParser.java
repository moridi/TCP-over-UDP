
import java.nio.*; 

public class TCPHeaderParser {
    private byte buffer[];
    private int MINIMUM_HEADER_SIZE = 40;
    private int SEQ_NUM_SIZE = 2;
    private int ACK_NUM_SIZE = 2;

    // [0] | [1] | [2] | [3] | [4] | [5] | [6]:ACK | [7]:SYN
    public int flagIndex = 0;
    public int seqNumIndex = 1;
    public int ackNumIndex = 3;

    public TCPHeaderParser(byte buffer[]) {
        this.buffer = buffer;
    }

    public boolean isSynPacket() {
        if ((buffer[flagIndex] & 0x01) == 0x01)
            return true;
        return false;
    }

    public short getSequenceNumber() {
        byte[] arr = new byte[SEQ_NUM_SIZE];

        for (int i = 0; i < SEQ_NUM_SIZE; ++i)
            arr[i] = buffer[i + seqNumIndex];

        ByteBuffer wrapped = ByteBuffer.wrap(arr); // big-endian by default
        short num = wrapped.getShort(); // 1

        return num;
    }
} 