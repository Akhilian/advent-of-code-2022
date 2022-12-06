package papa.noel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class StartOfPacketTest {

    @Test
    void test_first_example() {
        // Given

        // When
        StartOfPacket message = new StartOfPacket("mjqjpqmgbljsphdztnvjfqwrcgsmlb");

        // Then
        assertThat(message.getPacketStart()).isEqualTo(7);
    }

    @Test
    void test_second_example() {
        // Given

        // When
        StartOfPacket message = new StartOfPacket("bvwbjplbgvbhsrlpgdmjqwftvncz");

        // Then
        assertThat(message.getPacketStart()).isEqualTo(5);
    }

    @Test
    void test_third_example() {
        // Given

        // When
        StartOfPacket message = new StartOfPacket("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg");

        // Then
        assertThat(message.getPacketStart()).isEqualTo(10);
    }

    @Test
    void test_fourth_example() {
        // Given

        // When
        StartOfPacket message = new StartOfPacket("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");

        // Then
        assertThat(message.getPacketStart()).isEqualTo(11);
    }

    @Test
    void test_example_for_message_start() {
        // Given

        // When
        StartOfPacket message = new StartOfPacket("mjqjpqmgbljsphdztnvjfqwrcgsmlb");

        // Then
        assertThat(message.getMessageStart()).isEqualTo(19);
    }

    @Test
    void test_example_for_second_message_start() {
        // Given

        // When
        StartOfPacket message = new StartOfPacket("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");

        // Then
        assertThat(message.getMessageStart()).isEqualTo(26);
    }

    @Test
    void day_6_first_challenge() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("src/main/resources/input_day6"));

        StartOfPacket message = new StartOfPacket(strings.get(0));

        assertThat(message.getPacketStart()).isEqualTo(1100);
    }

    @Test
    void day_6_second_challenge() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("src/main/resources/input_day6"));

        StartOfPacket message = new StartOfPacket(strings.get(0));

        assertThat(message.getMessageStart()).isEqualTo(1100);
    }
}
