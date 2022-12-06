package papa.noel;

import java.util.Arrays;

public class StartOfPacket {
    public static final int PACKET_START_LENGTH = 4;
    public static final int MESSAGE_START_LENGTH = 14;
    private String packet;

    public StartOfPacket(String packet) {
        this.packet = packet;
    }

    public int getPacketStart() {
        return getPositionAfterUniqueSequence(PACKET_START_LENGTH);
    }

    private int getPositionAfterUniqueSequence(int messageLength) {
        int length = this.packet.length();

        for (int i = 0; i < length - messageLength; i++) {
            int indexUnderCheck = i + messageLength;
            String sequence = this.packet.substring(i, indexUnderCheck);
            String[] split = sequence.split("");

            if (Arrays.stream(split).distinct().count() == messageLength) {
                return indexUnderCheck;
            }

        }

        return -1;
    }

    public int getMessageStart() {
        return getPositionAfterUniqueSequence(MESSAGE_START_LENGTH);
    }
}
