//package academy.kovalevskyi.javadeepdive.week0.day2;
//
//import static academy.kovalevskyi.javadeepdive.week0.day2.CsvGenerator.*;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//
////import academy.kovalevskyi.javadeepdive.StreamProvider;
////import com.google.common.truth.Truth;
//import academy.kovalevskyi.javadeepdive.StreamProvider;
//import com.google.common.truth.Truth;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import academy.kovalevskyi.javadeepdive.JddProvider;
//import academy.kovalevskyi.javadeepdive.StreamProvider;
//import academy.kovalevskyi.testing.annotation.Container;
//import com.google.common.truth.Truth;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.Reader;
//import java.io.Writer;
//import java.util.Random;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//class CsvHelperTest {
//    @Test
//    public void writeCsvWithHeadersAndWithoutQuotes() throws IOException {
//        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//        OutputStreamWriter outputStream = StreamProvider.getOutputStreamTo(byteArray);
//        Csv csv = makeCsvNoReservedCharWithHeaders();
//        Assertions.assertDoesNotThrow(() -> {
//            CsvHelper.writeCsvTo(outputStream, makeCsvNoReservedCharWithHeaders(), ',');
//        });
//        outputStream.close();
//        String expected = csvToString(csv);
//        String actual = byteArray.toString();
//        Truth.assertWithMessage("We compare what has been written so that should be written.").that(actual).isEqualTo(expected);
//    }
//
//
//}
