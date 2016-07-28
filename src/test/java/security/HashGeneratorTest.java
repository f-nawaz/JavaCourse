package security;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * http://www.fileformat.info/tool/hash.htm
 */
public class HashGeneratorTest {
    @Test
    public void generateHashShouldReturnCorrectValue(){
        //arrange
        HashGenerator hashGenerator = new HashGenerator();
        String text = "password";
        String expectedResult = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        //act
        String result = hashGenerator.hash(text);
        //assert
        assertEquals(expectedResult,result);
    }

}
